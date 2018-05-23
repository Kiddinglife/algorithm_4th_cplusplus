//Chap02.question.19.MaxSubarraySumWithIndices.java

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String... args) {
        int[] arr = new int[]{-5, 9, 0, -2, 4, -2, 8, 2, -1, 3};
        System.out.print(new Solution().maxSubarraySum(arr));
    }

    public List<Integer> maxSubarraySum(int[] arr) {
        int maxSum = 0, maxStartIndex = 0, maxEndIndex = 0;
        int curSum = 0, startIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (curSum > maxSum) {
                maxSum = curSum;
                maxStartIndex = startIndex;
                maxEndIndex = i + 1;
            }
            if (curSum < 0) {
                curSum = 0;
                startIndex = i + 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(maxSum);//sum
        result.add(maxStartIndex);//start index
        result.add(maxEndIndex);//end index

        return result;
    }
}
