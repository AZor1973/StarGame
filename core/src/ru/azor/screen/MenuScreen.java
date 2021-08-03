package ru.azor.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.BaseScreen;
import ru.azor.math.Rect;
import ru.azor.sprite.Background;
import ru.azor.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Background background;
    private Logo logo;
    private static Vector2 v;
    private static Vector2 target;
    private static final float SPEED = 0.008f;

    public static Vector2 getV() {
        return v;
    }

    public static Vector2 getTarget() {
        return target;
    }

    public static float getSPEED() {
        return SPEED;
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        logo = new Logo(img);
        v = new Vector2();
        target = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        target = touch;
        v.set(touch.cpy().sub(logo.pos)).setLength(SPEED);
        return false;
    }
}
