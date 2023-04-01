package com.projectz.game.enemies;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.items.Item;
import com.projectz.game.player.Bullet;
import com.projectz.game.player.Player;


public abstract class Enemy extends Actor {
    
    Item[] drops;
    int health;
    protected Texture enemyTexture;
    protected Vector2 position;
    protected float speed;
    protected boolean alive;
    protected Player targetedPlayer;
    protected float minDistToChase;
    protected float attackDistance;
    protected Bullet[] bullets;



    public Enemy(Item[] dropItems, int health, float speed, Player player, float initial_x, float initial_y){
        this.drops = dropItems;
        this.health = health;
        this.position = new Vector2(initial_x,initial_y);
        this.speed = 20f;
        this.alive = true;
        this.targetedPlayer = player;
        bullets = null;
        attackDistance = 5;
    }
    public Vector2 getPosition(){
        return position;
    }
    public abstract void act(float deltaTime);

    public void draw(Batch batch, float parentAlpha) {
        // Draw the player sprite at the current position
        batch.draw(enemyTexture, position.x, position.y);
        itemDropAndDeadCheck(batch);
    }

    //used for memory management
    public void dispose() {
        enemyTexture.dispose();
    }

    private void itemDropAndDeadCheck(Batch batch){
        if(!this.alive){
            for (Item drop : drops) {
                batch.draw(drop.getItemTexture(), position.x, position.y);
                // Send message to player to know about dropped item
            }
            this.dispose();
        }
    }
}