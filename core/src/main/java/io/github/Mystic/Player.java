package io.github.Mystic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture playertexture;
    private Vector2 position;
    private float speed = 200f; // Player movement speed (pixels per second)

    public Player(float x, float y) {
        // Initialize player position and load texture
        position = new Vector2(x, y);
        playertexture = new Texture("01_knight.png");  // Make sure to have a 'player.png' file in your assets folder
    }

    // Method to render the player on the screen
    public void render(SpriteBatch batch) {
        batch.draw(playertexture, position.x, position.y,120,100);
    }

    // Method to handle player movement input
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) ) {
            position.y += speed * deltaTime; // Move up
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) ) {
            position.y -= speed * deltaTime; // Move down
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime; // Move left
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) ) {
            position.x += speed * deltaTime; // Move right
        }
    }

    // Method to dispose of resources when the player is no longer needed
    public void dispose() {
        playertexture.dispose();  // Dispose of the player's texture to free up memory
    }

    public Vector2 getPosition() {
        return position;
    }
}
