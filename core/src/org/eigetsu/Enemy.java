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

class Enemy {
    Vector2 pos;
    private float rotation;
    Rectangle rec;

    private TextureRegion txtcharr;
    private Texture txtpoint = new Texture("point.png");

    private float ENEMY_WIDTH = 32;
    private float ENEMY_HEIGHT = 32;

    private int health;

    boolean isDead = false;
    private boolean debugRecPos = false;

    private TDS game;

    BitmapFont mark = new BitmapFont();


    public Enemy(TDS game, Vector2 pos){
        this.pos = new Vector2(pos.cpy());
        rec = new Rectangle(pos.x, pos.y, ENEMY_WIDTH /2, ENEMY_HEIGHT /2);
        rotation = 0f;
        this.game = game;
        txtcharr = game.assets.getTextureRegionByName("Enemy");
        health = 100;

    }

    public Enemy(TDS game, float x, float y){
        this.pos = new Vector2(x,y);
        rec = new Rectangle(pos.x, pos.y, ENEMY_WIDTH /2, ENEMY_HEIGHT /2);
        rotation = 0f;
        this.game = game;
        txtcharr = game.assets.getTextureRegionByName("Enemy");
        health = 100;

    }

    public void update(float dt, SpriteBatch batch){
        if (health <= 0) {
            isDead = true;
            game.enemyDeadEvent();
        }
        if (isDead) return;
        rec.setCenter(pos.x, pos.y);
        rotation = ((float) Math.atan2(game.player1.getPos().y - pos.y, game.player1.getPos().x - pos.x) * 180.0f / (float) Math.PI);

        batch.draw(txtcharr, pos.x- ENEMY_WIDTH /2, pos.y- ENEMY_HEIGHT /2, ENEMY_WIDTH /2, ENEMY_HEIGHT /2, ENEMY_WIDTH, ENEMY_HEIGHT,1,1,rotation);
        pos.add((float) Math.cos(Math.toRadians(rotation))*20f*dt*(1f+game.level*0.05f),(float) Math.sin(Math.toRadians(rotation))*20f*dt*(1f+game.level*0.05f));



        if (debugRecPos) {
            batch.draw(txtpoint, rec.x, rec.y, 3, 3);
            batch.draw(txtpoint, rec.x + rec.width, rec.y, 3, 3);
            batch.draw(txtpoint, rec.x, rec.y+rec.height, 3, 3);
            batch.draw(txtpoint, rec.x + rec.width, rec.y + rec.height, 3, 3);
        }
        /*mark.draw(batch, ".",rec.x,rec.y);
        mark.draw(batch, ".",rec.x+ENEMY_WIDTH/2,rec.y);
        mark.draw(batch, ".",rec.x,rec.y+ENEMY_HEIGHT/2);
        mark.draw(batch, ".",rec.x+ENEMY_WIDTH/2,rec.y+ENEMY_HEIGHT/2);*/
    }
    public void recieveDamage(int damage){
        health -= damage;
        if (health <=0) isDead = true;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
