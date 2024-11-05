package io.github.Mystic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.audio.Sound;

public class Player {

    private Sound walk;
    private boolean isWalkingSoundPlaying = false;
    private Vector2 position;
    private float speed = 3f;
    private final float PLAYER_WIDTH = 2.5f;
    private final float PLAYER_HEIGHT = 2.5f;

    private Animation<TextureRegion> walkRightAnimation;
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> idleRightAnimation;
    private Animation<TextureRegion> idleLeftAnimation;
    private TextureRegion currentFrame;
    private float stateTime;

    private Direction lastDirection = Direction.RIGHT;

    public Player(float x, float y) {
        walk = Gdx.audio.newSound(Gdx.files.internal("Walk.mp3"));

        position = new Vector2(x, y);
        walkRightAnimation = loadAnimation("Walk_Right", 2, false);  // Load right walk animation
        walkLeftAnimation = loadAnimation("Walk_Right", 2, true);    // Load left walk as flipped version of right
        idleRightAnimation = loadAnimation("Idle_Right", 6, false);  // Load idle right animation
        idleLeftAnimation = loadAnimation("Idle_Right", 6, true);    // Load idle left as flipped version of idle right
        currentFrame = idleRightAnimation.getKeyFrame(0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, position.x, position.y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        boolean isMovingHorizontally = false;
        boolean isMovingVertically = false;

        // Horizontal movement
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime;
            currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
            lastDirection = Direction.LEFT;
            isMovingHorizontally = true;

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltaTime;
            currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
            lastDirection = Direction.RIGHT;
            isMovingHorizontally = true;
        }

        // Vertical movement
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltaTime;
            currentFrame = getWalkAnimationForDirection().getKeyFrame(stateTime, true);
            isMovingVertically = true;

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltaTime;
            currentFrame = getWalkAnimationForDirection().getKeyFrame(stateTime, true);
            isMovingVertically = true;
        }

        // Check if player is moving and play/stop sound accordingly
        if ((isMovingHorizontally || isMovingVertically) && !isWalkingSoundPlaying) {
            long id = walk.play();
            walk.setVolume(id,1f);
            walk.loop();// Start or loop the walking sound
            isWalkingSoundPlaying = true;
        } else if (!isMovingHorizontally && !isMovingVertically && isWalkingSoundPlaying) {
            long id = walk.play();
            walk.setVolume(id,1f);
            walk.stop();  // Stop the walking sound
            isWalkingSoundPlaying = false;
        }

        // Idle animation if not moving
        if (!isMovingHorizontally && !isMovingVertically) {
            currentFrame = (lastDirection == Direction.RIGHT ? idleRightAnimation : idleLeftAnimation)
                .getKeyFrame(stateTime, true);
        }
    }


    private Animation<TextureRegion> loadAnimation(String basePath, int frameCount, boolean flipHorizontally) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= frameCount; i++) {
            Texture texture = new Texture(basePath + i + ".png");
            TextureRegion region = new TextureRegion(texture);
            if (flipHorizontally) {
                region.flip(true, false);  // Flip horizontally for left animation
            }
            frames.add(region);
        }
        return new Animation<>(0.3f, frames, Animation.PlayMode.LOOP);
    }

    private Animation<TextureRegion> getWalkAnimationForDirection() {
        // Return walk animation for the last horizontal direction
        return lastDirection == Direction.LEFT ? walkLeftAnimation : walkRightAnimation;
    }

    public void dispose() {
        for (TextureRegion frame : walkRightAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
        for (TextureRegion frame : walkLeftAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
        for (TextureRegion frame : idleRightAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
        for (TextureRegion frame : idleLeftAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
        walk.dispose();
    }

    private enum Direction {
        LEFT, RIGHT
    }

    public Vector2 getPosition() {
        return position;
    }
}
