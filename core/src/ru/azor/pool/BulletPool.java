package ru.azor.pool;

import ru.azor.base.SpritesPool;
import ru.azor.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
