package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;

public class Settings implements Screen {
    private final Game game;
    private final Music backgroundMusic;
    private Stage stage;
    private Slider volumeSlider;
    private Label volumeLabel;
    private ImageButton backButton;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private Texture headerTexture;
    private Image headerImage;

    public Settings(Game game, Music backgroundMusic) {
        this.game = game;
        this.backgroundMusic = backgroundMusic;

        // Initialize the SpriteBatch and background texture
        batch = new SpriteBatch();
        backgroundTexture = new Texture("settings_background.jpg"); // Background image
        headerTexture = new Texture("settings.png"); // Ensure this image file exists in assets
        headerImage = new Image(new TextureRegionDrawable(new TextureRegion(headerTexture)));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Initialize skin for UI elements
        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Ensure this skin file exists in assets

        // Volume slider setup - enlarged size
        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(backgroundMusic.getVolume());
        volumeSlider.setWidth(600); // Enlarge the slider width
        volumeSlider.addListener(event -> {
            backgroundMusic.setVolume(volumeSlider.getValue());
            return false;
        });

        // Volume label setup - enlarged, bold, white
        Label.LabelStyle volumeLabelStyle = new Label.LabelStyle(skin.getFont("default-font"), Color.WHITE);
        volumeLabelStyle.font.getData().setScale(3.5f); // Increase font size
        volumeLabel = new Label("Volume", volumeLabelStyle);

        // Back button setup - smaller and positioned in the lower-left corner
        Texture backTexture = new Texture("back1.png"); // Ensure this texture exists in assets
        TextureRegionDrawable backDrawable = new TextureRegionDrawable(new TextureRegion(backTexture));
        backButton = new ImageButton(backDrawable);
        backButton.setSize(120, 120); // Reduce the size of the button
        backButton.setPosition(160, 130); // Lower-left corner padding
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Main(game)); // Go back to the main screen
            }
        });

        // Layout for volume controls and header image
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(headerImage).colspan(1).padBottom(150); // Add header image at the top
        table.row();
        table.add(volumeLabel).padBottom(10);
        table.row();
        table.add(volumeSlider).width(volumeSlider.getWidth()).padBottom(100);

        stage.addActor(table);
        stage.addActor(backButton); // Add the back button separately for positioning
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Draw the background texture
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the UI stage on top of the background
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        headerTexture.dispose();
    }
}
