package com.betterclever.icethrill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Shape;

public class IceThrillGame extends Game {
	/*SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;
*/

	@Override
	public void create () {

		setScreen(new FieldOfPlay());

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
