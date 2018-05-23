//Chap03.question.32.SinglyListQueue.java

import java.util.NoSuchElementException;

public class SinglyListQueue<T> {
    private Node<T> first, last;

    public void enqueue(T t) {
        if (first == null) {
            first = new Node<>(t, null);
            last = first;
        } else {
            last.next = new Node<>(t, null);
            last = last.next;
        }
    }

    public T dequeue() {
        if (last == null)
            throw new NoSuchElementException();
        else if (last == first) {
            T t = first.data;
            first = null;
            last = null;
            return t;
        } else {
            T t = first.data;
            first = first.next;
            return t;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T t, Node<T> n) {
            data = t;
            next = n;
        }
    }
}
