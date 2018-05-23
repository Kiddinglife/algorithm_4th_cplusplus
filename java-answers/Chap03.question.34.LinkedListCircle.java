//Chap03.question.34.LinkedListCircle.java

import java.util.IdentityHashMap;

public class LinkedListCircle<T> {
    public boolean checkCircle1(Node<T> head) {
        IdentityHashMap<Node<T>, Object> map = new IdentityHashMap<>();
        Node<T> cursor = head;
        while (cursor != null) {
            if (map.containsKey(cursor))
                return true;
            map.put(cursor, null);
            cursor = cursor.next;
        }
        return false;
    }

    public boolean checkCircle2(Node<T> head) {
        if (head == null) return false;
        Node<T> slow = head;
        Node<T> fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
    }
}
