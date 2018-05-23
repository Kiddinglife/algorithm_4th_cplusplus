//Chap03.question.14.MyLinkedListWithListIterator.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
    private DoublyLinkedNode head, tail;
    private int size;
    private int modCount;

    public MyLinkedList() {
        size = modCount = 0;
        head = new DoublyLinkedNode();
        tail = new DoublyLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addBefore(DoublyLinkedNode node, E e) {
        DoublyLinkedNode newNode = new DoublyLinkedNode(e);
        if (node.prev == head) {
            DoublyLinkedNode after = head.next;
            head.next = newNode;
            newNode.next = after;
            after.prev = newNode;
            newNode.prev = head;
        } else {
            DoublyLinkedNode before = node.prev;
            before.next = newNode;
            newNode.next = node;
            node.prev = newNode;
            newNode.prev = before;
        }
        size++;
        modCount++;
    }

    private class DoublyLinkedNode {
        E data;
        DoublyLinkedNode prev, next;

        DoublyLinkedNode() {
        }

        DoublyLinkedNode(E e) {
            data = e;
        }
    }

    private class MyLinkedListIterator implements ListIterator<E> {
        boolean canModify = false;
        private DoublyLinkedNode cur = head.next;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            DoublyLinkedNode node = cur;
            cur = cur.next;
            canModify = true;
            return node.data;
        }

        @Override
        public boolean hasPrevious() {
            return cur != head;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            DoublyLinkedNode node = cur;
            cur = cur.prev;
            canModify = true;
            return node.data;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            cur.data = e;
        }

        @Override
        public void add(E e) {
            if (!canModify)
                throw new IllegalStateException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            MyLinkedList.this.addBefore(cur, e);
            expectedModCount++;
            canModify = false;
        }
    }
}
