package com.projectz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;

public class InventoryActor extends Window {
    private Table inventoryTable;

    //Determine Which cells to add to the GUI
    final private int INVENTORY_CELL = 0;
    final private int HOT_BAR_CELL = 1;
    final private int WEAPON_CELL = 2;

    int screenHeight = Gdx.graphics.getHeight() / 2;
    int screenWidth = Gdx.graphics.getWidth() / 2;

    public InventoryActor(Inventory inventory, DragAndDrop dnd) {
        super("", loadDefualtInventory());

        //Initiate Inventory Features
        initScreenBackground();
        initTable();
        initSlotCells(inventory,dnd);

        //Window Properties
        this.setSize(250,430);
        this.setPosition(screenWidth - this.getWidth() / 2, screenHeight - this.getHeight() / 2);
        this.setMovable(true);
        this.setVisible(true);
        this.add(inventoryTable);
    }

    private static WindowStyle loadDefualtInventory() {
        WindowStyle inventoryStyle = new Window.WindowStyle(new BitmapFont(),
                Color.BLACK, new TextureRegionDrawable(new Texture("inventory.png")));

        return inventoryStyle;
    }


    private void initScreenBackground() {
        Image background = new Image( new Texture("Background_Tiles.jpg"));
        background.setHeight(background.getHeight() * 3);
        background.setWidth(background.getWidth() * 4);
        InventoryScreen.inventoryStage.addActor(background);
    }

    private void initTable() {
        inventoryTable = new Table();
        inventoryTable.setSize(250,430);
        inventoryTable.defaults().space(10);
        inventoryTable.setDebug(true);
    }

    private void initSlotCells(Inventory inventory, DragAndDrop dnd) {
        SlotActor slotActor;
        int i = 0;
        for(ItemSlot slots : inventory.getAllInventory()) {
            if(i < 9) {
                slotActor = new SlotActor(slots, i, INVENTORY_CELL);
            }
            else if(i == 9) {
                inventory.getAllInventory().set(i, inventory.getPrimary());
                slotActor = new SlotActor(inventory.getPrimary(), i, WEAPON_CELL);
            }
            else if(i == 10) {
                inventory.getAllInventory().set(i, inventory.getSecondary());
                slotActor = new SlotActor(inventory.getSecondary(), i, WEAPON_CELL);
            }
            else {
                slotActor = new SlotActor(slots,i, HOT_BAR_CELL);
            }

            dnd.addSource(new SlotSource(slotActor, dnd, inventory));
            dnd.addTarget(new SlotTarget(slotActor, inventory));

            inventoryTable.add(slotActor);

            i++;
            if(i % 3 == 0) {
                inventoryTable.row();
            }
        }
    }
}