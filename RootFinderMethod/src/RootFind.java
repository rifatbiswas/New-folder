import java.util.Scanner;
import java.util.function.Function;

public class RootFind {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0;

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Exiting the program...");
                break;
            }

            System.out.print("Enter the left endpoint (a): ");
            double a = scanner.nextDouble();

            System.out.print("Enter the right endpoint (b): ");
            double b = scanner.nextDouble();

            System.out.print("Enter the tolerance: ");
            double tolerance = scanner.nextDouble();

            System.out.print("Enter the maximum number of iterations: ");
            int maxIterations = scanner.nextInt();

           Function<Double, Double> function = RootFind::computeFunction; // Replace `computeFunction(x)` with your own function

            switch (choice) {
                case 1:
                    result = bisectionMethod(function, a, b, tolerance, maxIterations);
                    break;
                case 2:
                    result = falsePositionMethod(function, a, b, tolerance, maxIterations);
                    break;
                case 3:
                    result = newtonsRaphsonMethod(function, a, tolerance, maxIterations);
                    break;
                case 4:
                    result = secantMethod(function, a, b, tolerance, maxIterations);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            if (Double.isNaN(result)) {
                System.out.println("No root found within the given tolerance and maximum iterations.");
            } else {
                System.out.println("The root is approximately: " + result);
            }
        }

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("Root-Finding Methods:");
        System.out.println("1. Bisection Method");
        System.out.println("2. False Position Method");
        System.out.println("3. Newton's Raphson Method");
        System.out.println("4. Secant Method");
        System.out.println("5. Exit");
        System.out.print("Choose a method: ");
    }

    public static double bisectionMethod(Function<Double, Double> function, double a, double b, double tolerance, int maxIterations) {
        double fa = function.apply(a);
        double fb = function.apply(b);

        if (fa * fb >= 0) {
            return Double.NaN; // No root found
        }

        for (int i = 0; i < maxIterations; i++) {
            double c = (a + b) / 2;
            double fc = function.apply(c);

            if (Math.abs(fc) < tolerance || (b - a) / 2 < tolerance) {
                return c; // Root found within the tolerance
            }

            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        return Double.NaN; // Maximum iterations reached without finding a root
    }

    public static double falsePositionMethod(Function<Double, Double> function, double a, double b, double tolerance, int maxIterations) {
        double fa = function.apply(a);
        double fb = function.apply(b);

        if (fa * fb >= 0) {
            return Double.NaN; // No root found
        }

        for (int i = 0; i < maxIterations; i++) {
            double c = (a * fb - b * fa) / (fb - fa);
            double fc = function.apply(c);

            if (Math.abs(fc) < tolerance || (b - a) / 2 < tolerance) {
                return c; // Root found within the tolerance
            }

            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        return Double.NaN; // Maximum iterations reached without finding a root
    }

    public static double newtonsRaphsonMethod(Function<Double, Double> function, double x0, double tolerance, int maxIterations) {
        double x = x0;

        for (int i = 0; i < maxIterations; i++) {
            double fx = function.apply(x);
            double derivative = (function.apply(x + tolerance) - fx) / tolerance;
            double delta = fx / derivative;

            if (Math.abs(delta) < tolerance) {
                return x; // Root found within the tolerance
            }

            x -= delta;
        }

        return Double.NaN; // Maximum iterations reached without finding a root
    }

    public static double secantMethod(Function<Double, Double> function, double x0, double x1, double tolerance, int maxIterations) {
        double x = x1;
        double xPrev = x0;

        for (int i = 0; i < maxIterations; i++) {
            double fx = function.apply(x);
            double fxPrev = function.apply(xPrev);
            double delta = (fx * (x - xPrev)) / (fx - fxPrev);

            if (Math.abs(delta) < tolerance) {
                return x; // Root found within the tolerance
            }

            xPrev = x;
            x -= delta;
        }

        return Double.NaN; // Maximum iterations reached without finding a root
    }

    public static double computeFunction(double x) {
        // Replace this method with your own function
        return x * x - 4; // Example: f(x) = x^2 - 4
    }
}