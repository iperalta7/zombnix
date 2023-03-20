package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;


//Player.java
public class Player extends Actor {

    private OrthographicCamera camera;
    private Vector2 position;
    private float speed;
    private Texture playerTexture;

	Inventory inventory;


    private int health;
    private int expValue;
    private int expLevel;

    
    //default constructor
    //this is where we give the player a texture/skin
    // speed is defaulted ( smaller equals slower...vice versa)


    // Health and XP values are initialized


    public Player () {
        position = new Vector2();
        speed = 30f;
        playerTexture = new Texture("player.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        
        
		// Testing the inventory system.
        
		inventory = new Inventory();
		inventory.printInventory();
		inventory.addItem(Item.HealingPotion, 5);
		inventory.printInventory();

        // Initialize health and xp
        health = 100;
        expLevel = 1;
        expValue = 0;

    }

    //changes the position of player object based on input
    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltaTime;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            inventory.useConsumable(Item.HealingPotion);
        }

        // update the camera position to follow the player
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.update();
    }

    //draw method for player
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // set the Batch projection matrix to match the camera's view
        batch.setProjectionMatrix(camera.combined);


        // Draw the player sprite at the current position
        batch.draw(playerTexture, position.x, position.y);

    }

    //used for memory management
    public void dispose() {
        playerTexture.dispose();
    }


    public int getHealth() {
        return health;
    }
    public int getExpValue() {
        return expValue;
    }
    public int getExpLevel() {
        return expLevel;
    }
    public void setExpValue(int expValue) {
        this.expValue = expValue;
    }

}



