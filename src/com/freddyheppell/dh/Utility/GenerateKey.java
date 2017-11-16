package com.freddyheppell.dh.Utility;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GenerateKey {
    public static BigInteger generate() {
        SecureRandom random = new SecureRandom();

        int n = Settings.key_bound;

        SecureRandom r = new SecureRandom();
        byte[] b = new byte[n];
        r.nextBytes(b);

        return new BigInteger(b).abs();
    }
}
