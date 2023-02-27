package com.projectz.game.items;

public abstract class Item {
    
    public static final ItemHealPotion HealingPotion = new ItemHealPotion();
    
    private String name;
    public enum ItemType {Weapon, Consumable};
    private ItemType type;
    private int maxStackSize = 64;
    
    public Item(String name, ItemType t, int maxStackSize){
        this.name = name;
        this.type = t;
        this.maxStackSize = maxStackSize;
    }
    
    public String getName(){ return name; }
    
    public ItemType getType(){ return type; }
    
    public int getMaxStackSize(){ return maxStackSize; }
    
    /** Checks if the item is of the same class as the other item, instead of the same object. */
    @Override
    public boolean equals(Object i){
        return i.getClass() == this.getClass();
    }
    
    public abstract void onActivate();
}
