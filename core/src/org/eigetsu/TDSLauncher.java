package org.eigetsu;

import com.badlogic.gdx.Game;

/**
 * Created by Eigetsu on 2/1/18.
 */
public class TDSLauncher extends Game {
    public AssetManager assets;

    @Override
    public void create() {
        assets = new AssetManager();

        setScreen(new MainMenu(this));
    }

    public AssetManager getAssets() {
        return assets;
    }
}
