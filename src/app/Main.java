package app;

import ui.DrawCanvas;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        setTitle("Парабола");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        add(new DrawCanvas());
        setVisible(true);
    }

    public static void main(String[] args) {
        // Запуск UI в Event Dispatch Thread
        SwingUtilities.invokeLater(Main::new);
    }
}
