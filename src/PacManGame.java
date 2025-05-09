package src;

import javax.swing.*; //For JFrame, JPanel

public class PacManGame {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Pac-Man Game");
            GamePanel gamePanel = new GamePanel();

            // Set the JFrame to full screen
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
            window.setResizable(true); // Allow resizing if needed
            // Add the game panel
            window.add(gamePanel);
            window.setVisible(true);

            gamePanel.start();
        });
    }
}
