package com.my.game.roachconvolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.my.game.roachconvolution.managers.GameManager;
import com.my.game.roachconvolution.managers.InputManager;

/**
 * Created by dersu on 17.03.16.
 */
public class GameScreen implements Screen {
    private MainGame game;
    private SpriteBatch batch; //spritebatch for drawing
    private OrthographicCamera camera;

    public GameScreen (MainGame game){
        this.game = game;
        //get window dimensions and set our viewpoint dimensions
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        //set the camera viewpoint to window dimensions
        camera = new OrthographicCamera(width, height);
        //center the camera ar w/2,h/2
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        //initialize the game
        GameManager.initialize(width, height);

        Gdx.input.setInputProcessor(new InputManager(camera)); //enable InputManager to recieve input events
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        //render the game objects
        batch.begin();
        GameManager.renderGame(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        GameManager.dispose();
    }
}
