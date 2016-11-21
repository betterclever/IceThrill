package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.betterclever.icethrill.objects.Ball;
import com.betterclever.icethrill.objects.IceBall;
import com.betterclever.icethrill.objects.SimpleTarget;
import com.betterclever.icethrill.objects.SuperBall;
import com.betterclever.icethrill.objects.Target;
import com.betterclever.icethrill.objects.Cannon;
import java.util.ArrayList;

/**
 * Created by betterclever on 20/11/16.
 */

public class FieldOfPlay extends InputAdapter implements Screen {

    ExtendViewport viewport;
    SpriteBatch spriteBatch;
    ShapeRenderer renderer;
    Cannon cannon;
    Vector2 accG;

    private static final int inVelX = 10;
    private static final int inVelY = 10;

    float timePassed = 0;

    Vector2 position;
    Vector2 velocity;

    ArrayList<Ball> balls;

    DelayedRemovalArray<Target> targets;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        accG = new Vector2(0,-50);
        position = new Vector2(10,10);

        balls = new ArrayList<Ball>();

        cannon = new Cannon(renderer);

        targets = new DelayedRemovalArray<Target>();

        for (int i = 0; i < 30; i++) {

            velocity = new Vector2( (float) Math.random()*200, (float) Math.random()*200);

            if(i%2 == 0){
                balls.add(new SuperBall(renderer,velocity));
            }
            else {
                balls.add(new IceBall(renderer,velocity));
            }

        }

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        //Gdx.app.log("Targets", String.valueOf(targets.size));

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        for (Ball b: balls){
            b.updatePosition(delta);
            b.updateVelocity(delta);
            b.render();
        }

        for (Target t : targets) {
            t.render(delta);
        }


        timePassed += delta;

        if(timePassed % 5 < 0.02){
            targets.add(new SimpleTarget(
                    new Vector2(
                            (float) ( 2*viewport.getScreenWidth()/3 +  Math.random() * viewport.getScreenWidth() / 2 ),
                            (float) Math.random()* viewport.getScreenHeight()/3),
                    renderer));
        }

        //Gdx.app.log("World h:W",viewport.getScreenHeight() + " " + viewport.getScreenWidth());

        targets.begin();

        for (int i = 0; i < targets.size; i++) {
            if(targets.get(i).timePassed > 5){
                targets.removeIndex(i);
            }
        }
        targets.end();

        cannon.render();

        //Vector2 worldParameters = new Vector2(100,100);

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(worldClick);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector2  targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(targetPosition);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Gdx.app.log("I am", " called");
        Vector2  targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(targetPosition);

        return true;
    }
}
