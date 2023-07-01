import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.sql.Time;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements Constants {
    BufferedImage image;
    Player player;
    //Enemy enemy; // Single Enemy
    Timer timer;
    Enemy enemies [] = new Enemy[3]; // Array of Enemies


    void listenTheKeyEvent() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed = 0;
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
                    player.speed = 10;
                }
                else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
                    player.speed = -10;
                }
                // TODO Auto-generated method stub

            }
        });
    }
//    boolean checkCollision(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
//        return x1 < x4 && y1 < y4 && x3 < x2 && y3 < y2;
//    }
    boolean isCollide() {
        int imageWhiteSpace = 150;
        for(int i = 0; i<enemies.length; i++) {
            int xDistance = Math.abs(player.x - enemies[i].x);
            int yDistance = Math.abs(player.y- enemies[i].y);
            int width = Math.max(player.w, enemies[i].w);
            int height= Math.max(player.h, enemies[i].h);
            if(xDistance<=(width-imageWhiteSpace) && yDistance<=(height-imageWhiteSpace)) {
                return true;
            }
        }
        return false;
    }

    void checkCollision(Graphics g) {
        if (isCollide()) {
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Game Over", GAME_WIDTH / 2, GAME_HEIGHT / 2);
            timer.stop();
        }
    }
    void loadEnemies() {
        final int GAP = 200;
        int newX = 0;
        int speed = 5;
        for(int i = 0; i<enemies.length; i++) {
            enemies[i] = new Enemy(speed);
            speed = speed + 5;
            if(newX ==0) {
                newX = enemies[i].x;
            }
            newX = newX +  enemies[i].x  + GAP;
            enemies[i].x =  newX;
        }
    }
    void gameLoop() {
        timer = new Timer(50,(e)->{
            repaint();
        });
        timer.start();
    }

    public Board() {
        // Load Background Image
        try {
            image = ImageIO.
                    read(Board.class.getResource("gameimage.jpeg"));
            player= new Player();
            loadEnemies();
            //enemy = new Enemy();
            setFocusable(true);
            listenTheKeyEvent();
            gameLoop();

        }
        catch(Exception e) {
            System.out.println("Problem in Image....");
        }
    }

    @Override
    public void paintComponent(Graphics pen) {
        pen.drawImage(image, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        player.printPlayer(pen);
//		enemy.printEnemy(pen);
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].printEnemy(pen);
        }
//        if(checkCollision(player.x, player.y + player.h, player.x + player.w, player.y, enemies[0].x ,enemies[0].y + enemies[0].h, enemies[0].x + enemies[0].w, enemies[0].h)){
//            player.x = 100;
//            player.y = GAME_HEIGHT - player.h - player.floor;
//        }
        checkCollision(pen);
    }

}

