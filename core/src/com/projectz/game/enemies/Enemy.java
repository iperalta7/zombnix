package com.projectz.game.enemies;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.items.Item;


public abstract class Enemy extends Actor {
    
    Item[] drops;
    int health;
    protected Texture enemyTexture;
    protected Vector2 position;
    protected float speed;
    
    public Enemy(Item[] dropItems, int health){
        this.drops = dropItems;
        this.health = health;
        this.position = new Vector2();
        this.speed = 50f;
    }

    public Vector2 getPosition(){
        return position;
    }

    public abstract void act(float deltaTime);

    public void draw(Batch batch, float parentAlpha) {
        // Draw the player sprite at the current position
        batch.draw(enemyTexture, position.x, position.y);
    }

    //used for memory management
    public void dispose() {
        enemyTexture.dispose();
    }
    
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
