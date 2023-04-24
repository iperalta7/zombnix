package com.projectz.game.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.items.Item;

import java.util.ArrayList;

public class HotBar {
    protected ArrayList<ItemSlot> hotBarSlots;
    protected ItemSlot activeHotBarSlot;
    protected Sprite[] hotBarSlotSprites;
    protected Inventory inventory;
    private int activeSlotNumber;

    public HotBar(Inventory inventory) {
        this.inventory = inventory;
        hotBarSlots = inventory.getAllHotBarSlots();
        activeSlotNumber = 0;
        activeHotBarSlot = hotBarSlots.get(1);
        hotBarSlotSprites = new Sprite[9];
        fillSprites();
    }
    public void setActiveHotBarSlot(int slotNumber) {
        activeHotBarSlot = hotBarSlots.get(slotNumber);
        activeSlotNumber = slotNumber;
        fillSprites();
    }

    private void fillSprites() {
        for(int i = 0; i < 9; i++) {
            if(i == activeSlotNumber) {
                hotBarSlotSprites[i] = HotBarSprites.SELECTED_SLOT_SPRITE;
            }
            else {
                hotBarSlotSprites[i] = HotBarSprites.UNSELECTED_SLOT_SPRITE;
            }
        }
    }
}
