package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class IceBall extends Ball {

    public IceBall(ShapeRenderer renderer, Vector2 velocity, Vector2 position) {
        super(Constants.RADIUS_ICEBALL,renderer,velocity, position);
    }

    @Override
    public void render() {

        //Gdx.app.log("Iceball position", position.toString());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);

        renderer.circle(position.x,position.y,getRadius(),64);

        renderer.end();

    }
}
