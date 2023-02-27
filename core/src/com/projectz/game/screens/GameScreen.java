package com.projectz.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.projectz.game.ProjectZ;

public class GameScreen extends ScreenAdapter {

    InventoryScreen inventoryScreen;
    GameScreen gameScreen;
    ProjectZ game;

    public GameScreen(final ProjectZ game) {
        this.game = game;
        inventoryScreen = new InventoryScreen(game);
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown (int keyCode) {
                if (keyCode == Input.Keys.E) {
                    game.setScreen(new InventoryScreen(game));
                }
                return true;
            }
        });
    }

}