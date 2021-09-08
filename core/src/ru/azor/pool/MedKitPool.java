package ru.azor.pool;

import ru.azor.base.SpritesPool;
import ru.azor.math.Rect;
import ru.azor.sprite.MedKit;

public class MedKitPool extends SpritesPool<MedKit> {

    private final Rect worldBounds;

    public MedKitPool(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    protected MedKit newSprite() {
        return new MedKit(worldBounds);
    }
}
