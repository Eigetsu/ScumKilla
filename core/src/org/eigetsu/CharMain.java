package org.eigetsu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eigetsu on 2017/10/16.
 */

class CharMain {
    Vector2 pos;
    private float rotation;
    Rectangle rec;


    private Texture txtpoint;
    private TextureRegion txtcharr;

    private int health;

    private float PLAYER_WIDTH = 32;
    private float PLAYER_HEIGHT = 32;
//POSITION DEBUG FLAG
    private boolean debugRecPos = false;

    private TDS game;



    public CharMain(TDS game){
        this.game = game;
        pos = new Vector2(1280 / 2, 720 / 2);
        rec = new Rectangle(pos.x, pos.y, PLAYER_WIDTH, PLAYER_HEIGHT);
        rotation = 0f;

        health = 100;

        txtcharr = game.assets.getTextureRegionByName("Player");
        txtpoint = game.assets.getTextureByName("Point");


    }

    public void update(float dt, SpriteBatch batch){
        rec.setCenter(pos);
        rotation = ((float) Math.atan2(game.mousepos.y - pos.y,game.mousepos.x - pos.x) * 180.0f / (float) Math.PI);
        batch.draw(txtcharr, pos.x-PLAYER_WIDTH/2, pos.y-PLAYER_HEIGHT/2, PLAYER_WIDTH/2, PLAYER_HEIGHT/2, PLAYER_WIDTH,PLAYER_HEIGHT,1,1,rotation);

        if(debugRecPos) {
            batch.draw(txtpoint, rec.x, rec.y, 3, 3);
            batch.draw(txtpoint, rec.x + rec.width, rec.y, 3, 3);
            batch.draw(txtpoint, rec.x, rec.y+rec.height, 3, 3);
            batch.draw(txtpoint, rec.x + rec.width, rec.y + rec.height, 3, 3);
        }

    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
    public void setPos(float x, float y){
        this.pos.x = x;
        this.pos.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
