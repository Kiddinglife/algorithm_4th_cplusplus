//Chap03.question.26.TripleStack.java

import java.util.EmptyStackException;

public class TripleStack<T> {
    private static final int DEFAULT_CAPACITY = 100;
    private Object[] elements;
    private int capacity;
    private int topCursor1, topCursor2, topCursor3, bottomCursor3;

    public TripleStack(int c) {
        capacity = c > DEFAULT_CAPACITY ? c : DEFAULT_CAPACITY;
        elements = new Object[capacity];
        topCursor1 = 0;
        topCursor2 = capacity - 1;
        topCursor3 = bottomCursor3 = capacity / 3;
    }

    public void pushStack1(T t) throws StackFullException {
        if (topCursor1 == bottomCursor3)
            moveStack3();
        else {
            elements[topCursor1++] = t;
        }
    }

    public void pushStack2(T t) throws StackFullException {
        if (topCursor2 < topCursor3)
            moveStack3();
        else
            elements[topCursor2--] = t;
    }

    public void pushStack3(T t) throws StackFullException {
        if (topCursor2 < topCursor3)
            moveStack3();
        else
            elements[topCursor3++] = t;
    }

    // ..top1..bottom3..top3..top2..
    private void moveStack3() throws StackFullException {
        if (topCursor1 == bottomCursor3 && topCursor3 > topCursor2)
            throw new StackFullException();
        else if (topCursor1 == bottomCursor3) {
            //move right
            int gap = topCursor2 - topCursor3 + 1;
            int diff = (gap + 1) / 2;
            System.arraycopy(elements, bottomCursor3, elements, bottomCursor3 + diff, topCursor3 - bottomCursor3);
            topCursor3 += diff;
            bottomCursor3 += diff;
        } else if (topCursor3 > topCursor2) {
            //move left
            assert topCursor3 == topCursor2 + 1;
            int gap = bottomCursor3 - topCursor1;
            assert gap > 0;
            int diff = (1 + gap) / 2;
            System.arraycopy(elements, bottomCursor3, elements, bottomCursor3 - diff, topCursor3 - bottomCursor3);
            bottomCursor3 -= diff;
            topCursor3 -= diff;
        }
    }

    @SuppressWarnings("unchecked")
    public T popStack1() {
        if (topCursor1 == 0)
            throw new EmptyStackException();
        else
            return (T) elements[--topCursor1];
    }

    @SuppressWarnings("unchecked")
    public T popStack2() {
        if (topCursor2 == capacity - 1)
            throw new EmptyStackException();
        else
            return (T) elements[++topCursor2];
    }

    @SuppressWarnings("unchecked")
    public T popStack3() {
        if (topCursor3 == bottomCursor3)
            throw new EmptyStackException();
        else
            return (T) elements[--topCursor3];
    }

    private class StackFullException extends Throwable {
    }
}
