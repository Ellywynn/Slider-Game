import java.awt.*;

public class Bullet extends Rectangle {
    int speed = 10;
    Color color;

    Bullet(int x, int y){
        super(x,y,6,15);
        color = new Color((int)(Math.random() * 256),
                (int)(Math.random() * 256),(int)(Math.random() * 256));
    }

    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }

    public void move(){
        this.y -= speed;
    }
}
