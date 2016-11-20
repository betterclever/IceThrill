package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class SimpleTarget extends Target {


    public SimpleTarget(Vector2 position, ShapeRenderer renderer) {
        super(position, renderer);
        health = Constants.SIMPLE_TARGET_HEALTH;
    }

    @Override
    public void render(float delta) {

        timePassed += delta ;

        Gdx.app.log("timePassed", String.valueOf(timePassed));
        Gdx.app.log("position",getPosition().toString());

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.CYAN);

        renderer.circle(getPosition().x,getPosition().y,Constants.RADIUS_ICEBALL);

        renderer.end();
    }
}
