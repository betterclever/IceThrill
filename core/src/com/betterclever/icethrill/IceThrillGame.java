package com.betterclever.icethrill;

import com.badlogic.gdx.Game;
import com.betterclever.icethrill.screens.FieldOfPlay;
import com.betterclever.icethrill.screens.Scoreboard;

public class IceThrillGame extends Game {
	/*SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;
*/

	@Override
	public void create () {

		setScreen(new HomeScreen(this));

	}


	public void setScoreScreen(){
		setScreen(new Scoreboard(this));
	}

	public void setGameOverScreen(int score) {
		setScreen(new GameOver(this,score));
	}

    public void setGameScreen(){
        setScreen(new FieldOfPlay(this));
    }

    public void setHowToPlay() {
        setScreen(new HowToPlay(this));
    }

    public void setHomeScreen(){
        setScreen(new HomeScreen(this));
    }
/*
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.CHARTREUSE);


		shapeRenderer.circle(100.0f,100.0f,10.0f);
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		//img.dispose();
	}*/
}
