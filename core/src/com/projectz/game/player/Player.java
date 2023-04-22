package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.player.Animator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;


//Player.java
public class Player extends Actor {

    private OrthographicCamera camera;
    private Vector2 position;
    private float speed;
    private TextureRegion playerSprite;

    private Animator playerAnimator;
	Inventory inventory;
    
    //default constructor
    //this is where we give the player a texture/skin
    // speed is defaulted ( smaller equals slower...vice versa)

    public Player () {
        playerAnimator = new Animator();
        playerAnimator.setState("RUNNING");
        position = new Vector2();
        speed = 30f;

        playerSprite = playerAnimator.getFrame(0.0f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		// Testing the inventory system.
        
		inventory = new Inventory();
		inventory.printInventory();
		inventory.addItem(Item.HealingPotion, 5);
		inventory.printInventory();

    }

    //changes the position of player object based on input
    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        Vector2 prevPos = new Vector2();
        prevPos.x = position.x;
        prevPos.y = position.y;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime;
            playerAnimator.setFacingRight(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltaTime;
            playerAnimator.setFacingRight(true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            inventory.useConsumable(Item.HealingPotion);
        }
        //Gets the current frame of our Animation
        if(prevPos.x == position.x && prevPos.y == position.y)
            playerAnimator.setState("STANDING");
        else
            playerAnimator.setState("RUNNING");
        playerSprite = playerAnimator.getFrame(deltaTime);

        // update the camera position to follow the player
        camera.position.x = 0;//position.x;
        camera.position.y = 0;//position.y;
        camera.update();
    }
    //draw method for player
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // set the Batch projection matrix to match the camera's view
        batch.setProjectionMatrix(camera.combined);


        // Draw the player sprite at the current position
        batch.draw(playerSprite, position.x, position.y);

    }
    public void dispose() {
        //playerSprite.dispose();
    }
}



