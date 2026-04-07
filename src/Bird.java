/**
 * Bird.java
 * Represents the player-controlled bird in the Flappy Bird game.
 * Stores the bird's position, size, and image for rendering and collision
 * detection.
 */
public class Bird {
    // attributes of the bird
    int x;
    int y;
    int width;
    int height;
    java.awt.Image image;

    /**
     * Constructor for the Bird class.
     * Initializes the bird with a position, size, and image.
     * 
     * @param x      initial x position
     * @param y      initial y position
     * @param width  width of the bird image
     * @param height height of the bird image
     * @param image  the bird sprite image
     */
    Bird(int x, int y, int width, int height, java.awt.Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
}
