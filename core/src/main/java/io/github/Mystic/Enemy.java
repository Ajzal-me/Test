package io.github.Mystic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    private Vector2 position;
    private Vector2 velocity;
    private float xmin, xmax, ymin, ymax;
    private float scale;
    private boolean isMovingHorizontally;
    private boolean isMovingLeft;
    private boolean isMovingDown;
    private float stateTime;

    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> walkRightAnimation;

    public Enemy(float x, float y, float xmin, float xmax, float ymin, float ymax, int speed, float scale) {
        this.position = new Vector2(x, y);
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.scale = scale;
        this.stateTime = 0f;

        // Set up enemy animations by loading frames with a naming pattern
        this.walkLeftAnimation = createAnimation("./Enemy/Walk Left/Walk_Left", 4);
        this.walkRightAnimation = createAnimation("./Enemy/Walk Right/Walk_Right", 4);

        // Decide direction based on speed values
        this.velocity = new Vector2(speed, 0);
        this.isMovingHorizontally = speed != 0;
        this.isMovingLeft = speed < 0;
        this.isMovingDown = speed < 0;
    }

    private Animation<TextureRegion> createAnimation(String basePath, int frameCount) {
        TextureRegion[] animationFrames = new TextureRegion[frameCount];
        for (int i = 1; i <= frameCount; i++) {
            Texture texture = new Texture(basePath + i + ".png");  // Automatically adds the frame number
            animationFrames[i - 1] = new TextureRegion(texture);
        }
        return new Animation<>(0.1f, animationFrames);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (isMovingHorizontally) {
            moveHorizontally(deltaTime);
        } else {
            moveVertically(deltaTime);
        }
    }

    private void moveHorizontally(float deltaTime) {
        position.x += velocity.x * deltaTime;
        if (position.x <= xmin) {
            position.x = xmin;
            velocity.x = Math.abs(velocity.x);
            isMovingLeft = false;
        } else if (position.x >= xmax) {
            position.x = xmax;
            velocity.x = -Math.abs(velocity.x);
            isMovingLeft = true;
        }
    }

    private void moveVertically(float deltaTime) {
        position.y += velocity.y * deltaTime;
        if (position.y <= ymin) {
            position.y = ymin;
            velocity.y = Math.abs(velocity.y);
            isMovingDown = false;
        } else if (position.y >= ymax) {
            position.y = ymax;
            velocity.y = -Math.abs(velocity.y);
            isMovingDown = true;
        }
    }

    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame = isMovingHorizontally
            ? (isMovingLeft ? walkLeftAnimation : walkRightAnimation).getKeyFrame(stateTime, true)
            : (isMovingDown ? walkLeftAnimation : walkRightAnimation).getKeyFrame(stateTime, true);

        float width = currentFrame.getRegionWidth() * scale;
        float height = currentFrame.getRegionHeight() * scale;
        batch.draw(currentFrame, position.x, position.y, width, height);
    }

    public void dispose() {
        for (TextureRegion frame : walkLeftAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
        for (TextureRegion frame : walkRightAnimation.getKeyFrames()) {
            frame.getTexture().dispose();
        }
    }
}
