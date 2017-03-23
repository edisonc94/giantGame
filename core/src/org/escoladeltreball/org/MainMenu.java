package org.escoladeltreball.org;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by iam49812043 on 3/13/17.
 */

public class MainMenu implements Screen, ContactListener {
    private GameMain game;
    private Texture bg;
    private Player player;
    private Cloud c;
    private Cloud c2;
    private World world;

    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public MainMenu(GameMain game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,GameInfo.WIDTH/GameInfo.PPM,GameInfo.HEIGHT/GameInfo.PPM);
        camera.position.set(GameInfo.HALF_WIDTH,GameInfo.HALF_HEIGHT,0);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -9.8f), true);

        world.setContactListener(this);

        bg = new Texture("7 - Backgrounds/Game BG.png");
        player = new Player(world, "0 - Player/Player 1.png", GameInfo.HALF_WIDTH, GameInfo.HALF_HEIGHT+250);


        c = new Cloud(world,"4 - Clouds/Cloud 1.png","nube1", GameInfo.HALF_WIDTH+30,GameInfo.HALF_HEIGHT);
        c2 = new Cloud(world,"4 - Clouds/Cloud 2.png","nube2", GameInfo.HALF_WIDTH-100,GameInfo.HALF_HEIGHT+300);



    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().draw(player, player.getX(), player.getY()-player.getHeight()/2);
        game.getBatch().draw(c, c.getX()/1.8f+30, c.getY()-c.getHeight()/2);
        game.getBatch().draw(c2, (c2.getX()/1.8f)-100, (c.getY()-c.getHeight()/2)+300);
        game.getBatch().end();

        debugRenderer.render(world,camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        player.updatePlayer();
        
    }

    private void update(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            player.getBody().applyLinearImpulse(new Vector2(-1,0),
                    player.getBody().getWorldCenter(),true);
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            player.getBody().applyLinearImpulse(new Vector2(1,0),
                    player.getBody().getWorldCenter(),true);
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.getBody().applyLinearImpulse(new Vector2(0,3),
                    player.getBody().getWorldCenter(),true);
        }
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
        bg.dispose();
        player.getTexture().dispose();
    }


    @Override
    public void beginContact(Contact contact) {

        Fixture firstBody;
        Fixture secondBody;



        if(contact.getFixtureA().getUserData().equals("Player")){
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();

        } else{
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureA();
        }

        System.out.println("CONTACT: [First Body: "+firstBody.getUserData()+", Second Body: "+secondBody.getUserData()+"]");

    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}