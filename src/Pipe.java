/**
 * Pipe.java
 * Represents a single pipe obstacle in the Flappy Bird game.
 * Each Pipe stores its position, size, image, and whether the bird has passed
 * it.
 */
public class Pipe {
    int x;
    int y;
    int width;
    int height;
    java.awt.Image image;
    boolean passed = false; // passed the bird or not

    /**
     * Constructor for the Pipe class.
     * 
     * @param x      x position of the pipe
     * @param y      y position of the pipe
     * @param width  width of the pipe image
     * @param height height of the pipe image
     * @param image  the pipe sprite image (top or bottom)
     */
    Pipe(int x, int y, int width, int height, java.awt.Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
}
