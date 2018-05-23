//Chap04.question.45.LeftChildRightSiblingTree.java

import java.util.ArrayDeque;

public class LeftChildRightSiblingTree<T> {
    private Node<T> root;

    public void traverse(Function<T> function) {
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            function.method(node);
            if (node.firstChild != null) {
                node = node.firstChild;
                queue.offer(node);
                while (node.nextSibling != null) {
                    node = node.nextSibling;
                    queue.offer(node);
                }
            }
        }
    }

    public interface Function<T> {
        void method(Node<T> node);
    }

    private static class Node<T> {
        T data;
        Node<T> firstChild;
        Node<T> nextSibling;
    }
}
