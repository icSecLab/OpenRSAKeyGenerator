package com.hbsnail.orkg;

import java.math.BigInteger;

public class OpenRSAKeyGenerator {
    public BigInteger pA,pB,pM;
    private BigInteger N,faiN;
    public OpenRSAKeyGenerator(BigInteger priemA,BigInteger priemB,BigInteger priemMod){
        pA = priemA;
        pB = priemB;
        pM = priemMod;
    }

    private BigInteger gcd(BigInteger A,BigInteger B){
        return (B.longValue() == 0 ? A : gcd(B,A.mod(B)));
    }
    private BigInteger x,y;
    private void exgcd(BigInteger A, BigInteger B){
        if(B.compareTo(BigInteger.ZERO) == 0){
            y = BigInteger.ZERO;
            x = BigInteger.ONE;
        }else{
            exgcd(B, A.mod(B));
            BigInteger k = x;
            x = y;
            y = k.subtract(((A.divide(B)).multiply(y)));
        }
    }
    public String[] getKey(){
        String[] keys = null;
        N = pA.multiply(pB);
        faiN = pA.subtract(BigInteger.ONE).multiply(pB.subtract(BigInteger.ONE));
        if(pM.compareTo(faiN) == -1 && gcd(pM,faiN).longValue() == 1){
            exgcd(pM,faiN );
            BigInteger d = x.add(faiN).mod(faiN);
            keys =  new String[2];
            keys[0] = N.toString() + "," + pM.toString();
            keys[1] = N.toString() + "," + d.toString();
        }else{
            NumberFormatException ex = new NumberFormatException("模数必须与φ(N)互质并小于φ(N)");
            throw ex;
        }
        return keys;
    }
}
