package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;


//Player.java
public class Player extends Actor {
    private Vector2 position;
    private float speed;
    private Texture playerTexture;

    public Player () {
        position = new Vector2();
        speed = 30f;
        playerTexture = new Texture("player.png");
    }

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
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // Draw the player sprite at the current position
        batch.draw(playerTexture, position.x, position.y);

    }

    public void dispose() {
        playerTexture.dispose();
    }
}



