//Chap03.question.21.PairBalancing.java

import java.util.*;

public class PairBalancing {
    public static void main(String... args) {
        Map<String, String> pascal = new HashMap<>();
        pascal.put("begin", "end");
        pascal.put("(", ")");
        pascal.put("[", "]");
        pascal.put("{", "}");

        Map<String, String> java = new HashMap<>();
        java.put("/*", "*/");
        java.put("(", ")");
        java.put("[", "]");
        java.put("{", "}");

        System.out.println(checkBalancing("begin { hello world! ( vars ) ; } really end".split(" "), pascal));
        System.out.println(checkBalancing("begin this ( { hello ) world! } really end".split(" "), pascal));

        System.out.println(checkBalancing("func { hello world! ( vars ) ; } really end".split(" "), java));
        System.out.println(checkBalancing("begin this ( /* ( { hello ) world! } */ ) really end".split(" "), java));

        System.out.println(checkBalancing("func { hello world! { ( vars ) ; } really end".split(" "), java));
    }

    /**
     * check if keywords are paired up in the tokens list. if yes, return -1; else return the location
     * where there is an error
     *
     * @param tokens list of tokens
     * @param pairs  list of keyword paris
     * @return -1 if all keywords are paired up. else the location where there is an error
     */
    public static int checkBalancing(String[] tokens, Map<String, String> pairs) {
        class StrLoc {
            String str;
            int loc;

            StrLoc(String s, int l) {
                str = s;
                loc = l;
            }
        }
        LinkedList<StrLoc> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (pairs.keySet().contains(s) && (stack.isEmpty() || !stack.peek().str.equals("/*")))
                stack.push(new StrLoc(s, i));
            else if (pairs.values().contains(s)) {
                if (!pairs.get(stack.pop().str).equals(s))
                    return i;
            }
        }
        if (!stack.isEmpty())
            return stack.peek().loc;

        return -1;
    }
}
