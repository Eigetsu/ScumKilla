package org.eigetsu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Eigetsu on 1/17/18.
 * TODO: make a main Menu
 */
public class GameOverMenu implements Screen, InputProcessor {

    OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;
    private float gameScreenX = 1280f;
    private float gameScreenY = 720f;
    TDSLauncher game;
    public AssetManager assets;
    Texture maketa;
    int wait=60;

    public GameOverMenu(TDSLauncher game){
        this.game = game;
        assets = game.getAssets();
        camera = new OrthographicCamera(gameScreenX,gameScreenY);
        camera.setToOrtho(false, gameScreenX, gameScreenY);
        batch = new SpriteBatch();
        font = new BitmapFont();

        maketa = assets.getTextureByName("GameOverScreen");

    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();


        batch.begin();
        batch.draw(assets.getTextureRegionByName("Backgroundr"),0, 0,gameScreenX,gameScreenY);
        batch.draw(maketa, 0f, 0f,gameScreenX,gameScreenY);
        batch.end();

        if (Gdx.input.isTouched(Input.Buttons.LEFT)) {
            wait++;
            if (wait > 60) {
                wait =0;
                game.assets.resetBackground();
                game.setScreen(new MainMenu(game));
            }
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
    public void hide() {

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
