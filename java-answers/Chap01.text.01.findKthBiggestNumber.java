//Chap01.text.01.findKthBiggest.java

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[10000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000000);
        }
        //System.out.println(Arrays.toString(arr));
        long prev = System.currentTimeMillis();
        System.out.print("sort O(nlog(n)) " + new Solution().findKthBiggest(arr, arr.length / 2) + " : ");
        long post = System.currentTimeMillis();
        System.out.println(post - prev);
        prev = System.currentTimeMillis();
        System.out.print("min heap O(nlog(k)) " + new Solution().findKthBiggest2(arr, arr.length / 2) + " : ");
        post = System.currentTimeMillis();
        System.out.println(post - prev);
        prev = System.currentTimeMillis();
        System.out.print("quickSelect O(n) " + new Solution().findKthBiggest3(arr, arr.length / 2) + " : ");
        post = System.currentTimeMillis();
        System.out.println(post - prev);
//        prev = System.currentTimeMillis();
//        System.out.print("bubble up k biggest elements O(kn) " + new Solution().findKthBiggest1(arr, arr.length / 2) + " : ");
//        post = System.currentTimeMillis();
//        System.out.println(post - prev);
    }

    // O(nlog(n))
    private int findKthBiggest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    // bubble up k biggest elements O(kn)
    private int findKthBiggest1(int arr[], int k) {
        for (int i = arr.length - 1; i >= arr.length - k; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        return arr[arr.length - k];
    }

    // min heap O(nlog(k))
    private int findKthBiggest2(int arr[], int k) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>(k);
        for (int i : arr) {
            if (minheap.size() < k) {
                minheap.offer(i);
            } else if (i > minheap.peek()) {
                minheap.poll();
                minheap.offer(i);
            }
        }
        return minheap.peek();
    }

    // quickSelect
    // T(N) = T(N/2)+O(N)  => O(n)
    private int findKthBiggest3(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, arr.length - k);
    }

    private int quickSelect(int[] arr, int left, int right, int n) {
        assert n >= left && n <= right;
        while (left < right) {
            int pivotIndex = left + (int) Math.floor((right - left) * Math.random());
            pivotIndex = partition1(arr, left, right, pivotIndex);
            if (pivotIndex == n)
                return arr[n];
            else if (pivotIndex > n) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
        return arr[left];
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        assert pivotIndex >= left && pivotIndex <= right;
        int pivotValue = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = pivotValue;
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) { // on avg swap n/2 times
                int t = arr[i];
                arr[i] = arr[storeIndex];
                arr[storeIndex] = t;
                storeIndex++;
            }
        }
        arr[right] = arr[storeIndex];
        arr[storeIndex] = pivotValue;
        return storeIndex;
    }

    //yet another more efficient implementation of partition
    private int partition1(int[] arr, int left, int right, int pivotIndex) {
        assert pivotIndex >= left && pivotIndex <= right;
        int pivotValue = arr[pivotIndex];
        //move pivot to end
        arr[pivotIndex] = arr[right];
        arr[right] = pivotValue;
        int i = left, j = right - 1;
        while (i < j) {
            while (i < j && arr[i] <= pivotValue) i++;
            while (i < j && arr[j] >= pivotValue) j--;
            if(i < j) {
                int t = arr[i]; // on avg swap n/4 times
                arr[i] = arr[j];
                arr[j] = t;
                i++;
                j--;
            }
        }
        arr[right] = arr[i];
        arr[i] = pivotValue;
        return i;
    }
}
