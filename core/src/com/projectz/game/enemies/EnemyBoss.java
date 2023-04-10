package com.projectz.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;
import com.projectz.game.player.Player;

public class EnemyBoss extends Enemy{

    public EnemyBoss(Item[] HealingPotion, int health, Player player){
        super(new Item[] {Item.HealingPotion}, 100, player);

        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("C:/Users/demet/Documents/URI_Folders/CSC305/s23g/assets/Zombie/zombie_man.png"));

        Pixmap pixmap100 = new Pixmap(50, 50, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        enemyTexture = new Texture(pixmap100);
        pixmap200.dispose();
        pixmap100.dispose();
    }

    public void act(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * deltaTime;
        }
    }
}