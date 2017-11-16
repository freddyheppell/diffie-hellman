package com.freddyheppell.dh;

import com.freddyheppell.dh.Interface.ClientServerPicker;
import com.freddyheppell.dh.Interface.ConnectionDialog;
import com.freddyheppell.dh.Utility.Settings;

public class Main {
    public static void main(String[] args) {

        Settings.loadP();
        System.out.println(Settings.p);

        int choice = ClientServerPicker.launch();

        if (choice == 1) {
            Server.main(2020);
            System.exit(0);
        } else if (choice == 0) {
            ConnectionDialog.launch();

        } else {
            System.out.println("No choice!");
        }
    }
}
