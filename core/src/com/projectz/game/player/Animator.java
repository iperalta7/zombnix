package com.projectz.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animator {
    private float stateTimer;
    private boolean facingRight;
    private String currentState;
    private String previousState;
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerStand;
    private Texture spriteSheet;
    public Animator(){
        facingRight = true;
        stateTimer = 0;
        spriteSheet = new Texture("PlayerWalk/player_sheet.png");
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 8; i++){
            frames.add(new TextureRegion(spriteSheet,i*20,0,20,20));
        }
        playerRun = new Animation((1.0f/8.0f),frames);
        frames.clear();
        for (int i = 8; i < 10; i++){
            frames.add(new TextureRegion(spriteSheet,i*20,0,20,20));
        }
        playerStand = new Animation(0.5f,frames);
        frames.clear();

        currentState = "STANDING";
        previousState = "STANDING";
    }
    public void setStateTimer(float _dt){
        stateTimer += _dt; //currentState == previousState ? ((stateTimer + _dt)) : 0;
        if(stateTimer >= 1) stateTimer  = 0;
    }

    public TextureRegion setAnimation(int _state){
        switch(_state){

        }
        return null;
    }
    private Animation<TextureRegion> getTexture(String _state){
        switch(_state){
            case "RUNNING":
                return playerRun;
            case "STANDING":
                return playerStand;
            default:
                return playerStand;
        }
    }
    public void setState(String _state){
        previousState = currentState;
        currentState = _state;
    }
    public void setFacingRight(boolean _right){
        facingRight = _right;
    }
    public TextureRegion getFrame(float _dt){
        setStateTimer(_dt);
        //System.out.printf("%f",stateTimer);
        TextureRegion region = getTexture(currentState).getKeyFrame(stateTimer);
        if((facingRight && region.isFlipX() || (!facingRight && !region.isFlipX())))
            region.flip(true,false);
        return region;
    }
}
