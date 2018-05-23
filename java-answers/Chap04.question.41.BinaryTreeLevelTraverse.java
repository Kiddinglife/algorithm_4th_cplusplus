//Chap04.question.41.BinaryTreeLevelTraverse.java

import java.util.ArrayDeque;
import java.util.Arrays;

public class BinaryTreeLevelTraverse {
    public static void main(String... args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : Arrays.asList(4, 1, 3, 2, 5))
            tree.insert(i);
        tree.levelTraverse(new BinarySearchTree.Function<Integer>() {
            @Override
            public void f(BinarySearchTree.BinaryTreeNode<Integer> node) {
                System.out.print(node.data + " ");
            }
        });
    }

    public static class BinarySearchTree<T extends Comparable<? super T>> {
        private BinaryTreeNode<T> root;

        public void levelTraverse(Function<T> function) {
            if (root == null) return;
            ArrayDeque<BinaryTreeNode<T>> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    BinaryTreeNode<T> node = queue.poll();
                    function.f(node);
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
        }

        public void insert(T t) {
            root = insert(t, root);
        }

        private BinaryTreeNode<T> insert(T t, BinaryTreeNode<T> node) {
            if (node == null)
                return new BinaryTreeNode<>(t, null, null);
            int compareResult = t.compareTo(node.data);
            if (compareResult < 0)
                node.left = insert(t, node.left);
            if (compareResult > 0)
                node.right = insert(t, node.right);
            return node;
        }

        interface Function<T> {
            void f(BinaryTreeNode<T> node);
        }

        private static class BinaryTreeNode<T> {
            T data;
            BinaryTreeNode<T> left, right;

            BinaryTreeNode(T t, BinaryTreeNode<T> l, BinaryTreeNode<T> r) {
                data = t;
                left = l;
                right = r;
            }
        }
    }
}
