//Chap03.question.25.MinStack.java

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MinStack<T extends Comparable<? super T>> {
    private ArrayList<Pair> stack;

    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(T t) {
        if (stack.isEmpty()) {
            stack.add(new Pair(0, t));
        } else {
            Pair pair;
            int lastMinIndex = stack.get(stack.size() - 1).minIndex;
            T lastMin = stack.get(lastMinIndex).value;
            if (lastMin.compareTo(t) < 0) {
                stack.add(new Pair(lastMinIndex, t));
            } else {
                stack.add(new Pair(stack.size(), t));
            }
        }
    }

    public T pop() {
        if (stack.isEmpty())
            throw new EmptyStackException();
        T t = stack.get(stack.size() - 1).value;
        stack.remove(stack.size() - 1);
        return t;
    }

    public T findMin() {
        if (stack.isEmpty())
            throw new EmptyStackException();
        return stack.get(stack.get(stack.size() - 1).minIndex).value;
    }

    private class Pair {
        T value;
        int minIndex;

        Pair(int i, T t) {
            minIndex = i;
            value = t;
        }
    }
}
