package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SuperBall extends Ball{

    public SuperBall(ShapeRenderer renderer, Vector2 velocity) {
        super(Constants.RADIUS_SUPERBALL,renderer,velocity);
    }

    @Override
    public void render() {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);

        renderer.circle(position.x,position.y,getRadius(),64);

        renderer.end();

    }
}
