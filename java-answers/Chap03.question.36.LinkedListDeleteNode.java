//Chap03.question.36.LinkedListDeleteNode.java

public class LinkedListDeleteNode<T> {
    private Node<T> first, last;

    public void delete(Node<T> node) {
        assert node != last;
        node.data = node.next.data;
        node.next = node.next.next;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
    }
}
