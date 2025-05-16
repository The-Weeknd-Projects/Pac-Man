package src;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Ghost{
    
    public int speed=10;// Image class from java.awt package
    public int x, y; // start position
    public Image ghostImage;
    public Ghost(int startX, int startY, String path) {
        x = startX;
        y = startY;
        ghostImage = new ImageIcon(getClass().getResource(path)).getImage();
        /*
         * getClass() -> Makes the path relative so that it works on any computer
         * getResource() -> Returns URL Object
         * new ImageIcon() -> Creates an ImageIcon object from swing
         * getImage() -> extracts image from ImageIcon object
         */
    }
    public int getX() {
    	return x;
    }
    public int getY() {
       return y;
    }
    public void draw(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.drawImage(ghostImage, xOffset + x, yOffset + y, 30, 30, null);
    }
    public abstract void move();
}

class DeadPool extends Ghost{
    public DeadPool(int startX, int startY, String path) {
	super(startX, startY, path);
    }
    @Override
    public void move() {
        int dir = (int) (Math.random() * 4);
        switch (dir) {
            case 0:
                y -= speed;
                break;
            case 1:
                y += speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                x += speed;
                break;
        }
        /*
         * 0 -> Up
         * 1 -> Down
         * 2 -> Left
         * 3 -> Right
         */
        x = (x + 600) % 600;
        y = (y + 600) % 600;
    }
 
}

class Gian extends Ghost{
    public Gian(int startX, int startY, String path) {
	super(startX, startY, path);
    }
    @Override
    public void move() {
        int dir = (int) (Math.random() * 4);
        switch (dir) {
            case 0:
                y -= speed;
                break;
            case 1:
                y += speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                x += speed;
                break;
     }
    }

}

class AlienX extends Ghost{
    public AlienX(int startX, int startY, String path) {
	super(startX, startY, path);
    }
    @Override
    public void move() {
        int dir = (int) (Math.random() * 4);
        switch (dir) {
            case 0:
                y -= speed;
                break;
            case 1:
                y += speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                x += speed;
                break;
     }
    }

}

class Gamora extends Ghost{
    public Gamora(int startX, int startY, String path) {
	super(startX, startY, path);
    }
    @Override
    public void move() {
        int dir = (int) (Math.random() * 4);
        switch (dir) {
            case 0:
                y -= speed;
                break;
            case 1:
                y += speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                x += speed;
                break;
     }
    }
}
