import javax.swing.*;

/*
 * Program Description:
 *
 * This program is a Flappy Bird game built with Java Swing and 2D Graphics.
 * The player controls a bird that falls due to gravity and must fly through
 * gaps between pairs of pipes. Pressing SPACE or left-clicking flaps the bird
 * upward. The game ends if the bird hits a pipe or falls off the bottom of
 * the screen. When the game ends, Restart and Exit buttons appear on the
 * panel. The score goes up by 1 for each pipe pair passed.
 *
 * How to use:
 *   - Run Project2Runner. The game window opens on the start screen.
 *   - Press SPACE or left-click to start and flap the bird.
 *   - Avoid the pipes. Click Restart to play again or Exit to quit.
 *
 * Swing components used:
 *   1. JFrame   - the game window, set up in GameFrame.java
 *   2. JPanel   - FlappyBird extends JPanel, all game graphics are drawn here
 *   3. JButton  - Restart and Exit buttons appear on the panel after game over
 *
 * 2D Graphics:
 *   - draw() uses drawImage() to render the background, bird, and pipes each
 *     frame. Score, game over, and start screen text are drawn with
 *     drawString(). A filled rounded rectangle is drawn behind the score.
 *
 * Action Listener:
 *   - The game loop Timer fires actionPerformed() 60 times per second, moving
 *     the bird and pipes and repainting the panel each frame. The Restart and
 *     Exit buttons each have their own action listener as well.
 *
 * Mouse Listener:
 *   - Left-clicking the panel starts the game and flaps the bird upward.
 *
 * Keyboard Listener:
 *   - Pressing SPACE starts the game and flaps the bird upward.
 */

public class Project2Runner {
    public static void main(String[] args) throws Exception {
        new GameFrame(); // create and show the game window

    }
}
