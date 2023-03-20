package com.projectz.game.inventory;

import com.projectz.game.items.Item;

public class ItemStack {

    private int count = 0;
    private Item item;

    public ItemStack(Item item, int count){
        this.count = count;
        this.item = item;
    }

    public int getCount(){
        return count;
    }

    public Item getItem(){
        return item;
    }

    public void add(int i){
        if(i <= 0) return;
        count += i;
    }

    public void reduce(int i){
        if(i <= 0) return;
        count -= i;
    }

    public boolean isFull(){
        return count == item.getMaxStackSize();
    }

}