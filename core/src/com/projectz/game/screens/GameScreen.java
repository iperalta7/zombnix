package com.projectz.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.ProjectZ;
import com.projectz.game.player.Player;

public class GameScreen extends ScreenAdapter {
    ProjectZ game;
    Stage stage;
    Inventory inventory;

    Player player;

    public GameScreen(ProjectZ game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        create();
    }


    public void create() {
        this.player = new Player();
	    stage.addActor(player);

        inventory = new Inventory();
        inventory.printInventory();
        inventory.addItem(Item.HealingPotion, 5);
        //inventory.addItem(Item.SpeedPotion, 5);
        inventory.printInventory();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown (int keyCode) {
                if (keyCode == Input.Keys.E) {
                    game.setScreen(new InventoryScreen(game, inventory));
                }
                return true;
            }
        });

        stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
    }
}
