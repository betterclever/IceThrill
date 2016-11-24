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

        // TODO: Initialize a FitViewport with the difficulty world size constant
        viewport = new ExtendViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        //font = new BitmapFont();
        initFonts();
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



        // TODO: Set the SpriteBatche's projection matrix
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // TODO: Use SpriteBatch to draw the labels on the buttons
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
        final GlyphLayout easyLayout = new GlyphLayout(font24, "Desktop: ");
        font24.draw(batch, "Desktop: ", 55, 425, 0, Align.left, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font24, Constants.MEDIUM_LABEL);
        font24.draw(batch, "Press X to Shoot Cannon.", 70, 400, 0, Align.left, false);

        final GlyphLayout hardLayout = new GlyphLayout(font24, Constants.HARD_LABEL);
        font24.draw(batch, "Press LEFT and RIGHT to set Angle." ,70, 378, 0, Align.left, false);

        final GlyphLayout easyLayout1 = new GlyphLayout(font24, "Desktop: ");
        font24.draw(batch, "Android: ", 55, 330, 0, Align.left, false);

        final GlyphLayout mediumLayout1 = new GlyphLayout(font24, Constants.MEDIUM_LABEL);
        font24.draw(batch, "Press FIRE BUTTON to Shoot Cannon.", 70, 300, 0, Align.left, false);

        final GlyphLayout hardLayout1 = new GlyphLayout(font24, Constants.HARD_LABEL);
        font24.draw(batch, "Swipe Left and Right to set Angle." ,70, 275, 0, Align.left, false);

        final GlyphLayout easyLayout2 = new GlyphLayout(font24, "Desktop: ");
        font24.draw(batch, "iOS: ", 55, 225, 0, Align.left, false);

        final GlyphLayout mediumLayout2 = new GlyphLayout(font24, Constants.MEDIUM_LABEL);
        font24.draw(batch, "Press FIRE BUTTON to Shoot Cannon.", 70, 200, 0, Align.left, false);

        final GlyphLayout hardLayout2 = new GlyphLayout(font24, Constants.HARD_LABEL);
        font24.draw(batch, "Swipe Left and Right to set Angle." ,70, 180, 0, Align.left, false);

        final GlyphLayout easyLayout3 = new GlyphLayout(font24, "Desktop: ");
        font24.draw(batch, "Web Browser: ", 55, 130, 0, Align.left, false);

        final GlyphLayout mediumLayout3 = new GlyphLayout(font24, Constants.MEDIUM_LABEL);
        font24.draw(batch, "Press X to Shoot Cannon.", 70, 100, 0, Align.left, false);

        final GlyphLayout hardLayout3 = new GlyphLayout(font24, Constants.HARD_LABEL);
        font24.draw(batch, "Press LEFT and RIGHT to set Angle." ,70, 80, 0, Align.left, false);

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
class MyShapeRenderer extends ShapeRenderer{
    /**
     * Draws a rectangle with rounded corners of the given radius.
     */
    public void roundedRect(float x, float y, float width, float height, float radius){
        // Central rectangle
        super.rect(x + radius, y + radius, width - 2*radius, height - 2*radius);

        // Four side rectangles, in clockwise order
        super.rect(x + radius, y, width - 2*radius, radius);
        super.rect(x + width - radius, y + radius, radius, height - 2*radius);
        super.rect(x + radius, y + height - radius, width - 2*radius, radius);
        super.rect(x, y + radius, radius, height - 2*radius);

        // Four arches, clockwise too
        super.arc(x + radius, y + radius, radius, 180f, 90f);
        super.arc(x + width - radius, y + radius, radius, 270f, 90f);
        super.arc(x + width - radius, y + height - radius, radius, 0f, 90f);
        super.arc(x + radius, y + height - radius, radius, 90f, 90f);
    }
}