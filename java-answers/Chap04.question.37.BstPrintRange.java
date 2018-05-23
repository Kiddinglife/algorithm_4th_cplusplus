//Chap04.question.37.BstPrintRange.java

public class BstPrintRange {
    public static class BinarySearchTree<T extends Comparable<? super T>> {
        private TreeNode<T> root;

        public void printRange(T k1, T k2) {
            assert k1.compareTo(k2) <= 0;
            printRange(k1, k2, root);
        }

        private void printRange(T k1, T k2, TreeNode<T> node) {
            assert k1.compareTo(k2) <= 0;
            if (node == null) return;
            if (k1.compareTo(node.data) > 0)
                printRange(k1, k2, node.right);
            else if (k1.compareTo(node.data) <= 0 && k2.compareTo(node.data) >= 0) {
                printRange(k1, k2, node.left);
                System.out.print(node.data + " ");
                printRange(k1, k2, node.right);
            } else
                printRange(k1, k2, node.right);
        }

        private static class TreeNode<T> {
            T data;
            TreeNode<T> left, right;

            TreeNode(T t, TreeNode<T> l, TreeNode<T> r) {
                data = t;
                left = l;
                right = r;
            }
        }
    }
}
