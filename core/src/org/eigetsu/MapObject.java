package org.eigetsu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eigetsu on 2017/10/30.
 */

class MapObject {
    private Vector2 pos;
    private Rectangle rec;
    private TDS game;

    private Texture txtObj;


    public MapObject(Vector2 pos, TDS game){
        this.pos = new Vector2(pos);
        rec = new Rectangle(pos.x,pos.y,128,128);
        rec.setCenter(1280/2,720/2);

        txtObj = new Texture("badlogic.jpg");

        this.game = game;

    }
    public void update(float dt, SpriteBatch batch){
        batch.draw(txtObj,rec.x,rec.y,128,128);

    }
}
