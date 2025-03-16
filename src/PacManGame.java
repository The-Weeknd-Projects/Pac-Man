package src;

import javax.swing.*; //For JFrame, JPanel

public class PacManGame {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Pac-Man Game");
            GamePanel gamePanel = new GamePanel();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.add(gamePanel);
            window.pack();
            window.setVisible(true);
            gamePanel.start();
        });
    }
}
