import java.awt.*;

public class GUI extends Rectangle {
    public int score = 0;
    int window_w;
    int window_h;

    GUI(int w, int h){
        window_w = w;
        window_h = h;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 20));

        g.drawString("Врагов убито: " + String.valueOf(score), 0,20);
    }
}
