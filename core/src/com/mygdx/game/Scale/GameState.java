package com.mygdx.game.Scale;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by NewBalanse on 04.12.2017.
 */

/*
Абстрактный клас который имеет в себе функции рендера обновление по дельта времени и оброботчик входных даных
таких как нажатий и прочей шелупотни!
* */
public abstract class GameState {
    protected OrthographicCamera camera;
    protected GameManager gameManager;

    /*
    Бвзовый конструктор класа иницализирует
    камеру, вектор который типа являеться мышкой
    И сылка на клас GameManager()
    * */
    protected GameState(GameManager gameManager){
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
    }
    protected abstract void handleInput();
    protected abstract void update(float deltaTime);//update cadre in curent delta time
    protected abstract void render(SpriteBatch spriteBatch);
    protected abstract void dispose();

}
