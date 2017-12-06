package com.freddyheppell.dh;

import com.freddyheppell.dh.Interface.ProgressDialog;
import com.freddyheppell.dh.Interface.ResultsDialog;
import com.freddyheppell.dh.Message.ExchangeValueMessage;
import com.freddyheppell.dh.Message.InvalidPacketException;
import com.freddyheppell.dh.Message.Parser;
import com.freddyheppell.dh.Utility.GenerateKey;
import com.freddyheppell.dh.Utility.Settings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;

public class Server {

    public static void main(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            InetAddress ipAddress = InetAddress.getLocalHost();
            String progressString = String.format("Awaiting connection on %s port %s", ipAddress.getHostAddress(), Integer.toString(port));
            ProgressDialog awaitingConnection = new ProgressDialog(progressString);
            Socket server = serverSocket.accept();

            awaitingConnection.hide();

            ProgressDialog working = new ProgressDialog("S Working...");

            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());

            System.out.println("Awaiting connection notice");
            String data = in.readUTF();
            Map<String, String> response = Parser.parse(data);

            if (!Objects.equals(response.get("event"), "connecting")) {
                // Not a connection message
                throw new InvalidPacketException("connecting", data);
            }

            // Calculate the sharing value
            BigInteger a = GenerateKey.generate();
            BigInteger A = Settings.g.modPow(a, Settings.p);

            System.out.println(String.format("a=%d\nA=%d", a, A));

            // Send the sharing value
            ExchangeValueMessage exchangeValueMessage = new ExchangeValueMessage(A);
            String exchangeValueMessageJson = exchangeValueMessage.toJSON();

            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(exchangeValueMessageJson);

            // Wait for the client to send B
            String exchangeValueC = in.readUTF();
            Map<String, String> exchangeValueData = Parser.parse(exchangeValueC);
            BigInteger B = new BigInteger(exchangeValueData.get("exchange_value"));

            System.out.println(String.format("B=%d", B));

            // Calculate k
            BigInteger k = B.modPow(a, Settings.p);

            System.out.println(String.format("k=%d", k));

            serverSocket.close();
            working.hide();
            ResultsDialog.main(k);
        } catch (IOException | InvalidPacketException e) {
            e.printStackTrace();
        }
        System.out.println("Passed trycatch");
    }
}
