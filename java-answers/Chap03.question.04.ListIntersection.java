//Chap03.question.04.ListIntersection.java

import java.util.*;

public class Solution {

    public static void main(String... args) {
        ArrayList<Integer> L1 = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 7, 9));
        ArrayList<Integer> L2 = new ArrayList<>(Arrays.asList(2, 3, 5, 8, 9));
        System.out.print(intersect(L1, L2));
    }

    public static List<Integer> intersect(List<Integer> L1, List<Integer> L2) {
        List<Integer> L3 = new ArrayList<>();
        Iterator iterator1 = L1.iterator();
        Iterator iterator2 = L2.iterator();
        if (!iterator2.hasNext()) return L3;
        int i2 = (Integer) iterator2.next();
        while (iterator1.hasNext()) {
            int i1 = (Integer) iterator1.next();
            if (i1 == i2) {
                L3.add(i1);
            } else if (i1 > i2) {
                while (i1 > i2 && iterator2.hasNext()) {
                    i2 = (Integer) iterator2.next();
                }
                if (i1 == i2)
                    L3.add(i1);
            }
        }
        return L3;
    }
}
