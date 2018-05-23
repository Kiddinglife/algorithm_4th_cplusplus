//Chap04.question.39.TreePainter.java

import java.util.ArrayDeque;

public class TreePainter {
    public static void main(String... args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : new int[]{3, 2, 4, 1, 5}) {
            tree.insert(i);
        }
        tree.calcX().calcY().print();
        System.out.println();
        tree.generatePaintCommands();
    }

    public static class BinarySearchTree<T extends Comparable<? super T>> {
        private TreeNode<T> root;
        private int cnt;

        public void insert(T t) {
            root = insert(t, root);
        }

        private TreeNode<T> insert(T t, TreeNode<T> node) {
            if (node == null)
                return new TreeNode<>(t, null, null);
            int c = t.compareTo(node.data);
            if (c < 0)
                node.left = insert(t, node.left);
            if (c > 0)
                node.right = insert(t, node.right);
            return node;
        }

        public BinarySearchTree<T> calcX() {
            cnt = 1;
            calcX(root);
            return this;
        }

        private void calcX(TreeNode<T> node) {
            if (node == null) return;
            calcX(node.left);
            node.x = cnt++;
            calcX(node.right);
        }

        public BinarySearchTree<T> calcY() {
            int level = 0;
            ArrayDeque<TreeNode<T>> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                level++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode<T> node = queue.poll();
                    node.y = level;
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
            return this;
        }

        public void generatePaintCommands() {
            generatePaintCommands(root);
        }

        private void generatePaintCommands(TreeNode<T> node) {
            if (node == null) return;
            System.out.println(node.x + ": Circle(" + node.x + ", " + node.y + ")");
            if (node.left != null) {
                generatePaintCommands(node.left);
                System.out.println("DrawLine(" + node.x + " ," + node.left.x + ")");
            }
            if (node.right != null) {
                generatePaintCommands(node.right);
                System.out.println("DrawLine(" + node.x + " ," + node.right.x + ")");
            }
        }

        public void print() {
            print(root);
        }

        private void print(TreeNode<T> node) {
            if (node == null)
                System.out.print("# ");
            else {
                System.out.print("(" + node.data + "," + node.x + "," + node.y + ")");
                print(node.left);
                print(node.right);
            }
        }

        private static class TreeNode<T> {
            T data;
            TreeNode<T> left, right;
            int x, y;

            TreeNode(T t, TreeNode<T> l, TreeNode<T> r) {
                data = t;
                left = l;
                right = r;
            }
        }
    }
}
