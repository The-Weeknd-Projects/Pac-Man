package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int pacmanX = 100, pacmanY = 100, pacmanSize = 30;
    private int pacmanDX = 0, pacmanDY = 0;
    private Image pacmanImage;
    private Ghost ghost;
    private boolean running = true;
    private BufferedImage buffer;

    public GamePanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(); // Ensure focus for key events
        buffer = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);


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
    // Call the superclass method to ensure proper rendering behavior
    super.paintComponent(g);

    // Create a Graphics2D object from the buffered image
    Graphics2D g2d = buffer.createGraphics();

    // Set the background color to black and fill the entire panel
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    if (running) {
        // Draw Pac-Man at its current position
        g2d.drawImage(pacmanImage, pacmanX, pacmanY, pacmanSize, pacmanSize, null);

        // Draw the ghost using its draw method
        ghost.draw(g2d);
    } else {
        // Display "Game Over" text in red when the game ends
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("Game Over", 130, 200);
    }

    // Dispose of the Graphics2D object to free up resources
    g2d.dispose();

    // Draw the buffered image onto the screen
    g.drawImage(buffer, 0, 0, null);
}

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
        	pacmanX = (pacmanX + pacmanDX + 400) % 400; // Wrap horizontally using the modulous operation for horizontal wrapping around
        	pacmanY = (pacmanY + pacmanDY + 400) % 400; // Wrap vertically using the modulous operation for vertical wrapping around

        	ghost.move(); // Move the ghost

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
