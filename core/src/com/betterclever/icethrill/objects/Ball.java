package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;
import com.betterclever.icethrill.Movement;

/**
 * Created by betterclever on 21/11/16.
 */

public abstract class Ball implements Movement {

    private int radius;
    public Vector2 position;
    public Vector2 velocity;

    protected ShapeRenderer renderer;

    public Ball(int radius, ShapeRenderer renderer, Vector2 velocity){
        this.radius = radius;
        this.renderer = renderer;
        this.velocity = velocity;
        position = new Vector2(50,50);
    }

    public int getRadius() {
        return radius;
    }

    public abstract void render();


    @Override
    public void updateVelocity(float delta){
        velocity = velocity.mulAdd(Constants.ACC_G,delta);
    }

    @Override
    public void updatePosition(float delta){
        position = position.mulAdd(velocity,delta);
    }

}
