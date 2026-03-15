package Main;

import javax.swing.*;

public class main {

    public static void main(String[] args) {
        //Default values for the simulation
        int velocity = 0;
        int choice = 0;
        int choice1 = 1;
        int distance = 200;
        //
        String[] SolarSystemOptions = {
                "Sun",
                "Sirius"
        };
        // Get user input for planet selection
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
        // Get user input for simulation type
        String[] SimulateOptions = {
                "Solar System",
                "Two Planets"
        };
        // Show option dialog for simulation type selection
        int simulateChoice = JOptionPane.showOptionDialog(null, "Select the simulation type:", "Simulation Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, SimulateOptions, SimulateOptions[0]);
        // If the user selects "Solar System", show another option dialog for solar system selection
        // 1 for two planets, 0 for star and planet
        if (simulateChoice == 1) {
            choice = JOptionPane.showOptionDialog(null, "Select the first planet to simulate:", "Planet Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
            choice1 = JOptionPane.showOptionDialog(null, "Select the second planet to simulate:", "Planet Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
            // Get user input for distance between planets
            String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the planets (from 100 to 1000):");
            distance = Integer.parseInt(input2);
        } else if (simulateChoice == 0) {
            choice = JOptionPane.showOptionDialog(null, "Select the star:", "Star Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, SolarSystemOptions, SolarSystemOptions[0]);
            choice1 = JOptionPane.showOptionDialog(null, "Select the planet to simulate:", "Planet Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
            String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
            distance = Integer.parseInt(input2);
            String input3 = JOptionPane.showInputDialog("Enter the initial velocity of the planet in pixels per frame (from 1 to 10):");
            velocity = Integer.parseInt(input3);
        }

        // Create the system panel and start the simulation
        SystemPanel sys = new SystemPanel(distance, choice, choice1, velocity, simulateChoice,simulateChoice);
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
