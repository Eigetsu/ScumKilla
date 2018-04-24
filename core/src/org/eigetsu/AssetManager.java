package org.eigetsu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

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

        Gdx.app.log("system","Textures Loaded");

    }

    public Texture getTextureByName(String name){
        switch (name){
            case "Player": return txtchar;
            case "Enemy":return txtenemy;
            case "DefBullet":return txtbullet;
            case "BloodSplat": return txtBloodSplat;
            case "BloodSplat2": return txtBloodSplat2;
            case "BloodSplat3": return txtBloodSplat3;
            case "Background": return background;
            case "MainMenuScreen": return mainscreen;
            case "GameOverScreen": return maketa;
            case "Point": return txtpoint;
            case "UIHealth": return uiHealth;
            case "UIHealthBar": return uiHealthbar;
            default:return txtchar;
        }

    }
    public TextureRegion getTextureRegionByName(String name){
        switch (name){
            case "Player": return  txtcharr;
            case "Enemy": return txtenemyr;
            case "DefBullet": return txtbulletr;
            case "Backgroundr": return backgroundr;
            default: return  txtbulletr;
        }
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
