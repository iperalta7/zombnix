package com.projectz.game.items;

public class ItemHealPotion extends Item{

    public ItemHealPotion() {
<<<<<<< HEAD
<<<<<<< HEAD
        super("Healing Potion", ItemType.Consumable, 5);
=======
        super("Healing Potion", ItemType.Consumable, 3);
>>>>>>> 696678b (Commit before rebase)
=======
        super("Healing Potion", ItemType.Consumable, 5);
>>>>>>> 95f4c7a (rebase)
    }

    @Override
    public void onActivate() {
        //TODO: Get the player and add some amount of health to the player.
        System.out.println("Healed the player!");
    }
    
}
