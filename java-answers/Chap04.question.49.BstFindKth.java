//Chap04.question.49.BstFindKth.java

import java.util.Scanner;

public class BstFindKth {
    public static void main(String... args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int i = scanner.nextInt();
            if (i == -1)
                break;
            tree.insert(i);
        }
    }

    public static class BinarySearchTree<T extends Comparable<? super T>> {
        private Node<T> root;

        public void insert(T t) {
            root = insert(t, root);
        }

        private Node<T> insert(T t, Node<T> node) {
            if (node == null)
                return new Node<>(t, null, null, 1);
            int compareResult = t.compareTo(node.data);
            if (compareResult < 0)
                node.left = insert(t, node.left);
            else if (compareResult > 0)
                node.right = insert(t, node.right);

            node.nodeCount = getNodeCount(node.left)
                    + getNodeCount(node.right) + 1;
            return node;
        }

        private int getNodeCount(Node<T> node) {
            return node != null ? node.nodeCount : 0;
        }

        public T findKth(int k) {
            assert k > 0;
            return findKth(k, root);
        }

        private T findKth(int k, Node<T> node) {
            if (node == null || node.nodeCount < k) return null;
            if (k == node.nodeCount)
                return node.data;
            if (k <= getNodeCount(node.left))
                return findKth(k, node.left);
            return findKth(k - getNodeCount(node.left) - 1, node.right);
        }

        @Override
        public String toString() {
            return str(root);
        }

        private String str(Node<T> node) {
            if (node == null)
                return "# ";
            return "(" + node.data + "," + node.nodeCount + ") "
                    + str(node.left) + str(node.right);
        }

        private static class Node<T> {
            T data;
            Node<T> left, right;
            int nodeCount;

            Node(T t, Node<T> l, Node<T> r, int cnt) {
                data = t;
                left = l;
                right = r;
                nodeCount = cnt;
            }
        }
    }
}
