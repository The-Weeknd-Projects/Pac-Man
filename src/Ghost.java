package src;

import java.awt.*;
import javax.swing.ImageIcon;

public class Ghost {
    private int x, y; // start position
    private int speed;
    private Image ghostImage; // Image class from java.awt package

    public Ghost(int startX, int startY, int speed, String path) {
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.ghostImage = new ImageIcon(getClass().getResource(path)).getImage();
        /*
         * getClass() -> Makes the path relative so that it works on any computer
         * getResource() -> Returns URL Object
         * new ImageIcon() -> Creates an ImageIcon object from swing
         * getImage() -> extracts image from ImageIcon object
         */
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    public void draw(Graphics g) { // Graphics provides drawing tools for shapes, images etc
        g.drawImage(ghostImage, x, y, 30, 30, null); // null because we just need static image. We don't need to it to
                                                     // be synchronous
    }

    public void move() {
        int dir = (int) (Math.random() * 4);
        switch (dir) {
            case 0:
                y -= speed;break;
            case 1:
                y += speed;break;
            case 2:
                x -= speed;break;
            case 3:
                x += speed;break;
        }
        /*
         * 0 -> Up
         * 1 -> Down
         * 2 -> Left
         * 3 -> Right
         */
        x=(x+600)%600;
        y=(y+600)%600;
    }
}
