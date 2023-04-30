package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.projectz.game.player.Player;

/**
 * This class is the renderer for the status HUD. It takes
 * the contents of the StatusHUD class and displays them
 * visually for the user to see. It extends the class
 * "Actor" which is a libGDX class which can be added to
 * the stage (screen).
 */
public class StatusHUDRenderer extends Actor {
    private StatusHUD statusHUD;
    private Player player;
    private OrthographicCamera camera;
    private BitmapFont fontDrawer;
    private final float SCALE_FACTOR = 1f;
    private final float GUN_SCALE_FACTOR = 0.25f;
    private float timeCount;
    private int expAddNumber;

    /**
     * The constructor sets up the camera and fonts used for
     * displaying the status HUD.
     * @param statusHUD the status HUD object
     * @param player the player object used by all other objects
     */
    public StatusHUDRenderer(StatusHUD statusHUD, Player player) {
        this.statusHUD = statusHUD;
        this.player = player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fontDrawer = new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"));
        fontDrawer.getData().setScale(0.5f);
        timeCount = 3;
        expAddNumber = 0;
    }

    /**
     * This function calls the status HUD update method,
     * draws the health and experience bars, and prints the
     * health and experience values next to the bars.
     * @param batch the batch used to draw to the screen
     * @param parentAlpha the parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *           children
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setProjectionMatrix(camera.combined);
        //Update HUD values
        statusHUD.update();
        //Draw Textures for HUD
        Sprite healthSprite = statusHUD.healthSprite;
        Sprite expSprite = statusHUD.expSprite;
        Sprite pistolSprite = statusHUD.pistolSprite;
        batch.draw(healthSprite, 0, 0, healthSprite.getWidth()*SCALE_FACTOR, healthSprite.getHeight()*SCALE_FACTOR);
        batch.draw(expSprite, 0, 40, expSprite.getWidth()*SCALE_FACTOR, expSprite.getHeight()*SCALE_FACTOR);
        batch.draw(pistolSprite, 420, 10, pistolSprite.getWidth()*GUN_SCALE_FACTOR, pistolSprite.getHeight()*GUN_SCALE_FACTOR);
        //Draw Health and Level values
        fontDrawer.setColor(0f, 0f, 0f, 1.0f);
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getHealth()), 330, 25);
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getExpLevel()), 330, 65);
        fontDrawer.draw(batch, Integer.toString(statusHUD.numBullets), 450, 25);
        //Draw "+x" exp when new exp is collected
        if (timeCount < 1) {
            fontDrawer.setColor(1.0f, 1.0f, 1.0f, 1-timeCount);
            fontDrawer.draw(batch, "+"+Integer.toString(expAddNumber), 400, 65);
        }
    }

    /**
     * This function is called periodically by libGDX. It
     * updates the time count with the delta time.
     * @param deltaTime time in seconds since the last frame
     */
    @Override
    public void act(float deltaTime) {
        //Update time
        timeCount += deltaTime;
        //Test code
        /*if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            setExpAddNumber(5);
            player.setExpValue(player.getExpValue()+5);
        }*/
    }
    public void setExpAddNumber(int number) {
        //Set new exp number to be displayed reset time to 0
        expAddNumber = number;
        timeCount = 0;
    }

}

