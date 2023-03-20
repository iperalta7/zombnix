package com.projectz.game.items;

public class ItemHealPotion extends Item{

    public ItemHealPotion() {
        super("Healing Potion", ItemType.Consumable, 5, "healPotion.png");
    }

    @Override
    public void onActivate() {
        //TODO: Get the player and add some amount of health to the player.
        System.out.println("Healed the player!");
    }
    
}
