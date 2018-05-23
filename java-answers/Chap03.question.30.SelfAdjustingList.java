//Chap03.question.30.SelfAdjustingList.java

public class SelfAdjustingList {
}

class SelfAdjustingListArray<T> {
    private Object[] elements;
    private int size, capacity;

    public SelfAdjustingListArray(int c) {
        capacity = c > 10 ? c : 10;
        size = 0;
        elements = new Object[capacity];
    }

    public void add(T t) {
        if (size == capacity) {
            capacity = capacity + capacity >>> 1;
            Object[] arr = new Object[capacity];
            System.arraycopy(elements, 0, arr, 0, size);
            elements = arr;
        }
        elements[size++] = t;
    }

    public boolean find(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    elements[size - 1] = null;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(elements[i])) {
                    System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                    elements[size - 1] = t;
                    return true;
                }
            }
        }
        return false;
    }
}

class SelfAdjustingListLinkedList<T> {
    private Node<T> head, tail;

    public SelfAdjustingListLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void add(T t) {
        Node<T> before = tail.prev;
        Node<T> node = new Node<>(null, t, null);
        before.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = before;
    }

    public boolean find(T t) {
        Node<T> cursor = head.next;
        if (t == null) {
            while (cursor != tail) {
                if (cursor.data == null) {
                    Node<T> before = cursor.prev;
                    Node<T> after = cursor.next;
                    before.next = after;
                    after.prev = before;
                    Node<T> before1 = tail.prev;
                    before1.next = cursor;
                    cursor.next = tail;
                    tail.prev = cursor;
                    cursor.prev = before1;
                    return true;
                }
                cursor = cursor.next;
            }
        } else {
            while (cursor != tail) {
                if (t.equals(cursor.data)) {
                    Node<T> before = cursor.prev;
                    Node<T> after = cursor.next;
                    before.next = after;
                    after.prev = before;
                    Node<T> before1 = tail.prev;
                    before1.next = cursor;
                    cursor.next = tail;
                    tail.prev = cursor;
                    cursor.prev = before1;
                    return true;
                }
                cursor = cursor.next;
            }
        }
        return false;
    }

    private static class Node<T> {
        T data;
        Node<T> prev, next;

        Node(Node<T> p, T t, Node<T> n) {
            data = t;
            prev = p;
            next = n;
        }
    }
}
