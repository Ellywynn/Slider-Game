import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    final int WIDTH = 800;
    final int HEIGHT = 600;

    Window window;

    Game(){
        window = new Window(WIDTH, HEIGHT);
        this.add(window);
        this.setTitle("Game");
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
