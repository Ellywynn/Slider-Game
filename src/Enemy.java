import java.awt.*;

public class Enemy extends Rectangle {
    int speed;
    Color color;

    Enemy(int x, int y, int w, int h){
        super(x,y,w,h);
        color = new Color((int)(Math.random() * 256),
                (int)(Math.random() * 256),(int)(Math.random() * 256));
        speed = (int)(Math.random() * 7 + 2);
    }

    public void move(){
        x += speed;
    }

    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }
}
