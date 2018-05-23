//Chap03.question.18.MyLinkedList.java

import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<T> implements Iterable<T> {
    private class Node {
        T data;
        Node prev, next;

        Node() {
        }

        Node(T t) {
            data = t;
        }
    }

    private Node head, tail;
    private int size, modCount;

    public MyLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        size = modCount = 0;
    }

    private void add(int index, T t) {
        if (index < 0 || index > size) return;
        Node newNode = new Node(t);
        if (index < size / 2) {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            Node after = cur.next;
            cur.next = newNode;
            newNode.next = after;
            after.prev = newNode;
            newNode.prev = cur;
        } else {
            Node cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
            Node before = cur.prev;
            before.next = newNode;
            newNode.next = cur;
            cur.prev = newNode;
            newNode.prev = before;
        }
        size++;
        modCount++;
    }

    private T remove(int index) {
        if (index < 0 || index >= size) return null;
        Node r;
        if (index < size / 2) {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            r = cur.next;
            Node after = cur.next.next;
            cur.next = after;
            after.prev = cur;
        } else {
            Node cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
            r = cur.prev;
            Node before = cur.prev.prev;
            cur.prev = before;
            before.next = cur;
        }
        size--;
        modCount++;
        return r.data;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) return null;
        if (index < size / 2) {
            Node cur = head;
            for (int i = 0; i < index; i++)
                cur = cur.next;
            return cur.next;
        } else {
            Node cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    public void addFirst(T t) {
        add(0, t);
    }

    public void addLast(T t) {
        add(size, t);
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public T getFirst() {
        return getNode(0).data;
    }

    public T getLast() {
        return getNode(size - 1).data;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {

        }

        @Override
        public void add(T t) {

        }
    }
}
