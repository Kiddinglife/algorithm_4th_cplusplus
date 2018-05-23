//Chap03.question.10.MyLinkedListRemoveAll.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {
    private int size;
    private DoublyLinkedNode<E> head, tail;
    private int modCount;

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    private boolean remove(DoublyLinkedNode<E> node) {
        DoublyLinkedNode cur = head;
        while (cur != null) {
            if (cur.equals(node)) {
                // actual remove node code
                // be careful cur might be head or tail
                modCount++;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean contains(E e) {
        if (e == null) {
            for (E ee : this) {
                if (ee == null) return true;
            }
        } else {
            for (E ee : this)
                if (ee.equals(e)) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void removeAll(Iterable<? extends E> items) {
        for (Object item : items) {
            remove((E)item);
        }
    }

    private boolean remove(E e) {
        DoublyLinkedNode cur = head;
        while (cur != null) {
            if (cur.data.equals(e)) {
                //actual remove code here
                modCount++;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    private static class DoublyLinkedNode<E> {
        E data;
        DoublyLinkedNode<E> prev, next;

        DoublyLinkedNode() {
            data = null;
            prev = next = null;
        }
    }

    private class MyLinkedListIterator implements Iterator<E> {
        private int expectedModCount = modCount;
        private DoublyLinkedNode<E> cur = head;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return cur != null && cur.next != null;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            E nextItem = cur.data;
            canRemove = true;
            cur = cur.next;
            return nextItem;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if (!canRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(cur.prev);
            canRemove = false;
            expectedModCount++;
        }
    }
}
