package com.projectz.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.items.Item;
import com.projectz.game.screens.*;


public class SlotActor extends Window {
    private ItemSlot slot;
    private Image itemPng;
    private int slotIndex;
    private int cellType;


    /**
     * Constructor for the SlotActor Class
     * Creates a Slot Window for each itemSlot within the player inventory
     * Class allows easier access to graphical itemSlot properties and attributes
     *
     * @param slot The item slot containing the item stack
     * @param index the position of where the item stack is loacted within the inventory
     * @param cellType what type of cell the slot actor is
     */
    public SlotActor(ItemSlot slot, int index, int cellType) {
        super("", loadDefualtCell(cellType));
        this.setVisible(true);
        this.setSize(50,50);
        this.setTouchable(Touchable.enabled);

        this.slot = slot;
        this.slotIndex = index;
        this.cellType = cellType;

        //Load item
        loadSlotItem(slot);
    }



    /**
     * If ItemSlot is not empty, display the item to this window
     * @param slot the item slot containing the item stack
     */
    public void loadSlotItem(ItemSlot slot) {
        //Loading Consumable items
        if(!slot.isEmpty()) {
            this.itemPng = (new Image(new Texture(slot.getPngName())));
            this.add(itemPng);
            itemPng.setUserObject(this);
        }
        else { itemPng = null; }
    }


    /**
     * Clearing the slot actor when an item is moved to this window
     */
    public void clearSlotActor() {
        this.slot.clearSlot();
        this.itemPng = null;
    }


    /**
     * Checks whether the window contains an item png
     */
    public boolean hasItemPng() {
        return !(itemPng == null);
    }



    /**
     * Loads default cell design
     * @param cellType what type of cell this window should be
     * @return slotStyle chooses wihch cell png to use
     */
    private static WindowStyle loadDefualtCell(int cellType) {
        WindowStyle slotStyle;
        if(cellType == 0) {
            slotStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("cell.png")));
        }
        else if(cellType == 1){
            slotStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("hotbarcell.png")));
        }
        else {
            slotStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("primAndSec.png")));
        }
        return slotStyle;
    }


    /**
     * Gets the item slot
     * @return return the item slot belonging to this window
     */
    public ItemSlot getItemSlot() {
        return slot;
    }


    /**
     * gets the item image
     *
     * @return return the item png belonging to this window
     */
    public Image getItemImage() {
        return this.itemPng;
    }


    /**
     * gets the slot index
     *
     * @return returns the slots index, the index of each window does not change
     */
    public int getSlotIndex() {
        return slotIndex;
    }


    /**
     * When items are moved in the inventory it is necessary
     * to replace the stack between different itemSlots
     * @param slot the slot to be replaced
     * @param itemPng the itemPng to be replaced
     */
    public void replaceSlot(ItemSlot slot, Image itemPng) {
        this.slot.setStack(slot.getStack());
        this.itemPng = itemPng;
    }

    public int getCellType() {
        return cellType;
    }

}