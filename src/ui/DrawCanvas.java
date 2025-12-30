package ui;

import math.*;
import render.ParabolaRenderer;
import util.CoordinateTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawCanvas extends JPanel {

    private final CoordinateTransformer tr =
            new CoordinateTransformer(-5, 10, -5, 10);

    private final List<Point> points = new ArrayList<>();
    private Parabola parabola = null;

    public DrawCanvas() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });
    }

    private void handleClick(int x, int y) {
        if (points.size() == 3) {
            points.clear();
            parabola = null;
        }

        points.add(new Point(x, y));

        if (points.size() == 3) {
            try {
                double x1 = tr.toMathX(points.get(0).x, getWidth());
                double y1 = tr.toMathY(points.get(0).y, getHeight());
                double x2 = tr.toMathX(points.get(1).x, getWidth());
                double y2 = tr.toMathY(points.get(1).y, getHeight());
                double x3 = tr.toMathX(points.get(2).x, getWidth());
                double y3 = tr.toMathY(points.get(2).y, getHeight());

                parabola = ParabolaSolver.fromPoints(x1, y1, x2, y2, x3, y3);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawAxes(g2);
        ParabolaRenderer.draw(g2, parabola, tr, getWidth(), getHeight());

        g2.setColor(Color.RED);
        for (Point p : points) {
            g2.fillOval(p.x - 4, p.y - 4, 8, 8);
        }
    }

    private void drawAxes(Graphics g) {
        int x0 = tr.toScreenX(0, getWidth());
        int y0 = tr.toScreenY(0, getHeight());
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, y0, getWidth(), y0);
        g.drawLine(x0, 0, x0, getHeight());
    }
}
