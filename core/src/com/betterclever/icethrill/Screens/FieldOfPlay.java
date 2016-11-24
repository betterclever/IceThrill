package com.betterclever.icethrill.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.betterclever.icethrill.Constants;
import com.betterclever.icethrill.IceThrillGame;
import com.betterclever.icethrill.objects.Ball;
import com.betterclever.icethrill.objects.Explosion;
import com.betterclever.icethrill.objects.Life;
import com.betterclever.icethrill.objects.SimpleTarget;
import com.betterclever.icethrill.objects.SuperTarget;
import com.betterclever.icethrill.objects.Target;
import com.betterclever.icethrill.objects.Cannon;

/**
 * Created by betterclever on 20/11/16.
 */

public class FieldOfPlay extends InputAdapter implements Screen {

    ExtendViewport viewport;
    ShapeRenderer renderer;
    Cannon cannon;
    Vector2 accG;

    float timePassed = 0;

    Vector2 position;
    Vector2 velocity;

    int continuousHits = 0;
    int ballsFired = 0;
    Texture background;
    SpriteBatch spriteBatch;
    Life life;

    float angleOnTouchDown = 0.0f;

    DelayedRemovalArray<Ball> balls;
    DelayedRemovalArray<Target> targets;
    DelayedRemovalArray<Explosion> explosions;

    boolean dragging = false;
    Animation anim;

    Sprite sprite;

    IceThrillGame game;

    public FieldOfPlay(IceThrillGame game) {
        this.game = game;
    }

    int score = 0;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        accG = new Vector2(0, -50);
        position = new Vector2(10, 10);

        balls = new DelayedRemovalArray<Ball>();

        cannon = new Cannon(spriteBatch);
        life = new Life(spriteBatch);
        targets = new DelayedRemovalArray<Target>();
        explosions = new DelayedRemovalArray<Explosion>();

        background = new Texture("ice2back.jpg");
        sprite = new Sprite(background);
        Gdx.input.setInputProcessor(this);

    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(sprite, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        spriteBatch.end();

        drawScore();

        for (Ball b : balls) {
            b.updateAll(delta);
            b.render();
        }

        if (continuousHits == 2) {
            continuousHits = 0;
            life.increase();
        }

        if (life.lifeOver()) {
            game.setGameOverScreen(score);
            hide();
            dispose();
        }

        life.render();

        balls.begin();
        for (int i = 0; i < balls.size; i++) {
            if (balls.get(i).position.y < 50) {
                balls.removeIndex(i);
                life.reduce();
                continuousHits = 0;
            }
        }
        balls.end();

        explosions.begin();
        for (int i = 0; i < explosions.size; i++) {
            if (explosions.get(i).getTimePassed() > 0.5) {
                explosions.removeIndex(i);
            }
        }
        explosions.end();

        for (Target t : targets) {
            t.render(delta);
        }

        for (Explosion e : explosions) {
            e.render(delta);
        }

        timePassed += delta;

        generateTarget();


        targets.begin();

        for (int i = 0; i < targets.size; i++) {

            if (targets.get(i).health <= 0) {
                targets.removeIndex(i);
                score += targets.get(i).hitpoints;
                continue;
            }
            if (targets.get(i).timePassed > 10) {
                targets.removeIndex(i);
            }
        }
        targets.end();

        cannon.render();

        balls.begin();
        targets.begin();

        for (int i = 0; i < balls.size; i++) {
            for (int j = 0; j < targets.size; j++) {

                Ball b = balls.get(i);
                Target t = targets.get(j);

                if (b.bounds.overlaps(t.bounds)) {
                    explosions.add(new Explosion(t.getPosition(), spriteBatch));
                    balls.removeIndex(i);
                    t.health -= b.getDamage();
                    continuousHits += 1;
                    break;
                }
            }
        }

        targets.end();
        balls.end();
    }

    private void generateTarget() {

        // Generating Pseudo Random Targets
        if (timePassed % 5 > 0.02) {
            return;
        }

        int q = (int) (((timePassed * Math.random()) % 1) * 100);
        float position = (float) Constants.WORLD_WIDTH / 2.7f + (float) (Constants.WORLD_WIDTH / 2 * Math.random());
        Gdx.app.log("Position", String.valueOf(position));
        if (q % 2 == 0) {
            targets.add(new SimpleTarget(new Vector2(position, 50), spriteBatch));
        } else {
            targets.add(new SuperTarget(new Vector2(position, 50), spriteBatch));
        }
    }

    private void drawScore() {

        Vector2 pos = new Vector2(Constants.WORLD_WIDTH - 250, Constants.WORLD_HEIGHT - 20);

        spriteBatch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.draw(spriteBatch, "Score: " + score, pos.x, pos.y);
        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        angleOnTouchDown = cannon.getAngle();
        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        dragging = true;

        Vector2 targetPosition = viewport.unproject(new Vector2(screenX, screenY));
        cannon.updateAngle(targetPosition);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (dragging) {
            Vector2 targetPosition = viewport.unproject(new Vector2(screenX, screenY));
            cannon.updateAngle(targetPosition);

            float diff = cannon.getAngle() - angleOnTouchDown;
            if (Math.abs(diff) < 10.0) {
                fireBall();
            }
            dragging = false;
        } else {
            fireBall();
        }
        return true;
    }

    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.X) {
            fireBall();
        }

        return false;
    }

    public void fireBall() {

        if (ballsFired % 4 == 3) {
            balls.add(cannon.fire(spriteBatch, false));
        } else {
            balls.add(cannon.fire(spriteBatch, true));
        }

        ballsFired++;
    }
}