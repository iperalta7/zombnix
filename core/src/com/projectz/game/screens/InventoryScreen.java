package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.ProjectZ;

public class InventoryScreen extends ScreenAdapter {
    private Stage inventoryStage;
    private Table table;

    //UI elements
    private Image inventoryBackground;
    private Label inventoryLabel;
    private TextButton closeInventory;

    ProjectZ game;

    public InventoryScreen(ProjectZ game) {
        this.game = game;
        initInventory();
    }

    public void initInventory() {
        inventoryStage = new Stage(new ScreenViewport());
        inventoryBackground = new Image(new Texture("inventory.png"));
        inventoryBackground.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        inventoryBackground.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);

        inventoryLabel = new Label("Inventory", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        inventoryLabel.setPosition(Gdx.graphics.getWidth() / 2 - inventoryLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + inventoryLabel.getHeight() * 7);

        //closeInventory = new TextButton("Close", new TextButton.TextButtonStyle();

    }
    @Override
    public void render (float delta) {
        inventoryStage.getViewport().update(800, 600, true);
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        inventoryStage.addActor(inventoryBackground);
        inventoryStage.addActor(inventoryLabel);

        inventoryStage.act();
        inventoryStage.draw();
    }

    @Override
    public void show () {

    }

    @Override
    public void dispose () {
        inventoryStage.dispose();
    }
}