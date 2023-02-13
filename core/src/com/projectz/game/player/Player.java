package com.projectz.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//Player.java
public class Player extends Actor {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    public Player(float positionX, float positionY) {
        this.setPosition(positionX, positionY);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public enum Movement {

    }

}
