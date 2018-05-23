//Chap01.text.04.FunctionObject.java

import java.util.*;

public class Solution {
    public static void main(String... args) {
        Point[] points = new Point[]{new Point(1, 2), new Point(10, 5), new Point(2, 4), new Point(1, 7)};


        Point mostDistantPoint = findMostDistantPoint(points, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Point p1 = (Point) o1;
                Point p2 = (Point) o2;
                return Double.compare(Math.sqrt(p1.x * p1.x + p1.y * p1.y), Math.sqrt(p2.x * p2.x + p2.y * p2.y));
            }
        });

        System.out.println("most distant point: " + mostDistantPoint);

        Arrays.sort(points, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Point p1 = (Point) o1;
                Point p2 = (Point) o2;
                return Double.compare(Math.sqrt(p1.x * p1.x + p1.y * p1.y), Math.sqrt(p2.x * p2.x + p2.y * p2.y));
            }
        });

        System.out.print("sorted from near to far: " + Arrays.toString(points));
    }

    public static Point findMostDistantPoint(Point[] points, Comparator cmp) {
        int mostDistantIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (cmp.compare(points[i], points[mostDistantIndex]) > 0)
                mostDistantIndex = i;
        }
        return points[mostDistantIndex];
    }
}

class Point {
    int x;
    int y;

    Point(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ")";
    }
}
