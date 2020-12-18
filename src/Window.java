import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Window extends JPanel implements Runnable{
    Image image;
    Graphics graphics;
    Thread thread;
    Player player;
    GUI score;
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> bullets;
    boolean spawn = true;

    public Window(int w, int h){
        this.setPreferredSize(new Dimension(w,h));
        this.setFocusable(true);
        this.addKeyListener(new AL());
        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        player = new Player(w / 2, h - 20, 100, 20, bullets);
        score = new GUI(w,h);

        thread = new Thread(this);
        thread.start();
    }

    void spawnEnemies(){
        if(enemies.size() <= 6){
            spawn = true;
        }

        if(spawn) {
            if (enemies.size() <= 12) {
                int x = (int) (Math.random() * 301 + 50) * -1;
                int y = (int)(Math.random() * 100 + 15);
                int w = (int) (Math.random() * 30 + 25);
                enemies.add(new Enemy(x, y, w, w / 2));
            }
            else{
                spawn = false;
            }
        }
    }

    public void move(){
        player.move();

        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).move();
        }
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).move();
        }
    }

    public void checkCollision(){
        // enemy and bullet collision
        for(int i = 0; i < enemies.size(); i++){
            for(int j = 0; j < bullets.size(); j++){
                if(enemies.get(i).intersects(bullets.get(j))){
                    enemies.remove(i);
                    bullets.remove(j);
                    score.score++;
                    return;
                }
            }
        }

        // player window bounds collision

        if(player.x < 0) {
            player.x = 0;
            player.weapon.x = player.width/2 - player.weapon.width/2;
        }
        else if(player.x + player.getWidth() > this.getWidth()) {
            player.x = this.getWidth() - player.width;
            player.weapon.x = player.x + player.width/2 - player.weapon.width/2;
        }


        for(int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).y <= -50){
                bullets.remove(i);
                i--;
            }
        }

        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).x >= this.getWidth() + 200){
                enemies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void run() {
        // game loop
        long lastTime = System.nanoTime();
        double FPS = 30.0;
        double ns = 1000000000/FPS;
        double dt = 0;
        while(true){
            long now = System.nanoTime();
            dt+=(now - lastTime)/ns;
            lastTime = now;
            if(dt >= 1){
                spawnEnemies();
                move();
                checkCollision();
                repaint();
                dt--;
            }
        }
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        player.draw(g);
        for(int i = 0; i < enemies.size(); i++)
            enemies.get(i).draw(g);
        for(int i = 0; i < bullets.size(); i++)
            bullets.get(i).draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }
}
