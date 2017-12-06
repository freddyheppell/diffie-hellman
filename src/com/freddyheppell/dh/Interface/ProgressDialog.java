package com.freddyheppell.dh.Interface;

import com.freddyheppell.dh.Utility.Settings;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog {

    private JDialog dialog;

    public ProgressDialog(String message) {

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        JTextArea label = new JTextArea(message);
        label.setEditable(false);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(label, BorderLayout.PAGE_START);
        panel.add(progressBar, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        dialog = new JDialog();
        dialog.getContentPane().add(panel);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setSize(500, dialog.getHeight());
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(false);
        dialog.setVisible(true);
        label.setBackground(panel.getBackground());

    }

    public void hide() {
        dialog.dispose();
    }
}
