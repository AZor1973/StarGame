package ru.azor.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;

public class Ship extends Sprite {

    private static final float PADDING = 0.03f;
    private static final float SPEED = 0.003f;
    private static float speed;
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        // Следующий код при необходимости можно вынести из конструктора в поля класса
        TextureRegion region = atlas.findRegion("main_ship");
        TextureRegion[][] tiles = region.split(regions[frame].getRegionWidth() / 2, regions[frame].getRegionHeight());
        regions[frame] = tiles[0][1];
    }

    @Override
    public void update(float delta) {
        if (pos.x + speed > worldBounds.getLeft() + this.getWidth() / 2 && pos.x + speed < worldBounds.getRight() - this.getWidth() / 2) {
            pos.x += speed;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x >= pos.x) {
            speed = SPEED;
        } else {
            speed = -SPEED;
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        speed = 0;
        return false;
    }

    public boolean keyDown(int keycode) {
        if (keycode == 21){
            speed = -SPEED;
        }
        if (keycode == 22){
            speed = SPEED;
        }
            return false;
    }

    public boolean keyUp(int keycode) {
        if (keycode == 21){
            speed = 0;
        }
        if (keycode == 22){
            speed = 0;
        }
        return false;
    }
}
