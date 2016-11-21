package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public class Cannon  {

    ShapeRenderer renderer;
    float angle;
    boolean flicking,following;
    Vector2 flickStart,targetPosition;

    public Cannon(ShapeRenderer renderer){
        this.angle = 45;
        this.renderer = renderer;
    }

    public void render(){

        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            angle -= 0.4;
        }

        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            angle += 0.4;
        }

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.CHARTREUSE);

        renderer.rect(-40, 0,
                0,0,
                60, 200,
                1.0f, 1.0f,
                -angle);

        renderer.end();

    }

    public void updateAngle(Vector2 targetPosition){

        Vector2 v = calculateMidOfCanon();

        float angleDiff = v.angle(targetPosition);

        Gdx.app.log("angleDiff", String.valueOf(angleDiff));

        angle -= angleDiff;

    }

    private Vector2 calculateMidOfCanon(){

        int x = (-40 + 60) / 2;
        int y = (200 + 0) / 2;

        Vector2 v = new  Vector2(x,y);
        v.rotate(-angle);

        return  v;
    }




}
