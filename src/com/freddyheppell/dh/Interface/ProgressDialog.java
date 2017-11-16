package com.freddyheppell.dh.Interface;

import com.freddyheppell.dh.Utility.Settings;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog {
    private JFrame frame = new JFrame(Settings.title);

    public ProgressDialog(String message) {
        Container cp = frame.getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel(message);
        JProgressBar progressBar = new JProgressBar();

        progressBar.setIndeterminate(true);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        cp.add(new JLabel(message));
        cp.add(progressBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 70);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
