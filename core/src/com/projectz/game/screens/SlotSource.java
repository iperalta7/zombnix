package com.projectz.game.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.inventory.ItemStack;

public class SlotSource extends Source {
    private DragAndDrop dnd;
    private SlotActor slotActor;
    private Inventory inventory;

    public SlotSource(SlotActor slotActor, DragAndDrop dnd, Inventory inventory) {
        super(slotActor);
        this.dnd = dnd;
        this.slotActor = slotActor;
        this.inventory = inventory;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        ItemSlot slot = slotActor.getItemSlot();
        if(!(slotActor.hasItemPng())) {
            return null;
        }
        else {
            Payload payload = new Payload();
            //ItemSlot payloadSlot = new ItemSlot(slot.getStack().getItem().getType());
            ItemStack payloadStack = new ItemStack(slot.getStack().getItem(), slot.getStack().getCount());
            //payloadSlot.add(slot.getStack().getItem(), slot.getStack().getCount());

            payload.setObject(payloadStack);
            payload.setDragActor(slotActor.getItemImage());
            dnd.setDragActorPosition(getActor().getWidth() / 2, -getActor().getHeight() / 2);
            InventoryScreen.inventoryStage.addActor(slotActor.getItemImage());

            return payload;
        }
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        Image itemPng = slotActor.getItemImage();
        if (target == null) {
            ((Window)itemPng.getUserObject()).add(itemPng);
        }
        else {
            //Clearing original slot from previous item location
            slotActor.clearSlotActor();
            inventory.getInventory(slotActor.getSlotIndex()).clearSlot();
            inventory.printInventory();
        }
    }
}
