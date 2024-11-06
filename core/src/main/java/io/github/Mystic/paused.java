package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class paused implements Screen {

    private Sound click;
    private Music back;

    private SpriteBatch batch;
    private Texture backgroundtexture;
    private ScreenViewport viewport;
    private Stage stage;
    private ImageButton settings;
    private ImageButton menuback;

    private static final float settingsX = 790f;
    private static final float settingsY = 422f;
    private static final float menuX = 770f;
    private static final float menuY = 286f;
    paused(Game game)
    {
        click = Gdx.audio.newSound(Gdx.files.internal("Click.mp3"));
        back = Gdx.audio.newMusic(Gdx.files.internal("Background_menu.mp3"));
        back.setLooping(true);
        back.play();

        batch = new SpriteBatch();
        viewport = new ScreenViewport();
        backgroundtexture = new Texture("Game Paused (2).jpg");
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        Texture level5texture = new Texture("Screenshot 2024-11-06 123828.png");
        TextureRegionDrawable level5drawable = new TextureRegionDrawable(level5texture);
        settings = new ImageButton(level5drawable);
        settings.setPosition(settingsX, settingsY);

        Texture menubacktexture = new Texture("Screenshot 2024-11-06 123838.png");
        TextureRegionDrawable menudrawable = new TextureRegionDrawable(menubacktexture);
        menuback = new ImageButton(menudrawable);
        menuback.setPosition(menuX,menuY);


        stage.addActor(settings);
        stage.addActor(menuback);


        settings.addListener(new ClickListener()
        {
            public void clicked(InputEvent event,float x,float y)
            {
                System.out.println("I am settings");
                click.play();
                game.setScreen(new Settings(game,back));
            }
        });
        menuback.addListener(new ClickListener()
        {
            public void clicked(InputEvent event,float x,float y)
            {
                System.out.println("I am mainback");
                click.play();
                back.stop();
                game.setScreen(new Main(game));
            }
        });
    }

    @Override
    public void render(float delta)
    {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundtexture,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

    }
    @Override
    public void dispose() {
        batch.dispose();
        backgroundtexture.dispose();
        stage.dispose();
        settings.remove();
        menuback.remove();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width,int height) {}
    @Override
    public void hide() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
}
