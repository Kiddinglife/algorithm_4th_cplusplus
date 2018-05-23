//Chap04.question.15.BstRemoveTest.java

import java.util.Random;

public class BstRemoveTest {

}

class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryTreeNode<T> root;
    private boolean lastMethodLeft = false;

    public void remove(T t, RemoveMethod removeMethod) {
        root = remove(t, root, removeMethod);
    }

    private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> node, RemoveMethod removeMethod) {
        if (node == null) return null;
        int compareResult = t.compareTo(node.data);
        if (compareResult < 0)
            node.left = remove(t, node.left, removeMethod);
        else if (compareResult > 0)
            node.right = remove(t, node.right, removeMethod);
        else if (node.left != null && node.right != null) {
            switch (removeMethod) {
                case MAX_LEFT:
                    BinaryTreeNode<T> maxLeft = node.left;
                    while (maxLeft.right != null)
                        maxLeft = maxLeft.right;
                    node.data = maxLeft.data;
                    node.left = remove(maxLeft.data, node.left, removeMethod);
                    break;
                case MIN_RIGHT:
                    BinaryTreeNode<T> minRight = node.right;
                    while (minRight.left != null)
                        minRight = minRight.left;
                    node.data = minRight.data;
                    node.right = remove(minRight.data, node.right, removeMethod);
                    break;
                case ALTERNATE:
                    if (!lastMethodLeft) {
                        node = remove(t, node, RemoveMethod.MAX_LEFT);
                        lastMethodLeft = true;
                    } else {
                        node = remove(t, node, RemoveMethod.MIN_RIGHT);
                        lastMethodLeft = false;
                    }
                    break;
                case RANDOM:
                    if (new Random().nextBoolean())
                        node = remove(t, node, RemoveMethod.MAX_LEFT);
                    else
                        node = remove(t, node, RemoveMethod.MIN_RIGHT);
                    break;
            }
        } else
            node = node.left != null ? node.left : node.right;
        return node;
    }

    public enum RemoveMethod {
        MAX_LEFT, MIN_RIGHT, ALTERNATE, RANDOM
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
