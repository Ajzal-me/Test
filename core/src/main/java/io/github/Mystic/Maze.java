package io.github.Mystic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Maze {
    private int[][] maze; // 0 = walkable, 1 = wall
    private Texture wallTexture;
    private Texture floorTexture;

    public Maze() {
        wallTexture = new Texture("Castle_corner1.png");
        floorTexture = new Texture("stone 2.jpeg");

        // Example maze (1 = wall, 0 = floor)
        maze = new int[][] {
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    // Render the maze tiles
    public void render(SpriteBatch batch) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == 1) {
                    batch.draw(wallTexture, col * 32, row * 32, 32, 32);
                } else {
                    batch.draw(floorTexture, col * 32, row * 32, 32, 32);
                }
            }
        }
    }

    // Check if a position is walkable (not a wall)
    public boolean isWalkable(float x, float y) {
        int tileX = (int) x;
        int tileY = (int) y;

        // Ensure x and y are within the maze bounds
        if (tileX < 0 || tileY < 0 || tileX >= maze[0].length || tileY >= maze.length) {
            return false; // Out of bounds is not walkable
        }

        return maze[tileY][tileX] == 0; // Walkable if tile is 0
    }

    public void dispose() {
        wallTexture.dispose();
        floorTexture.dispose();
    }
}
