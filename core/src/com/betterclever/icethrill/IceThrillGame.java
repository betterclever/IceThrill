package com.betterclever.icethrill;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.betterclever.icethrill.screens.*;
import com.betterclever.icethrill.screens.GameOver;

public class IceThrillGame extends Game {

	Music music;

	@Override
	public void create () {
        music = Gdx.audio.newMusic(Gdx.files.internal("gamebackground.mp3"));
        setScreen(new HomeScreen(this));
	}

    @Override
    public void dispose() {
        music.dispose();
    }

	public void setScoreScreen(){
		setScreen(new Scoreboard(this));
	}

	public void setGameOverScreen(int score, String currentUser) {
        music.stop();
        setScreen(new GameOver(this,score,currentUser));
	}

    public void setGameScreen(){
		music.play();
        music.setLooping(true);
        music.setVolume(0.5f);
        setScreen(new FieldOfPlay(this));
    }

    public void setHowToPlay() {
        setScreen(new HowToPlay(this));
    }

    public void setHomeScreen(){
        setScreen(new HomeScreen(this));
    }
}
