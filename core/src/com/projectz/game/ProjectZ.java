package com.projectz.game;

import com.badlogic.gdx.Game;
import com.projectz.game.Map.GameScreen;

public class ProjectZ extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}

