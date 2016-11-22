package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public abstract class Target {


    private Vector2 position;
    public int health;
    public SpriteBatch spriteBatch;

    public float timePassed = 0;

    public Target(Vector2 position, SpriteBatch spriteBatch) {
        this.position = position;
        this.spriteBatch = spriteBatch;
    }

    public abstract void render(float delta);

    public Vector2 getPosition() {
        return position;
    }

}
