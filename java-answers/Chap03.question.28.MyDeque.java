//Chap03.question.28.MyDeque.java

import java.util.NoSuchElementException;

interface MyDequeInterface<T> {
    void push(T t);

    T pop();

    void inject(T t);

    T eject();
}

public class MyDeque<T> implements MyDequeInterface<T> {
    private Node<T> head, tail;

    public MyDeque() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void push(T t) {
        Node<T> after = head.next;
        Node<T> newNode = new Node<>(null, t, null);
        head.next = newNode;
        newNode.next = after;
        after.prev = newNode;
        newNode.prev = head;
    }

    @Override
    public T pop() {
        if (head == tail)
            throw new NoSuchElementException("deque is empty");
        Node<T> node = head.next;
        Node<T> after = node.next;
        head.next = after;
        after.prev = head;
        node.next = null;
        node.prev = null;
        return node.data;
    }

    @Override
    public void inject(T t) {
        Node<T> newNode = new Node<>(null, t, null);
        Node<T> before = tail.prev;
        before.next = newNode;
        newNode.next = tail;
        tail.prev = newNode;
        newNode.prev = before;
    }

    @Override
    public T eject() {
        if (tail.prev == head)
            throw new NoSuchElementException("deque is empty");
        Node<T> node = tail.prev;
        Node<T> before = node.prev;
        before.next = tail;
        tail.prev = before;
        node.next = null;
        node.prev = null;
        return node.data;
    }

    private static class Node<T> {
        T data;
        Node<T> prev, next;

        Node(Node<T> p, T d, Node<T> n) {
            data = d;
            prev = p;
            next = n;
        }
    }
}
