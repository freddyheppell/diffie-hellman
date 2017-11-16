package com.freddyheppell.dh.Interface;

import com.freddyheppell.dh.Client;
import com.freddyheppell.dh.Utility.Settings;

import javax.swing.*;

import java.awt.*;

import static com.freddyheppell.dh.Utility.Validation.ErrorDialog;
import static com.freddyheppell.dh.Utility.Validation.ValidateIpAddress;
import static com.freddyheppell.dh.Utility.Validation.ValidatePort;

public class ConnectionDialog {
    private JPanel content;
    private JTextField ipAddress;
    private JTextField port;
    private JButton connectButton;

    private static void TestConnection(String ipAddress, String port) {
        System.out.println(ipAddress);
        System.out.println(port);
    }

    private ConnectionDialog() {
        port.setText("2020");
    }

    public static void launch() {
        // Open the ConnectDialog form
        JFrame frame = new JFrame(Settings.title);

        ConnectionDialog connectionDialog = new ConnectionDialog();
        // Open the window
        frame.setContentPane(connectionDialog.content);
        // Close the window when the close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        connectionDialog.connectButton.addActionListener(e -> {
            String connectionPort = connectionDialog.port.getText();
            String connectionIpAddress = connectionDialog.ipAddress.getText();

            if (!ValidateIpAddress(connectionIpAddress)) {
                ErrorDialog("IP Address");
                return;
            }

            if(!ValidatePort(connectionPort)) {
                ErrorDialog("port");
                return;
            }

            TestConnection(connectionIpAddress, connectionPort);
            frame.dispose();
            Client.main(connectionIpAddress, connectionPort);

        });

        frame.pack();
        // Disable resizing
        frame.setResizable(false);
        // Center window
        frame.setLocationRelativeTo(null);
        // Show the window
        frame.setVisible(true);
    }
}
