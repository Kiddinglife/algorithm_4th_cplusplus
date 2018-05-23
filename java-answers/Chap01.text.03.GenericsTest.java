//Chap01.text.03.GenericsTest.java

import java.util.*;

public class Solution<Type> {

    /**
     * limits of generic types
     *
     * T<int>
     *
     * instanceof (casting will generate a warning, even successful
     *
     * not allowed in static context and static methods
     *
     * cannot instantiate
     *
     * cannot instantiate a generic type array
     *
     */

    public static void main(String[] args) {
        //Type t;
    }


    // so that any instance of Shape can be put into shapes
    public double totalArea(Collection<? extends Shape> shapes) {
        double area = 0.0;
        for (Shape shape : shapes) {
            if (shape != null)
                area += shape.area;
        }
        return area;
    }

    // doesn't work if you want to put a square into shapes
    public double totalArea2(Collection<Shape> shapes) {
        double area = 0.0;
        for (Shape s : shapes) if (s != null) area += s.area;
        return area;
    }

    // public static <T> T findMax(T[] arr) { //doesn't work because T might not have compareTo
    public static <T extends Comparable<? super T>> T findMax(T[] arr) {
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }
}


// ordinary generic class
class Map<K, V> {
    K key;
    V value;

    void setKey(K k) {
        key = k;
    }

    V getValue() {
        return value;
    }
}

class Shape {
    double area;
}

class Square extends Shape {

}
