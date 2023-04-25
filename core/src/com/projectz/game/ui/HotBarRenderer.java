package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.player.Player;

public class HotBarRenderer extends Actor {
    HotBar hotBar;
    private Player player;
    private OrthographicCamera camera;
    private final float SCALE_FACTOR = 1f;
    private float timeCount;
    private BitmapFont fontDrawer;

    public HotBarRenderer(HotBar hotBar) {
        this.hotBar = hotBar;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        timeCount = 3;
        fontDrawer = new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"));
        fontDrawer.getData().setScale(0.5f);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(camera.combined);

        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i], 550+i*75, 150,
                    hotBar.hotBarSlotSprites[i].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i].getHeight()*SCALE_FACTOR);
            try {
                batch.draw(hotBar.hotBarItemSprites[i], 565+i*75, 165,
                        hotBar.hotBarItemSprites[i].getWidth()*SCALE_FACTOR,
                        hotBar.hotBarItemSprites[i].getHeight()*SCALE_FACTOR);
            } catch(Exception e) {}
        }
        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i+3], 550+i*75, 75,
                    hotBar.hotBarSlotSprites[i+3].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i+3].getHeight()*SCALE_FACTOR);

            try {
                batch.draw(hotBar.hotBarItemSprites[i+3], 565+i*75, 90,
                        hotBar.hotBarItemSprites[i+3].getWidth()*SCALE_FACTOR,
                        hotBar.hotBarItemSprites[i+3].getHeight()*SCALE_FACTOR);
            } catch(Exception e) {}
        }
        for(int i = 0; i < 3; i++) {
            batch.draw(hotBar.hotBarSlotSprites[i+6], 550+i*75, 0,
                    hotBar.hotBarSlotSprites[i+6].getWidth()*SCALE_FACTOR,
                    hotBar.hotBarSlotSprites[i+6].getHeight()*SCALE_FACTOR);
            try {
                batch.draw(hotBar.hotBarItemSprites[i+6], 565+i*75, 15,
                        hotBar.hotBarItemSprites[i+6].getWidth()*SCALE_FACTOR,
                        hotBar.hotBarItemSprites[i+6].getHeight()*SCALE_FACTOR);
            } catch(Exception e) {}
        }
        displayItemName(batch);
    }
    @Override
    public void act(float deltaTime) {
        timeCount += deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            hotBar.setActiveHotBarSlot(0);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            hotBar.setActiveHotBarSlot(1);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            hotBar.setActiveHotBarSlot(2);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            hotBar.setActiveHotBarSlot(3);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
            hotBar.setActiveHotBarSlot(4);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
            hotBar.setActiveHotBarSlot(5);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
            hotBar.setActiveHotBarSlot(6);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
            hotBar.setActiveHotBarSlot(7);
            timeCount = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
            hotBar.setActiveHotBarSlot(8);
            timeCount = 0;
        }
        //TODO add ability to scroll through hotbar
    }
    private void displayItemName(Batch batch) {
        if(timeCount < 1) {
            fontDrawer.setColor(1.0f, 1.0f, 1.0f, 1-timeCount);
            fontDrawer.draw(batch, hotBar.activeHotBarSlot.getItemName().toUpperCase(), 635, 250);
        }//TODO make text center justified GlyphLayout
    }
}
