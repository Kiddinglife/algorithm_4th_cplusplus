//Chap01.question.14.OrderedCollection.java

public class OrderedCollection<E extends Comparable<? super E>> {
    private static final int DEFAULT_CAPACITY = 10;
    private Comparable[] arr;
    private int size;
    private int capacity;

    public OrderedCollection() {
        arr = new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        size = 0;
    }

    public boolean insert(E e) { // this collection doesn't allow nulls
        if (e == null) return false;
        if (size == capacity) {
            capacity = capacity + capacity >>> 1;
            Comparable[] arr1 = new Comparable[capacity];
            System.arraycopy(arr, 0, arr1, 0, size);
            arr = arr1;
        }
        arr[size++] = e;
        return true;
    }

    public boolean remove(E e) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(e)) {
                System.arraycopy(arr, i + 1, arr, i, size - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public E findMin() {
        if (isEmpty()) return null;
        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i].compareTo(arr[minIndex]) < 0) {
                minIndex = i;
            }
        }
        return (E) arr[minIndex];
    }

    @SuppressWarnings("unchecked")
    public E findMax() {
        if (isEmpty()) return null;
        int maxIndex = 0;
        for (int i = 1; i < size; i++)
            if (arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;
        return (E) arr[maxIndex];
    }
}
