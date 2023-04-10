package com.projectz.game.enemies;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.projectz.game.items.Item;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.projectz.game.player.Player;
import com.projectz.game.Map.GameScreen;

public class EnemyGeneric extends Enemy {
    private Texture enemyTexture;
    private Vector2 zombPos;
    Player player = new Player(); 
    
    // Vector2 zombiePos = new Vector2(zombie.getPosition(new Vector2()));
    Vector2 playerPos = new Vector2(player.getPosition());
    Vector2 direction = new Vector2();
    // direction.nor();
    // zombie.x += direction.x * 5;
    // zombie.y += direction.y * 5;

    //default constructor
    //this is where we give the player a texture/skin
    // speed is defaulted ( smaller equals slower...vice versa)

    public EnemyGeneric(Item[] dropItems, int health, Player player) {
        super(dropItems, health, player);
        //TODO Auto-generated constructor stub
        enemyTexture = new Texture("Zombie/zombie_man.png");
    }

    @Override
    public void act(float deltaTime) {

        // TODO Auto-generated method stub
        // position.set(10, 10); 
        direction.x = (playerPos.x + 40) - (position.x + 40);
        direction.y = (playerPos.y + 40) - (position.y + 40);
        direction.nor();
        position.x += direction.x * 5;
        position.y += direction.y * 5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        batch.draw(enemyTexture, position.x, position.y, 20, 20);
    }
    
}
