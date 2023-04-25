package com.projectz.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.projectz.game.weapons.WeaponSword;

public abstract class Item {

    public static final ItemHealPotion HealingPotion = new ItemHealPotion();
    public static final ItemSpeedPotion SpeedPotion = new ItemSpeedPotion();
    public static final WeaponSword sword = new WeaponSword();

    private String name;
    public enum ItemType {Weapon, Consumable};
    private ItemType type;
    private int maxStackSize = 64;
    private String itemPNG;
    private Sprite itemSprite;

    private Texture ItemTexture;

    public Item(String name, ItemType t, int maxStackSize, String texture){
        this.name = name;
        this.type = t;
        this.maxStackSize = maxStackSize;
        this.itemPNG = texture;

        Pixmap pixmap200 = new Pixmap(Gdx.files.internal(this.itemPNG));

        Pixmap pixmap100 = new Pixmap(50, 50, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        this.ItemTexture = new Texture(pixmap100);
        this.itemSprite = new Sprite(ItemTexture);
        pixmap200.dispose();
        pixmap100.dispose();
    }

    public String getName(){ return name; }

    public ItemType getType(){ return type; }

    public int getMaxStackSize(){ return maxStackSize; }

    public String getItemPNG() { return itemPNG; }


    public Texture getItemTexture(){return this.ItemTexture;}

    /** Checks if the item is of the same class as the other item, instead of the same object. */
    @Override
    public boolean equals(Object i){
        return i.getClass() == this.getClass();
    }

    public abstract void onActivate();

    public Sprite getItemSprite() {
        return itemSprite;
    }
}