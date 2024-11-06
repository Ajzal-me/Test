package io.github.Mystic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import box2dLight.RayHandler;
import box2dLight.PointLight;

public class Gamemain implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Game game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Player player;

    // Lighting components
    private RayHandler rayHandler;
    private PointLight playerLight;
    private World world;

    private final float UNIT_SCALE = 1/32f;
    private float mapWidth;
    private float mapHeight;

    // Variables for flickering
    private float flickerBaseRadius = 7f;
    private float flickerTime = 0f;
    private float flickerDelay = 0.2f; // Increased to slow flicker frequency

    public Gamemain(Game game) {
        this.game = game;
        batch = new SpriteBatch();

        // Load map
        map = new TmxMapLoader().load("Map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);

        // Get map dimensions
        mapWidth = map.getProperties().get("width", Integer.class) - 6.55f;
        mapHeight = map.getProperties().get("height", Integer.class);

        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 25, 15);

        // Start player position
        player = new Player(10, 10);

        // Initialize Box2D world
        world = new World(new Vector2(0, 0), true);

        // Set up lighting
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(10f);  // Adjust the ambient light level as needed

        // Create a point light attached to the player with a whitish color
        playerLight = new PointLight(rayHandler, 128, new Color(256f, 256f, 256f, 5f), flickerBaseRadius, player.getPosition().x, player.getPosition().y + 2); // Light positioned above the player
        playerLight.setSoftnessLength(1f);  // Smooth fall-off for a natural look
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

        // Flicker effect with slower, periodic flickering
        flickerTime += delta;
        if (flickerTime >= flickerDelay) {
            float flickerRadius = flickerBaseRadius + MathUtils.random(-0.5f, 0.5f);  // Slightly larger fluctuation for more visible flicker
            playerLight.setDistance(flickerRadius);
            flickerTime = 0f;  // Reset flicker timer
        }

        // Update player light position to follow the player
        playerLight.setPosition(player.getPosition().x+0.8f, player.getPosition().y + 1.15f); // Light positioned above the player

        // Render map and batch
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        batch.end();

        // Render lighting
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new paused(game));
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
        rayHandler.dispose();
        world.dispose();
    }
}
