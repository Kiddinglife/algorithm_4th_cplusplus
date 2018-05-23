//Chap01.question.05.DigitCount.java

public class Solution {
    public static void main(String... args) {
        System.out.print(new Solution().digitCount(-2));
    }

    public int digitCount(int n) {
        if (n == 0) return 0;
        if (n % 2 != 0)
            return digitCount(n >>> 1) + 1;
        else
            return digitCount(n >>> 1);
    }
}

//well there is a much better way. see jdk Long.java
