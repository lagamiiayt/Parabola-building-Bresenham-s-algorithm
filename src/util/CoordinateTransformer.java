package util;

// Класс, выполняющий преобразования между математическими координатами и пикселями
public class CoordinateTransformer {

    private final double X1, X2, Y1, Y2;

    public CoordinateTransformer(double X1, double X2, double Y1, double Y2) {
        this.X1 = X1;
        this.X2 = X2;
        this.Y1 = Y1;
        this.Y2 = Y2;
    }

    // math -> screen
    public int toScreenX(double x, int width) {
        double t = (x - X1) / (X2 - X1);
        return (int) Math.round(t * width);
    }

    public int toScreenY(double y, int height) {
        double t = (y - Y1) / (Y2 - Y1);
        // Инверсия Y, т.к. screen Y растёт вниз
        return (int) Math.round((1 - t) * height);
    }

    // screen -> math
    public double toMathX(int x, int width) {
        double t = x / (double) width;
        return X1 + t * (X2 - X1);
    }

    public double toMathY(int y, int height) {
        double t = 1.0 - (y / (double) height);
        return Y1 + t * (Y2 - Y1);
    }
}
