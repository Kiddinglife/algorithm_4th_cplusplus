//Chap02.question.17.MaxSubArray.java

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String... args) {
        int[] arr = new int[]{-3, 7, -2, 1, 4, -4, 9};
        for (Method m : Solution.class.getDeclaredMethods())
            try {
                if (m.getName().startsWith("min") || m.getName().startsWith("max"))
                    System.out.println(m.getName() + ":" + m.invoke(null, arr));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
    }

    //O(n)
    public static int maxSubArraySum(int[] arr) {
        int maxSum = 0;
        int curSum = 0;
        for (int i : arr) {
            curSum += i;
            maxSum = Math.max(maxSum, curSum);
            curSum = Math.max(curSum, 0);
        }
        return maxSum;
    }

    //O(n)
    public static int minSubArraySum(int[] arr) {
        int minSum = 0;
        int curSum = 0;
        for (int i : arr) {
            curSum += i;
            minSum = Math.min(minSum, curSum);
            curSum = Math.min(curSum, 0);
        }
        return minSum;
    }

    //brute force O(n^2)
    public static int minSubArrayPositiveSumBruteForce(int[] arr) {
        int minPosSum = 0x7fffffff;
        for (int i = 0; i < arr.length; i++) {
            int curSum = 0;
            for (int j = i; j < arr.length; j++) {
                curSum += arr[j];
                if (curSum > 0 && curSum < minPosSum)
                    minPosSum = curSum;
            }
        }
        return minPosSum;
    }

    //divide and conquer + merge sort + binary search O(n(log(n))^2)
    public static int minSubArrayPositiveSumDivideAndConquer(int[] arr) {
        return findMinPosSum(arr, 0, arr.length);
    }

    private static int findMinPosSum(int[] arr, int startIndex, int endIndex) {
        if (startIndex == endIndex) return 0x7fffffff;
        if (endIndex - startIndex == 1) {
            if (arr[startIndex] <= 0) return 0x7fffffff;
            else return arr[startIndex];
        }
        if (endIndex - startIndex == 2) {
            int r = 0x7fffffff;
            if (arr[startIndex] > 0 && r > arr[startIndex])
                r = arr[startIndex];
            if (arr[startIndex + 1] > 0 && r > arr[startIndex + 1])
                r = arr[startIndex + 1];
            if (arr[startIndex] + arr[startIndex + 1] > 0 && r > arr[startIndex] + arr[startIndex + 1])
                r = arr[startIndex] + arr[startIndex + 1];
            return r;
        }

        int midIndex = (startIndex + endIndex) / 2;
        int[] leftSum = new int[midIndex - startIndex + 1];
        leftSum[leftSum.length - 1] = arr[midIndex];
        for (int i = leftSum.length - 2; i >= 0; i--) {
            leftSum[i] = arr[midIndex + i - leftSum.length + 1] + leftSum[i + 1];
        }
        Arrays.sort(leftSum);
        int[] rightSum = new int[endIndex - midIndex];
        rightSum[0] = arr[midIndex + 1];
        for (int i = midIndex + 2; i < endIndex; i++) {
            rightSum[i - midIndex - 1] = arr[i] + rightSum[i - midIndex - 2];
        }
        Arrays.sort(rightSum);
        int minPosSum = findMinPosSumHelper(leftSum, rightSum);
        return Math.min(minPosSum, Math.min(findMinPosSum(arr, startIndex, midIndex + 1),
                findMinPosSum(arr, midIndex + 1, endIndex)));
    }

    private static int findMinPosSumHelper(int[] a, int[] b) {
        int minPosSum = 0x7fffffff;
        for (int aa : a) {
            int index = Arrays.binarySearch(b, -aa);
            if (index < 0) index = ~index;
            if (index < b.length && b[index] + aa > 0)
                minPosSum = Math.min(minPosSum, b[index] + aa);
            if (index - 1 >= 0 && b[index - 1] + aa > 0)
                minPosSum = Math.min(minPosSum, b[index - 1] + aa);
            if (index + 1 < b.length && b[index + 1] + aa > 0)
                minPosSum = Math.min(minPosSum, b[index + 1] + aa);
        }
        return minPosSum;
    }

    // O(n)
    public static int maxSubArrayProduct(int[] arr) {
        if (arr.length == 0) return 0;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (i - start != 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = start; j < i; j++) list.add(arr[j]);
                    lists.add(list);
                }
                start = i;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = start; j < arr.length; j++) list.add(arr[j]);
        lists.add(list);
        int maxProduct = Integer.MIN_VALUE;
        for (ArrayList<Integer> l : lists) {
            maxProduct = Math.max(maxProduct, getMaxProd(l));
        }
        return maxProduct;
    }

    private static int getMaxProd(ArrayList<Integer> list) {
        if (list.isEmpty()) return Integer.MIN_VALUE;
        if (list.size() == 1) return list.get(0);
        int p = 1;
        for (int a : list) p *= a;
        if (p > 0) return p;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 0) {
                p = Math.max(p, getMaxProd(new ArrayList<Integer>(list.subList(0, i))));
                p = Math.max(p, getMaxProd(new ArrayList<Integer>(list.subList(i + 1, list.size()))));
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) < 0) {
                p = Math.max(p, getMaxProd(new ArrayList<Integer>(list.subList(0, i))));
                p = Math.max(p, getMaxProd(new ArrayList<Integer>(list.subList(i + 1, list.size()))));
            }
        }
        return p;
    }
}
