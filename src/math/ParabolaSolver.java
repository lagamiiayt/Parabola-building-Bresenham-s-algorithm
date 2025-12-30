package math;

public class ParabolaSolver {

    public static Parabola fromPoints(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3) {

        double d = (x1 - x2) * (x1 - x3) * (x2 - x3);
        if (Math.abs(d) < 1e-12) {
            throw new IllegalArgumentException("Невозможно построить");
        }

        double a = (x3*(y2 - y1) + x2*(y1 - y3) + x1*(y3 - y2)) / d;
        double b = (x3*x3*(y1 - y2) + x2*x2*(y3 - y1) + x1*x1*(y2 - y3)) / d;
        double c = (x2*x3*(x2 - x3)*y1 +
                x3*x1*(x3 - x1)*y2 +
                x1*x2*(x1 - x2)*y3) / d;

        return new Parabola(a, b, c);
    }
}
