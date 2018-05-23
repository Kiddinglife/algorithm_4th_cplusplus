//Chap04.question.31.BinaryTreeCount.java

public class BinaryTreeCount<T> {
    private TreeNode<T> root;

    public int nodeCount() {
        return nodeCount(root);
    }

    private int nodeCount(TreeNode<T> node) {
        if (node == null) return 0;
        return nodeCount(node.left) + nodeCount(node.right) + 1;
    }

    public int leafCount() {
        return leafCount(root);
    }

    private int leafCount(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null)
            return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    public int fullNodeCount() {
        return fullNodeCount(root);
    }

    private int fullNodeCount(TreeNode<T> node) {
        if (node == null) return 0;
        int sum = fullNodeCount(node.left) + fullNodeCount(node.right);
        if (node.left != null && node.right != null)
            sum++;
        return sum;
    }

    private static class TreeNode<T> {
        T data;
        TreeNode<T> left, right;
    }
}
