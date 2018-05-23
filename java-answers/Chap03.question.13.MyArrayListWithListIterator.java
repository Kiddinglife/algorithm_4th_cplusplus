//Chap03.question.13.MyArrayListWithListIterator.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int capacity;
    private int modCount;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            this.capacity = DEFAULT_CAPACITY;
        } else {
            this.capacity = capacity;
        }
        size = 0;
        elements = new Object[this.capacity];
        modCount = 0;
    }

    public void add(E e) {
        if (size == capacity) {
            ensureCapacity(capacity + capacity >> 1);
        }
        elements[size++] = e;
        modCount++;
    }

    public void addAt(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (size == capacity)
            ensureCapacity(capacity + capacity >> 1);
        System.arraycopy(elements, index, elements, index + 1, size - index - 1);
        elements[index] = e;
        modCount++;
        size++;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity <= capacity) return;
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(elements, 0, newArr, 0, size);
        elements = newArr;
    }

    public boolean remove(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeAt(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elements[i])) {
                    removeAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (E) elements[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        elements[index] = e;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return true;
        } else {
            for (int i = 0; i < size; i++)
                if (e.equals(elements[i]))
                    return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }


    private class MyArrayListIterator implements ListIterator<E> {
        private int cursor = 0;
        private boolean canModify = false;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        private int expectedModCount = modCount;

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            Object nextItem = elements[cursor++];
            canModify = true;
            return (E) nextItem;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            canModify = true;
            return (E) elements[cursor--];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            elements[cursor] = e;
        }

        @Override
        public void add(E e) {
            if (!canModify)
                throw new IllegalStateException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            MyArrayList.this.addAt(cursor++, e);
            expectedModCount++;
            canModify = false;
        }


    }
}
