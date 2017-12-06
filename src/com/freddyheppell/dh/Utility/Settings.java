package com.freddyheppell.dh.Utility;

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Settings {
    public static String title = "Diffie-Hellman";
    public static BigInteger p;
    public static BigInteger g = BigInteger.valueOf(5);
    public static int key_bound = 10000;

    public static void loadP() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("p.txt");

        try {
            p = new BigInteger(org.apache.commons.io.IOUtils.toByteArray(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
