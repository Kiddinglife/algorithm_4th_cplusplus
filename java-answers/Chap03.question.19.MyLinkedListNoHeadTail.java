//Chap03.question.19.MyLinkedListNoHeadTail.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private int size, modCount;

    public MyLinkedList() {
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public void clear() {
        first = null;
        size = 0;
        modCount++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T t) {
        add(size, t);
        return true;
    }

    public void add(int index, T t) {
        addBefore(getNode(index), t);
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T set(int index, T t) {
        Node<T> n = getNode(index);
        T old = n.data;
        n.data = t;
        modCount++;
        return old;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    private void addBefore(Node<T> p, T x) {
        assert p != null;
        Node<T> newNode = new Node<>(null, x, p);
        Node<T> before = p.prev;
        if (before != null)
            before.next = newNode;
        //newNode.next = p;
        p.prev = newNode;
        newNode.prev = before;
        size++;
        modCount++;
    }

    private T remove(Node<T> p) {
        assert p != null;
        if (p.prev == null) {
            first = first.next;
            first.prev = null;
        } else if (p.next == null) {
            p.prev.next = null;
        } else {
            Node<T> before = p.prev;
            Node<T> after = p.next;
            before.next = after;
            after.prev = before;
        }
        p.prev = null;
        p.next = null;
        T t = p.data;
        p = null;

        size--;
        modCount++;
        return t;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException();
        Node<T> cur = first;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur;
    }

    private static class Node<T> {
        T data;
        Node<T> prev, next;

        private Node(Node<T> p, T t, Node<T> n) {
            prev = p;
            data = t;
            next = n;
        }
    }

    private class MyIterator implements Iterator<T> {
        private Node<T> cursor = first;
        private int expectedModCount = modCount;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return cursor != null;
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
