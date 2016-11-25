package com.betterclever.icethrill.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.betterclever.icethrill.utilities.Constants;
import com.betterclever.icethrill.IceThrillGame;


public class HowToPlay extends InputAdapter implements Screen {

    public static final String TAG = HowToPlay.class.getName();
    Texture background;
    IceThrillGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;

    BitmapFont font24;
    FreeTypeFontGenerator.FreeTypeFontParameter params;

    public HowToPlay(IceThrillGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new Texture("ice_new1.jpg");
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new ExtendViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        initFonts();

        font24.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        font24.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {

        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.5f));
        renderer.rect(50, 60, 540,370);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();

        font24.draw(batch, "Desktop: ", 55, 425, 0, Align.left, false);

        font24.draw(batch, "Press X to Shoot Cannon.", 70, 400, 0, Align.left, false);

        font24.draw(batch, "Press LEFT and RIGHT to set Angle." ,70, 378, 0, Align.left, false);

        font24.draw(batch, "Android: ", 55, 330, 0, Align.left, false);

        font24.draw(batch, "Tap anywhere to Shoot Cannon.", 70, 300, 0, Align.left, false);

        font24.draw(batch, "Swipe Left and Right to set Angle." ,70, 275, 0, Align.left, false);

        font24.draw(batch, "iOS: ", 55, 225, 0, Align.left, false);

        font24.draw(batch, "Tap anywhere to Shoot Cannon.", 70, 200, 0, Align.left, false);

        font24.draw(batch, "Swipe Left and Right to set Angle." ,70, 180, 0, Align.left, false);

        font24.draw(batch, "Web Browser: ", 55, 130, 0, Align.left, false);

        font24.draw(batch, "Press X to Shoot Cannon.", 70, 100, 0, Align.left, false);

        font24.draw(batch, "Press LEFT and RIGHT to set Angle." ,70, 80, 0, Align.left, false);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font24.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        game.setHomeScreen();
        return true;
    }
    private void initFonts() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 15;
        params.color = Color.WHITE;
        font24 = generator.generateFont(params);
    }
}
