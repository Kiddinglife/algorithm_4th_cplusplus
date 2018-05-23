//Chap03.question.23.InfixPostfix.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * + - * / ^
 * ( )
 * +d -d
 */

public class InfixPostfix {
    public static void main(String... args) {
        String s1 = "1 + 2 ^ 3 - 4 * ( -3 + +4 ) + 12";
        System.out.println(s1);
        System.out.println(infix2Postfix(s1.split(" ")));
        System.out.println(evaluatePostfix(infix2Postfix(s1.split(" ")).split(" ")));
        System.out.println(postfix2Infix(infix2Postfix(s1.split(" ")).split(" ")));
    }

    public static String postfix2Infix(String[] tokens) {
        LinkedList<String> stack = new LinkedList<>();
        for (String s : tokens) {
            if (!new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^", "(", ")")).contains(s)) {
                stack.push(s);
            } else {
                if (stack.isEmpty())
                    throw new IllegalArgumentException();
                String b = stack.pop();
                if (stack.isEmpty())
                    throw new IllegalArgumentException();
                String a = stack.pop();
                stack.push("( " + a + " " + s + " " + b + " )");
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException();
        return stack.pop();
    }

    public static String infix2Postfix(String[] tokens) {
        LinkedList<String> stack = new LinkedList<>();
        ArrayList<String> r = new ArrayList<>();
        for (String s : tokens) {
            if (!new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^", "(", ")")).contains(s)) {
                r.add(s);
            } else {
                switch (s) {
                    case "(":
                        stack.push(s);
                        break;
                    case "+":
                    case "-":
                        while (!stack.isEmpty() && !stack.peek().equals("(")) {
                            r.add(stack.pop());
                        }
                        stack.push(s);
                        break;
                    case "*":
                    case "/":
                        while (!stack.isEmpty() && (stack.peek().equals("*")
                                || stack.peek().equals("/") || stack.peek().equals("^"))) {
                            r.add(stack.pop());
                        }
                        stack.push(s);
                        break;
                    case "^":
                        while (!stack.isEmpty() && stack.peek().equals("^")) {
                            r.add(stack.pop());
                        }
                        stack.push(s);
                        break;
                    case ")":
                        while (!stack.peek().equals("(")) {
                            r.add(stack.pop());
                        }
                        stack.pop();
                        break;
                }
            }
        }

        while (!stack.isEmpty())
            r.add(stack.pop());

        StringBuilder sb = new StringBuilder();
        for (String s : r) {
            sb.append(s);
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static int evaluatePostfix(String[] tokens) {
        LinkedList<String> stack = new LinkedList<>();
        for (String s : tokens) {
            if (!new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^")).contains(s)) {
                stack.push(s);
            } else {
                String b, a;
                try {
                    switch (s) {
                        case "+":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(a) + Integer.valueOf(b)));
                            break;
                        case "-":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(a) - Integer.valueOf(b)));
                            break;
                        case "*":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(a) * Integer.valueOf(b)));
                            break;
                        case "/":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf(Integer.valueOf(a) / Integer.valueOf(b)));
                            break;
                        case "^":
                            b = stack.pop();
                            a = stack.pop();
                            stack.push(String.valueOf((int) (Math.pow(Integer.valueOf(a), Integer.valueOf(b)))));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException();
        return Integer.valueOf(stack.pop());
    }
}
