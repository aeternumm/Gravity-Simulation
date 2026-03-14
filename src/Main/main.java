package Main;

import javax.swing.*;
import java.awt.*;

public class main {

    public static void main(String[] args) {
        String[] planets = {
                "Earth",
                "Mars",
                "Jupiter",
                "Saturn",
                "Uranus",
                "Neptune",
                "Mercury",
                "Venus"
        };
        int choice = JOptionPane.showOptionDialog(null, "Select the first planet to simulate:", "Planet Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
        int choice1 = JOptionPane.showOptionDialog(null, "Select the second planet to simulate:", "Planet Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
        // Get user input for distance between planets
        String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the planets:");
        int distance = Integer.parseInt(input2);

        SystemPanel sys = new SystemPanel(distance, choice, choice1);
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Gravitation Simulator");
        window.add(sys);
        window.pack();
        window.setLocationRelativeTo(null);
        sys.startGameThread();
        window.setVisible(true);
    }
}
