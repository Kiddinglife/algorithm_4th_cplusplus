//Chap04.text.01.TreeMemoryUsageTest.java

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class TreeMemoryUsageTest {
    public static final int CHILDREN_NUMBER = 2000;

    public static void main(String... args) {
        TreeNodeDirectLinksArray.test();
        TreeNodeDirectLinksArrayPreAllocated.test();
        TreeNodeDirectLinksArrayList.test();
        TreeNodeNextSibling.test();
        TripleTree.test();
        TripleTree.test2();

    }

    public static <T extends Serializable> void printObjectSize(T t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            oos.close();
            System.out.println(t.getClass().getName() + ": " + baos.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TripleTree implements Serializable {
    int data;
    TripleTree a, b, c;

    TripleTree(int d) {
        data = d;
    }

    static void test() {
        int d = 0;
        TripleTree root = new TripleTree(d++);
        root.a = new TripleTree(d++);
        root.b = new TripleTree(d++);
        root.c = new TripleTree(d++);
        root.a.a = new TripleTree(d++);
        root.a.b = new TripleTree(d++);
        root.a.c = new TripleTree(d++);
        root.b.a = new TripleTree(d++);
        root.b.b = new TripleTree(d++);
        root.b.c = new TripleTree(d++);
        root.c.a = new TripleTree(d++);
        root.c.b = new TripleTree(d++);
        root.c.c = new TripleTree(d++);
        System.out.print(d);
        TreeMemoryUsageTest.printObjectSize(root);
    }

    static void test2() {
        int d = 0;
        TreeNodeNextSibling root = new TreeNodeNextSibling(d++);
        root.firstChild = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.firstChild = new TreeNodeNextSibling(d++);
        root.firstChild.firstChild.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.firstChild.nextSibling.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.firstChild = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.firstChild.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.firstChild.nextSibling.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.nextSibling.firstChild = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.nextSibling.firstChild.nextSibling = new TreeNodeNextSibling(d++);
        root.firstChild.nextSibling.nextSibling.firstChild.nextSibling.nextSibling = new TreeNodeNextSibling(d++);
        System.out.print(d);
        TreeMemoryUsageTest.printObjectSize(root);
    }
}

class TreeNodeNextSibling implements Serializable {
    int data;
    TreeNodeNextSibling firstChild;
    TreeNodeNextSibling nextSibling;

    TreeNodeNextSibling(int d) {
        data = d;
    }

    static void test() {
        int d = 1;
        TreeNodeNextSibling root = new TreeNodeNextSibling(d++);
        root.firstChild = new TreeNodeNextSibling(d++);
        TreeNodeNextSibling temp = root.firstChild;
        for (int i = 0; i < TreeMemoryUsageTest.CHILDREN_NUMBER - 1; i++) {
            temp.nextSibling = new TreeNodeNextSibling(d++);
            temp = temp.nextSibling;
        }

        TreeMemoryUsageTest.printObjectSize(root);
    }
}

class TreeNodeDirectLinksArray implements Serializable {
    int data;
    TreeNodeDirectLinksArray[] children;

    TreeNodeDirectLinksArray(int d) {
        data = d;
    }

    static void test() {
        int d = 1;
        TreeNodeDirectLinksArray root = new TreeNodeDirectLinksArray(d++);
        root.children = new TreeNodeDirectLinksArray[TreeMemoryUsageTest.CHILDREN_NUMBER];
        for (int i = 0; i < TreeMemoryUsageTest.CHILDREN_NUMBER; i++) {
            root.children[i] = new TreeNodeDirectLinksArray(d++);
        }

        TreeMemoryUsageTest.printObjectSize(root);
    }
}

class TreeNodeDirectLinksArrayPreAllocated implements Serializable {
    int data;
    TreeNodeDirectLinksArrayPreAllocated[] children;

    TreeNodeDirectLinksArrayPreAllocated(int d) {
        data = d;
        children = new TreeNodeDirectLinksArrayPreAllocated[TreeMemoryUsageTest.CHILDREN_NUMBER];
    }

    static void test() {
        int d = 1;
        TreeNodeDirectLinksArrayPreAllocated root = new TreeNodeDirectLinksArrayPreAllocated(d++);
        for (int i = 0; i < TreeMemoryUsageTest.CHILDREN_NUMBER; i++) {
            root.children[i] = new TreeNodeDirectLinksArrayPreAllocated(d++);
        }

        TreeMemoryUsageTest.printObjectSize(root);
    }
}

class TreeNodeDirectLinksArrayList implements Serializable {
    int data;
    ArrayList<TreeNodeDirectLinksArrayList> children;

    TreeNodeDirectLinksArrayList(int d) {
        data = d;
        children = new ArrayList<TreeNodeDirectLinksArrayList>();
    }

    static void test() {
        int d = 1;
        TreeNodeDirectLinksArrayList root = new TreeNodeDirectLinksArrayList(d++);
        for (int i = 0; i < TreeMemoryUsageTest.CHILDREN_NUMBER; i++) {
            root.children.add(new TreeNodeDirectLinksArrayList(d++));
        }

        TreeMemoryUsageTest.printObjectSize(root);
    }
}
