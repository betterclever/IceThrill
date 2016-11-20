package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.betterclever.icethrill.objects.Ball;
import com.betterclever.icethrill.objects.IceBall;
import com.betterclever.icethrill.objects.SuperBall;

import java.util.ArrayList;

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

    ArrayList<Ball> balls;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(100,100);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        accG = new Vector2(0,-50);
        position = new Vector2(10,10);

        balls = new ArrayList<Ball>();

        for (int i = 0; i < 30; i++) {

            velocity = new Vector2( (float) Math.random()*200, (float) Math.random()*200);

            if(i%2 == 0){
                balls.add(new SuperBall(renderer,velocity));
            }
            else {
                balls.add(new IceBall(renderer,velocity));
            }

        }

        //Gdx.input.setInputProcessor((InputProcessor) this);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        for (Ball b: balls){
            b.updatePosition(delta);
            b.updateVelocity(delta);
            b.render();
        }

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
