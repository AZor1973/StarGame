package ru.azor.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.azor.math.Rect;
import ru.azor.math.Rnd;
import ru.azor.pool.MedKitPool;
import ru.azor.sprite.MedKit;

public class MedKitEmitter {

    private static final float GENERATE_INTERVAL = 12f;
    private static final float MEDKIT_SMALL_HEIGHT = 0.05f;
    private static final int MEDKIT_SMALL_HP = 1;
    private static final float MEDKIT_MEDIUM_HEIGHT = 0.07f;
    private static final int MEDKIT_MEDIUM_HP = 5;
    private static final float MEDKIT_BIG_HEIGHT = 0.1f;
    private static final int MEDKIT_BIG_HP = 10;
    private final Rect worldBounds;
    private final MedKitPool medKitPool;
    private final TextureRegion[] medkitRegions;
    private final Vector2 medkitSmallV = new Vector2(0f, -0.1f);
    private final Vector2 medkitMediumV = new Vector2(0f, -0.2f);
    private final Vector2 medkitBigV = new Vector2(0f, -0.3f);
    private float generateTimer;
    private int level;

    public MedKitEmitter(Rect worldBounds, MedKitPool medKitPool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.medKitPool = medKitPool;
        medkitRegions = Regions.split(atlas.findRegion("first_aid_kit"), 1, 1, 1);
    }

    public void generate(float delta, int frags) {
        level = frags / 10 + 1;
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL+level) {
            generateTimer = 0f;
            MedKit medKit = medKitPool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                medKit.set(
                        medkitRegions,
                        medkitSmallV,
                        MEDKIT_SMALL_HEIGHT,
                        MEDKIT_SMALL_HP
                );
            } else if (type < 0.8f) {
                medKit.set(
                        medkitRegions,
                        medkitMediumV,
                        MEDKIT_MEDIUM_HEIGHT,
                        MEDKIT_MEDIUM_HP
                );
            } else {
                medKit.set(
                        medkitRegions,
                        medkitBigV,
                        MEDKIT_BIG_HEIGHT,
                        MEDKIT_BIG_HP
                );
            }
            float posX = Rnd.nextFloat(
                    worldBounds.getLeft() + medKit.getHalfWidth(),
                    worldBounds.getRight() - medKit.getHalfWidth()
            );
            medKit.setPos(posX, worldBounds.getTop());
        }
    }
}