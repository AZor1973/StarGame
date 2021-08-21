package ru.azor.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;

public class MedKit extends Sprite {

    private final Rect worldBounds;
    private final Vector2 v0;
    private final Vector2 v;
    private int hp;


    public MedKit(Rect worldBounds) {
        super();
        v0 = new Vector2();
        v = new Vector2();
        this.worldBounds = worldBounds;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        setHeightProportion(height);
        this.hp = hp;
        v.set(0, -0.4f);
    }

    public void setPos(float x, float y) {
        pos.set(x, y);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}