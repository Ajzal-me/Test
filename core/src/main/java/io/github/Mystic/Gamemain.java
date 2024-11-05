package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Music;

public class Gamemain implements Screen {

    private Music back;
    private Player player;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Game game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private final float UNIT_SCALE = 1/32f;
    private float mapWidth;
    private float mapHeight;

    public Gamemain(Game game) {
        this.game = game;
        batch = new SpriteBatch();

        back = Gdx.audio.newMusic(Gdx.files.internal("Background.mp3"));
        back.setLooping(true);
        back.play();
        // Load map
        map = new TmxMapLoader().load("Map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);

        // Get map dimensions
        mapWidth = map.getProperties().get("width", Integer.class)-6.55f;
        mapHeight = map.getProperties().get("height", Integer.class);

        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 25, 15);

        // Start player position
        player = new Player(10, 10);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Update player
        player.update(Gdx.graphics.getDeltaTime());

        // Clamp player to map bounds
        player.getPosition().x = MathUtils.clamp(player.getPosition().x, 1, mapWidth - 2);
        player.getPosition().y = MathUtils.clamp(player.getPosition().y, 1, mapHeight - 2);

        // Update and clamp camera
        camera.position.x = MathUtils.clamp(player.getPosition().x,
            camera.viewportWidth / 2,
            mapWidth - camera.viewportWidth / 2);
        camera.position.y = MathUtils.clamp(player.getPosition().y,
            camera.viewportHeight / 2,
            mapHeight - camera.viewportHeight / 2);
        camera.update();

        // Render
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
           back.stop();
            game.setScreen(new Main(game));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

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
        map.dispose();
        mapRenderer.dispose();
        back.dispose();
    }
}
