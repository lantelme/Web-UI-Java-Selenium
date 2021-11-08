package Lesson4.HW4;

public class Function {
    public static double functionTriangleSquare(double a, double b, double c) throws Exception {
        if (a <= 0 || b <= 0 || c <= 0) throw new Exception();
        double semiPerimeter = (a + b + c) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c));
    }
}