package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int width = 1920; // Dimension of the game window
    private int height = 1080; // Dimension of the game window
    private int tileSize = 30; // Size of each square tile in the grid
    private int pacmanX = 30, pacmanY = 30, pacmanSize = 30;
    private int pacmanDX = 15, pacmanDY = 0; // Default movement
    private Image[][] pacmanFrames = new Image[3][4]; // 3 mouth states, 4 directions
    private int mouthState = 0; // 0 = closed, 1 = half-open, 2 = full-open
    private int direction = 0; // 0 = Right, 1 = Left, 2 = Up, 3 = Down
    private DeadPool red;
    private Gamora green;
    private Gian orange;
    private AlienX nigger;
    private boolean running = true;
    private BufferedImage buffer;
    private SoundSystem pacSound, ghostSound;
    private Font gameFont;
    private boolean trigger = false;
    private boolean isAlive = true;
    private boolean map[][];

    public GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pacSound = new SoundSystem();
        ghostSound = new SoundSystem();
        map = new boolean[][] {
            {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  

true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true},
            {true,  false, false, false, false, false, true,  false, true,  false, false, false, false, false, false, false, false, false, false, 

false, true,  false, false, false, false, false, false, false, false, false, false, true},
            {true,  false, true,  false, true,  false, false, false, true,  false, true,  true,  false, true,  true,  true,  true,  true,  false, 

true,  true,  false, true,  true,  false, true,  false, false, true,  true,  false, true},
            {true,  false, false, false, true,  true,  true,  false, true,  false, true,  false, false, false, false, false, true,  false, false, 

true,  true,  false, true,  false, false, false, false, true,  false, true,  false, false},
            {true,  true,  true,  false, true,  false, true,  false, false, false, false, false, true,  true,  true,  false, true,  false, true,  

true,  false, false, true,  true,  true,  false, true,  true,  false, false, false, true},
            {true,  false, false, false, true,  false, false, false, true,  false, true,  false, true,  false, false, false, false, false, false, 

true,  true,  false, false, true,  false, false, false, true,  true,  true,  false, true},
            {true,  false, true,  false, false, false, true,  false, true,  false, true,  false, true,  true,  true,  false, true,  false, true,  

false, true,  true,  false, true,  false, false, false, true,  false, true,  false, true},
            {true,  false, true,  false, true,  false, true,  false, true,  false, true,  false, false, false, false, false, true,  false, true,  

false, false, false, false, true,  true,  true,  true,  true,  false, false, false, true},
            {true,  false, false, false, true,  false, true,  true,  true,  false, true,  false, true,  false, true,  true,  true,  false, false, 

false, true,  false, true,  false, false, false, false, false, false, true,  false, true},
            {true,  true,  true,  false, false, false, true,  false, true,  false, true,  false, true,  true,  true,  false, true,  true,  false, 

true,  true,  false, false, false, true,  true,  true,  false, false, false, false, true},
            {true,  false, false, false, true,  false, false, false, false, false, false, false, false, false, false, false, true,  false, false, 

false, true,  true,  true,  true,  true,  false, true,  true,  true,  true,  true,  true},
            {true,  false, true,  true,  true,  true,  false, true,  false, true,  true,  false, true,  false, true,  false, false, false, true,  

false, false, false, false, false, false, false, true,  false, false, false, false, true},
            {false, false, false, false, false, true,  false, true,  false, true,  true,  false, true,  true,  true,  false, true,  false, true,  

false, true,  true,  false, true,  false, true,  true,  false, true,  true,  false, true},
            {true,  false, true,  true,  false, true,  false, false, false, true,  false, false, false, false, false, false, false, false, true,  

false, true,  true,  false, false, false, false, false, false, false, true,  false, true},
            {true,  false, true,  false, false, false, true,  true,  true,  true,  false, true,  false, true,  false, true,  true,  true,  true,  

false, true,  true,  true,  true,  true,  false, true,  true,  false, true,  false, true},
            {true,  false, false, false, true,  false, false, false, false, false, false, true,  true,  true,  false, false, false, true,  false, 

false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  

true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true,  true}
        }; // 20x20 grid

        // Load Pac-Man images
        String[] directions = { "right", "left", "up", "down" };
        try {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 4; col++) {
                    String path = "/resources/pacman_" + directions[col] + "_" + row + ".png";
                    pacmanFrames[row][col] = new ImageIcon(getClass().getResource(path)).getImage();
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Error: Required Pac-Man images are missing!");
            System.exit(1);
        }

        // Initialize Ghost
        orange = new Gian(720, 180, "/resources/gian.png");
        green = new Gamora(780, 180,"/resources/gamora.png");
        nigger = new AlienX(750, 180, "/resources/alienX.png");
	red = new DeadPool(750, 150,"/resources/deadPool.png");
        // Load custom font
        loadGameFont();

        timer = new Timer(100, this);
        timer.start();
    }

    private void loadGameFont() {
        try (InputStream fontStream = getClass().getResourceAsStream("/resources/PressStart2P-Regular.ttf")) {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(30f);
        } catch (Exception e) {
            gameFont = new Font("Arial", Font.BOLD, 30); // Fall back font
        }
    }

    public void start() {
        requestFocusInWindow();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = buffer.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Calculate offsets to center the grid
        int gridWidth = map[0].length * tileSize;
        int gridHeight = map.length * tileSize;
        int xOffset = (getWidth() - gridWidth) / 2;
        int yOffset = (getHeight() - gridHeight) / 2;

        // Draw the grid
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    g2d.setColor(Color.BLUE);
                    g2d.drawRect(xOffset + j * tileSize, yOffset + i * tileSize, tileSize, tileSize);
                }
            }
        }

        if (running) {
            if (!trigger)
                g2d.drawImage(pacmanFrames[0][direction], xOffset + pacmanX, yOffset + pacmanY, pacmanSize, pacmanSize,
                        null);
            else
                g2d.drawImage(pacmanFrames[mouthState][direction], xOffset + pacmanX, yOffset + pacmanY, pacmanSize,
                        pacmanSize, null);
            red.draw(g2d, xOffset, yOffset); // Pass offsets to the ghost's draw method
	    green.draw(g2d, xOffset, yOffset);
	    nigger.draw(g2d, xOffset, yOffset);
	    orange.draw(g2d, xOffset, yOffset);
        } else {
            drawGameOverScreen(g2d);
        }

        g2d.dispose();
        g.drawImage(buffer, 0, 0, null);
    }

    private void drawGameOverScreen(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.RED);
        g2d.setFont(gameFont);
        String text = "Game Over";
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() / 2) + metrics.getAscent();

        g2d.drawString(text, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            if (trigger) {
                try{
                    int tempX = pacmanX + pacmanDX;
                    int tempY = pacmanY + pacmanDY;
                    if (!wallCollision(tempX, tempY)) {
                        pacmanX = tempX;
                        pacmanY = tempY;
                    }
                }
                catch(ArrayIndexOutOfBoundsException ex){
                    if(pacmanX < 0){
                        pacmanX = 930;
                        pacmanY = 90;
                    }
                    else{
                        pacmanX = 0;
                        pacmanY = 360;
                    }
                }
                finally{
			red.move();
			green.move();
			orange.move();
			nigger.move();
                }
            }
            mouthState = (mouthState + 1) % 3;
            if (checkCollision()) {
                isAlive = false;
                pacSound.stop();
                ghostSound.stop();
                pacSound.play("/resources/death.wav", false);
                running = false;
                timer.stop();
            }

            if (trigger && !ghostSound.isLooping()) {
                ghostSound.play("/resources/ghost.wav", true);
            }

            repaint();
        }
    }

    private boolean checkCollision() {
        Rectangle pacmanBounds = new Rectangle(pacmanX, pacmanY, pacmanSize, pacmanSize);
        Rectangle[] ghostBounds = {
    	new Rectangle(red.getX(), red.getY(), 30, 30),
    	new Rectangle(green.getX(), green.getY(), 30, 30),
    	new Rectangle(orange.getX(), orange.getY(), 30, 30),
    	new Rectangle(nigger.getX(), nigger.getY(), 30, 30)
	};
	for (Rectangle bounds : ghostBounds) {
    		if (pacmanBounds.intersects(bounds)) return true;
	}
	return false;
    }

    public boolean wallCollision(int x, int y) {
        int left = x;
        int right = x + pacmanSize - 1; // so that it doesn't check pixel with wall (only 0 to 29 pixels)
        int top = y;
        int bottom = y + pacmanSize - 1;
        // four edges of pacman
        return map[top / tileSize][left / tileSize] ||
                map[top / tileSize][right / tileSize] ||
                map[bottom / tileSize][left / tileSize] ||
                map[bottom / tileSize][right / tileSize];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        boolean changed = false;
        int newDX = pacmanDX, newDY = pacmanDY, newDirection = direction;

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            newDX = -15;
            newDY = 0;
            newDirection = 1;
            changed = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            newDX = 15;
            newDY = 0;
            newDirection = 0;
            changed = true;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            newDX = 0;
            newDY = -15;
            newDirection = 2;
            changed = true;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            newDX = 0;
            newDY = 15;
            newDirection = 3;
            changed = true;
        }

        if (changed) {
            // Check if the next tile in the intended direction is open
            int tempX = pacmanX + newDX;
            int tempY = pacmanY + newDY;
            if (!wallCollision(tempX, tempY)) {
                pacmanDX = newDX;
                pacmanDY = newDY;
                direction = newDirection;
                trigger = true;
                if (isAlive && !pacSound.isLooping()) {
                    pacSound.play("/resources/chomp.wav", true);
                }
            }
            // else: do nothing, keep moving in current direction
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
