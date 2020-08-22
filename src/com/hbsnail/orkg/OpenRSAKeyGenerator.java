/*
MIT License
Copyright (c) 2020 HBSnail
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
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
