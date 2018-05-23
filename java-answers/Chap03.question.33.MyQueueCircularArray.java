//Chap03.question.33.MyQueueCircularArray.java

import java.util.NoSuchElementException;

public class MyQueueCircularArray<T> {
    private Object[] elements;
    private int capacity, size, head, tail;

    public MyQueueCircularArray(int c) {
        capacity = c > 100 ? c : 100;
        size = 0;
        elements = new Object[capacity];
        head = tail = 0;
    }

    public void enqueue(T t) throws ArrayFullException {
        if (size == capacity)
            throw new ArrayFullException();
        elements[head] = t;
        head++;
        if (head == capacity)
            head = 0;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0)
            throw new NoSuchElementException();
        Object o = elements[tail];
        tail++;
        if (tail == capacity)
            tail = 0;
        size--;
        return (T) o;
    }
}
