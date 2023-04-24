package com.projectz.game.inventory;

import java.util.ArrayList;

import com.projectz.game.items.Item;
import com.projectz.game.items.Item.ItemType;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.weapons.WeaponSword;

public class Inventory{
    private int currentSlot = 0;
    private ItemSlot primary;
    private ItemSlot secondary;
    private ArrayList<ItemSlot> slots;
    private ArrayList<ItemSlot> hotBarSlots;
    private int slotCount = 9;

    public Inventory(){
        primary = new ItemSlot(ItemType.Weapon);
        secondary = new ItemSlot(ItemType.Weapon);

        slots = new ArrayList<ItemSlot>();
        hotBarSlots = new ArrayList<ItemSlot>();
        for(int i = 0; i < slotCount; i++){
            slots.add(new ItemSlot(ItemType.Consumable));
            hotBarSlots.add(new ItemSlot(ItemType.Consumable));
        }

        hotBarSlots.get(2).setStack(new ItemStack(new WeaponSword(), 1));
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

    public void replaceItemSLot(ItemSlot itemSlot, int index) {
        slots.get(index).setStack(itemSlot.getStack());
    }

    public int getInventorySize() {
        return slotCount;
    }

    public ItemSlot getInventory(int index) {
        return slots.get(index);
    }
    public ArrayList<ItemSlot> getHotBarSlots() {
        return hotBarSlots;
    }
    public ArrayList<ItemSlot> getAllInventory() { return slots; }
}