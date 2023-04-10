package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.player.Player;

public class HotBarRenderer extends Actor {
    HotBar hotBar;
    private Player player;
    private OrthographicCamera camera;
    private final float SCALE_FACTOR = 1f;

    public HotBarRenderer(HotBar hotBar) {
        this.hotBar = hotBar;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(camera.combined);

        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i], 550+i*75, 150,
                    hotBar.hotBarSlotSprites[i].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i].getHeight()*SCALE_FACTOR);
        }
        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i+3], 550+i*75, 75,
                    hotBar.hotBarSlotSprites[i+3].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i].getHeight()*SCALE_FACTOR);
        }
        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i+6], 550+i*75, 0,
                    hotBar.hotBarSlotSprites[i+6].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i+6].getHeight()*SCALE_FACTOR);
        }

    }
    @Override
    public void act(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            hotBar.setActiveHotBarSlot(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            hotBar.setActiveHotBarSlot(1);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            hotBar.setActiveHotBarSlot(2);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            hotBar.setActiveHotBarSlot(3);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
            hotBar.setActiveHotBarSlot(4);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
            hotBar.setActiveHotBarSlot(5);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
            hotBar.setActiveHotBarSlot(6);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
            hotBar.setActiveHotBarSlot(7);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
            hotBar.setActiveHotBarSlot(8);
        }
        //TODO add ability to scroll through hotbar
    }
}
