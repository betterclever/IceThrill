package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by betterclever on 20/11/16.
 */

public class FieldOfPlay implements Screen {

    ExtendViewport viewport;
    SpriteBatch spriteBatch;
    ShapeRenderer renderer;

    Vector2 accG;

    private static final int inVelX = 10;
    private static final int inVelY = 10;

    Vector2 position;
    Vector2 velocity;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(100,100);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        accG = new Vector2(0,-50);
        velocity = new Vector2(100,100);
        position = new Vector2(10,10);
        //Gdx.input.setInputProcessor((InputProcessor) this);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        velocity.mulAdd(accG,delta);
        position.mulAdd(velocity,delta);

        Gdx.app.log("Delta", String.valueOf(delta));
        renderer.setColor(Color.CHARTREUSE);

        renderer.circle(position.x,position.y,10,50);
        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
