package org.escoladeltreball.org;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by iam49812043 on 3/16/17.
 */

public class Cloud extends Sprite {

    private World world;
    private Body body;
    private String cloudName;

    public Cloud(World world, String name,String cloudName, float x, float y) {
        super(new Texture(name));
        this.world = world;
        this.cloudName = cloudName;
//        this.setPosition(
//                x - this.getWidth() / 2,
//                y - this.getHeight() / 2);
        setSpritePosition(x,y);
        createBody();
    }

    public void setSpritePosition(float x, float y){
        setPosition(x,y);
        createBody();
    }

    public String getCloudName() {
        return cloudName;
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        //Static type no es afectado por gravedad ni fuerzas
        //Kinematic boy no gravedad si fuerzas
        //Dynamic body si gravedad si fuezas
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(getX()/GameInfo.PPM,getY()/GameInfo.PPM);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2)/GameInfo.PPM-0.18f,(getHeight()/2)/GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Cloud");
        //fixture.setSensor(true);

        shape.dispose();
    }
}
