package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.utilities.Constants;
import com.betterclever.icethrill.Movement;

/**
 * Created by betterclever on 21/11/16.
 */

public abstract class Ball implements Movement {

    private int radius;
    public Vector2 position;
    public Vector2 velocity;
    public float angle = 0;
    private int damage;

    public Rectangle bounds;

    protected SpriteBatch spriteBatch;

    public Ball(int radius, SpriteBatch spriteBatch, Vector2 velocity, Vector2 position, int damage){
        this.radius = radius;
        this.spriteBatch = spriteBatch;
        this.velocity = velocity;
        this.position = position;
        this.damage = damage;

        bounds = new Rectangle();

        bounds.setWidth(2*getRadius());
        bounds.setHeight(2*getRadius());
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

    @Override
    public void updateAngle(float delta) {angle = angle - delta*100;}

    public void updateAll(float delta){
        updatePosition(delta);
        updateVelocity(delta);
        updateAngle(delta);
    }

    public int getDamage() {
        return damage;
    }
}
