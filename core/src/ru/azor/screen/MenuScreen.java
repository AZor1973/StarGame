package ru.azor.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.BaseScreen;
import ru.azor.math.Rect;
import ru.azor.sprite.Background;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Background background;
    private static Vector2 v;
    private static Vector2 target;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        v = new Vector2();
        target = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }
}
