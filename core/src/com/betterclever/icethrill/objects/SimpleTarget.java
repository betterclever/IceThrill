package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SimpleTarget extends Target {


    public SimpleTarget(Vector2 position, SpriteBatch spriteBatch) {
        super(position, spriteBatch);
        health = Constants.SIMPLE_TARGET_HEALTH;
    }

    @Override
    public void render(float delta) {

        timePassed += delta ;

        Gdx.app.log("Target",getPosition().toString());

        TextureRegion tr;

        int q = ( (int) (timePassed * 25)) % 7;

        tr = Constants.t[q];

        Gdx.app.log("time", String.valueOf(q));

        spriteBatch.begin();
        spriteBatch.draw(tr,getPosition().x,getPosition().y,50,80);
        spriteBatch.end();

    }
}
