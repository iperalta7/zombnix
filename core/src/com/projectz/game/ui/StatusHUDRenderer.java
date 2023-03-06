package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class StatusHUDRenderer extends Actor {
    private StatusHUD statusHUD;
    private OrthographicCamera camera;
    //private Vector2 position;
    private Texture healthTexture;
    private Texture expTexture;
    private Label healthLabel;
    private Label expLabel;
    private BitmapFont healthFont;
    private BitmapFont expFont;
    public StatusHUDRenderer(StatusHUD statusHUD) {
        this.statusHUD = statusHUD;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        healthFont = new BitmapFont();
        expFont = new BitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setProjectionMatrix(camera.combined);
        //Update HUD values
        statusHUD.update();
        //Draw Textures for HUD
        batch.draw(statusHUD.getHealthTexture(), 0, 0);
        batch.draw(statusHUD.getExpTexture(), 0, 40);
        //Draw Health and Level values
        healthFont.draw(batch,Integer.toString(statusHUD.getPlayer().getHealth()), 340, 20);
        expFont.draw(batch, Integer.toString(statusHUD.getPlayer().getExpLevel()), 340, 60);
    }
}
