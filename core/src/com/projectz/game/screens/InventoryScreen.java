package com.projectz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.Map.GameScreen;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.*;

public class InventoryScreen extends ScreenAdapter {
    public static Stage inventoryStage;
    Inventory inventory;
    DragAndDrop dnd;
    GameScreen gameScreen;
    InventoryActor inventoryActor;
    ProjectZ game;

    private ImageButton.ImageButtonStyle buttonStyle;
    private TextureRegion closeInventory;
    private ImageButton closeInventoryButton;

    /**
     * Constructor for the InventoryScreen Class
     * Initializes the inventory stage for the inventory actor to act on
     *
     * @param game ProjectZ game itself
     * @param inventory player inventory.
     * @param gameScreen previous state of the game screen
     */
    public InventoryScreen(ProjectZ game, Inventory inventory, GameScreen gameScreen)  {
        this.game = game;
        this.inventory = inventory;
        inventoryStage = new Stage(new ScreenViewport());
        dnd = new DragAndDrop();
        Gdx.input.setInputProcessor(inventoryStage);
        inventoryActor = new InventoryActor(inventory,dnd);
        inventoryStage.addActor(inventoryActor);
        inventoryCloseButton();
        this.gameScreen = gameScreen;
    }


    /**
     * Renders the inventory stage
     * @param delta
     */
    @Override
    public void render (float delta) {
        inventoryStage.getViewport().update(800, 600, true);
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        inventoryStage.act();
        inventoryStage.draw();
    }

    /**
     * Show method of screen adapter
     */
    @Override
    public void show () {}


    /**
     * Resize the stage based on the viewport
     */
    @Override
    public void resize(int width, int height) {
        inventoryStage.getViewport().update(width, height, true);
    }


    /**
     * Dispose of the stage after inventory screen is closed
     */
    @Override
    public void dispose () {
        inventoryStage.dispose();
    }


    /**
     * Creates style for the close button for the inventory screen.
     * If button is clicked, then switch back to the game screen.
     */
    private void inventoryCloseButton() {
        closeInventory = new TextureRegion(new Texture(Gdx.files.internal("exitInventory.png")));
        buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(closeInventory);
        closeInventoryButton = new ImageButton(buttonStyle);
        closeInventoryButton.setPosition(0,0);

        closeInventoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Change to game screen
                game.setScreen(gameScreen);
            }
        });

        InventoryScreen.inventoryStage.addActor(closeInventoryButton);
    }
}
