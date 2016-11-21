package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class IceBall extends Ball {

    TextureRegion background;

    public IceBall(SpriteBatch spriteBatch, Vector2 velocity, Vector2 position) {
        super(Constants.RADIUS_ICEBALL,spriteBatch,velocity, position);

        background = new TextureRegion( new Texture("planet.png") );
    }

    @Override
    public void render() {

        //Gdx.app.log("Iceball position", position.toString());
        spriteBatch.begin();
        spriteBatch.draw(background,position.x,position.y,getRadius(),getRadius(),2*getRadius(),2*getRadius(),1,1,angle);
        spriteBatch.end();

    }
}
