package com.projectz.game.inventory;

import java.util.ArrayList;

import com.projectz.game.items.Item;
import com.projectz.game.items.Item.ItemType;

public class Inventory{
    
    private int currentSlot = 0;
    private ItemSlot primary;
    private ItemSlot secondary;
    private ArrayList<ItemSlot> slots;
    private int slotCount = 5;
    
    public Inventory(){
        primary = new ItemSlot(ItemType.Weapon);
        secondary = new ItemSlot(ItemType.Weapon);
        
        slots = new ArrayList<ItemSlot>();
        for(int i = 0; i < slotCount; i++){
            slots.add(new ItemSlot(ItemType.Consumable));
        }
    }
    
    /** Returns true if the item was added to the inventory successfully, false if not. */
    public boolean addItem(Item item){
        if(item == null) return false;
        
        // TODO: Enable addition of weapons to the inventory.
        // What that should do is to check if the primary is empty and add if it is.
        // If it is not, check the secondary and add if IT is.
        // If neither are empty, swap current weapon with incoming item.
        
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
                slots.get(i).setStack(new ItemStack(item, 1));
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
    
    public void useConsumable(int slot){
        if(isInSlotBounds(slot)){
            slots.get(slot).Use();
        }
    }
    
    private boolean isInSlotBounds(int slot){
        return slot < 0 || slot >= slotCount;
    }
    
}