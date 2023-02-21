package com.projectz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.projectz.game.player.Player;

public class ProjectZ extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Player player;
	Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//Libgdx said this is how I should do it (required for multiple scenes)
		stage = new Stage();
		player = new Player();
		stage.addActor(player);
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
		player.dispose();
	}
}
