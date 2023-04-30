package com.projectz.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This class is a container for the sprites for the
 * hot bar slots. It consists of "selected" and "unselected"
 * versions of the slots.
 */
public class HotBarSprites {
    static final Sprite SELECTED_SLOT_SPRITE = new Sprite(new Texture("HotBarImages/hotBarSelected.png"));
    static final Sprite UNSELECTED_SLOT_SPRITE = new Sprite(new Texture("HotBarImages/hotBarUnselected.png"));
}
