import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite  {
    int floor = 100;
    public Player() {
        x = 100;
        h = 200;
        w = 200;
        speed = 0;
        y = GAME_HEIGHT - h - floor;
        image = new ImageIcon(Player
                .class.getResource("player.gif"));
    }
    public void move() {
        x = x + speed;
    }
    public void printPlayer(Graphics pen) {
        pen.drawImage(image.getImage(), x, y , w, h, null);
        move();
    }
}
