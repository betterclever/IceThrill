package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 23/11/16.
 */

public class Explosion {

    public Vector2 position;
    private SpriteBatch spriteBatch;
    private float timePassed;

    public Explosion(Vector2 position,SpriteBatch spriteBatch){
        this.position = position;
        this.spriteBatch = spriteBatch;
    }

    public float getTimePassed() {
        return timePassed;
    }

    public void render(float delta){
        timePassed += delta;

        Gdx.app.log("tp", String.valueOf(timePassed));

        TextureRegion tr;
        int q = ( (int) (timePassed * 10)) % Constants.EXPLOSION_TEXTURE_REGIONS.length;

        Gdx.app.log("q", String.valueOf(q));
        tr = Constants.EXPLOSION_TEXTURE_REGIONS[q+3];

        spriteBatch.begin();
        spriteBatch.draw(tr,position.x ,position.y,50,80);
        spriteBatch.end();

    }



}
