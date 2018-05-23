//Chap04.question.32.BstCheck.java

public class BstCheck<T extends Comparable<? super T>> {
    private TreeNode<T> root;

    public boolean check() {
        return check(root);
    }

    private boolean check(TreeNode<T> node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.right == null)
            return check(node.left) && node.data.compareTo(node.left.data) >= 0;
        if (node.left == null)
            return check(node.right) && node.data.compareTo(node.right.data) < 0;
        return node.data.compareTo(node.left.data) >= 0 
                && node.data.compareTo(node.right.data) < 0
                && check(node.left) && check(node.right);
    }

    private static class TreeNode<T> {
        T data;
        TreeNode<T> left, right;
    }
}
