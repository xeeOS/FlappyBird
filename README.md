# FlappyBird

A desktop Java game inspired by the classic Flappy Bird arcade-style experience. The player controls a bird flying through a series of moving pipes and must avoid crashing.

## Features

- Smooth bird movement with gravity and jump controls.
- Randomized pipe pair generation with gaps.
- Simple collision detection between the bird and pipes.
- Visual game window using Java Swing.
- Clear separation between game logic and rendering.

## Project Structure

- `src/` : Java source files for game classes.
- `bin/` : compiled `.class` files (ignored by Git).
- `lib/` : external libraries or dependencies (currently unused).
- `images/` : game image assets.
- `README.md` : project documentation.
- `.gitignore` : files and folders excluded from version control.

## Controls

- `Space` or `left mouse-click` : make the bird flap/jump.
- `Close Window` : exit the game.

## Prerequisites

- Java JDK installed (Java 17 or newer recommended).
- `JAVA_HOME` properly set, or the `java` and `javac` commands available in your terminal.
- A terminal or command prompt opened in the project root directory.

## How to Run

1. Open a terminal and navigate to the project folder:
   ```powershell
   cd "path\to\FlappyBird"
   ```
2. Compile the project source files: javac -d bin src\*.java

3. Start the game: java -cp bin Project2Runner
