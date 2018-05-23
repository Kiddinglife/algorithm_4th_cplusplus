//Chap04.question.35.MinAvl.java

import java.util.Random;

public class MinAvl {
    public static class AvlTree {
        private Node root;

        public static Node generateMinNodeAvlTree(int height) {
            if (height <= 0) return null;
            Node node=new Node();
            if(height==1) return node;
            Node n1 = generateMinNodeAvlTree(height-1);
            Node n2 = generateMinNodeAvlTree(height-2);
            if(new Random().nextBoolean()) {
                node.left=n1;
                node.right=n2;
            }else{
                node.right=n1;
                node.left=n2;
            }
            return node;
        }

        private static class Node {
            Node left, right;
        }
    }
}
