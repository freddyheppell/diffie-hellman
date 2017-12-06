package com.freddyheppell.dh.Interface;

import com.freddyheppell.dh.Utility.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.math.BigInteger;

public class ResultsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea keyField;
    private JButton copy;

    public ResultsDialog(String key) {
        keyField.setText(key);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(key);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

//        // call onCancel() when cross is clicked
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                close();
//            }
//        });
//
//        // call onCancel() on ESCAPE
//        contentPane.registerKeyboardAction(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                close();
//            }
//        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


//    private void close() {
//        // add your code here if necessary
//        dispose();
//    }

    public static void main(BigInteger key) {
        ResultsDialog dialog = new ResultsDialog(key.toString());
        dialog.pack();
        dialog.setSize(new Dimension(600, 300));
        dialog.setLocationRelativeTo(null);
        dialog.setTitle(Settings.title);
        dialog.setVisible(true);
        System.exit(0);
    }
}
