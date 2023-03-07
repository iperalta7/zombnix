package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;


import com.projectz.game.player.Weapon;
import com.projectz.game.player.Bullet;

//Player.java
public class Player extends Actor {

    private Vector2 position;
    public final float speed;
    private Texture playerTexture;
    private Weapon weapon;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();

    //default constructor
    //this is where we give the player a texture/skin
    // speed is defaulted ( smaller equals slower...vice versa)

    public Player () {
        position = new Vector2();
        speed = 25f;
        playerTexture = new Texture("player.png");
        weapon = new Weapon(this);
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

    }

    //draw method for player
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Draw the player sprite at the current position
        batch.draw(playerTexture, position.x, position.y);

        // Draw the bullets
        weapon.draw(batch, parentAlpha);
    }

    //used for memory management
    public void dispose() {
        weapon.dispose();
        playerTexture.dispose();
    }
}