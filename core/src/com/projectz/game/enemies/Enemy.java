package com.projectz.game.enemies;

import java.util.Random;

import com.projectz.game.items.Item;

public abstract class Enemy {
    
    Item[] drops;
    int health;
    
    public Enemy(Item[] dropItems, int health){
        this.drops = dropItems;
        this.health = health;
    }
    
    public abstract void Update();
    public abstract void Render();
    public abstract void attack();
    
    public void onDeath(){
        // Random r = new Random();
        
        // for(int i = 0; i < 3; i++){
        //     int index = r.nextInt(drops.length);
        //     int drop_count = r.nextInt(drops[index].getMaxStackSize());
        //     World.drop(drops[index], drop_count);
        //     // choose a count of drops,
        //     // "drop" them
        // }
    }
    
}
