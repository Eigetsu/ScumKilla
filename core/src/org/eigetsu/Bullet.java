package org.eigetsu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eigetsu on 2017/10/16.
 */

class Bullet {
    Vector2 pos;
    private float rotation;
    private float directionX;
    private float directionY;
    boolean isexploding = false;
    private float elapsed_time = 0f;
    int size = 16;
    int damage = 25;

    private float CANNON_SPEED = 400f;
    private float timeOfLife = 10f;

    private TextureRegion txtbulletr;


    private TDS game;

    Bullet(TDS game, Vector2 playerPos){
        pos = new Vector2(playerPos.cpy());
        rotation = game.player1.getRotation();
        directionX = (float) Math.cos(Math.toRadians(rotation));
        directionY = (float) Math.sin(Math.toRadians(rotation));
        this.game = game;
        txtbulletr = game.assets.getTextureRegionByName("Bullet");
    }

    public void update(float dt, SpriteBatch batch){
        if (isexploding) return;

        pos.add(directionX*CANNON_SPEED*dt,directionY*CANNON_SPEED*dt);
        batch.draw(txtbulletr, pos.x-size/2, pos.y-size/2, size/2, size/2, size,size,1,1,rotation-90f);

        elapsed_time += dt;
        if (elapsed_time > timeOfLife) isexploding = true;

    }
    public int getDamage(){
        return damage;
    }


}
