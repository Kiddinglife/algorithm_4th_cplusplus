//Chap01.text.02.CrossWord.java

import java.util.*;

public class Solution {
    private static final int MULTIPLIER = 10000;

    public static void main(String[] args) {
        String[] words = new String[]{
                "this", "wats", "o", "thh",
                "z", "twah", "taht", "twahi",
                "shgof", "twof", "", "gaah",
                "twofgaah", "taagdh", "tahtgsti",
                "ffffffffff", "tawoadg", "htsisgt",
                "tawoadhtsih", "tt", "aaofghdgssitht"};
        char[][] matrix = new char[][]{
                {'t', 'h', 'i', 's'},
                {'w', 'a', 't', 's'},
                {'o', 'a', 'h', 'g'},
                {'f', 'g', 'd', 't'}
        };
        /*
        int m = 10000, n = 10000;
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (char) ('a' + new Random().nextInt(26));
            }
        }
        */
        Solution solution = new Solution();
        for (String s : words) {
            System.out.println(s + " : " + solution.containsWord(matrix, s));
        }
    }

    public boolean containsWord(char[][] matrix, String word) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Set<Integer> used = new HashSet<>();
                if (dfs(matrix, i, j, word, used)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] matrix, int i, int j, String word, Set<Integer> used) {
        if (word.isEmpty()) return true;
        if (matrix[i][j] != word.charAt(0)) return false;
        for (int ii = i - 1; ii <= i + 1; ii++) {
            for (int jj = j - 1; jj <= j + 1; jj++) {
                int compoundIndex = ii * MULTIPLIER + jj;
                if (ii >= 0 && jj >= 0 && ii < matrix.length && jj < matrix[0].length
                        && !used.contains(compoundIndex)
                        && (ii != i || jj != j)) {
                    used.add(compoundIndex);
                    if (dfs(matrix, ii, jj, word.substring(1), used))
                        return true;
                    else {
                        used.remove(Integer.valueOf(compoundIndex));
                    }
                }
            }
        }
        return false;
    }
}
