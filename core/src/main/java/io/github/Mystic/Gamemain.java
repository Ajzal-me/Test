package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Gamemain implements Screen {
    private Player player;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Game game;
    private Texture top;

    public Gamemain(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(120, 130);
        top = new Texture("Top View Main (1).png");
    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        player.update(Gdx.graphics.getDeltaTime());
        camera.position.set(player.getPosition().x + 16, player.getPosition().y + 16, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined); // Set the projection matrix for the batch
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new Main(game));
        }
        batch.begin();
        player.render(batch);
        batch.draw(top,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
    }
    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
