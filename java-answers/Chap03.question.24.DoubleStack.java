//Chap03.question.24.DoubleStack.java

import java.util.EmptyStackException;

public class DoubleStack<T> {
    private static final int DEFAULT_CAPACITY = 100;
    private Object[] elements;
    private int capacity;
    private int cursorLeft, cursorRight;

    public DoubleStack(int capacity) {
        this.capacity = capacity > DEFAULT_CAPACITY ? capacity : DEFAULT_CAPACITY;
        elements = new Object[this.capacity];
        cursorLeft = 0;
        cursorRight = this.capacity - 1;
    }

    public void pushLeft(T t) throws ArrayFullException {
        if (cursorLeft > cursorRight)
            throw new ArrayFullException();
        elements[cursorLeft++] = t;
    }

    public void pushRight(T t) throws ArrayFullException {
        if (cursorLeft > cursorRight)
            throw new ArrayFullException();
        elements[cursorRight--] = t;
    }

    @SuppressWarnings("unchecked")
    public T popLeft() {
        if (cursorLeft == 0)
            throw new EmptyStackException();
        return (T) elements[--cursorLeft];
    }

    @SuppressWarnings("unchecked")
    public T popRight() {
        if (cursorRight == capacity - 1)
            throw new EmptyStackException();
        return (T) elements[++cursorRight];
    }

    @SuppressWarnings("unchecked")
    public T peekLeft() {
        if (cursorLeft == 0)
            throw new EmptyStackException();
        return (T) elements[cursorLeft - 1];
    }

    @SuppressWarnings("unchecked")
    public T peekRight() {
        if (cursorRight == capacity - 1)
            throw new EmptyStackException();
        return (T) elements[cursorRight + 1];
    }

    private class ArrayFullException extends Exception {
    }
}
