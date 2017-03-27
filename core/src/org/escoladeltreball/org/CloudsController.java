package org.escoladeltreball.org;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by iam49812043 on 3/27/17.
 */

public class CloudsController {

    private World world;
    private Array<Cloud> clouds = new Array<Cloud>();
    private final float DISTANCE_BETWEEN_CLOUDS = 250f;
    private float minX, maxX;
    private Random random = new Random();


    public CloudsController(World world) {
        this.world = world;
        minX = GameInfo.HALF_WIDTH - 210;
        maxX = GameInfo.HALF_WIDTH + 50;
        createClouds();
        positionClouds();
    }

    private void createClouds() {

        for (int i = 0; i < 2; i++) {
            clouds.add(new Cloud(world, "4 - Clouds/Dark Cloud.png", "Dark Cloud", GameInfo.HALF_WIDTH, GameInfo.HALF_WIDTH));

        }

        int index = 1;
        for (int i = 0; i < 6; i++) {
            clouds.add(new Cloud(world, "4 - Clouds/Cloud " + index + ".png", "Cloud", GameInfo.HALF_WIDTH, GameInfo.HALF_WIDTH));
            index++;
            if (index == 4) {
                index = 1;
            }
        }
        clouds.shuffle();

    }

    public void positionClouds() {
        while (clouds.get(0).getCloudName().equals("Dark Cloud")) {
            clouds.shuffle();
        }
        float positionY = GameInfo.HALF_HEIGHT;
        int controlX = 0;
        for (Cloud c : clouds) {
            float tempX = 0;
            if (controlX == 0) {
                tempX = randomBetweenNumbers(maxX-150,maxX);
                controlX = 1;
            } else if (controlX == 1) {
                tempX = randomBetweenNumbers(minX+100,minX);
                controlX = 0;
            }
            c.setSpritePosition(tempX, positionY);
            positionY -= DISTANCE_BETWEEN_CLOUDS;
        }
    }

    public void drawClouds(SpriteBatch batch) {
        for (Cloud c : clouds) {
            batch.draw(c, c.getX(),
                    c.getY());
        }
    }

    private float randomBetweenNumbers(float max, float min) {
        return random.nextFloat() * (max - min) + min;
    }
}
































