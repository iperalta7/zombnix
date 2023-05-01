package com.projectz.game.screens;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.inventory.ItemStack;
import com.projectz.game.items.Item;

public class SlotTarget extends Target{
    private ItemSlot slot;
    private SlotActor slotActor;
    Inventory inventory;

    /**
     * Constructor for the SlotTarget Class
     * Allows items to be dragged to a target cells
     *
     * @param slotActor slotActor window that contains the itemSlot
     * @param inventory player inventory
     */
    public SlotTarget(SlotActor slotActor, Inventory inventory) {
        super(slotActor);
        this.slotActor = slotActor;
        this.slot = slotActor.getItemSlot();
        this.inventory = inventory;
    }

    /**
     * Return true if the drag is possible.
     * Return false if trying to drag a consumable item to a weapon cell.
     * @return drag if drag is valid or not valid
     */
    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if(slotActor.getSlotIndex() == 9 && (((ItemStack)payload.getObject()).getItem().getType() == Item.ItemType.Consumable)) {
            return false;
        }
        else if(slotActor.getSlotIndex() == 10 && (((ItemStack)payload.getObject()).getItem().getType() == Item.ItemType.Consumable)) {
            return false;
        }
        return true;
    }

    /**
     * Dropping the item to an appropriate slot actor window
     * Accepts drop if cell is empty
     * checks whether an item is dropped in a weapon cell
     */
    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        //Check if item is dropped into WeaponSlot (update primary/secondary slot)
        if(slotActor.getCellType() == 2 && slotActor.getSlotIndex() == 9) {
            //inventory.setPrimary((ItemSlot) payload.getObject());
            inventory.getPrimary().setStack((ItemStack)payload.getObject());
        }
        else if(slotActor.getCellType() == 2 && slotActor.getSlotIndex() == 10) {
            //inventory.setSecondary((ItemSlot) payload.getObject());
            inventory.getSecondary().setStack((ItemStack)payload.getObject());
        }

        //Accepts Drop
        if(slot.isEmpty()) {
            this.slot.clearSlot();
            this.slot.setStack((ItemStack)payload.getObject());
            this.slotActor.replaceSlot(slot, (Image) payload.getDragActor());
            ((Image) payload.getDragActor()).setUserObject(slotActor);
            slotActor.add((Image) payload.getDragActor());
            inventory.getAllInventory().get(this.slotActor.getSlotIndex()).setStack((ItemStack)payload.getObject());
        }
    }
}