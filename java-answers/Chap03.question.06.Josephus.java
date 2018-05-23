//Chap03.question.06.Josephus.java

class SinglyLinkedNode {
    int data;
    SinglyLinkedNode next;

    SinglyLinkedNode(int i) {
        data = i;
        next = null;
    }
}

public class Solution {
    public static void main(String[] args) {
        for (int[] arr : new int[][]{{0, 5}, {1, 5}}) {
            System.out.println(josephus(arr[0], arr[1]));
        }
    }

    public static int josephus(int m, int n) {
        assert m >= 0 : "m should not be negative";

        SinglyLinkedNode dummy = new SinglyLinkedNode(-1);
        SinglyLinkedNode cur = dummy;

        for (int i = 1; i <= n; i++) {
            cur.next = new SinglyLinkedNode(i);
            cur = cur.next;
        }
        cur.next = dummy.next;
        //now we have a circle
        assert cur.next.data == 1;

        while (cur.next != cur) {
            for (int i = 0; i < m; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }

        return cur.data;
    }
}
