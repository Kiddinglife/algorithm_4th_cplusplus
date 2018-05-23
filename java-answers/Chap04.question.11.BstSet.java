import java.util.*;
import java.util.Collection;

public class BstSet<T extends Comparable<? super T>> implements Set<T> {

    private int size;
    private BstNode root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return contains0(root, o);
    }

    @SuppressWarnings("unchecked")
    private boolean contains0(BstNode node, Object o) {
        if (node == null || o == null)
            return false;
        try {
            int compareResult = node.data.compareTo((T) o);
            if (compareResult < 0)
                return contains0(node.left, o);
            else if (compareResult > 0)
                return contains0(node.right, o);
            else
                return true;
        } catch (ClassCastException cce) {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int nextIndex = 0;
            private BstNode nextNode;
            private BstNode prev;

            //go to first node when initialized
            {
                nextNode = root;
                while (nextNode != null && nextNode.left != null)
                    nextNode = nextNode.left;
            }

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                prev = nextNode;
                nextIndex++;
                if (nextNode.right != null) {
                    nextNode = nextNode.right;
                    while (nextNode.left != null)
                        nextNode = nextNode.left;
                } else if (nextNode.parent == null) {
                    return prev.data;
                } else if (nextNode.parent.left == nextNode.left) {
                    nextNode = nextNode.parent;
                } else {
                    BstNode p = nextNode.parent;
                    while (p.parent != null && p.parent.right == p) {
                        nextNode = p;
                        p = p.parent;
                    }
                }
                return prev.data;
            }

            @Override
            public void remove() {
                if (nextIndex == 0)
                    throw new ConcurrentModificationException();
                BstSet.this.remove(prev);
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        Iterator<T> iterator = iterator();
        for (int i = 0; i < size; i++) {
            objects[i] = iterator.next();
        }
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] r = a.length >= size ? a :
                (T1[]) java.lang.reflect.Array.newInstance(
                        a.getClass().getComponentType(), size);
        Iterator<T> iterator = iterator();
        for (int i = 0; i < size; i++) {
            r[i] = (T1) iterator.next();
        }
        return r;
    }

    @Override
    public boolean add(T t) {
        if (t == null)
            return false;
        int oldSize = size;
        root = add0(root, t);
        return oldSize < size;
    }

    private BstNode add0(BstNode node, T t) {
        assert t != null : "t should not be null";

        if (node == null) {
            size++;
            return new BstNode(t);
        }
        int compareResult = node.data.compareTo(t);
        if (compareResult < 0) {
            node.left = add0(node.left, t);
            node.left.parent = node;
        } else if (compareResult > 0) {
            node.right = add0(node.right, t);
            node.right.parent = node;
        }
        return node;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        int oldSize = size;
        remove0(root, o);
        return oldSize > size;
    }

    @SuppressWarnings("unchecked")
    private BstNode remove0(BstNode node, Object o) {
        assert o != null : "o shouldn't be null";

        if (node == null) return null;

        try {
            int compareResult = node.data.compareTo((T) o);
            if (compareResult < 0) {
                node.left = remove0(node.left, o);
                if (node.left != null)
                    node.left.parent = node;
            } else if (compareResult > 0) {
                node.right = remove0(node.right, o);
                if (node.right != null)
                    node.right.parent = node;
            } else {
                //found and remove
                size--;
                if (node.left == null && node.right == null)
                    //delete leaf node
                    node = null;
                else if (node.left == null) {
                    //bypass node with one child
                    node.right.parent = node.parent;
                    node = node.right;
                } else if (node.right == null) {
                    node.left.parent = node.parent;
                    node = node.left;
                } else {
                    //replace with successor and remove successor
                    BstNode successor = node.right;
                    while (successor.left != null)
                        successor = successor.left;
                    node.data = successor.data;
                    node.right = remove0(node.right, successor.data);
                    if (node.right != null)
                        node.right.parent = node;
                }
            }
        } catch (ClassCastException cce) {
            return node;
        }
        return node;
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        if (c == null) return false;
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null)
            return false;
        int oldSize = size;
        for (Object o : c) {
            add((T) o);
        }
        return size > oldSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) return false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (!c.contains(t))
                iterator.remove();
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) return false;
        int oldSize = size;
        for (Object o : c) {
            remove(o);
        }
        return oldSize > size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    private class BstNode {
        T data;
        BstNode parent, left, right;

        BstNode(T t) {
            data = t;
        }
    }
}
