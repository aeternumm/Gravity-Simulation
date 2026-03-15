package Main;

import Planets.*;
import Stars.Sirius;
import Stars.Sun;

import javax.swing.*;
import java.awt.*;

public class SystemPanel extends JPanel implements Runnable {
    int NumPlanets;
    boolean exploded = false;
    //1 for planet-planet, 0 for star-planet
    int SimulationMode;
    double leapAmount = 0.01;
    double offsetX, offsetY;
    Planet star;
    Planet pl1;
    Planet pl2;
    Planet pl3;
    int ScreenWidth = 1200;
    int ScreenHeight = 1000;

    final int FPS = 60; //Frames per second

    public SystemPanel (double distance1, double distance2, double distance3, int velocity, int star, int choice1, int choice2, int choice3,
                        int simulateChoice, int NumPlanets) {
        this.NumPlanets = NumPlanets;
        this.SimulationMode = simulateChoice;

        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));

        if (simulateChoice == 1) {
            pl1 = createPlanet(choice1, 200, 400);
            pl2 = createPlanet(choice2, 200 + distance1, 400);
        } else if (simulateChoice == 0) {
            this.star = createStar(star, 500, 400);
            if (NumPlanets == 1) {
                //Planet
                pl1 = createPlanet(choice1, 500 , 400 + distance1);
                pl1.velx = velocity;
            } else if (NumPlanets == 2) {
                //Planet 1
                pl1 = createPlanet(choice1, 500 , 400 + distance1);
                pl1.velx = velocity;
                //Planet 2
                pl2 = createPlanet(choice2, 500 + distance2, 400 );
                pl2.vely = velocity;
            } else if (NumPlanets == 3) {
                //Planet 1
                pl1 = createPlanet(choice1, 500, 400 + distance1);
                pl1.velx = velocity;
                //Planet 2
                pl2 = createPlanet(choice2, 500 + distance2, 400 );
                pl2.vely = velocity;
                //Planet 3
                pl3 = createPlanet(choice3, 500 , 400 - distance3);
                pl3.vely = -velocity;
            }

        }
    }

    private Planet createStar(int choice, double x, double y) {
        return switch (choice) {
            case 0 -> new Sun(x, (int) y); // Sun
            case 1 -> new Sirius(x, y); // Sirius
            default -> new Sun(x, (int) y);
        };

    }

    private Planet createPlanet(int type, double x, double y) {
        return switch (type) {
            case 0 -> new Earth(x, y);
            case 1 -> new Mars(x, y);
            case 2 -> new Jupiter(x, y); // Jupiter
            case 3 -> new Saturn(x, y); // Saturn
            case 4 -> new Uranus(x, y); // Uranus
            case 5 -> new Neptune(x, y); // Neptune
            case 6 -> new Mercury(x, y); // Mercury
            case 7 -> new Venus(x, y); // Venus
            default -> new Earth(x, y);
        };
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

    //Draw the Screen
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g;
         //Draw Here
         g2.translate(offsetX, offsetY);
         if (SimulationMode == 0) {
             star.draw(g2);
             if (NumPlanets == 1) {
                 pl1.draw(g2);
             } else if (NumPlanets == 2) {
                 pl1.draw(g2);
                 pl2.draw(g2);
             } else if (NumPlanets == 3) {
                 pl1.draw(g2);
                 pl2.draw(g2);
                 pl3.draw(g2);
             }
         }else if (SimulationMode == 1) {
             pl1.draw(g2);
             pl2.draw(g2);
         }

         if (exploded) {
             g2.setColor(Color.WHITE);
             g2.setFont(new Font("Arial", Font.BOLD, 50));
             String text = "EXPLOSION";
             int StringWidth = g2.getFontMetrics().stringWidth(text);
             g2.drawString(text, (ScreenWidth / 2) - (StringWidth / 2), ScreenHeight / 2);
         }
         g2.dispose();
     }

     //Run the Game
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // 0..16666 Seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                if (!exploded){
                    double dx = star.x - pl1.x;
                    double dy = star.y - pl1.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < star.radius + pl1.radius) {
                        exploded = true;
                    }
                    applyGravity(star, pl1);
                    star.update();
                    pl1.update();
                }
                //Objekt position is updating

                if (SimulationMode == 0) {
                    //If it's a star-planet simulation, we want to keep the star in the center of the screen
                    double targetX = (getWidth() / 2) - star.x;
                    double targetY = (getHeight() / 2) - star.y;
                    offsetX += (targetX - offsetX) * leapAmount;
                    offsetY += (targetY - offsetY) * leapAmount;
                }
                //Draw the Screen with updated info
                repaint();
                delta--;
            }


        }
    }
}
