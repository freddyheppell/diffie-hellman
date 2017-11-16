package com.freddyheppell.dh.Utility;

import javax.swing.*;
import java.util.regex.Pattern;

public class Validation {
    /**
     * Regexp for an IP address
     */
    private static final Pattern IpPattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    private static final Pattern PortPattern = Pattern.compile("^-?\\d+$");

    /**
     * Check if an IP Address matches the correct format
     *
     * @param ipAddress The IPV4 address to be checked
     * @return If the IP Address is valid
     */
    public static boolean ValidateIpAddress(String ipAddress) {
        return IpPattern.matcher(ipAddress).matches();
    }

    /**
     * Check if a port is valid
     *
     * @param port The port to be checked
     * @return If the port is valid
     */
    public static boolean ValidatePort(String port) {
        return PortPattern.matcher(port).matches();
    }

    public static void ErrorDialog(String field) {
        JOptionPane.showMessageDialog(null,
                String.format("That %s looks wrong!", field),
                Settings.title, JOptionPane.ERROR_MESSAGE);

    }
}
