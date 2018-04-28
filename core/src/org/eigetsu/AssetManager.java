package org.eigetsu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;

/**
 * Created by Eigetsu on 2017/11/13.
 * TODO: Texture Atlas
 * TODO: Pooling
 */

public class AssetManager implements Disposable{
    private Texture txtBloodSplat;
    private Texture txtBloodSplat2;
    private Texture txtBloodSplat3;
    private Texture txtchar;
    private Texture txtenemy;
    private TextureRegion txtcharr;
    private TextureRegion txtenemyr;
    private Texture txtbullet;
    private TextureRegion txtbulletr;

    private Texture txtpoint;

    private Texture background;
    private Texture backgroundtmp;
    private Pixmap backgroundPixMap;
    private TextureRegion backgroundr;
    private Texture maketa;
    private Texture mainscreen;
    private Texture uiHealth;
    private Texture uiHealthbar;
    
    private HashMap<String, Texture> textureHashMap;
    private HashMap<String, TextureRegion> textureRegionHashMap;

    public AssetManager(){
        Gdx.app.log("system","Textures Loading");
        txtpoint = new Texture("point.png");

        txtchar = new Texture("char.png");
        txtenemy = new Texture("enemy.png");
        txtcharr = new TextureRegion(txtchar);
        txtenemyr = new TextureRegion(txtenemy);

        txtBloodSplat = new Texture("blood_splat.png");
        txtBloodSplat2 = new Texture("blood_splat2.png");
        txtBloodSplat3 = new Texture("blood_splat3.png");

        txtbullet = new Texture("bullet.png");
        txtbulletr = new TextureRegion(txtbullet);

        background = new Texture("bckg2.jpg");
        backgroundr = new TextureRegion(background);

        maketa = new Texture("youlost.png");
        mainscreen = new Texture("startscreen.jpg");

        uiHealth = new Texture("UItxt_health.png");
        uiHealthbar = new Texture("healthbar.png");

        textureHashMap = new HashMap<String, Texture>();
        textureHashMap.put("Player",txtchar);
        textureHashMap.put("Enemy",txtenemy);
        textureHashMap.put("DefBullet",txtbullet);
        textureHashMap.put("BloodSplat",txtBloodSplat);
        textureHashMap.put("BloodSplat2",txtBloodSplat2);
        textureHashMap.put("BloodSplat3",txtBloodSplat3);
        textureHashMap.put("Background",background);
        textureHashMap.put("MainMenuScreen",mainscreen);
        textureHashMap.put("GameOverScreen",maketa);
        textureHashMap.put("Point",txtpoint);
        textureHashMap.put("UIHealth",uiHealth);
        textureHashMap.put("UIHealthBar",uiHealthbar);

        textureRegionHashMap = new HashMap<String, TextureRegion>();
        textureRegionHashMap.put("Player", txtcharr);
        textureRegionHashMap.put("Enemy",txtenemyr);
        textureRegionHashMap.put("DefBullet",txtbulletr);
        textureRegionHashMap.put("Backgroundr",backgroundr);
        

        Gdx.app.log("system","Textures Loaded");

    }

    public Texture getTextureByName(String name){
        return textureHashMap.get(name);
    }
    public TextureRegion getTextureRegionByName(String name){
        return textureRegionHashMap.get(name);
    }

    public void updateBackground(){

        backgroundr = ScreenUtils.getFrameBufferTexture();

        Gdx.app.log("debug","Updating Background");

    }

    public void resetBackground(){
        backgroundr = new TextureRegion(background);
    }


    @Override
    public void dispose() {
        txtBloodSplat.dispose();
        txtchar.dispose();
        txtbullet.dispose();
        txtpoint.dispose();
        background.dispose();
        maketa.dispose();


    }
}
