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
import com.betterclever.icethrill.utilities.Constants;
import com.betterclever.icethrill.utilities.FirebaseHelper;
import com.betterclever.icethrill.IceThrillGame;
import com.betterclever.icethrill.utilities.User;

import java.util.ArrayList;
import java.util.List;


public class Scoreboard extends InputAdapter implements Screen {

    public static final String TAG = Scoreboard.class.getName();

    IceThrillGame game;
    List<User> userList;

    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;
    Texture background;
    BitmapFont font24,font25;
    FreeTypeFontGenerator.FreeTypeFontParameter params;
    FreeTypeFontGenerator generator;
    int score;

    public Scoreboard(IceThrillGame game) {
        this.game = game;
        userList = new ArrayList<User>();
        FirebaseHelper helper = new FirebaseHelper();
        helper.getHighScores();
        userList = helper.users;

    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        background = new Texture("gameoverbg.jpg");

        viewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        Gdx.input.setInputProcessor(this);
        initFonts();

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

        renderer.begin(ShapeType.Filled);

        renderer.setColor(Constants.EASY_COLOR);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.MEDIUM_COLOR);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.HARD_COLOR);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(background, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(0, 0, 0, 0.5f));
        renderer.rect(0,580,Constants.WORLD_WIDTH,100 );
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);


        batch.begin();

        font25.draw(batch, "High Scores", Constants.WORLD_WIDTH / 2, 660, 0, Align.center, false);

        int q = 500;
        int z = userList.size();
        if(z > 7){
            z = 7;
        }

        for(int i = 0;i<z;i++) {
            User user = userList.get(i);
            font24.draw(batch, user.getName() +": " + user.getScore() , Constants.WORLD_WIDTH / 2, q, 0, Align.center, false);
            q -= 60;
        }

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

        game.setHomeScreen();
        return true;

    }
    private void initFonts() {

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 30;
        params.color = Color.WHITE;
        font24 = generator.generateFont(params);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arcon-Rounded-Regular.otf"));
        params.size = 60;
        params.color = Color.WHITE;
        params.shadowColor=Color.GRAY;
        params.shadowOffsetX=2;
        params.shadowOffsetY=2;
        font25 = generator.generateFont(params);

    }
}
