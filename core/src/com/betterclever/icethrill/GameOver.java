package com.betterclever.icethrill;

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
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


public class GameOver extends InputAdapter implements Screen {

    public static final String TAG = GameOver.class.getName();

    IceThrillGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;
    Texture background;
    BitmapFont font24,font25;
    FreeTypeFontGenerator.FreeTypeFontParameter params;
    FreeTypeFontGenerator generator;
    int score;

    public GameOver(IceThrillGame game, int score) {
        this.game = game;
        this.score = score;

        FirebaseHelper helper = new FirebaseHelper();
        helper.addScore(new User("betterclever",score));

    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        background = new Texture("gameoverbg.jpg");
        // TODO: Initialize a FitViewport with the difficulty world size constant
        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        Gdx.input.setInputProcessor(this);
        initFonts();
        font24 = new BitmapFont();
        font25 = new BitmapFont();
        // TODO: Set the font scale using the constant we defined
        font24.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        font24.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        // TODO: Apply the viewport
        viewport.apply();
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        // TODO: Use ShapeRenderer to draw the buttons
        renderer.begin(ShapeType.Filled);

        renderer.setColor(Constants.EASY_COLOR);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.MEDIUM_COLOR);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.HARD_COLOR);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();


        // TODO: Set the SpriteBatche's projection matrix
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // TODO: Use SpriteBatch to draw the labels on the buttons
        // HINT: Use GlyphLayout to get vertical centering

        batch.begin();
        batch.draw(background, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.5f));
        renderer.rect(0,320,Constants.WORLD_WIDTH,100 );
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);


        batch.begin();


        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params.size = 60;
        params.color = Color.WHITE;
        params.shadowColor=Color.GRAY;
        params.shadowOffsetX=2;
        params.shadowOffsetY=2;
        font25 = generator.generateFont(params);
        final GlyphLayout mediumLayout = new GlyphLayout(font24, Constants.MEDIUM_LABEL);
        font25.draw(batch, "GAME  OVER", Constants.WORLD_WIDTH / 2, 390, 0, Align.center, false);
        font24.draw(batch, "Score: " + score, Constants.WORLD_WIDTH / 2, 300, 0, Align.center, false);
        final GlyphLayout hardLayout = new GlyphLayout(font24, Constants.HARD_LABEL);
        //font24.draw(batch, Constants.HARD_LABEL, Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        // TODO: Update the viewport
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

        game.setHomeScreen();

        return true;
    }
    private void initFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 60;
        params.color = Color.WHITE;
        font24 = generator.generateFont(params);
    }
}
