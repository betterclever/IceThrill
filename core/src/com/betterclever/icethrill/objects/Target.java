package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public abstract class Target {


    private Vector2 position;
    public int health;
    public ShapeRenderer renderer;

    public float timePassed = 0;

    public Target(Vector2 position, ShapeRenderer renderer) {
        this.position = position;
        this.renderer = renderer;
    }

    public abstract void render(float delta);

    public Vector2 getPosition() {
        return position;
    }

}
