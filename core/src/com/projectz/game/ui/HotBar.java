package com.projectz.game.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;

import java.util.ArrayList;

public class HotBar {
    protected ArrayList<ItemSlot> hotBarSlots;
    protected ItemSlot activeHotBarSlot;
    protected Sprite[] hotBarSlotSprites;
    protected Sprite[] hotBarItemSprites;
    protected Inventory inventory;
    private int activeSlotNumber;

    public HotBar(Inventory inventory) {
        this.inventory = inventory;
        hotBarSlots = inventory.getAllHotBarSlots();
        activeSlotNumber = 0;
        activeHotBarSlot = hotBarSlots.get(0);
        hotBarSlotSprites = new Sprite[9];
        hotBarItemSprites = new Sprite[9];
        fillSelectedSlotSprites();
        fillSlotItemSprites();
    }
    public void setActiveHotBarSlot(int slotNumber) {
        activeHotBarSlot = hotBarSlots.get(slotNumber);
        activeSlotNumber = slotNumber;
        fillSelectedSlotSprites();
    }

    private void fillSelectedSlotSprites() {
        for(int i = 0; i < hotBarSlotSprites.length; i++) {
            if(i == activeSlotNumber) {
                hotBarSlotSprites[i] = HotBarSprites.SELECTED_SLOT_SPRITE;
            }
            else {
                hotBarSlotSprites[i] = HotBarSprites.UNSELECTED_SLOT_SPRITE;
            }
        }
    }

    private void fillSlotItemSprites() {
        for(int i = 0; i < hotBarItemSprites.length; i++) {
            try {
                hotBarItemSprites[i] = hotBarSlots.get(i).getStack().getItem().getItemSprite();
            } catch(Exception e) {
                System.out.println("Item slot empty");
            }

        }
    }
}
