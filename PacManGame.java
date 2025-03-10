package PacMan;

import javax.swing.*; //For JFrame, JPanel

public class PacManGame {
    public static void main(String args[]) {
        JFrame window = new JFrame("Pac-Man Game");
        GamePanel gamePanel = new GamePanel(); // For all rendering
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Direct exit on close button activation
        window.add(gamePanel);
        window.pack(); // Resizes the window to exactly fit the components
        window.setVisible(true);
        gamePanel.start(); // starts the game loop
    }
}
