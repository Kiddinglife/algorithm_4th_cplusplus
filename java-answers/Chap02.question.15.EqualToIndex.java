//Chap02.question.15.EqualToIndex.java


import java.util.Arrays;

public class Solution {

    public static void main(String... args) {
        int[] arr = new int[]{-2, -1, 1, 2, 4, 7};
        System.out.println(new Solution().check(arr, 0, arr.length));
    }

    /**
     * check if there exists i so that arr[i+1]==i
     *
     * @param arr        int array, should be sorted and contain no duplicates
     * @param startIndex start index to search from
     * @param endIndex   end index to search to (exclusive)
     * @return boolean value whether exists or not
     * @throws java.lang.IllegalArgumentException if there are duplicates
     */
    public boolean check(int[] arr, int startIndex, int endIndex)
            throws IllegalArgumentException {
        if (startIndex >= endIndex) return false;

        if (arr[startIndex] <= 0) {
            int zeroIndex = Arrays.binarySearch(arr, startIndex, endIndex, 0);
            if (zeroIndex >= 0)
                return check(arr, zeroIndex + 1, endIndex);
            else
                return check(arr, ~zeroIndex, endIndex);
        }

        //assert arr[endIndex - 1] >= endIndex - startIndex : "wrong input array";
        if (!(arr[endIndex - 1] >= endIndex - startIndex)) {
            throw new IllegalArgumentException("wrong input array");
        }

        while (startIndex < endIndex) {
            int midIndex = startIndex + ((endIndex - startIndex) >>> 1); // avoid overflow
            if (arr[midIndex] == midIndex + 1) return true;
            else if (arr[midIndex] < midIndex + 1)
                startIndex = midIndex + 1;
            else
                endIndex = midIndex;
        }

        return false;
    }
}
