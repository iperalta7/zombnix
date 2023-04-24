package com.projectz.game.screens;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.inventory.ItemSlot;
import com.projectz.game.items.Item;

public class SlotTarget extends Target{
    private ItemSlot slot;
    private SlotActor slotActor;
    Inventory inventory;

    public SlotTarget(SlotActor slotActor, Inventory inventory) {
        super(slotActor);
        this.slotActor = slotActor;
        this.slot = slotActor.getItemSlot();
        this.inventory = inventory;
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if(slotActor.getSlotIndex() == 9 && ((ItemSlot) payload.getObject()).getSlotType() == Item.ItemType.Consumable) {
            return false;
        }
        else if(slotActor.getSlotIndex() == 10 && ((ItemSlot) payload.getObject()).getSlotType() == Item.ItemType.Consumable) {
            return false;
        }
        return true;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        //Check if item is dropped into WeaponSlot (update primary/secondary slot)
        if(slotActor.getCellType() == 2 && slotActor.getSlotIndex() == 9) {
            inventory.setPrimary((ItemSlot) payload.getObject());
        }
        else if(slotActor.getCellType() == 2 && slotActor.getSlotIndex() == 10) {
            inventory.setSecondary((ItemSlot) payload.getObject());
        }

        //Accepts Drop
        if(slot.isEmpty()) {
            this.slot.clearSlot();
            this.slot = (ItemSlot) payload.getObject();
            this.slotActor.replaceSlot((ItemSlot) payload.getObject(), (Image) payload.getDragActor());
            ((Image) payload.getDragActor()).setUserObject(slotActor);
            slotActor.add((Image) payload.getDragActor());
            inventory.getAllInventory().set(this.slotActor.getSlotIndex(), (ItemSlot) payload.getObject());
        }
    }
}