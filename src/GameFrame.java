import javax.swing.*;

/**
 * The main JFrame window for the Flappy Bird game.
 * Responsible for creating and configuring the window, then adding the
 * FlappyBird game panel.
 */
public class GameFrame extends JFrame {

    /**
     * Constructs the game window and adds the FlappyBird panel to it.
     */
    public GameFrame() {
        int boardWidth = 360; // width of the game board
        int boardHeight = 640; // height of the game board

        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(boardWidth, boardHeight);
        setLocationRelativeTo(null);
        setResizable(false);

        FlappyBird game = new FlappyBird();
        add(game);
        pack(); // not include the title bar in the size of the frame
        game.requestFocus(); // request focus for the game panel to receive key events
        setVisible(true);
    }
}
