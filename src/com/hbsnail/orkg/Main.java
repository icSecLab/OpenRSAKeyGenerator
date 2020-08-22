package com.hbsnail.orkg;

import java.math.BigInteger;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BigInteger p1 = BigInteger.probablePrime(2048,new Random());
        BigInteger p2 = BigInteger.probablePrime(2048,new Random());
        OpenRSAKeyGenerator orkg = new OpenRSAKeyGenerator(p1,p2,BigInteger.valueOf(65537));
        String keypair[] = orkg.getKey();
        System.out.println("OpenRSAPublicKey: " + keypair[0]);
        System.out.println("OpenRSAPrivateKey: " + keypair[1]);
    }
}
