package com.betterclever.icethrill.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.betterclever.icethrill.utilities.Constants;

/**
 * Created by betterclever on 24/11/16.
 */

public class Life {

    int life;
    SpriteBatch spriteBatch;

    public Life(SpriteBatch spriteBatch){
        life = 10;
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
        if(life > 10){
            life = 10;
        }
    }

    public void render(){

        Vector2 current = new Vector2(10,Constants.WORLD_HEIGHT-50);
        spriteBatch.begin();
        for (int i = 0; i < (life - 1)/2 + 1; i++) {
            spriteBatch.draw(Constants.LIFE,current.x,current.y,40,40);
            current.x += 50;
        }
        spriteBatch.end();

    }


}
