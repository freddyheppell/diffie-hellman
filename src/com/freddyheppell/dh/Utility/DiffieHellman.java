package com.freddyheppell.dh.Utility;

import java.math.BigInteger;

import static java.lang.Math.pow;

public class DiffieHellman {

    public static BigInteger power(BigInteger base, BigInteger power, BigInteger mod) {
        return base.modPow(power, mod);
    }
}
