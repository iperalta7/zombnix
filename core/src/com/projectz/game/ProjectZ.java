package com.projectz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.projectz.game.player.Player;
import com.projectz.game.utils.MyInputProcessor;

import com.badlogic.gdx.Input.Keys;

public class ProjectZ extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	MyInputProcessor inputProcessor = new MyInputProcessor();

	private Player player;
	private Texture playerTexture;
	Stage stage;

	@Override
	public void create() {

		//Libgdx said this is how I should do it (required for multiple scenes)
		stage = new Stage();
		player = new Player();
		stage.addActor(player);

		//Processes the input for wasd (can be furthered enhanced for other features)
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render() {

		//set default color to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//default call to create stage (from documentation page)
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		playerTexture.dispose();
	}
}
