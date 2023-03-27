package com.projectz.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;



public class EnemyBoss extends Enemy{
    public EnemyBoss(){
        super(new Item[] {Item.HealingPotion}, 100);

        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("zombie_man.png"));

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
        if(Gdx.input.isKeyPressed(Input.Keys.T)){
            this.alive = false;
        }
    }
}