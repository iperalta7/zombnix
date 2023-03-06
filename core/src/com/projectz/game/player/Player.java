package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.ArrayList;

//Player.java
//Player.java
public class Player extends Actor {

    private Vector2 position;
    private float speed;
    private Texture playerTexture;
    private ArrayList<Bullet> bullets;
    private float shootDelay;
    private float timeSinceLastShot;

    //default constructor
    //this is where we give the player a texture/skin
    // speed is defaulted (smaller equals slower...vice versa)
    public Player() {
        position = new Vector2();
        speed = 50f;
        playerTexture = new Texture("player.png");
        bullets = new ArrayList<Bullet>();
        shootDelay = 0.2f; // 5 bullets per second
        timeSinceLastShot = 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
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

    }

    //draw method for player
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Draw the player sprite at the current position
        batch.draw(playerTexture, position.x, position.y);

    }


    //used for memory management
    public void dispose() {
        playerTexture.dispose();
        //shootSound.dispose();

    }
}