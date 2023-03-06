package com.projectz.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.player.Player;

public class StatusHUD {
    private Player player;
    private Texture healthTexture;
    private Texture expTexture;
    public StatusHUD(Player player) {
        this.player = player;
        //Default Textures
        healthTexture = StatusHUDTextures.HEALTH_TEXTURE_10;
        expTexture = StatusHUDTextures.EXP_TEXTURE_10;
    }

    public Texture getHealthTexture() {
        return healthTexture;
    }
    public Texture getExpTexture() {
        return expTexture;
    }
    public void update() {
        int health = player.getHealth();
        int expValue = player.getExpValue();
        updateHealth(health);
        updateExp(expValue);
    }
    private void updateHealth(int health) {
        if(health == 0) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_0;
        } else if(health > 0 && health < 15) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_1;
        } else if(health >= 15 && health < 25) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_2;
        } else if(health >= 25 && health < 35) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_3;
        } else if(health >= 35 && health < 45) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_4;
        } else if(health >= 45 && health < 55) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_5;
        } else if(health >= 55 && health < 65) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_6;
        } else if(health >= 65 && health < 75) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_7;
        } else if(health >= 75 && health < 85) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_8;
        } else if(health >= 85 && health < 95) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_9;
        } else if(health >= 95 && health < 100) {
            healthTexture = StatusHUDTextures.HEALTH_TEXTURE_10;
        }
    }
    public void updateExp(int expValue) {

    }
    public Player getPlayer() {
        return player;
    }
}
