package com.mygdx.game.Scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * Created by NewBalanse on 04.12.2017.
 */
/*
Стартовая сцена содержыт в себе кнопку стар и кнопку рейтинга
* */
public class SceneMenu extends GameState {

    private Texture imageBackGround;
    private Texture imagePlayButton;
    float positionPlayX;
    float positionPlayY;

    /*
    Иницыализация заднего фона и кнопки старт
    вызываеться базовый конструктор класа
    * */
    public SceneMenu(GameManager gameManager) {
        super(gameManager);
        imageBackGround = new Texture("bg.png");
        imagePlayButton = new Texture("playbtn.png");
        positionPlayX = ((Gdx.graphics.getWidth() / 2) - (imagePlayButton.getWidth() / 2));
        positionPlayY = (Gdx.graphics.getHeight() / 2);
    }

    @Override
    protected void dispose() {
        imagePlayButton.dispose();
        imageBackGround.dispose();
    }

    /**
     * Оброботчик нажатий на кнопки
     */
    @Override
    protected void handleInput() {
        /*if (Gdx.input.getInputProcessor().touchDown()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            *//*if (playButton.contains(touch.x, touch.y)) {

            }*//*
            dispose();
        }*/
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (x > positionPlayX && x < positionPlayX + imagePlayButton.getWidth() && y < positionPlayY && y > positionPlayY - imagePlayButton.getHeight())
                gameManager.set(new GameScene(gameManager));
        }
    }

    @Override
    public void update(float deltaTime) {

        handleInput();
    }

    /**
     * Рисуем задний фон и кнопку старт
     * Кнопка старт рисуеься по оси х : Берем высоту екрана девайса делим на два получаем половину екрана
     * и отнимаем половину высоты самого изображения кнопки
     * таким образом централизируем ее
     * по оси у : просто берем половину шырины
     */
    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(imageBackGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(imagePlayButton, positionPlayX - (imagePlayButton.getWidth() / 2),
                positionPlayY, imagePlayButton.getWidth() * 2, imagePlayButton.getHeight() * 2);
        spriteBatch.end();
    }
}
