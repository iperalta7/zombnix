package com.projectz.game.items;

public class ItemHealPotion extends Item{

    public ItemHealPotion() {
<<<<<<< HEAD
        super("Healing Potion", ItemType.Consumable, 5);
=======
        super("Healing Potion", ItemType.Consumable, 3);
>>>>>>> 696678b (Commit before rebase)
    }

    @Override
    public void onActivate() {
        //TODO: Get the player and add some amount of health to the player.
        System.out.println("Healed the player!");
    }
    
}
