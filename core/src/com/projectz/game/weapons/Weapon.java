package com.projectz.game.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.projectz.game.items.Item;

public abstract class Weapon extends Item{

    public Weapon(String name) {
        super(name, ItemType.Weapon, 1, new Pixmap(Gdx.files.internal("potion.png")));
    }
    
}