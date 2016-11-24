package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SimpleTarget extends Target {


    public SimpleTarget(Vector2 position, SpriteBatch spriteBatch) {
        super(position, spriteBatch);
        health = Constants.SIMPLE_TARGET_HEALTH;
        bounds = new Rectangle(position.x,position.y,50,50);
        hitpoints = Constants.SIMPLE_TARGET_HEALTH/2;
    }

    @Override
    public void render(float delta) {

        timePassed += delta ;

        //Gdx.app.log("Target",getPosition().toString());

        TextureRegion tr;
        int q = ( (int) (timePassed * 25)) % 7;
        tr = Constants.TARGET_TEXTURE_REGIONS[q];

        //Gdx.app.log("time", String.valueOf(q));

        spriteBatch.begin();
        spriteBatch.draw(tr,getPosition().x,getPosition().y,50,80);
        spriteBatch.end();

    }
}
