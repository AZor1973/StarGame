package ru.azor.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;

public class ProgressBar extends Sprite {

    private static final float PADDING = 0.01f;
    private float partWidth;

    public ProgressBar(TextureAtlas atlas) {
        super(atlas.findRegion("progress_line"));
    }

    public void update(float delta, int hp) {
        super.update(delta);
        if (hp == 0){
            setWidth(0);
        }
        setWidth(partWidth*hp);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.01f);
        setTop(worldBounds.getTop() - PADDING);
        if (partWidth == 0)
        partWidth = getWidth()/100;
    }
}
