//Chap04.question.16.BstLazyDeletion.java

public class BstLazyDeletion<T extends Comparable<? super T>> {
    private static final int MIN_SIZE = 100;
    private BinaryTreeNode<T> root;

    //size is # of nodes not deleted
    private int size, deleteCount;

    public void insert(T t) {
        root = insert(t, root);
    }

    private BinaryTreeNode<T> insert(T t, BinaryTreeNode<T> node) {
        if (node == null) {
            size++;
            return new BinaryTreeNode<>(t, null, null);
        }
        int compareResult = t.compareTo(node.data);
        if (compareResult < 0)
            node.left = insert(t, node.left);
        else if (compareResult > 0)
            node.right = insert(t, node.right);
        else if (node.deleted) {
            node.deleted = false;
            size++;
            deleteCount--;
        }
        return node;
    }

    public void remove(T t) {
        root = remove(t, root);
        if (size > MIN_SIZE && size < deleteCount) {
            //trigger real delete
            root = removeDeletedNodes(root);
        }
    }

    private BinaryTreeNode<T> removeDeletedNodes(BinaryTreeNode<T> node) {
        if (node == null) return null;
        node.left = removeDeletedNodes(node.left);
        node.right = removeDeletedNodes(node.right);
        if (node.deleted) {
            if (node.left != null && node.right != null) {
                BinaryTreeNode<T> rightMinNode = node.right;
                BinaryTreeNode<T> rightMinNodeParent = node;
                while (rightMinNode.left != null) {
                    rightMinNodeParent = rightMinNode;
                    rightMinNode = rightMinNode.left;
                }
                node.data = rightMinNode.data;
                node.deleted = false;
                if (rightMinNodeParent == node)
                    rightMinNodeParent.right = null;
                else
                    rightMinNodeParent.left = rightMinNode.right;
            } else
                node = node.left != null ? node.left : node.right;
        }
        return node;
    }

    private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> node) {
        if (node == null) return null;
        int compareResult = t.compareTo(node.data);
        if (compareResult < 0)
            node.left = remove(t, node.left);
        else if (compareResult > 0)
            node.right = remove(t, node.right);
        else if (!node.deleted) {
            node.deleted = true;
            size--;
            deleteCount++;
        }
        return node;
    }

    public T findMin() {
        return findMin(root);
    }

    private T findMin(BinaryTreeNode<T> node) {
        if (node == null) return null;
        T leftMin = findMin(node.left);
        if (leftMin != null)
            return leftMin;
        if (!node.deleted)
            return node.data;
        return findMin(node.right);
    }

    public T findMax() {
        return findMax(root);
    }

    private T findMax(BinaryTreeNode<T> node) {
        if (node == null) return null;
        T rightMax = findMax(node.right);
        if (rightMax != null) return rightMax;
        if (!node.deleted) return node.data;
        return findMax(node.left);
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    private boolean contains(T t, BinaryTreeNode<T> node) {
        if (node == null) return false;
        int compareResult = t.compareTo(node.data);
        if (compareResult < 0)
            return contains(t, node.left);
        else if (compareResult > 0)
            return contains(t, node.right);
        else
            return !node.deleted;
    }

    private static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left, right;
        boolean deleted;

        BinaryTreeNode(T t, BinaryTreeNode<T> l, BinaryTreeNode<T> r) {
            data = t;
            left = l;
            right = r;
            deleted = false;
        }
    }
}
