package com.projectz.game.inventory;

<<<<<<< HEAD
import com.projectz.game.items.Item;
=======
>>>>>>> 696678b (Commit before rebase)
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
    
<<<<<<< HEAD
    public boolean isEmpty(){
        return stack == null;
    }
    
    public boolean add(Item item, int count){
        if(stack == null){
            stack = new ItemStack(item, count);
            return true;
        }
        else if(item == stack.getItem()){
            if (stack.getCount() + count > item.getMaxStackSize()){
                return false;
            }
            
            stack.add(count);
            return true;
        }
        return false;
    }
    
=======
>>>>>>> 696678b (Commit before rebase)
    public void Use(){
        if(stack != null){
            stack.getItem().onActivate();
            stack.reduce(1);
            if(stack.getCount() <= 0) stack = null;
        }
    }
}