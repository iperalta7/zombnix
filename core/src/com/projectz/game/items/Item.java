package com.projectz.game.items;

import com.projectz.game.weapons.WeaponSword;

public abstract class Item {

    public static final ItemHealPotion HealingPotion = new ItemHealPotion();
    public static final WeaponSword sword = new WeaponSword();

    private String name;
    public enum ItemType {Weapon, Consumable};
    private ItemType type;
    private int maxStackSize = 64;
    private String itemPNG;

    public Item(String name, ItemType t, int maxStackSize, String itemPNG){
        this.name = name;
        this.type = t;
        this.maxStackSize = maxStackSize;
        this.itemPNG = itemPNG;
    }

    public String getName(){ return name; }

    public ItemType getType(){ return type; }

    public int getMaxStackSize(){ return maxStackSize; }

    public String getItemPNG() { return itemPNG; }

    /** Checks if the item is of the same class as the other item, instead of the same object. */
    @Override
    public boolean equals(Object i){
        return i.getClass() == this.getClass();
    }

    public abstract void onActivate();
}