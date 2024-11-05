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


public class Select_level implements Screen {

    private Sound click;

    private SpriteBatch batch;
    private Texture backgroundtexture;
    private ScreenViewport viewport;
    private Stage stage;
    private ImageButton level1;
    private ImageButton level2;
    private ImageButton level3;
    private ImageButton level4;
    private ImageButton level5;
    private ImageButton menuback;
    private static final float level1X = 793f;
    private static final float level1Y = 713f;
    private static final float level2X = 793f;
    private static final float level2Y = 572f;
    private static final float level3X = 793f;
    private static final float level3Y = 430f;
    private static final float level4X = 793f;
    private static final float level4Y = 292f;
    private static final float level5X = 793f;
    private static final float level5Y = 152f;
    private static final float menuX = 88f;
    private static final float menuY = 60f;
    Select_level(Game game)
    {
            click = Gdx.audio.newSound(Gdx.files.internal("Click.mp3"));

            batch = new SpriteBatch();
            viewport = new ScreenViewport();
            backgroundtexture = new Texture("SelectlevelBack.png");
            stage = new Stage(viewport);
            Gdx.input.setInputProcessor(stage);

            Texture level1texture = new Texture("level1.png");
            TextureRegionDrawable level1drawable = new TextureRegionDrawable(level1texture);
            level1 = new ImageButton(level1drawable);
            level1.setPosition(level1X,level1Y);

            Texture level2texture = new Texture("level2.png");
            TextureRegionDrawable level2drawable = new TextureRegionDrawable(level2texture);
            level2 = new ImageButton(level2drawable);
            level2.setPosition(level2X,level2Y);

            Texture level3texture = new Texture("level3.png");
            TextureRegionDrawable level3drawable = new TextureRegionDrawable(level3texture);
            level3 = new ImageButton(level3drawable);
            level3.setPosition(level3X,level3Y);

            Texture level4texture = new Texture("level4.png");
            TextureRegionDrawable level4drawable = new TextureRegionDrawable(level4texture);
            level4 = new ImageButton(level4drawable);
            level4.setPosition(level4X,level4Y);

            Texture level5texture = new Texture("level5.png");
            TextureRegionDrawable level5drawable = new TextureRegionDrawable(level5texture);
            level5 = new ImageButton(level5drawable);
            level5.setPosition(level5X,level5Y);

            Texture menubacktexture = new Texture("mainbackmenu.png");
            TextureRegionDrawable menudrawable = new TextureRegionDrawable(menubacktexture);
            menuback = new ImageButton(menudrawable);
            menuback.setPosition(menuX,menuY);

            stage.addActor(level1);
            stage.addActor(level2);
            stage.addActor(level3);
            stage.addActor(level4);
            stage.addActor(level5);
            stage.addActor(menuback);

            level1.addListener(new ClickListener()
            {
               public void clicked(InputEvent event,float x,float y)
               {
                    System.out.println("I am level1");
                    click.play();
               }
            });
            level2.addListener(new ClickListener()
            {
                public void clicked(InputEvent event,float x,float y)
                {
                    System.out.println("I am level2");
                    click.play();
                }
            });
            level3.addListener(new ClickListener()
            {
                public void clicked(InputEvent event,float x,float y)
                {
                    System.out.println("I am level3");
                    click.play();
                }
            });
            level4.addListener(new ClickListener()
            {
                public void clicked(InputEvent event,float x,float y)
                {
                    System.out.println("I am level4");
                    click.play();
                }
            });
            level5.addListener(new ClickListener()
            {
                public void clicked(InputEvent event,float x,float y)
                {
                    System.out.println("I am level5");
                    click.play();
                }
            });
            menuback.addListener(new ClickListener()
            {
                public void clicked(InputEvent event,float x,float y)
                {
                    System.out.println("I am mainback");
                    click.play();
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
        level1.remove();
        level2.remove();
        level3.remove();
        level4.remove();
        level5.remove();
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
