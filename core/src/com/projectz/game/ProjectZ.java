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
<<<<<<< HEAD
<<<<<<< HEAD
	
	
=======
=======
>>>>>>> 95f4c7a (rebase)

>>>>>>> 1e39cbf (removed inputProcessor class (not needed for player atm))
	private Player player;
	MyInputProcessor inputProcessor = new MyInputProcessor();
	Stage stage;
	
	@Override
<<<<<<< HEAD
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
=======
	public void create() {

=======
	
	
	private Player player;
	MyInputProcessor inputProcessor = new MyInputProcessor();
	Stage stage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
>>>>>>> 07e02ca (Added basic ability to use healing potion. In essence, this example can be used for any consumable. I also made using items easier by making it so that items created will be statically and finally created in the Item class. Thereby, we can use something akin to Item.ITEMNAME to add items to the inventory as well as compare items easily.)
>>>>>>> 95f4c7a (rebase)
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
