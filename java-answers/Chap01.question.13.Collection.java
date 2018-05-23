//Chap01.question.13.Collection.java

public class Collection<T> {
    private Object[] arr;
    private int size;
    private int capacity = 10;

    public Collection() {
        arr = new Object[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        size = 0;
    }

    public void insert(T e) {
        if (size == capacity) {
            capacity = capacity + capacity >>> 1;
            Object[] arr1 = new Object[capacity];
            System.arraycopy(arr, 0, arr1, 0, size);
            arr = arr1;
        }
        arr[size++] = e;
    }

    public boolean remove(T e) {
        if (e == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    removeAt(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (arr[i].equals(e)) {
                    removeAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    private void removeAt(int i) {
        assert 0 <= i && i < size;
        System.arraycopy(arr, i + 1, arr, i, size - i - 1);
        size--;
    }

    public boolean isPresent(T e) {
        if (e == null) {
            for (Object o : arr) {
                if (o == null) return true;
            }
            return false;
        }
        for (Object o : arr) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }
}
