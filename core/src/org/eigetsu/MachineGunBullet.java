package org.eigetsu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eigetsu on 2017/10/30.
 */

public class MachineGunBullet extends Bullet {
    private static Texture txtbullet = new Texture("point.png");

    MachineGunBullet(TDS game, Vector2 playerPos) {
        super(game, playerPos);
        damage = 15;
        size = 3;
    }

    @Override
    public void update(float dt, SpriteBatch batch) {
        super.update(dt, batch);

    }
}
