package render;

import java.awt.*;
import math.Parabola;
import util.CoordinateTransformer;

// Содержит только код отрисовки
public class ParabolaRenderer {

    public static void draw(
            Graphics g,
            Parabola parabola,
            CoordinateTransformer tr,
            int width,
            int height) {

        if (parabola == null) return;

        g.setColor(Color.BLUE);

        double dx = (trX2(tr) - trX1(tr)) / (double) width;
        double x_m = tr.toMathX(0, width);

        double y = parabola.valueAt(x_m);
        double dy = dx * (2.0 * parabola.getA() * x_m + parabola.getA() * dx + parabola.getB());
        double d2 = 2.0 * parabola.getA() * dx * dx;

        int prevX = Integer.MIN_VALUE;
        int prevY = Integer.MIN_VALUE;

        for (int sx = 0; sx < width; sx++) {
            int sy = tr.toScreenY(y, height);

            if (prevX != Integer.MIN_VALUE) {
                // Линия между соседними пикселями — сглаживание кривой
                g.drawLine(prevX, prevY, sx, sy);
            } else {
                g.fillRect(sx, sy, 1, 1);
            }

            prevX = sx;
            prevY = sy;

            // Инкрементальная схема Брезенхейма
            y += dy;
            dy += d2;
            x_m += dx;
        }
    }

    // возвращают границы math X в единичном масштабе
    private static double trX1(CoordinateTransformer tr) {
        return tr.toMathX(0, 1);
    }

    private static double trX2(CoordinateTransformer tr) {
        return tr.toMathX(1, 1);
    }
}
