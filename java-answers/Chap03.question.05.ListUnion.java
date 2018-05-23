//Chap03.question.05.ListUnion.java

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
        SinglyLinkedNode dummy = new SinglyLinkedNode(-1);
        SinglyLinkedNode cur = dummy;
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            cur.next = new SinglyLinkedNode(i);
            cur = cur.next;
        }
        SinglyLinkedNode L1 = dummy.next;
        dummy = new SinglyLinkedNode(-1);
        cur = dummy;
        for (int i : new int[]{-1, 2, 5, 7}) {
            cur.next = new SinglyLinkedNode(i);
            cur = cur.next;
        }
        SinglyLinkedNode L2 = dummy.next;
        output(L1);
        output(L2);
        output(intersect(L1, L2));
        output(union(L1, L2));
    }

    private static void output(SinglyLinkedNode linkedNode) {
        while (linkedNode != null) {
            System.out.print("\"" + linkedNode.data + '"');
            if (linkedNode.next != null)
                System.out.print("->");
            linkedNode = linkedNode.next;
        }
        System.out.println();
    }

    public static SinglyLinkedNode intersect(SinglyLinkedNode L1, SinglyLinkedNode L2) {
        SinglyLinkedNode dummy = new SinglyLinkedNode(-1);
        SinglyLinkedNode cur = dummy;
        while (L1 != null && L2 != null) {
            int d1 = L1.data;
            int d2 = L2.data;
            if (d1 == d2) {
                cur.next = new SinglyLinkedNode(d1);
                cur = cur.next;
                L1 = L1.next;
                L2 = L2.next;
            } else if (d1 > d2) {
                while (L2.next != null && d2 < d1) {
                    L2 = L2.next;
                    d2 = L2.data;
                }
            } else {
                while (L1.next != null && d1 < d2) {
                    L1 = L1.next;
                    d1 = L1.data;
                }
            }
        }
        return dummy.next;
    }

    public static SinglyLinkedNode union(SinglyLinkedNode L1, SinglyLinkedNode L2) {
        SinglyLinkedNode dummy = new SinglyLinkedNode(-1);
        SinglyLinkedNode cur = dummy;
        while (L1 != null && L2 != null) {
            if (L1.data == L2.data) {
                cur.next = new SinglyLinkedNode(L1.data);
                cur = cur.next;
                L1 = L1.next;
                L2 = L2.next;
            } else if (L1.data > L2.data) {
                while (L2 != null && L1.data > L2.data) {
                    cur.next = new SinglyLinkedNode(L2.data);
                    cur = cur.next;
                    L2 = L2.next;
                }
            } else {
                while (L1 != null && L1.data < L2.data) {
                    cur.next = new SinglyLinkedNode(L1.data);
                    cur = cur.next;
                    L1 = L1.next;
                }
            }
        }
        while (L1 != null) {
            cur.next = new SinglyLinkedNode(L1.data);
            cur = cur.next;
            L1 = L1.next;
        }
        while (L2 != null) {
            cur.next = new SinglyLinkedNode(L2.data);
            cur = cur.next;
            L2 = L2.next;
        }

        return dummy.next;
    }
}
