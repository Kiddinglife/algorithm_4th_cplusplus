//Chap02.question.16.GCD.java

public class Solution {
    public static void main(String... args) {
        long A = 10000000000L;
        long B = 10000000500L;
        for (long i = A; i <= B; i++) {
            for (long j = A; j <= B; j++) {
                long g1 = gcd1(i, j);
                long g2 = gcd2(i, j);
                if (g1 != g2)
                    System.out.println(i + "," + j + ":" + g1 + "," + g2);
            }
        }
    }

    public static long gcd1(long m, long n) {
        if (m <= 0 || n <= 0)
            throw new IllegalArgumentException("both args should be positive.");
        while (n > 0) {
            long t = n;
            n = m % n;
            m = t;
        }
        return m;
    }

    public static long gcd2(long m, long n) {
        if (m <= 0 || n <= 0)
            throw new IllegalArgumentException("both args should be positive.");

        if (m < n) return gcd2(n, m);

        if (m == n) return m;

        if (m % 2 == 0 && n % 2 == 0) {
            return 2 * gcd2(m / 2, n / 2);
        } else if (m % 2 == 0 && n % 2 != 0) {
            return gcd2(m / 2, n);
        } else if (m % 2 != 0 && n % 2 == 0) {
            return gcd2(m, n / 2);
        } else
            return gcd2((m + n) / 2, (m - n) / 2);
    }
}
