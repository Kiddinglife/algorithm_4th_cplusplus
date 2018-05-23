//Chap04.question.36.PerfectBstGeneration.java

public class PerfectBstGeneration {

    public static void main(String... args) {
        generate(2).print();
    }

    public static BinarySearchTree<Integer> generate(int h) {
        assert h >= 0;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.root = generate(1, (int) Math.pow(2, h + 1) - 1);
        return tree;
    }

    private static BinarySearchTree.BinaryTreeNode<Integer> generate(int min, int max) {
        if (max < min) return null;
        int mid = (min + max) / 2;
        BinarySearchTree.BinaryTreeNode<Integer> node
                = new BinarySearchTree.BinaryTreeNode<>(mid, null, null);
        node.left = generate(min, mid - 1);
        node.right = generate(mid + 1, max);
        return node;
    }

    static class BinarySearchTree<T extends Comparable<? super T>> {
        public BinaryTreeNode<T> root;

        public void print() {
            print(root);
        }

        private void print(BinaryTreeNode<T> node) {
            if (node == null)
                System.out.print("# ");
            else {
                System.out.print(node.data + " ");
                print(node.left);
                print(node.right);
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
            else if (compareResult > 0)
                node.right = insert(t, node.right);
            return node;
        }

        static class BinaryTreeNode<T> {
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
