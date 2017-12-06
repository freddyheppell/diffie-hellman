package com.freddyheppell.dh;

import com.freddyheppell.dh.Interface.ProgressDialog;
import com.freddyheppell.dh.Interface.ResultsDialog;
import com.freddyheppell.dh.Message.ConnectedMessage;
import com.freddyheppell.dh.Message.ExchangeValueMessage;
import com.freddyheppell.dh.Message.Parser;
import com.freddyheppell.dh.Utility.GenerateKey;
import com.freddyheppell.dh.Utility.Settings;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Map;

public class Client {
    public static void main(String ipAddress, String port) {
        try {
            System.out.println(String.format("Attempting connection to %s:%s", ipAddress, port));

            int serverPort = Integer.parseInt(port);
            Socket client = new Socket(ipAddress, serverPort);

            System.out.println("Client is connecting");

            OutputStream outputStream = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);

            ProgressDialog working = new ProgressDialog("CWorking...");


            // Send connection message
            ConnectedMessage connectedMessage = new ConnectedMessage();
            out.writeUTF(connectedMessage.toJSON());
            System.out.println("Sent connection notice. Awaiting value receipt.");

            // Wait for server to send A
            InputStream inFromServer = client.getInputStream();
            String receivedValueJSON = new DataInputStream(inFromServer).readUTF();

            System.out.println(receivedValueJSON);

            // Parse A
            Map<String, String> receivedValue = Parser.parse(receivedValueJSON);
            BigInteger A = new BigInteger(receivedValue.get("exchange_value"));

            System.out.println(String.format("A=%d", A));

            // Calculate B
            BigInteger b = GenerateKey.generate();
            BigInteger B = Settings.g.modPow(b, Settings.p);

            System.out.println(String.format("b=%d\nB=%d", b, B));

            // Send B to server
            ExchangeValueMessage exchangeValueMessage = new ExchangeValueMessage(B);
            String exchangeValueMessageJson = exchangeValueMessage.toJSON();

            out.writeUTF(exchangeValueMessageJson);

            // Calculate k
            BigInteger k = A.modPow(b, Settings.p);

            System.out.println(String.format("k=%d", k));

            working.hide();
            ResultsDialog.main(k);

        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
