//Chap01.question.15.Rectangle.java

import java.util.Comparator;

public class Rectangle {
    private double length;
    private double width;

    public Rectangle(double l, double w) {
        length = l;
        width = w;
    }

    public static void main(String... args) {
        Rectangle[] arr = new Rectangle[]{
                new Rectangle(1.0, 2.0),
                new Rectangle(2.1, 3.4),
                new Rectangle(100.0, 0.01),
                new Rectangle(5.0, 5.0)
        };

        Rectangle maxAreaRec = findMax(arr, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return Double.compare(o1.getLength() * o1.getWidth(), o2.getLength() * o2.getWidth());
            }
        });

        Rectangle maxPerimeterRec = findMax(arr, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return Double.compare(o1.getLength() + o1.getWidth(), o2.getLength() + o2.getWidth());
            }
        });

        System.out.println(maxAreaRec + "has max area.");
        System.out.println(maxPerimeterRec + "has max perimeter.");
    }

    public static Rectangle findMax(Rectangle[] arr, Comparator<? super Rectangle> comparator) {
        if (arr.length == 0) return null;
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (comparator.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "(length=" + length + ", width=" + width + ")";
    }
}
