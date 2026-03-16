package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Type of star
        int star = 0;
        //Type of planet
        int choice1 = 1;
        int choice2 = 1;
        int choice3 = 1;
        // Distance between star and planet or between two planets
        int distance1 = 200;
        int distance2 = 200;
        int distance3 = 200;
        //Velocity of the planet
        int velocity = 0;

        // Get user input for star selection
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
        int NumPlanets = 0;
        if (simulateChoice == 1) {
            choice1 = JOptionPane.showOptionDialog(null, "Select the first planet to simulate:", "Planet Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
            choice2 = JOptionPane.showOptionDialog(null, "Select the second planet to simulate:", "Planet Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
            // Get user input for distance between planets
            String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the planets (from 100 to 1000):");
            while (!checkInputDistance(Integer.parseInt(input2))) {
                input2 = JOptionPane.showInputDialog("Enter distance in pixels between the planets (from 100 to 1000):");
            }
            distance1 = Integer.parseInt(input2);
        } else if (simulateChoice == 0) {
            star = JOptionPane.showOptionDialog(null, "Select the star:", "Star Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, SolarSystemOptions, SolarSystemOptions[0]);
            // Get user input for number of planets to simulate
            String input4 = JOptionPane.showInputDialog("How many planets do you want to circle around the star (from 1 to 3):");
            while (Integer.parseInt(input4) < 1 || Integer.parseInt(input4) > 3) {
                input4 = JOptionPane.showInputDialog("How many planets do you want to circle around the star (from 1 to 3):");
            }
            NumPlanets = Integer.parseInt(input4);

            if (NumPlanets == 1) {
                choice1 = JOptionPane.showOptionDialog(null, "Select the planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                while (!checkInputDistance(Integer.parseInt(input2))) {
                    input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance1 = Integer.parseInt(input2);

            } else if (NumPlanets == 2) {
                choice1 = JOptionPane.showOptionDialog(null, "Select the first planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                //The first distance
                while (!checkInputDistance(Integer.parseInt(input2))) {
                    input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance1 = Integer.parseInt(input2);

                choice2 = JOptionPane.showOptionDialog(null, "Select the second planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                //The second distance
                String input3 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                while (!checkInputDistance(Integer.parseInt(input2))) {
                    input3 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance2 = Integer.parseInt(input3);
            } else if (NumPlanets == 3) {
                choice1 = JOptionPane.showOptionDialog(null, "Select the first planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                //The first distance
                String input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                while (!checkInputDistance(Integer.parseInt(input2))) {
                    input2 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance1 = Integer.parseInt(input2);

                choice2 = JOptionPane.showOptionDialog(null, "Select the second planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                //The second distance
                String input3 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                while (!checkInputDistance(Integer.parseInt(input3))) {
                    input3 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance2 = Integer.parseInt(input3);

                choice3 = JOptionPane.showOptionDialog(null, "Select the third planet to simulate:", "Planet Selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, planets, planets[0]);
                //The third distance
                String input41 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                while (!checkInputDistance(Integer.parseInt(input41))) {
                    input41 = JOptionPane.showInputDialog("Enter distance in pixels between the star and the planet (from 100 to 1000):");
                }
                distance3 = Integer.parseInt(input41);
            }

            // Get user input for initial velocity of the planet
            String input3 = JOptionPane.showInputDialog("Enter the initial velocity of the planet in pixels per frame (from 1 to 10):");
            while (!checkInputVelocity(Integer.parseInt(input3))) {
                input3 = JOptionPane.showInputDialog("Enter the initial velocity of the planet in pixels per frame (from 1 to 10):");
            }
            velocity = Integer.parseInt(input3);
        }


        // Create the system panel and start the simulation
        SystemPanel sys = new SystemPanel(distance1, distance2, distance3, velocity, star ,choice1, choice2, choice3, simulateChoice, NumPlanets);
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
    // Method to check if the input distance is valid
    private static boolean checkInputDistance(int distance) {
        if (distance < 100 || distance > 1000) {
            JOptionPane.showMessageDialog(null, "Distance must be between 100 and 1000 pixels.");
            return false;
        }
        return true;
    }
    // Method to check if the input velocity is valid
    private static boolean checkInputVelocity(int velocity) {
        if (velocity < 1 || velocity > 10) {
            JOptionPane.showMessageDialog(null, "Velocity must be between 1 and 10 pixels per frame.");
            return false;
        }
        return true;
    }
}
