package ru.azor.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;
import ru.azor.screen.MenuScreen;

public class Logo extends Sprite {

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
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
        if (MenuScreen.getTarget().dst(pos) > MenuScreen.getSPEED()) {
            pos.add(MenuScreen.getV());
        } else {
            pos.set(MenuScreen.getTarget());
        }
    }
}
