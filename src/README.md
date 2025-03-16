# Pac-Man Game (Java Swing & AWT)

This is a simple **Pac-Man game** built using **Java Swing and AWT**. The game features a **movable Pac-Man** and a **randomly moving ghost**. The objective is to avoid the ghost—if Pac-Man collides with the ghost, the game ends.

## Project Structure

### 1. `PacManGame.java` (Main Class)
- Initializes the game window (`JFrame`).
- Creates an instance of `GamePanel` and starts the game.

### 2. `GamePanel.java` (Game Logic & Rendering)
- Handles game rendering (`paintComponent()`).
- Implements **keyboard controls** for Pac-Man's movement.
- Uses a **timer (`Timer`)** to update the game loop.
- Checks for **collisions** between Pac-Man and the ghost.

### 3. `Ghost.java` (Ghost Behavior)
- Loads and displays the ghost image.
- Moves the ghost **randomly** in different directions.
- Provides position data for collision detection.

## How to Play
1. **Run `PacManGame.java`** to start the game.
2. Use **arrow keys (`← ↑ → ↓`)** to control Pac-Man.
3. Avoid **colliding with the ghost**.
4. If you collide with the ghost, the game **displays "Game Over"** and stops.

## Requirements
- **Java 8+**
- Ensure the following **image files** are available in the `resources` directory:
  -  `resources/pacman.png`
  -  `resources/ghost.png`

## Future Enhancements (Optional)
- Add **multiple ghosts** with AI movement.
- Implement **Pellets (dots) collection**.
- Add a **scoring system**.
- Improve ghost movement with **pathfinding algorithms (e.g., BFS, A*)**.
