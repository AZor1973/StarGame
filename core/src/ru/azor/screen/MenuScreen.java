package ru.azor.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.azor.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 pos;//позиция объекта
    private Vector2 v;//скорость
    private float speed;//скалярная величина скорости
    private int x;//координаты цели
    private int y;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        v = new Vector2();
        speed = 1f;//Задаём величину скорости
        x = 0;
        y = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        pos.add(v);
        //Увеличиваем цель чтобы не промахнутся
        if (Math.abs(pos.x - x) < 2 && Math.abs(pos.y - y) < 2) {
            v.set(0, 0);
        }
//        В этом случае часто пролетаем мимо
//        if ((int)pos.x == x && (int)pos.y == y){
//            v.set(0 ,0);
//        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //задаём направление и величину скорости;
        v.set(screenX, Gdx.graphics.getHeight() - screenY).sub(pos).nor().scl(speed);
        //Вытаскиваем координаты цели для остановки
        x = screenX;
        y = Gdx.graphics.getHeight() - screenY;
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
