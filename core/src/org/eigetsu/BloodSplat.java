package org.eigetsu;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eigetsu on 2017/10/30.
 */

class BloodSplat {
    Vector2 pos;
    private TDS game;
    Texture txtBloodSplat;
    private int txtrandom;
    private float size;



    public BloodSplat(Vector2 pos, TDS game){
        this.pos = pos;
        this.game = game;
        txtrandom = (int)(Math.random()*3);
        size = 50+ 5*txtrandom;
        //System.out.println(txtrandom);
        txtBloodSplat = game.assets.getTextureByName("BloodSplat");
        if (txtrandom == 1) txtBloodSplat = game.assets.getTextureByName("BloodSplat2");
        if (txtrandom == 2) txtBloodSplat = game.assets.getTextureByName("BloodSplat3");



    }

    public void update(float dt, SpriteBatch batch){
        batch.draw(txtBloodSplat, pos.x-16, pos.y-16,size,size);
        game.drawCountDebug++;
    }

    public Pixmap getPixMap(){
        return txtBloodSplat.getTextureData().consumePixmap();
    }
}
