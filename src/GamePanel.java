package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int pacmanX = 100, pacmanY = 100, pacmanSize = 30;
    private int pacmanDX = 0, pacmanDY = 0;
    private Image pacmanImage;
    private Ghost ghost;
    private boolean running = true;

    public GamePanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(); // Ensure focus for key events

        try {
            // Load Pac-Man image
            pacmanImage = new ImageIcon(getClass().getResource("/resources/pacman.png")).getImage();

            // Initialize Ghost
            ghost = new Ghost(200, 200, 10, "/resources/ghost.png");

        } catch (NullPointerException e) {
            System.err.println("Error: Required image file is missing!");
            System.exit(1); // Terminate the program if any image is missing
        }

        timer = new Timer(100, this);
        timer.start(); // Start the timer
    }

    public void start() {
        requestFocusInWindow(); // Ensure focus
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            // Draw Pac-Man
            g.drawImage(pacmanImage, pacmanX, pacmanY, pacmanSize, pacmanSize, null);

            // Draw Ghost
            ghost.draw(g);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", 130, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            pacmanX += pacmanDX;
            pacmanY += pacmanDY;
            ghost.move();

            if (checkCollision()) {
                running = false;
                timer.stop();
            }
            repaint();
        }
    }

    private boolean checkCollision() {
        Rectangle pacmanBounds = new Rectangle(pacmanX, pacmanY, pacmanSize, pacmanSize);
        Rectangle ghostBounds = new Rectangle(ghost.getX(), ghost.getY(), 30, 30);
        return pacmanBounds.intersects(ghostBounds);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == 'a' || key == 'A') {
            pacmanDX = -10;
            pacmanDY = 0;
        }
        if (key == KeyEvent.VK_RIGHT || key == 'd' || key == 'D') {
            pacmanDX = 10;
            pacmanDY = 0;
        }
        if (key == KeyEvent.VK_UP || key == 'w' || key == 'W') {
            pacmanDX = 0;
            pacmanDY = -10;
        }
        if (key == KeyEvent.VK_DOWN || key == 's' || key == 'S') {
            pacmanDX = 0;
            pacmanDY = 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}