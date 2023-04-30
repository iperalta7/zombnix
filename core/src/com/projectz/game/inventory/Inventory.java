package com.projectz.game.inventory;

import java.util.ArrayList;

import com.projectz.game.items.Item;
import com.projectz.game.items.Item.ItemType;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.player.Player;
import com.projectz.game.weapons.WeaponGun;
import com.projectz.game.weapons.WeaponSword;

public class Inventory{
    private int currentSlot = 0;
    private ItemSlot primary;
    private ItemSlot secondary;
    private ArrayList<ItemSlot> slots;
    private ArrayList<ItemSlot> hotBarSlots;
    private Player player;
    private int slotCount = 18;
    private final int HOTBAR_SLOT_COUNT = 9;

    public Inventory(Player player){
        this.player = player;
        primary = new ItemSlot(ItemType.Weapon);
        secondary = new ItemSlot(ItemType.Weapon);

        slots = new ArrayList<ItemSlot>();
        hotBarSlots = new ArrayList<ItemSlot>();
        for(int i = 0; i < slotCount; i++){
            slots.add(new ItemSlot(ItemType.Consumable));
        }
        for(int i = 0; i < HOTBAR_SLOT_COUNT; i++) {
            hotBarSlots.add(slots.get(i+9));
        }
        hotBarSlots.set(0, primary);
        hotBarSlots.set(1, secondary);
        hotBarSlots.get(2).setStack(new ItemStack(new WeaponGun(player), 1));
    }

    /** Returns true if the item was added to the inventory successfully, false if not. */
    public boolean addItem(Item item, int count){
        if(item == null) return false;

        if(item.getType() == ItemType.Weapon){
            if(primary.isEmpty()){
                primary.setStack(new ItemStack(item, 1));
                return true;
            }
            else if(secondary.isEmpty()){
                secondary.setStack(new ItemStack(item, 1));
                return true;
            }
            else{
                if(currentSlot == 0){
                    primary.drop();
                    primary.setStack(new ItemStack(item, 1));
                    return true;
                }
                else if(currentSlot == 1){
                    secondary.drop();
                    secondary.setStack(new ItemStack(item, 1));
                    return true;
                }
            }

            return false;
        }

        // check for existing itemstack in slots
        for(int i = 0; i < slots.size(); i++){
            if(slots.get(i).getStack() == null){
                slots.get(i).setStack(new ItemStack(item, count));
                return true;
            }
            else if(slots.get(i).getStack().getItem().equals(item)){
                if(slots.get(i).getStack().isFull()){
                    continue;
                }
                else{
                    slots.get(i).getStack().add(1);
                    return true;
                }
            }
        }
        // Slot doesn't exist with item

        return false;
    }

    public void setCurrentSlot(int i){
        currentSlot = i;
    }

    public void swapPrimary(){
        ItemStack buffer = primary.getStack();
        primary.setStack(primary.getStack());
        secondary.setStack(buffer);
    }

    public void printInventory(){
        System.out.println("Primary: " + primary.getItemName());
        System.out.println("Secondary: " + secondary.getItemName());

        for(int i = 0; i < slots.size(); i++){
            System.out.println("Slot " + i + ": " + slots.get(i).getItemName());
        }
    }

    public void useConsumable(Item item){
        for(int i = 0; i < slotCount; i++){
            if(slots.get(i).getItemName() == item.getName()){
                slots.get(i).Use();
            }
        }
    }

    public ItemSlot getInventory(int index) {
        return slots.get(index);
    }

    public ItemSlot getHotBar(int index) { return hotBarSlots.get(index); }

    public ArrayList<ItemSlot> getAllInventory() { return slots; }

    public ArrayList<ItemSlot> getAllHotBarSlots() { return hotBarSlots; }

    public ItemSlot getPrimary() {
        return primary;
    }

    public ItemSlot getSecondary() {
        return secondary;
    }

    public void setPrimary(ItemSlot primary) {
        this.primary = primary;
    }
    public void setSecondary(ItemSlot secondary) {
        this.secondary = secondary;
    }
}