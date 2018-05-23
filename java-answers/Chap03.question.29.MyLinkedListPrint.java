//Chap03.question.29.MyLinkedListPrint.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head, tail;
    private int size, modCount;
    private int deletedNodeCount;

    public MyLinkedList() {
        size = modCount = deletedNodeCount = 0;
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void print() {
        Node<T> cursor = tail.prev;
        while (cursor != head) {
            System.out.print(cursor.data);
            if (cursor.prev != head && cursor.next != tail)
                System.out.print("<=>");
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private void addBefore(Node<T> node, T t) {
        Node<T> before = node.prev;
        Node<T> newNode = new Node<T>(null, t, null);
        before.next = newNode;
        newNode.next = node;
        node.prev = newNode;
        newNode.prev = before;

        size++;
        modCount++;
    }


    private void remove(Node<T> node) {
        //node must be in the list
        node.deleted = true;
        deletedNodeCount++;
        size--;
        modCount++;
        if (deletedNodeCount >= size) {
            Node<T> cursor = head.next;
            while (cursor != tail) {
                if (cursor.deleted) {
                    Node<T> before = cursor.prev;
                    Node<T> after = cursor.next;
                    before.next = after;
                    after.prev = before;
                    cursor.prev = null;
                    cursor.next = node;
                }
                cursor = cursor.next;
            }
            deletedNodeCount = 0;
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException();
        if (index < size / 2) {
            Node<T> cursor = head;
            int i = 0;
            while (i < index) {
                cursor = cursor.next;
                if (!cursor.deleted)
                    i++;
            }
            return cursor.next;
        } else {
            Node<T> cursor = tail;
            int i = 0;
            while (i < size - index) {
                cursor = cursor.prev;
                if (!cursor.deleted)
                    i--;
            }
            return cursor;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> prev, next;
        boolean deleted;

        private Node(Node<T> p, T d, Node<T> n) {
            prev = p;
            data = d;
            next = n;
            deleted = false;
        }
    }

    private class MyListIterator implements Iterator<T> {
        private Node<T> cursor = head;
        private int expectedModCount = modCount;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return cursor.next != tail;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            T t = cursor.data;
            cursor = cursor.next;
            canRemove = true;
            return t;
        }

        @Override
        public void remove() {
            if (!canRemove)
                throw new IllegalStateException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            MyLinkedList.this.remove(cursor.prev);
            canRemove = false;
            expectedModCount++;
        }
    }
}
