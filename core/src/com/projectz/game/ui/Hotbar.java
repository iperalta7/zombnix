package com.projectz.game.ui;

import com.badlogic.gdx.utils.Array;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.items.Item;

import java.util.ArrayList;

public class HotBar {
    protected ArrayList<ItemSlot> hotBarSlots;
    protected int activeSlotNumber;
    protected Inventory inventory;

    public HotBar(Inventory inventory) {
        this.inventory = inventory;
        hotBarSlots = inventory.getHotBarSlots();
        activeSlotNumber = 1;
    }
    public void setActiveHotBarSlot(int slotNumber) {
        activeSlotNumber = slotNumber;
    }
}
