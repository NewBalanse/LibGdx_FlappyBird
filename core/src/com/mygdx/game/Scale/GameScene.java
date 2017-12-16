package com.mygdx.game.Scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameObj.Birds;
import com.mygdx.game.GameObj.Floor;
import com.mygdx.game.GameObj.Trubu;
import com.mygdx.game.GameSetting.GameCount;

import java.util.ArrayList;

/**
 * Created by NewBalanse on 04.12.2017.
 */

public class GameScene extends GameState {
    static final int TUBE_SPASING = 158;
    final int TUBE_COUNT = 2;

    private Texture imageBackground;
    private Texture imageGameOver;

    private Birds birds;
    private Floor floor;
    private GameCount gameCount;

    private boolean isGameOver;
    private int count;

    ArrayList<Trubu> tube;

    protected GameScene(GameManager gameManager) {
        super(gameManager);
        count=0;

        imageBackground = new Texture("bg.png");
        imageGameOver = new Texture("gameover.png");

        birds = new Birds(50, 700);
        tube = new ArrayList<Trubu>();
        floor = new Floor(0, -200);

        gameCount = new GameCount();

        for (int i = 0; i < TUBE_COUNT; i++)
            tube.add(new Trubu((i + 1) * (TUBE_SPASING + 120)));
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        birds.setFloorStop(floor.getFloor().getHeight());
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (isGameOver) {
                gameManager.set(new GameScene(gameManager));
                gameCount.setTotalCount(count);
                dispose();
            } else
                birds.jump();

        }
    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
        floor.update(deltaTime);
        birds.update(deltaTime);
        for (Trubu item : tube) {
            item.update(deltaTime);
            if (item.collidersTube(birds.getRectangleBird())) {
                isGameOver = true;
                birds.setFly(true);
            }
            if(birds.getPostitionGameObject().x - birds.getImageBird().getWidth()/2 > item.getBottom().x + item.getTruba_bottom().getWidth())
                count++;

        }
    }

    @Override
    protected void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(imageBackground,
                camera.position.x - (camera.viewportWidth / 2), 0,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Trubu trubu : tube) {
            spriteBatch.draw(trubu.getTruba_top(), trubu.getTop().x, trubu.getTop().y,
                    trubu.getTruba_top().getWidth() * 2, trubu.getTruba_top().getHeight() * 2);

            spriteBatch.draw(trubu.getTruba_bottom(), trubu.getBottom().x, trubu.getBottom().y,
                    trubu.getTruba_bottom().getWidth() * 2, trubu.getTruba_bottom().getHeight() * 2);
        }
        spriteBatch.draw(floor.getFloor(), floor.getPosition1().x, floor.getPosition1().y);
        spriteBatch.draw(floor.getFloor(), floor.getPosition2().x, floor.getPosition2().y);
        spriteBatch.draw(floor.getFloor(), floor.getPosition3().x, floor.getPosition3().y);

        spriteBatch.draw(birds.getImageBird(), birds.getPostitionGameObject().x, birds.getPostitionGameObject().y,
                birds.getImageBird().getWidth() * 2, birds.getImageBird().getHeight() * 2);
        if (isGameOver)
            spriteBatch.draw(imageGameOver, (float) (Gdx.graphics.getWidth()/2 - imageGameOver.getWidth()*2.5), camera.position.y,
                    imageGameOver.getWidth() * 2, imageGameOver.getHeight() * 2);

        spriteBatch.end();

    }

    @Override
    protected void dispose() {
        for (Trubu item : tube)
            item.dispose();
        birds.dispose();
        imageBackground.dispose();
        imageGameOver.dispose();
        floor.dispose();
    }

}
