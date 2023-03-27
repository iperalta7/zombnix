package com.projectz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.*;
import com.projectz.game.ProjectZ;
import com.projectz.game.items.Item;

public class InventoryScreen extends ScreenAdapter {
    private Stage inventoryStage;
    Inventory inventory;
    ItemSlot itemSlot;
    ItemStack stack;
    DragAndDrop dnd;


    //UI elements
    private Image inventoryBackground;
    private Image cellImage;
    private Array<Image> cellArray;
    private Label inventoryLabel;
    private TextButton closeInventory;
    private Image itemPNG;
    private Array<ItemSlot> inventorySlots;

    //UI Measurements
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

        //Load each itemSlot into the inventorySlot Array
        inventorySlots = new Array<>();
        for(int i = 0; i < inventory.getInventorySize(); i++) {
            inventorySlots.add(inventory.getInventory(i));
        }
    }

    public void initInventory() {
        inventoryBackground = new Image(new Texture("inventory.png"));
        inventoryBackground.setPosition(Gdx.graphics.getWidth() / 2 - inventoryBackground.getWidth() /2, Gdx.graphics.getHeight() / 2 - inventoryBackground.getHeight() / 2);
        inventoryBackground.toBack();
        inventoryStage.addActor(inventoryBackground);

       //closeInventory = new TextButton("Close", new TextButton.TextButtonStyle();
    }

    public void addCells() {
        cellArray = new Array<>();
        float inventoryWidth = inventoryBackground.getWidth() / 2;
        float inventoryHeight = inventoryBackground.getHeight() / 2;
        final int SPACING = 10;
        int cell_row = 0;
        int cell_col = 0;
        int slotIndex = 0;

        for(int i = 0; i < COL_LENGTH; i++) {
            for(int j = 0; j < ROW_LENGTH; j++) {
                //Create new cell
                cellImage = new Image(new Texture("cell.png"));

                //Set the position of every cell
                cellImage.setPosition(screenWidth - inventoryWidth + SPACING + cell_row,
                        screenHeight - inventoryHeight + (inventoryBackground.getHeight() - CELL_LENGTH - SPACING - cell_col));
                //Add cell to array
                cellArray.add(cellImage);

                //Add cell to the stage
                inventoryStage.addActor(cellImage);

                dnd.addTarget(new DragAndDrop.Target(cellImage){
                    @Override
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        return true;
                    }

                    @Override
                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        //Place payload into the given cell
                        payload.getDragActor().setPosition(cellImage.getImageX(), cellImage.getY());
                    }
                });

                //Adds spacing between cells
                cell_row += CELL_LENGTH + SPACING;
            }
            //Creates the next row
            cell_row = 0;
            cell_col += CELL_LENGTH + SPACING;
        }
    }


    public void loadInventory() {
        int i = 0;
        for(final ItemSlot slot : inventorySlots) {
            if(!slot.isEmpty()) {
                itemPNG = new Image(new Texture(slot.getPngName()));

                itemPNG.setPosition(cellArray.get(i).getX(), cellArray.get(i).getY());

                inventoryStage.addActor(itemPNG);

                itemPNG.toFront();

                dnd.addSource(new DragAndDrop.Source(itemPNG) {
                    @Override
                    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                        DragAndDrop.Payload payload = new DragAndDrop.Payload();
                        payload.setDragActor(itemPNG);
                        payload.getDragActor().toFront();
                        payload.setObject(slot);
                        return payload;
                    }

                    @Override
                    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                        if(target == null) {
                            //Return the item to its orginal position in the array
                            
                        }
                    }
                });
            }
            i++;
        }
    }

    public void updateInventory() {
        //TODO when items are moved in UI, chage where the item is in array
    }

    @Override
    public void render (float delta) {
        inventoryStage.getViewport().update(800, 600, true);
        Gdx.input.setInputProcessor(inventoryStage);
        dnd = new DragAndDrop();
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        initInventory();
        addCells();
        loadInventory();

        inventoryStage.act();
        inventoryStage.draw();
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
}

