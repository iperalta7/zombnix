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

    public void loadSlotItem(ItemSlot slot) {
        //Loading Consumable items
        if(!slot.isEmpty()) {
            this.itemPng = (new Image(new Texture(slot.getPngName())));
            this.add(itemPng);
            itemPng.setUserObject(this);
        }
        else { itemPng = null; }
    }

    public void clearSlotActor() {
        this.slot = null;
        this.itemPng = null;
    }

    public boolean hasItemPng() {
        return !(itemPng == null);
    }

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

    public ItemSlot getItemSlot() {
        return slot;
    }

    public Image getItemImage() {
        return this.itemPng;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void replaceSlot(ItemSlot slot, Image itemPng) {
        this.slot = slot;
        this.itemPng = itemPng;
    }

    public int getCellType() {
        return cellType;
    }

}