package com.projectz.game.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
public class GameScreen implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer; 
    private OrthographicCamera camera; 

 

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera); 
        renderer.render(); 

    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width; 
        camera.viewportHeight = height;
        camera.position.set(camera.viewportWidth / 3f, camera.viewportHeight / 3f, 0);
        camera.zoom = 1/ 4f;
        camera.update(); 
        
    }


    @Override
    public void show(){

        // TmxMapLoader loader = new TmxMapLoader();
        // map = loader.load("maps/basic_map.tmx");
        map = new TmxMapLoader().load("maps/basic_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera(); 
        camera.update();
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
