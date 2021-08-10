package ru.azor.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import ru.azor.base.Sprite;
import ru.azor.math.Rect;
import ru.azor.pool.BulletPool;

public class MainShip extends Sprite {

    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private final Vector2 v0 = new Vector2(0.5f, 0);
    private final Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    private Rect worldBounds;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletPos;
    private Vector2 bulletV;
    private float bulletHeight;
    private int bulletDamage;

    private final Timer.Task task;
    private static final float RATE_OF_FIRE = 0.1f;
    private final Sound sound;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletPos = new Vector2();
        bulletV = new Vector2(0, 0.5f);
        bulletHeight = 0.01f;
        bulletDamage = 1;
        task = new Timer.Task() {
            @Override
            public void run() {
                shoot();
            }
        };
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
//        if (getRight() > worldBounds.getRight()) {
//            setRight(worldBounds.getRight());
//            stop();
//        }
//        if (getLeft() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getLeft());
//            stop();
//        }

        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.DOWN:
                Timer.schedule(task, 0, RATE_OF_FIRE);
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
            case Input.Keys.DOWN:
                task.cancel();
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotateDeg(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        sound.play(1.0f);
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(pos.x, pos.y + getHalfHeight());
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, bulletDamage);
    }

    public void dispose() {
        sound.dispose();
    }
}
