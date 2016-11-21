package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SuperBall extends Ball{

    TextureRegion textureRegion;
    public SuperBall(SpriteBatch spriteBatch, Vector2 velocity, Vector2 position) {
        super(Constants.RADIUS_SUPERBALL,spriteBatch,velocity,position);
        textureRegion = new TextureRegion(new Texture("icicle.png"));
    }

    @Override
    public void render() {

        spriteBatch.begin();
        spriteBatch.draw(textureRegion,position.x,position.y,getRadius(),getRadius(),2*getRadius(),2*getRadius(),1,1,angle);
        spriteBatch.end();
    }
}
