package com.projectz.game.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.projectz.game.player.Player;
import com.projectz.game.enemies.EnemyBoss;

public class GameScreen implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer; 
    private OrthographicCamera camera; 

    Player player;

    EnemyBoss enemy;

    Stage stage;

    Batch batch;


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // update the camera position to follow the player
        camera.position.x = player.getPosition().x + player.getWidth()/2;
        camera.position.y = player.getPosition().y + player.getHeight()/2;
        camera.update();

        renderer.setView(camera);
        renderer.render();


        //default call to create stage (from documentation page)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width; 
        camera.viewportHeight = height;
        camera.position.set(camera.viewportWidth / 3f, camera.viewportHeight / 3f, 0);
        camera.update(); 

    }


    @Override
    public void show(){

        // TmxMapLoader loader = new TmxMapLoader();
        // map = loader.load("maps/basic_map.tmx");
        map = new TmxMapLoader().load("maps/basic_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 3f);

        player = new Player();
        player.setPlayerPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        enemy = new EnemyBoss(player, 200, 200);


        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );

        stage = new Stage();
        stage.addActor(player);
        stage.addActor(enemy);
    }


    @Override
    public void hide(){
        dispose();
    }


    @Override
    public void pause(){
        
    }

    @Override
    public void resume(){
        
    }

    @Override
    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
}
