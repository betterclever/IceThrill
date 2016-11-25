package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.utilities.Constants;

/**
 * Created by betterclever on 21/11/16.
 */

public class Cannon  {

    SpriteBatch spriteBatch;

    public float getAngle() {
        return angle;
    }

    float angle;
    Texture image;

    public Cannon(SpriteBatch batch){
        this.angle = 45;
        this.spriteBatch = batch;
        image = new Texture("my_canon.png");
    }


    public void render(){

        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            angle -= 0.8;
        }

        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            angle += 0.8;
        }

        clampAngle();

        spriteBatch.begin();
        spriteBatch.draw(new TextureRegion(image),0,0,67,51,150,150,1,1,-angle+60);
        spriteBatch.end();

    }

    public float updateAngle(Vector2 targetPosition){

        Vector2 v = calculateMidOfCanon();

        float angleDiff = v.angle(targetPosition);

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
        int y = (200) / 2;

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

        v.rotate(-angle+29);

        v.add(shifted);

        return  v;
    }


    public Ball fire(SpriteBatch spriteBatch,boolean type) {

        Ball b;


        if(type) {
            Constants.CANNON_NORMAL_SOUND.play();
            b = new IceBall(spriteBatch, new Vector2(0, 700).rotate(-angle-10), calculateTopOfCanon());

        }
        else {
            Constants.CANNON_SUPER_SOUND.play();
            b = new SuperBall(spriteBatch, new Vector2(0, 700).rotate(-angle-10), calculateTopOfCanon());
        }
        return b;
    }
}
