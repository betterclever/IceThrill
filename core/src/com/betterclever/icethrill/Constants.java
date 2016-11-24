package com.betterclever.icethrill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public class Constants {

    public static final float WORLD_HEIGHT = 100;
    public static final float WORLD_WIDTH = 100;
    public static final Vector2 ACC_G = new Vector2(0,-400);
    public static final int RADIUS_ICEBALL = 20;
    public static final int RADIUS_SUPERBALL = 30;
    public static final int SIMPLE_TARGET_HEALTH =  100;
    public static final int SUPER_TARGET_HEALTH = 200;
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

}
