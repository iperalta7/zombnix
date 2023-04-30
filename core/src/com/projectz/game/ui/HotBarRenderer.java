package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.player.Player;
import com.projectz.game.weapons.WeaponGun;

/**
 * This class is the renderer for the hot bar. It takes
 * the contents of the HotBar class and displays them
 * visually for the user to see. It extends the class
 * "Actor" which is a libGDX class which can be added to
 * the stage (screen).
 */
public class HotBarRenderer extends Actor {
    HotBar hotBar;
    private Player player;
    private OrthographicCamera camera;
    private final float SCALE_FACTOR = 1f;
    private float timeCount;
    private BitmapFont fontDrawer;

    /**
     * The constructor sets up the camera and fonts used for
     * displaying the hot bar.
     * @param hotBar the hot bar object
     * @param player the player object used by all other objects
     */
    public HotBarRenderer(HotBar hotBar, Player player) {
        this.hotBar = hotBar;
        this.player = player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        timeCount = 3;
        fontDrawer = new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"));
        fontDrawer.getData().setScale(0.5f);
    }

    /**
     * This function draws the hot bar slot sprites,
     * the hot bar item sprites, and the item name when
     * a hot bar key is pressed (1-9).
     * @param batch the batch used to draw to the screen
     * @param parentAlpha the parent alpha, to be multiplied
     *                    with this actor's alpha, allowing
     *                    the parent's alpha to affect all children
     */
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

    /**
     * This function is called periodically by libGDX. It
     * fills the hot bar's sprites, tells the player object
     * whether a gun is selected, and checks keyboard input
     * for key presses 1-9 to select hot bar slots.
     * @param deltaTime time in seconds since the last frame
     */
    @Override
    public void act(float deltaTime) {
        hotBar.fillSlotItemSprites();
        timeCount += deltaTime;
        if(!hotBar.activeHotBarSlot.isEmpty() && hotBar.activeHotBarSlot.getItemName().equals("Gun")) {
            player.holdingGun = true;
        }
        else {
            player.holdingGun = false;
        }

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

    /**
     * This method displays the name of the selected
     * item as soon as it is selected.
     * @param batch same batch as "act" method
     */
    private void displayItemName(Batch batch) {
        if(timeCount < 1) {
            fontDrawer.setColor(1.0f, 1.0f, 1.0f, 1-timeCount);
            fontDrawer.draw(batch, hotBar.activeHotBarSlot.getItemName().toUpperCase(), 635, 250);
        }//TODO make text center justified GlyphLayout
    }
}
