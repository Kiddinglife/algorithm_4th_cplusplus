//Chap04.question.21.AvlTree.java

public class AvlTree<T extends Comparable<? super T>> {
    private AvlNode<T> root;

    public AvlTree() {
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

    private boolean contains(T t, AvlNode<T> root) {
        if (root == null) return false;
        int compareResult = t.compareTo(root.data);
        if (compareResult < 0)
            return contains(t, root.left);
        else return compareResult == 0 || contains(t, root.right);
    }

    public T findMin() {
        return findMin(root);
    }

    private T findMin(AvlNode<T> root) {
        if (root == null) return null;
        while (root.left != null)
            root = root.left;
        return root.data;
    }

    public T findMax() {
        return findMax(root);
    }

    private T findMax(AvlNode<T> root) {
        if (root == null) return null;
        while (root.right != null)
            root = root.right;
        return root.data;
    }

    public void insert(T t) {
        insert(t, root);
    }

    private AvlNode<T> insert(T t, AvlNode<T> root) {
        if (root == null) return new AvlNode<>(t, null, null);
        int compareResult = t.compareTo(root.data);
        if (compareResult < 0) {
            root.left = insert(t, root.left);
            if (getHeight(root.left) - getHeight(root.right) == 2) {
                if (t.compareTo(root.left.data) < 0)
                    root = singleRotateLeftChild(root);
                else
                    root = doubleRotateLeftChild(root);
            }
        } else if (compareResult > 0) {
            root.right = insert(t, root.right);
            if (getHeight(root.right) - getHeight(root.left) == 2) {
                if (t.compareTo(root.right.data) < 0)
                    root = doubleRotateRightChild(root);
                else
                    root = singleRotateRightChild(root);
            }
        }

        root.height = (byte) (Math.max(getHeight(root.left), getHeight(root.right)) + 1);
        return root;
    }

    private AvlNode<T> doubleRotateRightChild(AvlNode<T> k2) {
        k2.right=singleRotateLeftChild(k2.right);
        return singleRotateRightChild(k2);
    }

    private AvlNode<T> doubleRotateLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        assert k1 != null;
        AvlNode<T> k3 = k1.right;
        assert k3 != null;
        k1.right = k3.left;
        k2.left = k3.right;
        k3.left = k1;
        k3.right = k2;
        k1.height = (byte) (Math.max(getHeight(k1.left), getHeight(k1.right)) + 1);
        k2.height = (byte) (Math.max(getHeight(k2.left), getHeight(k2.right)) + 1);
        k3.height = (byte) (Math.max(getHeight(k1), getHeight(k2)) + 1);
        return k3;
    }

    private AvlNode<T> singleRotateRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k1.right = k2;
        k2.right = k1.left;
        k2.height = (byte) (Math.max(getHeight(k2.left), getHeight(k2.right)) + 1);
        k1.height = (byte) (Math.max(getHeight(k2), getHeight(k1.right)) + 1);
        return k1;
    }

    private AvlNode<T> singleRotateLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k1.right = k2;
        k2.left = k1.right;
        k2.height = (byte) (Math.max(getHeight(k2.left), getHeight(k2.right)) + 1);
        k1.height = (byte) (Math.max(getHeight(k1.left), getHeight(k2)) + 1);
        return k1;
    }

    private byte getHeight(AvlNode<T> node) {
        return node == null ? -1 : node.height;
    }

    public void remove(T t) {
        remove(t, root);
    }

    private AvlNode<T> remove(T t, AvlNode<T> root) {
        //todo
        return null;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(AvlNode<T> root) {
        if (root == null)
            System.out.print("# ");
        else {
            System.out.print(root.data + " ");
            printTree(root.left);
            printTree(root.right);
        }
    }

    private static class AvlNode<T> {
        T data;
        AvlNode<T> left, right;
        byte height;

        AvlNode(T t, AvlNode<T> l, AvlNode<T> r) {
            data = t;
            left = l;
            right = r;
        }
    }
}
