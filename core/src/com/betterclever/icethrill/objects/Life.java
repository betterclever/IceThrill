package com.betterclever.icethrill.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.Constants;
import com.google.gson.Gson;

/**
 * Created by betterclever on 24/11/16.
 */

public class Life {

    int life;
    SpriteBatch spriteBatch;

    public Life(SpriteBatch spriteBatch){
        life = 50;
        this.spriteBatch = spriteBatch;
    }

    public boolean reduce(){
        life--;
        return life != 0;
    }

    public boolean lifeOver(){
        return life <= 0;
    }

    public void increase(){
        life++;
        if(life > 50){
            life = 50;
        }
    }

    public void render(){

        //Gdx.app.log("cool","I am here");

        Vector2 current = new Vector2(10,Constants.WORLD_HEIGHT-50);
        spriteBatch.begin();
        for (int i = 0; i < (life - 1)/10 + 1; i++) {
            spriteBatch.draw(Constants.LIFE,current.x,current.y,40,40);
            current.x += 50;
        }
        spriteBatch.end();

    }


}
