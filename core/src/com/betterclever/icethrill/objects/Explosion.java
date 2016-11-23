package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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

    }



}
