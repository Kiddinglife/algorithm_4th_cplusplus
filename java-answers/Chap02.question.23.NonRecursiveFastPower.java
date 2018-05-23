//Chap02.question.23.NonRecursiveFastPower.java

import java.math.BigInteger;
import java.util.ArrayList;

public class Solution {
    public static void main(String... args) {
        System.out.println(exponentiationRecursive(BigInteger.valueOf(17), BigInteger.valueOf(36945)));
        System.out.println(exponentiationNonRecursive(BigInteger.valueOf(17), BigInteger.valueOf(36945)));
    }

    public static BigInteger exponentiationRecursive(BigInteger x, BigInteger n) {
        assert n.compareTo(BigInteger.ZERO) >= 0;

        if (n.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;
        if (n.compareTo(BigInteger.ONE) == 0) return x;

        BigInteger n1 = n.divide(BigInteger.valueOf(2));
        BigInteger n2 = n.subtract(n1);

        return exponentiationRecursive(x, n1).multiply(exponentiationRecursive(x, n2));
    }

    public static BigInteger exponentiationNonRecursive(BigInteger x, BigInteger n) {
        assert n.compareTo(BigInteger.ZERO) >= 0;

        if (n.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;
        if (n.compareTo(BigInteger.ONE) == 0) return x;

        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        list.add(BigInteger.ONE);
        list.add(x);
        BigInteger p = BigInteger.ONE;
        for (int i = 2; p.compareTo(n) <= 0; i++) {
            BigInteger prev = list.get(i - 1);
            list.add(prev.multiply(prev));
            p = p.multiply(BigInteger.valueOf(2));
        }

        BigInteger result = BigInteger.ONE;

        int i = 1;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            if (n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) != 0)
                result = result.multiply(list.get(i));
            n = n.divide(BigInteger.valueOf(2));
            i++;
        }

        return result;
    }
}
