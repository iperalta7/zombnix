package com.projectz.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ItemHealPotion extends Item{

    public ItemHealPotion() {
        super("Healing Potion", ItemType.Consumable, 5, "potion.png");
    }

    @Override
    public void onActivate() {
        //TODO: Get the player and add some amount of health to the player.
        System.out.println("Healed the player!");
    }

}