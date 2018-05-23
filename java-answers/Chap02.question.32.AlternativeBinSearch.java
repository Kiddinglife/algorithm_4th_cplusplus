//Chap02.question.32.AlternativeBinSearch.java

import java.util.Arrays;

public class Solution {
    public static void main(String... args) {
        Double[] arr = new Double[]{0.5, 1.0, 2.3, 1.2, 4.0, 0.5};
        Arrays.sort(arr);
        System.out.println(binarySearch3Comparisons(arr, 1.3));
        System.out.println(binarySearch2Comparisons(arr, 1.3));
    }

    public static <T extends Comparable<? super T>> int binarySearch3Comparisons(T[] arr, T target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            } else if (arr[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ~low;
    }

    public static <T extends Comparable<? super T>> int binarySearch2Comparisons(T[] arr, T target) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (arr[low] == target)
            return low;
        else
            return ~low;
    }
}
