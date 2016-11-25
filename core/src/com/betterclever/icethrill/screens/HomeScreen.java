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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.betterclever.icethrill.utilities.Constants;
import com.betterclever.icethrill.IceThrillGame;


public class HomeScreen extends InputAdapter implements Screen {

    public static final String TAG = HomeScreen.class.getName();

    IceThrillGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;
    Texture background;
    BitmapFont font24,font25,ban;
    FreeTypeFontGenerator.FreeTypeFontParameter params;
    FreeTypeFontGenerator generator;

    public HomeScreen(IceThrillGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        background = new Texture("ice_new1.jpg");

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
        batch.draw(background, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.5f));
        renderer.rect(0,330,Constants.WORLD_WIDTH,120 );
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
        ban.draw(batch, "ICE THRILL", (Constants.WORLD_WIDTH) / 3, 450, 0, Align.center, false);
        batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(1, 0, 0, 0.5f));
        renderer.circle(Constants.WORLD_WIDTH / 3 - Constants.OFFSET, Constants.WORLD_HEIGHT / 3, 70);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.5f));
        renderer.circle(Constants.WORLD_WIDTH /3 , Constants.WORLD_HEIGHT / 3, 80);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(1, 0, 0, 0.5f));
        renderer.circle(Constants.WORLD_WIDTH / 3+ Constants.OFFSET, Constants.WORLD_HEIGHT / 3, 70);
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);


        batch.begin();

        font24.draw(batch, "ScoreBoard", Constants.WORLD_WIDTH / 3 - Constants.OFFSET, Constants.WORLD_HEIGHT / 3 + 10, 0, Align.center, false);
        font25.draw(batch, "PLAY", Constants.WORLD_WIDTH / 3 , Constants.WORLD_HEIGHT / 3 + 20, 0, Align.center, false);
        font24.draw(batch, "How To Play", Constants.WORLD_WIDTH / 3 + Constants.OFFSET, Constants.WORLD_HEIGHT / 3 + 10, 0, Align.center, false);

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
        font25.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.EASY_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.setScoreScreen();
        }

        if (worldTouch.dst(Constants.MEDIUM_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.setGameScreen();
        }

        if (worldTouch.dst(Constants.HARD_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS) {
            game.setHowToPlay();
        }

        return true;
    }
    private void initFonts() {

        font24 = new BitmapFont();
        font25 = new BitmapFont();
        ban = new BitmapFont();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 15;
        params.color = Color.WHITE;
        font24 = generator.generateFont(params);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params.size = 30;
        params.color = Color.WHITE;
        params.shadowColor=Color.GRAY;
        params.shadowOffsetX=2;
        params.shadowOffsetY=2;
        font25 = generator.generateFont(params);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Godhong-Regular-Free.ttf"));
        params.size = 150;
        params.color = Color.WHITE;
        params.shadowColor=Color.GRAY;
        params.shadowOffsetX=2;
        params.shadowOffsetY=2;
        ban = generator.generateFont(params);
    }
}
