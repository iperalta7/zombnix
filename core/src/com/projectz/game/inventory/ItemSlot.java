package com.projectz.game.inventory;

import com.projectz.game.items.Item.ItemType;

public class ItemSlot{
    
    private ItemStack stack;
    private ItemType slotType;
    
    public ItemSlot(ItemType type){
        stack = null;
        slotType = type;
    }
    
    public ItemStack getStack(){
        return stack;
    }
    
    public void setStack(ItemStack stack){
        this.stack = stack;
    }
    
    public ItemStack drop(){
        ItemStack buffer = stack;
        stack = null;
        return buffer;
    }
    
    public ItemType getSlotType(){
        return slotType;
    }
    
    public String getItemName(){
        if(stack == null) {
            return "Empty";
        }
        else{
            return stack.getItem().getName();
        }
    }
    
    public void Use(){
        if(stack != null){
            stack.getItem().onActivate();
            stack.reduce(1);
            if(stack.getCount() <= 0) stack = null;
        }
    }
}