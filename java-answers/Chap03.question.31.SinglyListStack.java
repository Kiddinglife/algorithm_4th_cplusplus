//Chap03.question.31.SinglyListStack.java

import java.util.EmptyStackException;

public class SinglyListStack<T> {
    private Node<T> top, bottom;

    public void push(T t) {
        if (top == null) {
            top = new Node<>(t, null);
            bottom = top;
        } else {
            top.next = new Node<>(t, null);
            top = top.next;
        }
    }

    public T pop() {
        if (top == null)
            throw new EmptyStackException();
        Node<T> cursor = bottom;
        while (cursor.next != top)
            cursor = cursor.next;
        T t = top.data;
        cursor.next = null;
        top = cursor;
        return t;
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
