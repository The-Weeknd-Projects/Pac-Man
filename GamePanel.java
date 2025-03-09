package PacMan;

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

        // Load Pac-Man image
        pacmanImage = new ImageIcon(getClass().getResource("/pacman.png")).getImage();

        // Initialize ghost
        ghost = new Ghost(200, 200, 5, "/ghost.png");

        timer = new Timer(100, this);
        timer.start(); // Start the timer
    }

    public void start() {
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
        return (Math.abs(pacmanX - ghost.getX()) < pacmanSize && Math.abs(pacmanY - ghost.getY()) < pacmanSize);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) { pacmanDX = -10; pacmanDY = 0; }
        if (key == KeyEvent.VK_RIGHT) { pacmanDX = 10; pacmanDY = 0; }
        if (key == KeyEvent.VK_UP) { pacmanDX = 0; pacmanDY = -10; }
        if (key == KeyEvent.VK_DOWN) { pacmanDX = 0; pacmanDY = 10; }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
