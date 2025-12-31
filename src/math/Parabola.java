package math;

public class Parabola {

    private final double a, b, c;

    public Parabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Возвращает значение функции в точке x
    public double valueAt(double x) {
        return a * x * x + b * x + c;
    }

    // формулы Брезенхейма используют a и b
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
