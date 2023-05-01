package com.projectz.game.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;

import java.util.ArrayList;

/**
 * This class contains a reference to the hot bar slots in
 * the inventory. It also contains arrays for the sprites which
 * are displayed as the hot bar.
 */
public class HotBar {
    protected ArrayList<ItemSlot> hotBarSlots;
    protected ItemSlot activeHotBarSlot;
    protected Sprite[] hotBarSlotSprites;
    protected Sprite[] hotBarItemSprites;
    protected Inventory inventory;
    private int activeSlotNumber;

    /**
     * The constructor initializes the hot bar slots and and
     * sprites.
     * @param inventory the inventory object used by all other objects
     */
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

    /**
     * Sets the active hot bar slot based on number passed in, which is
     * determined by user's key press. Also calls the function to fill
     * the sprite array.
     * @param slotNumber the number corresponding to the hot bar slot
     */
    public void setActiveHotBarSlot(int slotNumber) {
        activeHotBarSlot = hotBarSlots.get(slotNumber);
        activeSlotNumber = slotNumber;
        fillSelectedSlotSprites();
    }

    /**
     * Fills the sprite array for each slot based on whether that
     * slot is selected. If a slot's number is the same as the active
     * slot number, the "selected" sprite is set. If not, the "unselected"
     * slot is set.
     */
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

    /**
     * Fills the sprite array for the items inside the slot. Called
     * during the looping "act" function in HotBarRenderer. The array
     * is updated with the current inventory item sprites. If an item
     * does not exist in the hot bar slot, it will throw an exception,
     * in which case the sprite is set to null.
     */
    protected void fillSlotItemSprites() {
        for(int i = 0; i < hotBarItemSprites.length; i++) {
            try {
                hotBarItemSprites[i] = hotBarSlots.get(i).getStack().getItem().getItemSprite();
                //System.out.println("Item filled");
            } catch(Exception e) {
                //System.out.println("Item slot empty");
                hotBarItemSprites[i] = null;
            }

        }
    }
}
