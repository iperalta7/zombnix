package com.projectz.game.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.projectz.game.player.Player;

public class MyInputProcessor implements InputProcessor {


    @Override
    public boolean keyDown(int keycode) {


        System.out.println(keycode);

        switch (keycode)
        {
            case Input.Keys.W:
                System.out.println("W");
                return true;
            case Input.Keys.A:
                System.out.println("A");
                return true;
            case Input.Keys.S:
                System.out.println("S");
                return true;
            case Input.Keys.D:
                System.out.println("D");
                return true;
            case Input.Keys.NUM_3:
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
