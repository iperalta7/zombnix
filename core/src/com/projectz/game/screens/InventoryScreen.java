package com.projectz.game.screens;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.projectz.game.Map.GameScreen;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.*;

public class InventoryScreen extends ScreenAdapter {
    private Stage inventoryStage;
    Inventory inventory;
    GameScreen gameScreen;
    ItemSlot itemSlot;
    ItemStack stack;
    DragAndDrop dnd;

    //Window Elements
    private Window.WindowStyle inventoryStyle;
    private Window window;
    private Window.WindowStyle cellStyle;
    private Window cellWindow;
    private Window.WindowStyle hotBarStyle;
    private Window hotBarWindow;
    private Window weaponWindow;

    //UI elements
    private Array<Window> cellArray;
    private Array<Window> hotBarArray;
    private ImageButton.ImageButtonStyle buttonStyle;
    private TextureRegion closeInventory;
    private ImageButton closeInventoryButton;
    private Image itemPNG;
    private Array<ItemSlot> inventorySlots;

    //GUI Measurements
    int screenHeight = Gdx.graphics.getHeight() / 2;
    int screenWidth = Gdx.graphics.getWidth() / 2;
    private final int CELL_LENGTH = 50;
    private final int ROW_LENGTH = 3;
    private final int COL_LENGTH = 3;

    ProjectZ game;

    public InventoryScreen(ProjectZ game, Inventory inventory)  {
        this.game = game;
        this.inventory = inventory;
        inventoryStage = new Stage(new ScreenViewport());
        dnd = new DragAndDrop();
        Gdx.input.setInputProcessor(inventoryStage);

        initInventory();
        addCells();
        cellTargets();

        //Load each itemSlot into the inventorySlot Array
        inventorySlots = new Array<>();
        for(int i = 0; i < inventory.getInventorySize(); i++) {
            inventorySlots.add(inventory.getInventory(i));
        }

        loadInventory();
    }

    //Renders the inventory background and close button to the window
    public void initInventory() {
        //Close Inventory Button
        closeInventory = new TextureRegion(new Texture(Gdx.files.internal("exitInventory.png")));
        buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(closeInventory);
        closeInventoryButton = new ImageButton(buttonStyle);
        closeInventoryButton.setPosition(0,0);
        inventoryStage.addActor(closeInventoryButton);

        //Inventory Window
        inventoryStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("inventory.png")));
        window = new Window("",inventoryStyle);
        window.setVisible(true);
        window.setSize(250,430);
        window.setMovable(true);
        window.setPosition(screenWidth - window.getWidth() / 2, screenHeight - window.getHeight() / 2);
        inventoryStage.addActor(window);
    }

    //Physically renders each empty cell which represents the item slot
    public void addCells() {
        cellArray = new Array<>();
        hotBarArray = new Array<>();
        float inventoryWidth = window.getWidth() / 2;
        float inventoryHeight = window.getHeight() / 2;
        final int SPACING = 10;
        int cell_row = 0;
        int cell_col = 0;
        int wepaonSlot = 0;

        for(int i = 0; i < COL_LENGTH; i++) {
            for(int j = 0; j < ROW_LENGTH; j++) {
                //Create new cell
                cellStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("cell.png")));
                cellWindow = new Window("",cellStyle);
                cellWindow.setVisible(true);
                cellWindow.setMovable(true);
                cellWindow.setSize(CELL_LENGTH, CELL_LENGTH);

                hotBarStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("hotbarcell.png")));
                if(wepaonSlot == 0 | wepaonSlot == 1) {
                    hotBarStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("primAndSec.png")));
                    wepaonSlot++;
                }
                //Creates cell within hot bar
                hotBarWindow = new Window("",hotBarStyle);
                hotBarWindow.setVisible(true);
                hotBarWindow.setMovable(true);
                hotBarWindow.setSize(CELL_LENGTH, CELL_LENGTH);

                //Set the position of every cell
                cellWindow.setPosition(screenWidth - (inventoryWidth - 30) + SPACING + cell_row,
                        screenHeight - (inventoryHeight + 15) + (window.getHeight() - CELL_LENGTH - SPACING - cell_col));

                hotBarWindow.setPosition(screenWidth - (inventoryWidth - 30) + SPACING + cell_row,
                        screenHeight / 2 - (inventoryHeight + 75) + (window.getHeight() - CELL_LENGTH - SPACING - cell_col));

                //Add cell to the stage
                inventoryStage.addActor(cellWindow);
                inventoryStage.addActor(hotBarWindow);

                //Add cells to corresponding array
                cellArray.add(cellWindow);
                hotBarArray.add(hotBarWindow);

                //Adds spacing between cells
                cell_row += CELL_LENGTH + SPACING;
            }
            //Creates the next row
            cell_row = 0;
            cell_col += CELL_LENGTH + SPACING;
        }
    }

    //Render the items from the inventory into correct cell
    public void loadInventory() {
        int i = 0;
        for(final ItemSlot slot : inventorySlots) {
            if(!slot.isEmpty()) {
                //Add item to each cell
                itemPNG = new Image(new Texture(slot.getPngName()));
                cellArray.get(i).add(itemPNG);
                itemPNG.setUserObject(cellArray.get(i));

                //Allows cells to have movable items
                dnd.addSource(new DragAndDrop.Source(itemPNG) {
                    @Override
                    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                        DragAndDrop.Payload payload = new DragAndDrop.Payload();
                        payload.setDragActor(getActor());
                        inventoryStage.addActor(getActor());
                        dnd.setDragActorPosition(getActor().getWidth() / 2, -getActor().getHeight() / 2);
                        return payload;
                    }

                    @Override
                    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                        if(target == null) {
                            //Return the item to its orginal position in the array
                            ((Window)itemPNG.getUserObject()).add(itemPNG);
                        }
                    }
                });
            }
            i++;
        }
    }

    //Makes the cells within hot bar and storage cells available areas to drag to
    public void cellTargets() {
        for(int i = 0; i < inventory.getInventorySize(); i++) {
            dnd.addTarget(new DragAndDrop.Target(cellArray.get(i)) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v1, int i) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v1, int i) {
                    cellArray.get(i).add((Image) payload.getDragActor());
                    payload.getDragActor().setUserObject(cellArray.get(i));
                }
            });

            dnd.addTarget(new DragAndDrop.Target(hotBarArray.get(i)) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v1, int i) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v1, int i) {
                    hotBarArray.get(i).add((Image) payload.getDragActor());
                    payload.getDragActor().setUserObject(hotBarArray.get(i));
                }
            });

        }
    }

    @Override
    public void render (float delta) {
        inventoryStage.getViewport().update(800, 600, true);
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        inventoryStage.act();
        inventoryStage.draw();

        closeInventoryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Change to game screen
                game.setScreen(new GameScreen(game));
            }
        });
    }

    @Override
    public void show () {

    }
    @Override
    public void resize(int width, int height) {
        inventoryStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose () {
        inventoryStage.dispose();
    }

    public void updateInventory(int index) {

    }

    public void updateHotBar(int index) {

    }
}

