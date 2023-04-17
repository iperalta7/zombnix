package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Bullet.java
public class Bullet extends Actor {

    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private Texture bulletTexture;
    private boolean active;

    public Bullet(float x, float y, Vector2 direction, float speed) {
        position = new Vector2(x, y);
        this.direction = direction;
        this.speed = speed;
        bulletTexture = new Texture("bullet-blue.png");
        active = true;
        setBounds(position.x, position.y, bulletTexture.getWidth(), bulletTexture.getHeight());
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void act(float delta) {
        position.add(direction.x * speed * delta, direction.y * speed * delta);
        setBounds(position.x, position.y, bulletTexture.getWidth(), bulletTexture.getHeight());

        // check if bullet is off screen
        if (position.x < 0 || position.y < 0 || position.x > Gdx.graphics.getWidth() || position.y > Gdx.graphics.getHeight()) {
            active = false;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(bulletTexture, position.x, position.y);
    }

    public void dispose() {
        bulletTexture.dispose();
    }

    public Vector2 getPosition(){
        return this.position;
    }
}
