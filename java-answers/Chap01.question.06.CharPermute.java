//Chap01.question.06.CharPermute.java

public class Solution {
    public static void main(String... args) {
        new Solution().permute("abcd");
    }

    public void permute(String str) {
        permute(str.toCharArray(), 0, str.length() - 1);
    }

    private void permute(char[] str, int low, int high) {
        assert 0 <= low && low <= high && high < str.length;

        if (low > high) {
            System.out.print(String.valueOf(str) + ", ");
            return;
        }

        for (int i = low; i <= high; i++) {
            char c = str[i];
            str[i] = str[low];
            str[low] = c;
            permute(str, low + 1, high);
            str[low] = str[i];
            str[i] = c;
        }
    }
}
