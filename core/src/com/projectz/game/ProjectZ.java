package com.projectz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.ItemHealPotion;

public class ProjectZ extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	Inventory inventory;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		// Testing the inventory system.
		inventory = new Inventory();
		inventory.printInventory();
		inventory.addItem(new ItemHealPotion());
		inventory.printInventory();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
