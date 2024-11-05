package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main implements Screen{
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final ScreenViewport viewport;
    private final Stage stage;
    private final ImageButton playbutton;
    private final ImageButton select_level;
    private final ImageButton settings;
    private final ImageButton exit;

    private static final float PLAY_BUTTON_X = 788f;
    private static final float PLAY_BUTTON_Y = 670f;

    private static final float select_levelX = 788f;
    private static final float select_levelY = 525f;

    private static final float settingsX = 788f;
    private static final float settingsY = 380f;

    private static final float exitX = 788f;
    private static final float exitY = 230f;

    public Main(Game game) {

        batch = new SpriteBatch();
        backgroundTexture = new Texture("main.jpg");
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        Texture playbuttontexture = new Texture("play.png");
        TextureRegionDrawable playbuttondrawable = new TextureRegionDrawable(new TextureRegion(playbuttontexture));
        playbutton = new ImageButton(playbuttondrawable);
        playbutton.setPosition(PLAY_BUTTON_X,PLAY_BUTTON_Y);

        Texture selectleveltexture = new Texture("select-level.png");
        TextureRegionDrawable selectleveldrawable = new TextureRegionDrawable(new TextureRegion(selectleveltexture));
        select_level = new ImageButton(selectleveldrawable);
        select_level.setPosition(select_levelX,select_levelY);

        Texture settingtexture = new Texture("settings.png");
        TextureRegionDrawable settingdrawable = new TextureRegionDrawable(new TextureRegion(settingtexture));
        settings = new ImageButton(settingdrawable);
        settings.setPosition(settingsX,settingsY );

        Texture exittexture = new Texture("exit.png");
        TextureRegionDrawable exitdrawable = new TextureRegionDrawable(exittexture);
        exit = new ImageButton(exitdrawable);
        exit.setPosition(exitX,exitY);

        playbutton.addListener(new ClickListener()
        {
                public void clicked(InputEvent event, float x, float y)
                {
                        System.out.println("I am being Clicked(play)");
                        game.setScreen(new Gamemain(game));
                }
        });
        select_level.addListener(new ClickListener()
        {
                public void clicked(InputEvent event,float x,float y)
                {
                        System.out.println("I am also being clicked(select_level)");
                        game.setScreen(new Select_level(game));
                }
        });
        settings.addListener(new ClickListener()
        {
                public void clicked(InputEvent event,float x,float y)
                {
                        System.out.println("This is settings(settings)");
                }
        });
        exit.addListener(new ClickListener()
        {
                public void clicked(InputEvent event,float x,float y)
                {
                        Gdx.app.exit();
                }
        });

       stage.addActor(playbutton);
       stage.addActor(select_level);
       stage.addActor(settings);
       stage.addActor(exit);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        batch.draw(backgroundTexture, 8, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        stage.dispose();
        playbutton.remove();
        select_level.remove();
        settings.remove();
        exit.remove();

    }
}
