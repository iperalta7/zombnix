package com.projectz.game.weapons;

import com.projectz.game.items.Item;

public abstract class Weapon extends Item{

    public Weapon(String name, String ItemPng) {
        super(name, ItemType.Weapon, 1, ItemPng);
    }
    
}
