package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SuperTarget extends Target {


    public SuperTarget(Vector2 position, SpriteBatch spriteBatch) {
        super(position, spriteBatch);
        health = Constants.SUPER_TARGET_HEALTH;
        bounds = new Rectangle(position.x+10,position.y,60,100);
        hitpoints = Constants.SUPER_TARGET_HEALTH/2;
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
        spriteBatch.draw(tr,getPosition().x,getPosition().y,80,130);
        spriteBatch.end();

    }
}
