package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.weapons.WeaponGun;

//Player.java
public class Player extends Actor {

    private Vector2 position;
    public final float speed;
    private Texture playerTexture;
    private WeaponGun weapon;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    private OrthographicCamera camera;
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
        speed = 25f;
        playerTexture = new Texture("player.png");
        weapon = new WeaponGun(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		// Testing the inventory system.
		//inventory = new Inventory();
		//inventory.printInventory();
		//inventory.addItem(Item.HealingPotion, 5);
		//inventory.printInventory();


        // Initialize health and xp
        health = 100;
        expLevel = 1;
        expValue = 0;
    }

    public Vector2 getPosition(){
        return position;
    }



    public void setPlayerPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    //changes the position of player object based on input
    @Override
    public void act(float deltaTime) {
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

        weapon.update(deltaTime);


        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            inventory.useConsumable(Item.HealingPotion);
        }
        weapon.update(deltaTime);

    }

    //draw method for player
    @Override
    public void draw(Batch batch, float parentAlpha) {

        // Calculate the new width and height of the texture based on the viewport size
        float factor = 2.4F;
        float scale = camera.viewportWidth / w * factor ; // w is the original window width
        float width = playerTexture.getWidth() * scale;
        float height = playerTexture.getHeight() * scale;


        // Draw the player sprite at the current position
// Draw the player sprite at the current position with the new width and height
        batch.draw(playerTexture, (getStage().getWidth() - playerTexture.getWidth() * 2) / 2, (getStage().getHeight() - playerTexture.getHeight() * 2) / 2, width, height);
        // Draw the bullets
        weapon.draw(batch, parentAlpha);
    }

    public WeaponGun getWeapon() {
        return weapon;
    }
    //used for memory management
    public void dispose() {
        weapon.dispose();
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

