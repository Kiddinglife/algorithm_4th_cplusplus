//Chap03.question.22.PostfixExpressionEvaluator.java

import java.util.ArrayDeque;

public class PostfixExpressionEvaluator {
    public static double evaluate(String[] tokens) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (Character.isDigit(s.charAt(0)))
                stack.push(s);
            else {
                try {
                    String a, b;
                    switch (s) {
                        case "+":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Double.valueOf(a) + Double.valueOf(b)));
                            break;
                        case "-":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Double.valueOf(a) - Double.valueOf(b)));
                            break;
                        case "*":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Double.valueOf(a) * Double.valueOf(b)));
                            break;
                        case "/":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Double.valueOf(a) / Double.valueOf(b)));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException();

        return Double.valueOf(stack.pop());
    }

    public static void main(String... args) {
        System.out.println(evaluate("3 4 + 2 * 4 * 2 / 2 / 10 -".split(" ")));
    }
}
