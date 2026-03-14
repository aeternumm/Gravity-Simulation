package Main;

import Planets.*;

import javax.swing.*;
import java.awt.*;

public class SystemPanel extends JPanel implements Runnable {
    Planet pl1;
    Planet pl2;
    final int FPS = 60; //Frames per second
    public SystemPanel (double distance, int choice, int choice1) {
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(1000, 800));
        pl1 = createPlanet(choice, 200, 400);
        pl2 = createPlanet(choice1, 200 + distance, 400);
    }

    private Planet createPlanet(int type, double x, double y) {
         switch (type) {
             case 0:
                 return new Earth(x, y);
             case 1:
                 return new Mars(x, y);
             case 2:
                 return new Jupiter(x, y); // Jupiter
             case 3:
                 return new Saturn(x, y); // Saturn
             case 4:
                 return new Uranus(x, y); // Uranus
             case 5:
                 return new Neptune(x, y); // Neptune
                case 6:
                    return new Mercury(x, y); // Mercury
                case 7:
                    return new Venus(x, y); // Venus
             default:
                 return new Earth(x, y);
         }
    }

    //Initialize
    Thread gameThread;

    //Apply gravity between the two planets
    public void applyGravity(Planet pl1, Planet pl2) {
        double G = 2; // Gravitational constant
        double distanceX = pl1.x - pl2.x;
        double distanceY = pl1.y - pl2.y;
        //Pythagorean theorem to calculate the distance between the two planets
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        double force = G * (pl2.mass * pl1.mass) / (distance * distance);
        double acceleration2 = force / pl2.mass;
        double acceleration1 = force / pl1.mass;
        pl2.velx += acceleration2 * (distanceX / distance);
        pl2.vely += acceleration2 * (distanceY / distance);
        pl1.velx -= acceleration1 * (distanceX / distance);
        pl1.vely -= acceleration1 * (distanceY / distance);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
        //Update the position of the planets
     public void update() {
        pl1.update();
        pl2.update();
     }
     //Draw the Screen
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g;
         //Draw Here
        pl1.draw(g2);
        pl2.draw(g2);
        g2.dispose();
     }

     //Run the Game
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // 0..16666 Seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                //Objekt position is updating
                applyGravity(pl1, pl2);
                pl1.update();
                pl2.update();
                //Draw the Screen with updated info
                repaint();
                delta--;
                drawCount++;
            }


        }
    }
}
