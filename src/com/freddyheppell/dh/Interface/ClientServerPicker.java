package com.freddyheppell.dh.Interface;

import com.freddyheppell.dh.Utility.Settings;

import javax.swing.*;

public class ClientServerPicker {
    public static int launch() {
        Object[] options = {"Client", "Server"};
        int n = JOptionPane.showOptionDialog(null,
                "Should this machine act as client or server?",
                Settings.title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                null);

        return n;
    }
}
