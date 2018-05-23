//Chap04.text.02.BinarySearchTree.java

public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left, right;

        BinaryTreeNode(BinaryTreeNode<T> l, T d, BinaryTreeNode<T> r) {
            left = l;
            data = d;
            right = r;
        }
    }

    private BinaryTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    private boolean contains(T t, BinaryTreeNode<T> root) {
        if (root == null) return false;
        int compareResult = t.compareTo(root.data);
        if (compareResult < 0)
            return contains(t, root.left);
        else if (compareResult > 0)
            return contains(t, root.right);
        else
            return true;
    }

    public T findMin() {
        if (root == null) return null;
        while (root.left != null)
            root = root.left;
        return root.data;
    }

    public T findMax() {
        if (root == null) return null;
        while (root.right != null)
            root = root.right;
        return root.data;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    private BinaryTreeNode<T> insert(T t, BinaryTreeNode<T> root) {
        if (root == null)
            return new BinaryTreeNode<T>(null, t, null);
        int compareResult = t.compareTo(root.data);
        if (compareResult < 0)
            root.left = insert(t, root.left);
        else if (compareResult > 0)
            root.right = insert(t, root.right);

        return root;
    }

    public void remove(T t) {
        root = remove(t, root);
    }

    private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> root) {
        if (root == null)
            return null;
        int compareResult = t.compareTo(root.data);
        if (compareResult < 0)
            root.left = remove(t, root.left);
        else if (compareResult > 0)
            root.right = remove(t, root.right);
        else if (root.left != null && root.right != null) {
            BinaryTreeNode<T> rightMin = root.right;
            while (rightMin.left != null)
                rightMin = rightMin.left;
            root.data = rightMin.data;
            root.right = remove(rightMin.data, root.right);
        } else
            root = root.left != null ? root.left : root.right;
        return root;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(BinaryTreeNode<T> root) {
        if (root == null)
            System.out.print("# ");
        else {
            System.out.print(root.data + " ");
            printTree(root.left);
            printTree(root.right);
        }
    }
}
