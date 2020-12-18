import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

public class Player extends Rectangle {
    int speed = 10;
    int xVelocity = 0;
    public Rectangle weapon;
    ArrayList<Bullet> bullets;
    boolean shoot = true;

    Player(int x, int y, int w, int h, ArrayList<Bullet> bullets){
        super(x, y, w, h);
        weapon = new Rectangle(x + w/2, y - h/2, w/6, h/2);
        weapon.x = weapon.x - weapon.width/2;
        this.bullets = bullets;
    }

    public void move(){
        x += xVelocity;
        weapon.x = x + width/2 - weapon.width/2;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            xVelocity = -speed;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            xVelocity = speed;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && shoot){
            bullets.add(new Bullet(weapon.x + weapon.width/2 - 3,
                    weapon.y - 5));
            shoot = false;
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            xVelocity = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            xVelocity = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            shoot = true;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.setColor(Color.blue);
        g.fillRect(weapon.x, weapon.y, weapon.width, weapon.height);
    }
}
