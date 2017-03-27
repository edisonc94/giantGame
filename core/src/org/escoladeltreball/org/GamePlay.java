package org.escoladeltreball.org;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by iam49812043 on 3/23/17.
 */

public class GamePlay implements Screen {
    private GameMain game;
    private Sprite[] backgrounds;
    private OrthographicCamera camera;
    private Viewport viewport;

    private OrthographicCamera box2dCamera;
    private Box2DDebugRenderer debugRenderer;

    private World world;

    private float lastPosition;

    //TODO delete
//    Cloud c;
    CloudsController cloudsController;


    public GamePlay(GameMain game) {
        this.game = game;
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        camera.position.set(GameInfo.HALF_WIDTH, GameInfo.HALF_HEIGHT, 0);
        viewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);
        box2dCamera = new OrthographicCamera();
        box2dCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2dCamera.position.set(GameInfo.HALF_WIDTH, GameInfo.HALF_HEIGHT, 0);
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, GameInfo.GRAVITY), true);
//        c = new Cloud(world,"4 - Clouds/Cloud 1.png","Cloud 1",GameInfo.HALF_WIDTH,GameInfo.HALF_WIDTH);
        cloudsController = new CloudsController(world);
        createBackgrounds();
    }

    public void createBackgrounds() {
        backgrounds = new Sprite[3];
        for (int i = 0; i < backgrounds.length; i++) {
            backgrounds[i] = new Sprite(new Texture("7 - Backgrounds/Game BG.png"));
            backgrounds[i].setPosition(0, -(i * backgrounds[i].getHeight()));
            lastPosition = Math.abs(backgrounds[i].getY());
        }
    }

    public void drawBackgrounds() {
        for (int i = 0; i < backgrounds.length; i++) {
            game.getBatch().draw(backgrounds[i], backgrounds[i].getX(), backgrounds[i].getY());
        }
    }

    public void checkBackgroundOutOfBounds() {
        for (int i = 0; i < backgrounds.length; i++) {
            if (backgrounds[i].getY() - backgrounds[i].getHeight() / 2f - 5 > camera.position.y) {
                float newPosition = backgrounds[i].getHeight() + lastPosition;
                backgrounds[i].setPosition(0, -newPosition);
                lastPosition = newPosition;
            }
        }
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        drawBackgrounds();
//        game.getBatch().draw(c,c.getX()/1.2f,c.getY()/1.1f);
        cloudsController.drawClouds(game.getBatch());
        update(delta);
        game.getBatch().end();

        debugRenderer.render(world, box2dCamera.combined);

        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();

    }

    public void update(float delta) {
        moveCamera();
        checkBackgroundOutOfBounds();
    }

    private void moveCamera() {
        camera.position.y -= 1;
    }


    @Override
    public void resize(int width, int height) {

    }


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

    }
}
