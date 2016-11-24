package com.betterclever.icethrill;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public class Constants {
    public static final float OFFSET = 180;
    public static final float WORLD_HEIGHT = 720;
    public static final float WORLD_WIDTH = 1280;
    public static final Vector2 ACC_G = new Vector2(0,-400);
    public static final int RADIUS_ICEBALL = 20;
    public static final int RADIUS_SUPERBALL = 30;
    public static final int SIMPLE_TARGET_HEALTH =  100;
    public static final int SUPER_TARGET_HEALTH = 200;
    public static final int ICE_BALL_DAMAGE = 100;
    public static final int SUPER_BALL_DAMAGE = 200;
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;
    public static final Color BACKGROUND_COLOR = Color.BLUE;
    public static final String EASY_LABEL = "Cold";
    public static final String MEDIUM_LABEL = "Colder";
    public static final String HARD_LABEL = "Coldest";
    public static final Vector2 EASY_CENTER = new Vector2(Constants.WORLD_WIDTH / 3 - Constants.OFFSET, Constants.WORLD_HEIGHT / 3);
    public static final Vector2 MEDIUM_CENTER = new Vector2(Constants.WORLD_WIDTH / 3 , Constants.WORLD_HEIGHT / 3);
    public static final float DIFFICULTY_BUBBLE_RADIUS = 70;
    public static final Vector2 HARD_CENTER = new Vector2(Constants.WORLD_WIDTH / 3 + Constants.OFFSET, Constants.WORLD_HEIGHT / 3);
    public static final TextureRegion[] TARGET_TEXTURE_REGIONS = {
            new TextureRegion( new Texture("fires1.png")),
            new TextureRegion( new Texture("fires2.png")),
            new TextureRegion( new Texture("fires3.png")),
            new TextureRegion( new Texture("fires4.png")),
            new TextureRegion( new Texture("fires6.png")),
            new TextureRegion( new Texture("fires6.png")),
            new TextureRegion( new Texture("fires1.png")),
            new TextureRegion( new Texture("fires8.png"))
    };
    public static final Animation ANIMATION = new Animation(0.2f, TARGET_TEXTURE_REGIONS);
    public static final TextureRegion[] EXPLOSION_TEXTURE_REGIONS = {
            new TextureRegion( new Texture("explosion1.png")),
            new TextureRegion( new Texture("explosion2.png")),
            new TextureRegion( new Texture("explosion3.png")),
            new TextureRegion( new Texture("explosion4.png")),
            new TextureRegion( new Texture("explosion5.png")),
            new TextureRegion( new Texture("explosion6.png")),
            new TextureRegion( new Texture("explosion8.png")),
            new TextureRegion( new Texture("explosion9.png")),
            new TextureRegion( new Texture("explosion10.png"))
    };
    public static final Texture LIFE = new Texture("heart.png");
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

}
