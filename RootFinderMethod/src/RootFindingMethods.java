public class RootFindingMethods {
    public static void main(String[] args) {
        double a = 1.0;
        double b = 2.0;
        double epsilon = 0.00001;

        System.out.println("Bisection Method:");
        double bisectionResult = bisectionMethod(a, b, epsilon);
        System.out.println("Root: " + bisectionResult);

        System.out.println("False Position Method:");
        double falsePositionResult = falsePositionMethod(a, b, epsilon);
        System.out.println("Root: " + falsePositionResult);

        System.out.println("Newton-Raphson Method:");
        double newtonRaphsonResult = newtonRaphsonMethod(a, epsilon);
        System.out.println("Root: " + newtonRaphsonResult);

        System.out.println("Secant Method:");
        double secantResult = secantMethod(a, b, epsilon);
        System.out.println("Root: " + secantResult);
    }

    public static double bisectionMethod(double a, double b, double epsilon) {
        double c;
        do {
            c = (a + b) / 2.0;
            if (function(c) * function(a) < 0) {
                b = c;
            } else {
                a = c;
            }
        } while (Math.abs(function(c)) > epsilon);
        return c;
    }

    public static double falsePositionMethod(double a, double b, double epsilon) {
        double c;
        do {
            c = (a * function(b) - b * function(a)) / (function(b) - function(a));
            if (function(c) * function(a) < 0) {
                b = c;
            } else {
                a = c;
            }
        } while (Math.abs(function(c)) > epsilon);
        return c;
    }

    public static double newtonRaphsonMethod(double x, double epsilon) {
        double x1;
        do {
            x1 = x - (function(x) / derivative(x));
            x = x1;
        } while (Math.abs(function(x)) > epsilon);
        return x;
    }

    public static double secantMethod(double x0, double x1, double epsilon) {
        double x2;
        do {
            x2 = x1 - (function(x1) * (x1 - x0)) / (function(x1) - function(x0));
            x0 = x1;
            x1 = x2;
        } while (Math.abs(function(x2)) > epsilon);
        return x2;
    }

    public static double function(double x) {
        // Define your function here
        return x * x - 4;
    }

    public static double derivative(double x) {
        // Define the derivative of your function here
        return 2 * x;
    }
}
