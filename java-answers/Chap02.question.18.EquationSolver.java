//Chap02.question.18.EquationSolver.java

interface Function {
    double f(double x);
}

public class Solution {
    private static final double THRESHOLD = 1e-16;

    public static void main(String... args) {
        System.out.println(new Solution().solve(-1000.0, 5.0, new Function() {
            @Override
            public double f(double x) {
                return (3 * x - 0.5) / (x * x + 1.0);
            }
        }));
    }

    public double solve(double low, double high, Function function) {
        if (function.f(low) * function.f(high) >= 0)
            throw new IllegalArgumentException("illegal args");

        if (function.f(low) > 0.0) {
            double t = low;
            low = high;
            high = t;
        }

        while (Math.abs(low - high) > THRESHOLD) {
            double mid = (low + high) / 2;
            if (function.f(mid) == 0.0) return mid;
            else if (function.f(mid) > 0.0)
                high = mid;
            else
                low = mid;
        }

        return low;
    }
}
