package org.escoladeltreball.org;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class mainGame extends Game {

    //UN SPRITEBATCH POR GAME
    private SpriteBatch batch;
//    Texture img;
//    Texture player1;

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void create() {
        System.out.println("Create");
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
//        img = new Texture("7 - Backgrounds/Game BG.png");
//        player1 = new Texture("0 - Player/Player 1.png");


    }

    @Override
    public void render() {
//        System.out.println("Render");
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//
//        batch.draw(img, 0, 0);
//        batch.draw(player1, GameInfo.HALF_WIDTH - player1.getWidth()/2, GameInfo.HALF_HEIGHT - player1.getHeight()/2);
//
//        batch.end();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        img.dispose();
    }
}
