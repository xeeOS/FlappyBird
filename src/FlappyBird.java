import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * FlappyBird.java
 * The main game panel for Flappy Bird. Extends JPanel and handles all game
 * logic including bird movement, pipe spawning, collision detection, and
 * drawing everything to the screen.
 *
 * The game loop runs via a Timer that fires 60 times per second. The player
 * controls the bird by pressing SPACE or clicking the mouse to flap upward.
 * When the game ends, Restart and Exit buttons appear on the panel.
 */

public class FlappyBird extends JPanel implements ActionListener, KeyListener, MouseListener {
    private int boardWidth = 360; // width of the game board
    private int boardHeight = 640; // height of the game board

    // images
    private Image backgroundImage;
    private Image birdImage;
    private Image TopPipeImage;
    private Image BottomPipeImage;

    // bird
    private int birdX = boardWidth / 8; // initial x position of the bird
    private int birdY = boardHeight / 2; // initial y position of the bird
    private int birdWidth = 34; // width of the bird image
    private int birdHeight = 24; // height of the bird image

    // pipe
    private int pipeX = boardWidth;
    private int pipeY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;

    // game components
    private Bird bird; // create a bird object
    private Timer timer; // timer for the game loop
    private Timer pipeTimer; // timer for adding new pipes
    private int velocityX = -5; // move the pipes to the left by 5 pixels every frame
    private int velocityY = 0; // initial velocity of the bird
    private int gravity = 1; // move the bird down by 1 pixel every frame
    private ArrayList<Pipe> pipes = new ArrayList<>(); // list of pipes in the game
    private boolean gameOver = false; // game state
    private double score = 0; // player's score
    private boolean gameStarted = false;

    private JButton restartButton;
    private JButton exitButton;

    /**
     * Constructor for FlappyBird.
     * Sets up the panel size, loads images, initializes the bird,
     * and starts both the game loop timer and the pipe-spawning timer.
     */
    public FlappyBird() {
        /*
         * File bgFile = new File("images/background.png");
         * System.out.println("Background exists? " + bgFile.exists());
         */

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        // setBackground(Color.cyan);

        setFocusable(true); // allow game panel to receive focus for key events
        addKeyListener(this); // add key listner to game panel to listen for key events
        addMouseListener(this);

        // load images
        backgroundImage = new ImageIcon("images/flappybirdbg.png").getImage();
        birdImage = new ImageIcon("images/flappybird.png").getImage();
        TopPipeImage = new ImageIcon("images/toppipe.png").getImage();
        BottomPipeImage = new ImageIcon("images/bottompipe.png").getImage();
        // System.out.println(getClass().getResource("/images/flappybirdbg.png"));

        // bird
        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImage); // initialize the bird object with the bird
                                                                         // image
        // pipe timer not started here, started in startGame() on first input
        pipeTimer = new Timer(1000, new ActionListener() { // set the timer to add a new pipe every 1.5 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                addPipe(); // add a new pipe to the game
            }
        });

        // game loop timer
        timer = new Timer(1000 / 60, this);// set the timer to update at 60 frames per second
        timer.start(); // start the timer

    }

    /*
     * startGame is called on the first SPACE press or mouse click.
     * Sets the gameStarted flag and starts the pipe spawning timer.
     * The game loop timer was already running to show the start screen.
     */
    private void startGame() {
        gameStarted = true;
        pipeTimer.start();
    }

    /**
     * addPipe creates a new pair of pipes (top and bottom) at a random vertical
     * position
     * and adds them to the pipes ArrayList. Called by the pipe timer every second.
     * The gap between the top and bottom pipe gives the bird a space to fly
     * through.
     */
    public void addPipe() {
        // random Y position so the pipes shift up and down
        int randomY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int gap = 150; // The space between the top and bottom pipe for the bird to fly through

        // top pipe
        Pipe topPipe = new Pipe(pipeX, randomY, pipeWidth, pipeHeight, TopPipeImage);
        pipes.add(topPipe);

        // bottom pipe shifting it down by the height of the top pipe + the gap
        Pipe bottomPipe = new Pipe(pipeX, randomY + pipeHeight + gap, pipeWidth, pipeHeight, BottomPipeImage);
        pipes.add(bottomPipe);
    }

    /**
     * paintComponent is called by Swing whenever the panel needs to be redrawn.
     * Clears the panel via super.paintComponent() then calls draw().
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the superclass method to clear the panel
        draw(g);
    }

    /**
     * draw renders all game elements onto the Graphics context:
     * background, bird, all pipes, and the score (or game over screen).
     * Called every frame from paintComponent().
     */
    public void draw(Graphics g) {
        // System.out.println("draw");
        // draw background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        // draw bird
        g.drawImage(bird.image, bird.x, bird.y, bird.width, bird.height, null);
        // draw pipes
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // draw score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        if (gameOver) {
            // show game over text above the buttons
            g.drawString("Game Over!", boardWidth / 2 - 70, boardHeight / 2 - 20);
            g.drawString("Final Score: " + (int) score, boardWidth / 2 - 80, boardHeight / 2 + 20);
        } else if (!gameStarted) {
            // show start screen instructions
            g.drawString("Flappy Bird!", boardWidth / 2 - 70, boardHeight / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Click or SPACE to start", boardWidth / 2 - 82, boardHeight / 2 + 20);
        } else {
            // show score during gameplay
            g.drawString("Score: " + (int) score, 10, 30);
        }
        // draw a background box behind the score
        g.setColor(new Color(0, 0, 0, 100)); // semi-transparent black
        g.fillRoundRect(5, 5, 120, 35, 8, 8);
        // draw score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + (int) score, 10, 30);

    }

    /*
     * showGameOverButtons adds a Restart and Exit JButton to the panel after
     * the game ends. Buttons are positioned in the center of the screen below
     * the game over text.
     */
    private void showGameOverButtons() {
        restartButton = new JButton("Restart");
        exitButton = new JButton("Exit");

        restartButton.setBounds(boardWidth / 2 - 90, boardHeight / 2 + 60, 85, 30);
        exitButton.setBounds(boardWidth / 2 + 5, boardHeight / 2 + 60, 85, 30);

        restartButton.addActionListener(e -> {
            resetGame();
            remove(restartButton);
            remove(exitButton);
            revalidate();
            repaint();
            requestFocusInWindow(); // give keyboard focus back to the game panel after clicking restart
        });

        exitButton.addActionListener(e -> System.exit(0));

        setLayout(null); // allows manual positioning with setBounds
        add(restartButton);
        add(exitButton);
        revalidate();
        repaint();
    }

    /**
     * move updates the positions of the bird and all pipes each frame.
     * Applies gravity to the bird, moves pipes left, checks if pipes have been
     * passed
     * (to increment score), and checks for collisions or out-of-bounds conditions
     * that would trigger a game over.
     */
    public void move() {
        if (!gameStarted) {
            return; // don't move anything until the game starts
        }
        // bird movement
        velocityY += gravity; // apply gravity to the bird's vertical velocity
        bird.y += velocityY;

        // bird cannot move above the top of the screen
        if (bird.y < 0) {
            bird.y = 0;
        }

        // bird cannot move below the bottom of the screen
        // if (bird.y > boardHeight - bird.height) {
        // bird.y = boardHeight - bird.height;
        // }

        // pipe movement
        for (Pipe pipe : pipes) {
            pipe.x += velocityX; // move the pipe to the left by the velocityX value

            if (!pipe.passed && pipe.x + pipe.width < bird.x) {
                score += 0.5; // increase score by 0.5 for each pipe passed (top and bottom)
                pipe.passed = true; // mark the pipe as passed
            }

            if (checkCollision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    /**
     * checkCollision determines whether the bird's bounding box overlaps with a
     * pipe's
     * bounding box using standard axis-aligned rectangle intersection logic.
     * Returns true if a collision is detected (game should end), false otherwise.
     */
    public boolean checkCollision(Bird bird, Pipe pipe) {
        // check if the bird's bounding box intersects with the pipe's bounding box
        if (bird.x < pipe.x + pipe.width && bird.x + bird.width > pipe.x &&
                bird.y < pipe.y + pipe.height && bird.y + bird.height > pipe.y) {
            return true; // collision detected
        }
        return false; // no collision
    }

    /*
     * resetGame resets all game state back to the initial values so the player
     * can play again without restarting the program. Clears pipes, resets the bird,
     * score, and all flags, then restarts the game loop timer.
     */
    private void resetGame() {
        bird.x = birdX;
        bird.y = birdY;
        velocityY = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
        gameStarted = false; // player must click or press SPACE to start again
        timer.start();
        // pipeTimer starts again in startGame() on first input
    }

    /**
     * actionPerformed is called by the game loop Timer 60 times per second.
     * Triggers a repaint (to redraw the game) and calls move() to update positions.
     * Also stops both timers if the game is over.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // repaint the game panel to update the game state
        move(); // update the position of the bird
        if (gameOver) {
            timer.stop();
            pipeTimer.stop();
            showGameOverButtons(); // show restart and exit buttons
        }
    }

    /**
     * keyPressed listens for the SPACE bar and makes the bird flap upward by
     * setting its vertical velocity to a negative value.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                startGame(); // start the game on first press
            }
            velocityY = -10;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!gameStarted) {
                startGame(); // start the game on first click
            }
            velocityY = -10;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
