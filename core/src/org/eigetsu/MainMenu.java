package org.eigetsu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Eigetsu on 1/17/18.
 * TODO: make a main Menu
 */
public class MainMenu extends Game implements InputProcessor {

    OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;
    private float gameScreenX = 1280f;
    private float gameScreenY = 720f;

    @Override
    public void create(){
        camera = new OrthographicCamera(gameScreenX,gameScreenY);
        camera.setToOrtho(false, gameScreenX, gameScreenY);
        batch = new SpriteBatch();
        font = new BitmapFont();
    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();


        batch.begin();
        font.draw(batch, "SCUMKILLA!!!", gameScreenX/2, gameScreenY/2);
        font.draw(batch, "Click anywhere to begin!", gameScreenX/2, gameScreenY/2-20);
        batch.end();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            System.out.println("click!");
            //this.setScreen(new TDS());
        }

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
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
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
    public boolean scrolled(int amount) {
        return false;
    }
}
