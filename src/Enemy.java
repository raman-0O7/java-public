import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Sprite {
    public Enemy(int mySpeed) {
        x = 100;
        y = 100;
        h = 100;
        w = 100;
        speed = mySpeed;
        image = new ImageIcon(Enemy
                .class.getResource("spider.gif"));
    }

    public void move() {
        y = y + speed;
        if(y>GAME_HEIGHT) {
            y = 0;
        }
    }

    public void printEnemy(Graphics pen) {
        pen.drawImage(image.getImage(), x, y , w, h, null);
        move();
    }

}
