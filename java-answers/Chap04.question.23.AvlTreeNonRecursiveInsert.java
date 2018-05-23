//Chap04.question.23.AvlTreeNonRecursiveInsert.java

import java.util.ArrayDeque;

public class AvlTree<T extends Comparable<? super T>> {
    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }

    public void insert(T t) {
        insert(t, root);
    }

    public void insertNonRecursive(T t) {
        AvlNode<T> node = root;
        ArrayDeque<AvlNode<T>> stack = new ArrayDeque<>();
        while (node != null) {
            if (t.compareTo(node.data) < 0) {
                stack.push(node);
                node = node.left;
            } else if (t.compareTo(node.data) > 0) {
                stack.push(node);
                node = node.right;
            } else {
                return;
            }
        }
        node = new AvlNode<>(t, null, null);
        node.height = 0;
        boolean rotated = false;
        while (!stack.isEmpty()) {
            AvlNode<T> parent = stack.pop();
            if (node.data.compareTo(parent.data) < 0)
                parent.left = node;
            else {
                assert node.data.compareTo(parent.data) != 0;
                parent.right = node;
            }
            if (!rotated && getHeight(parent.left) - getHeight(parent.right) == 2) {
                if (t.compareTo(node.data) < 0)
                    parent = singleRotateLeftChild(parent);
                else {
                    assert t.compareTo(node.data) != 0;
                    parent = doubleRotateLeftChild(parent);
                }
                rotated = true;
            } else if (!rotated && getHeight(parent.right) - getHeight(parent.left) == 2) {
                if (t.compareTo(node.data) < 0)
                    parent = doubleRotateRightChild(parent);
                else {
                    assert t.compareTo(node.data) != 0;
                    parent = singleRotateRightChild(parent);
                }
                rotated = true;
            }
            node = parent;
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }

        assert node == root;
    }

    private AvlNode<T> insert(T t, AvlNode<T> node) {
        if (node == null) return new AvlNode<>(t, null, null);
        int compareResult = t.compareTo(node.data);
        if (compareResult < 0) {
            node.left = insert(t, node.left);
            if (getHeight(node.left) - getHeight(node.right) == 2) {
                if (t.compareTo(node.left.data) < 0)
                    node = singleRotateLeftChild(node);
                else
                    node = doubleRotateLeftChild(node);
            }
        } else if (compareResult > 0) {
            node.right = insert(t, node.right);
            if (getHeight(node.right) - getHeight(node.left) == 2) {
                if (t.compareTo(node.right.data) < 0)
                    node = doubleRotateRightChild(node);
                else
                    node = singleRotateRightChild(node);
            }
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node;
    }

    private AvlNode<T> doubleRotateRightChild(AvlNode<T> k2) {
        k2.right = singleRotateLeftChild(k2.right);
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
        k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        k3.height = Math.max(getHeight(k1), getHeight(k2)) + 1;
        return k3;
    }

    private AvlNode<T> singleRotateRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k1.right = k2;
        k2.right = k1.left;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        k1.height = Math.max(getHeight(k2), getHeight(k1.right)) + 1;
        return k1;
    }

    private AvlNode<T> singleRotateLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k1.right = k2;
        k2.left = k1.right;
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        k1.height = Math.max(getHeight(k1.left), getHeight(k2)) + 1;
        return k1;
    }

    private int getHeight(AvlNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private static class AvlNode<T> {
        T data;
        AvlNode<T> left, right;
        int height;

        AvlNode(T t, AvlNode<T> l, AvlNode<T> r) {
            data = t;
            left = l;
            right = r;
        }
    }
}
