package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public class Cannon  {

    SpriteBatch spriteBatch;
    Music normal_sound;
    Music heavy_sound;

    public float getAngle() {
        return angle;
    }

    float angle;
    Texture image;

    boolean flicking,following;
    Vector2 flickStart,targetPosition;

    public Cannon(SpriteBatch batch){
        this.angle = 45;
        this.spriteBatch = batch;
        image = new Texture("my_canon.png");
        //heavy_sound = Gdx.audio.newMusic(Gdx.files.internal("cannon_super.mp3"));
        //normal_sound = Gdx.audio.newMusic(Gdx.files.internal("cannon_sound.mp3"));
    }


    public void render(){

        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            angle -= 0.4;
        }

        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            angle += 0.4;
        }

        clampAngle();

        spriteBatch.begin();
        spriteBatch.draw(new TextureRegion(image),0,0,67,51,150,150,1,1,-angle+60);
        spriteBatch.end();

        /*renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.CHARTREUSE);

        renderer.rect(-40, 0,
                0,0,
                60, 200,
                1.0f, 1.0f,
                -angle);

        renderer.end();*/

    }

    public float updateAngle(Vector2 targetPosition){

        Vector2 v = calculateMidOfCanon();

        float angleDiff = v.angle(targetPosition);

        Gdx.app.log("angleDiff", String.valueOf(angleDiff));

        angle -= angleDiff;
        clampAngle();


        return angleDiff;
    }

    private void clampAngle(){

        if(angle >= 70.0f){
            angle = 70.0f;
        }

        if(angle <= 10) {
            angle = 10;
        }
    }

    private Vector2 calculateMidOfCanon(){

        int x = (-40 + 60) / 2;
        int y = (200 + 0) / 2;

        Vector2 v = new  Vector2(x,y);
        v.rotate(-angle);

        return  v;
    }

    private Vector2 calculateTopOfCanon(){

        int x = (108) ;
        int y = (145) ;

        Vector2 v = new  Vector2(x,y);
        Vector2 shifted = new Vector2(67,51);

        v = v.sub(shifted);

        v.rotate(-angle + 30);

        v.add(shifted);

        return  v;
    }


    public Ball fire(SpriteBatch spriteBatch,boolean type) {

        Ball b;


        if(type) {
            Music sound = Gdx.audio.newMusic(Gdx.files.internal("cannon_sound.mp3"));
            sound.play();
            b = new IceBall(spriteBatch, new Vector2(0, 700).rotate(-angle), calculateTopOfCanon());
            //sound.dispose();
        }
        else {
            Music sound = Gdx.audio.newMusic(Gdx.files.internal("cannon_super.mp3"));
            sound.play();
            b = new SuperBall(spriteBatch, new Vector2(0, 700).rotate(-angle), calculateTopOfCanon());
            //sound.dispose();
        }
        return b;
    }
}
