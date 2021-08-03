package ru.azor.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 target;
    private static final float SPEED = 8f;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        v = new Vector2();
        target = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (target.dst(pos) > SPEED) {
            pos.add(v);
        } else {
            pos.set(target);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        target.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(target.cpy().sub(pos)).setLength(SPEED);
        return false;
    }
}
