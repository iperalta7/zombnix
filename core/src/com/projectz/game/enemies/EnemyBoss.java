package com.projectz.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;
import com.projectz.game.player.Player;
import java.lang.Math;



public class EnemyBoss extends Enemy{
    public EnemyBoss(Player player, float initial_x, float initial_y){
        super(new Item[] {Item.HealingPotion}, 100, 50f, player, initial_x, initial_y);
        enemyTexture = new Texture(createEnemyBossPixmap());
        minDistToChase = 100;
    }

    private Pixmap createEnemyBossPixmap(){
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("zombie_man.png"));

        Pixmap pixmap100 = new Pixmap(50, 50, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        return pixmap100;
    }

    @Override
    public void act(float deltaTime){
        move(deltaTime);
    }

    private void move(float deltaTime){
        float playerPosX = targetedPlayer.getPosition().x;
        float playerPosY = targetedPlayer.getPosition().y;
        float enemyPosX = this.position.x;
        float enemyPosY = this.position.y;
        float enemyPlayerDisplacement = (float) Math.sqrt(Math.pow(playerPosX-enemyPosX,2)+Math.pow(playerPosY-enemyPosY,2));
        if(enemyPlayerDisplacement <= minDistToChase){
            targetedMove(deltaTime);
        }
        else{
            randomMove(deltaTime);
        }
    }

    private void targetedMove(float deltaTime){
    }

    private void randomMove(float deltaTime){
    }



}