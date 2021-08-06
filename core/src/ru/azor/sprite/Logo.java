package ru.azor.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;

public class Logo extends Sprite {
    private static Vector2 v;
    private static Vector2 target;
    private static final float SPEED = 0.008f;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        v = new Vector2();
        target = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame],
                pos.x, pos.y,
                halfWidth, halfHeight,
                0.3f, 0.3f,
                scale, scale,
                angle
        );
        if (target.dst(pos) > SPEED) {
            pos.add(v);
        } else {
            pos.set(target);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        target = touch;
        v.set(touch.cpy().sub(pos)).setLength(SPEED);
        return false;
    }
}
