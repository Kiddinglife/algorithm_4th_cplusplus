//Chap04.question.33.GenericTreeRemoveLeaves.java

import java.util.List;

public class GenericTreeRemoveLeaves<T> {
    private TreeNode<T> root;

    public void removeLeaves() {
        root = removeLeaves(root);
    }

    private TreeNode<T> removeLeaves(TreeNode<T> node) {
        if (node == null) return null;
        if (node.children == null || node.children.isEmpty()) {
            node.children = null;
            node = null; //help GC
            return null;
        }
        for (int i = 0; i < node.children.size(); i++) {
            TreeNode<T> child = node.children.get(i);
            child = removeLeaves(child);
            node.children.set(i, child);
        }
        return node;
    }

    private static class TreeNode<T> {
        T data;
        List<TreeNode<T>> children;
    }
}
