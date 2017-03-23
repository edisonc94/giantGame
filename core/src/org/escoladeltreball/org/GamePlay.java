package org.escoladeltreball.org;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by iam49812043 on 3/23/17.
 */

public class GamePlay implements Screen{
    private GameMain game;
    private Sprite[] backgrounds;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float lastPosition;


    public GamePlay(GameMain game) {
        this.game = game;
        camera = new OrthographicCamera(GameInfo.WIDTH,GameInfo.HEIGHT);
        camera.position.set(GameInfo.HALF_WIDTH,GameInfo.HALF_HEIGHT,0);
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT,camera);
        createBackgrounds();
    }

    public void createBackgrounds(){
        backgrounds = new Sprite[3];
        for (int i = 0; i < backgrounds.length;i++){
            backgrounds[i] = new Sprite(new Texture("7 - Backgrounds/Game BG.png"));
            backgrounds[i].setPosition(0,-(i*backgrounds[i].getHeight()));
            lastPosition = Math.abs(backgrounds[i].getY());
        }
    }

    public void drawBackgrounds(){
        for (int i = 0; i < backgrounds.length; i++) {
            game.getBatch().draw(backgrounds[i], backgrounds[i].getX(),backgrounds[i].getY());
        }
    }
    
    public void checkBackgroundOutOfBounds(){
        for (int i = 0; i < backgrounds.length; i++) {
            if(backgrounds[i].getY() - backgrounds[i].getHeight() / 2f -5> camera.position.y){
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
        update(delta);
        game.getBatch().end();
        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();

    }

    public void update(float delta){
        moveCamera();
        checkBackgroundOutOfBounds();
    }

    private void moveCamera() {
        camera.position.y -= 5;
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
