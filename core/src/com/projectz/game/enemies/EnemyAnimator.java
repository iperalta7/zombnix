package com.projectz.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class EnemyAnimator {
    private float stateTimer;
    private boolean facingRight;
    private String currentState;
    private String previousState;
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerStand;
    private Texture spriteSheet;

    /**
     * The Animator object can be used to add animations to the player. Currently, is set up to only allow preset animatons,
     * but could be upgraded to a more dynamic state that can add animations to any entity.
     **/
    public EnemyAnimator() {
        facingRight = true;
        stateTimer = 0;
        spriteSheet = new Texture("PlayerWalk/zombie_sheet.png");
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(spriteSheet, i * 20, 0, 20, 20));
        }
        playerRun = new Animation((1.0f / 4.0f), frames);
        frames.clear();

        currentState = "RUNNING";
        previousState = "RUNNING";
    }

    /**
     * Returns the current state timer, which can be used to set an animation's frame"
     *
     * @param _dt A float Delta Time to be added to the stateTimer and calculate current frame
     **/
    private void setStateTimer(float _dt) {
        stateTimer += _dt; //currentState == previousState ? ((stateTimer + _dt)) : 0;
        if (stateTimer >= 1) stateTimer = 0;
    }

    /**
     * Returns the Animation of the current State, dictated by currentState
     *
     * @param _state the State you want to get the Animation of
     * @return an Animation
     **/
    private Animation<TextureRegion> getTexture(String _state) {
        switch (_state) {
            case "RUNNING":
                return playerRun;
            case "STANDING":
                return playerStand;
            default:
                return playerStand;
        }
    }

    /**
     * Sets the current state, which dictates which animation will be used.
     *
     * @param _state Can be "RUNNING", or "STANDING". May implement "SHOOTING"
     **/
    public void setState(String _state) {
        previousState = currentState;
        currentState = _state;
    }

    /**
     * Sets whether all sprites of this object should be facing right or left
     *
     * @param _right Boolean. If true, face right. If false, face left.
     **/
    public void setFacingRight(boolean _right) {
        facingRight = _right;
    }

    /**
     * Returns the current frame of the current animation as defined by "setState"
     *
     * @param _dt A float Delta Time to be added to the stateTimer and calculate current frame
     * @return A TextureRegion of the current frame of the animation.
     **/
    public TextureRegion getFrame(float _dt) {
        setStateTimer(_dt);
        //System.out.printf("%f",stateTimer);
        TextureRegion region = getTexture(currentState).getKeyFrame(stateTimer);
        if ((facingRight && region.isFlipX() || (!facingRight && !region.isFlipX())))
            region.flip(true, false);
        return region;
    }
}
/**
 static class AnimatorTest {
@Test
void getDifferentFrames(){
Animator myAnimator = new Animator();
assertNotEquals(myAnimator.getFrame(0.6f),myAnimator.getFrame(0.0f));
}
void setInvalidState(){
Animator myAnimator = new Animator();
assertFalse(myAnimator.setState(null));
}
void getSameFrame() {
Animator myAnimator = new Animator();
assertEquals(myAnimator.getFrame(0.0f), myAnimator.getFrame(0.0001f));
}
}
 **/