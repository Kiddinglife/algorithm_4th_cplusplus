//Chap04.question.47.IsomorphicTrees.java

public class IsomorphicTrees {
    public static class BinaryTree<T> {
        private Node<T> root;

        public static <T> boolean isomorphic(Node<T> a, Node<T> b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            if (!a.data.equals(b.data)) return false;
            return isomorphic(a.left, b.left) && isomorphic(a.right, b.right)
                    || isomorphic(a.left, b.right) && isomorphic(a.right, b.left);
        }

        private static class Node<T> {
            T data;
            Node<T> left, right;
        }
    }
}
