package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    ShapeRenderer renderer;
    Cannon cannon;
    Vector2 accG;

    private static final int inVelX = 10;
    private static final int inVelY = 10;

    float timePassed = 0;

    Vector2 position;
    Vector2 velocity;

    Texture background;
    SpriteBatch spriteBatch;

    float angleOnTouchDown = 0.0f;

    DelayedRemovalArray<Ball> balls;

    DelayedRemovalArray<Target> targets;

    boolean dragging = false;
    Animation anim;

    Sprite sprite;
    //Camera camera;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        accG = new Vector2(0,-50);
        position = new Vector2(10,10);

        balls = new DelayedRemovalArray<Ball>();

        cannon = new Cannon(spriteBatch);

        targets = new DelayedRemovalArray<Target>();

        background = new Texture("volcano.png");
        sprite = new Sprite(background);

        //viewport.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(this);
        //anim = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("assets/background.gif").read());
    }


    @Override
    public void render(float delta) {

        //Gdx.app.log("Targets", String.valueOf(targets.size));

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport.apply();

        spriteBatch.begin();
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.app.log("Dim", "Width:" + Gdx.graphics.getWidth() + " Height: " + Gdx.graphics.getHeight());
        spriteBatch.draw(sprite,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.end();

        for (Ball b: balls){
            b.updateAll(delta);
            b.render();
        }


        balls.begin();

        for (int i = 0; i < balls.size ; i++) {
            if(balls.get(i).position.y < 50){
                balls.removeIndex(i);
            }
        }

        balls.end();

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

        /*Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(worldClick);
*/
        //balls.add(cannon.fire(renderer));
        angleOnTouchDown = cannon.getAngle();

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        dragging = true;

        Vector2  targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(targetPosition);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(dragging) {
            Gdx.app.log("I am", " called");
            Vector2 targetPosition = viewport.unproject(new Vector2(screenX, screenY));
            cannon.updateAngle(targetPosition);

            float diff = cannon.getAngle() - angleOnTouchDown;
            if(Math.abs(diff) < 10.0){
                balls.add(cannon.fire(spriteBatch,true));
            }
            dragging = false;
        }
        else {
            balls.add(cannon.fire(spriteBatch,true));
        }
        return true;
    }

    public boolean keyDown (int keycode) {

        if(keycode == Input.Keys.X){
            Gdx.app.log("tag","keydown");
            balls.add(cannon.fire(spriteBatch,true));
        }
        if(keycode == Input.Keys.Z){
            Gdx.app.log("tag","keydown");
            balls.add(cannon.fire(spriteBatch,false));
        }


		return false;
	}
}