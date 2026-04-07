# FlappyBird

A desktop Java game inspired by the classic Flappy Bird arcade-style experience. The player controls a bird flying through a series of moving pipes and must avoid crashing.

## Features

- Smooth bird movement with gravity and jump controls.
- Randomized pipe pair generation with gaps.
- Simple collision detection between the bird and pipes.
- Visual game window using Java Swing.
- Clear separation between game logic and rendering.

## Project Structure

- `src/` — Java source files for game classes.
- `bin/` — compiled `.class` files (ignored by Git).
- `lib/` — external libraries or dependencies (currently unused).
- `images/` — game image assets used by the project.
- `README.md` — project documentation.
- `.gitignore` — files and folders excluded from version control.

## Controls

- `Space` or `Up Arrow` — make the bird flap/jump.
- `Close Window` — exit the game.

> If you add additional controls or menu screens, update this section accordingly.

## Prerequisites

- Java JDK installed (Java 17 or newer recommended).
- `JAVA_HOME` properly set, or the `java` and `javac` commands available in your terminal.
- A terminal or command prompt opened in the project root directory.

## How to Run

1. Open a terminal in the project folder:
   ```powershell
   cd "C:\Users\bnoah\OneDrive\Documents\CS-UG\SEM-2\CPS-209\projects\FlappyBird"
   ```
2. Compile the project source files:
   ```powershell
   javac -d bin src\*.java
   ```
3. Start the game:
   ```powershell
   java -cp bin Project2Runner
   ```

## Notes

- The `bin/` directory is included in `.gitignore` because it stores compiled output.
- If you add external libraries, put them in `lib/` and update the compile/run commands accordingly.
- For a cleaner repo, avoid committing generated files like `.class` files or IDE settings.

## Ideas for Improvement

- Add a scoring system and display current score.
- Add sound effects for jumping and collisions.
- Add a title screen and restart option.
- Enhance pipe patterns and background visuals.
