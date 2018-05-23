//Chap03.question.16.MyArrayListReverseIterator.java

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int capacity;
    private int modCount;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int newCapacity) {
        if (newCapacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        } else {
            capacity = newCapacity;
        }
        size = 0;
        elements = new Object[this.capacity];
        modCount = 0;
    }

    public void add(T t) {
        if (size == capacity) {
            ensureCapacity(capacity + capacity >>> 1);
        }
        elements[size++] = t;
        modCount++;
    }

    public void addAt(int index, T t) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (size == capacity)
            ensureCapacity(capacity + capacity >>> 1);
        System.arraycopy(elements, index, elements, index + 1, size - index - 1);
        elements[index] = t;
        modCount++;
        size++;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity <= capacity) return;
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(elements, 0, newArr, 0, size);
        elements = newArr;
    }

    public boolean remove(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeAt(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(elements[i])) {
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
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (T) elements[index];
    }

    public void set(int index, T t) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        elements[index] = t;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++)
                if (elements[i] == null)
                    return true;
        } else {
            for (int i = 0; i < size; i++)
                if (t.equals(elements[i]))
                    return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    public Iterator<T> reversedIterator() {
        return new ReversedIterator();
    }

    private class MyArrayListIterator implements ListIterator<T> {
        private int cursor = 0;
        private boolean canModify = false;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            checkForComodification();
            Object nextItem = elements[cursor++];
            canModify = true;
            return (T) nextItem;
        }

        private void checkForComodification() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            checkForComodification();
            canModify = true;
            return (T) elements[cursor--];
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
        public void set(T t) {
            checkForComodification();
            elements[cursor] = t;
        }

        @Override
        public void add(T t) {
            if (!canModify)
                throw new IllegalStateException();
            checkForComodification();
            MyArrayList.this.addAt(cursor++, t);
            expectedModCount++;
            canModify = false;
        }

        private int expectedModCount = modCount;


    }

    private class ReversedIterator implements Iterator<T> {
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            canRemove = true;
            return (T) elements[cursor--];
        }

        @Override
        public void remove() {
            if (!canRemove)
                throw new IllegalStateException();
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            MyArrayList.this.removeAt(cursor + 1);
            expectedModCount++;
            canRemove = false;
        }

        private int cursor = size - 1;


        private int expectedModCount = modCount;


    }
}
