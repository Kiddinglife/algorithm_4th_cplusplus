//Chap04.question.46.SimilarTrees.java

public class SimilarTrees {
    public static class BinaryTree<T> {
        private static class Node<T> {
            T data;
            Node<T> left, right;
        }
        private Node<T> root;
        public static <T> boolean checkSimilarity(Node<T> node1, Node<T> node2) {
            return node1 == null && node2 == null 
                    || !(node1 == null || node2 == null) 
                    && checkSimilarity(node1.left, node2.left) 
                    && checkSimilarity(node1.right, node2.right);
        }
    }
}
