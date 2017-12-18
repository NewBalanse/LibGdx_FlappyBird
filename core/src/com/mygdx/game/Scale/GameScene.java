package com.mygdx.game.Scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.GameObj.Birds;
import com.mygdx.game.GameObj.Floor;
import com.mygdx.game.GameObj.NumbersHelper;
import com.mygdx.game.GameObj.Trubu;
import com.mygdx.game.GameSetting.GameCount;
import com.mygdx.game.GameSetting.GameSetting;

import java.util.ArrayList;

/**
 * Created by NewBalanse on 04.12.2017.
 */

public class GameScene extends GameState {
    static final int TUBE_SPASING = 158;
    final int TUBE_COUNT = 2;

    int SCOPE = 0;

    private Texture imageBackground;
    private Texture imageGameOver;
    private Texture imageGetReady;
    private Texture TotalCount;
    private Texture num;


    private Birds birds;
    private Floor floor;
    private GameCount gameCount;
    private NumbersHelper number;
    private GameSetting setting;

    private boolean isGameOver;
    private boolean isGetReady;
    private boolean isSelectMedal;
    private float count;
    private int type_medal;

    ArrayList<Trubu> tube;
    ArrayList<Texture> Medals;


    protected GameScene(GameManager gameManager) {
        super(gameManager);
        count = 0;//from test 268
        type_medal = 0;
        isGetReady = false;

        imageBackground = new Texture("bg.png");
        imageGameOver = new Texture("gameover.png");
        imageGetReady = new Texture("GetReady.png");
        TotalCount = new Texture("TotalCount.png");
        num = new Texture("numbers/0.png");

        birds = new Birds(50, 700);
        floor = new Floor(0, -200);
        number = new NumbersHelper();
        setting = new GameSetting();

        tube = new ArrayList<Trubu>();
        Medals = new ArrayList<Texture>();


        gameCount = new GameCount();

        for (int i = 0; i < TUBE_COUNT; i++)
            tube.add(new Trubu((i + 1) * (TUBE_SPASING + 120)));


        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        birds.setFloorStop(floor.getFloor().getHeight());


        Medals.add(new Texture("GoldMedal.png"));
        Medals.add(new Texture("SilverMedal.png"));
        Medals.add(new Texture("BronzeMedal.png"));


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (!isGetReady) {
                isGetReady = true;
            } else if (isGameOver) {
                gameManager.set(new GameScene(gameManager));
                //gameCount.setTotalCount(count);
                dispose();
            } else
                birds.jump();

        }
    }

    @Override
    protected void update(float deltaTime) {
        handleInput();
        if (isGetReady) {
            floor.update(deltaTime);
            birds.update(deltaTime);
            for (Trubu item : tube) {
                item.update(deltaTime, birds.getPostitionGameObject().x);
                if (item.collidersTube(birds.getRectangleBird())) {
                    isGameOver = true;
                    birds.setFly(true);
                    if (!isSelectMedal)
                        type_medal = GetTypeMedals();
                    isSelectMedal = true;
                }
                CountBe();

            }
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

        if (isGameOver) {
            //draw texture 'game over'
            spriteBatch.draw(imageGameOver, (float) (Gdx.graphics.getWidth() / 2 - imageGameOver.getWidth() * 2.5), camera.position.y,
                    imageGameOver.getWidth() * 2, imageGameOver.getHeight() * 2);
            //draw Total count table
            spriteBatch.draw(TotalCount, Gdx.graphics.getWidth() / 2 - TotalCount.getWidth() * 4,
                    camera.position.y - imageGameOver.getWidth(),
                    TotalCount.getWidth() * 3, TotalCount.getHeight() * 3);
            //draw medal
            spriteBatch.draw(Medals.get(type_medal), (float) ((Gdx.graphics.getWidth() / 2 - TotalCount.getWidth() * 4)
                            + Medals.get(type_medal).getWidth() * 1.8)
                    , (float) ((camera.position.y - imageGameOver.getWidth()) +
                            Medals.get(type_medal).getHeight() * 1.8),
                    Medals.get(type_medal).getWidth() * 3, Medals.get(type_medal).getHeight() * 3);
            //draw end count
            if (SCOPE > setting.getTotalCount())
                setting.SaveCount(SCOPE);
            RenderScope(spriteBatch,
                    (float) (((camera.position.y - imageGameOver.getWidth() / 1.5) + number.update(0).getWidth() * 2)),
                    SCOPE);//Render current count
            RenderScope(spriteBatch,
                    (camera.position.y - imageGameOver.getWidth() + number.update(0).getWidth() * 2),
                    setting.getTotalCount());//render best count
        }

        //draw count
        if (!isGameOver) {
            float index = 0;
            if (count >= 10) {
                if (count % 10 > 0.5)
                    index = (float) ((count / 10) + 0.5);
                else
                    index = count / 10;
                index = index / 2;
            }
            String tmp = String.valueOf((int) index);
            if (index > 9) {
                int IndexParent = (int) index / 10;
                if (IndexParent > 0) {
                    RenderNumberForHelper(spriteBatch, (float) (Gdx.graphics.getWidth() / 2 - imageGetReady.getWidth() * 3.5),
                            camera.position.y + imageGetReady.getWidth() * 4,
                            num.getWidth() * 3, num.getHeight() * 3,
                            IndexParent);
                    index = index % 10;

                    tmp = String.valueOf(IndexParent) + String.valueOf((int) index);
                }
            }
            RenderNumberForHelper(spriteBatch,
                    (float) (Gdx.graphics.getWidth() / 2 - imageGetReady.getWidth() * 3),
                    camera.position.y + imageGetReady.getWidth() * 4,
                    num.getWidth() * 3, num.getHeight() * 3,
                    (int) index);
            SCOPE = Integer.parseInt(tmp);
        }

        if (!isGetReady)
            spriteBatch.draw(imageGetReady, (float) (Gdx.graphics.getWidth() / 2 - imageGetReady.getWidth() * 4.5),
                    camera.position.y + imageGetReady.getHeight() * 2,
                    imageGetReady.getWidth() * 3, imageGetReady.getHeight() * 3);


        spriteBatch.end();

    }

    private void RenderNumberForHelper(SpriteBatch spriteBatch, float posX, float posY, float width, float height, int index) {
        spriteBatch.draw(number.update(index), posX, posY, width, height);
    }

    private void RenderScope(SpriteBatch spriteBatch, float posY, int TotalValue) {
        float tmpIndex = TotalValue;
        if (tmpIndex > 9) {
            int tmpParentIndex = (int) tmpIndex / 10;
            if (tmpParentIndex > 0) {
                RenderNumberForHelper(spriteBatch, (float) (Gdx.graphics.getWidth() / 2 - imageGetReady.getWidth() * 2.3),
                        posY,
                        num.getWidth() * 2, num.getHeight() * 2, tmpParentIndex);
                tmpIndex = tmpIndex % 10;
            }
        }
        RenderNumberForHelper(spriteBatch, (float) (Gdx.graphics.getWidth() / 2 - imageGetReady.getWidth() * 2),
                posY,
                num.getWidth() * 2, num.getHeight() * 2, (int) tmpIndex);
    }

    private int GetTypeMedals() {

        int type = 0;
        count = (count / 2);

        if (count % 10 > 0.5)
            count = (float) ((count / 10) + 0.5);
        else
            count = count / 10;

        if (count > 25)
            type = 0;
        else if (count > 10 && count < 25)
            type = 1;
        else
            type = 2;

        return type;
    }


    private void CountBe() {
        float posT1 = birds.getPostitionGameObject().x + birds.getImageBird().getWidth();
        float posT2 = birds.getPostitionGameObject().x + birds.getImageBird().getWidth();
        //
        //
        if (tube.get(0).getTop().x + (tube.get(0).getTruba_top().getWidth() * 2)
                > birds.getPostitionGameObject().x) {
            posT1 = tube.get(0).getTop().x + (tube.get(0).getTruba_top().getWidth() * 2);

        }
        if (tube.get(1).getTop().x + (tube.get(1).getTruba_top().getWidth() * 2)
                > birds.getPostitionGameObject().x)
            posT2 = tube.get(1).getTop().x + (tube.get(1).getTruba_top().getWidth() * 2);
        //
        //
        if (posT1 < (birds.getPostitionGameObject().x + birds.getImageBird().getWidth())) {
            count++;
        }
        if (posT2 < (birds.getPostitionGameObject().x + birds.getImageBird().getWidth())) {
            count++;
        }
    }

    @Override
    protected void dispose() {
        for (Trubu item : tube)
            item.dispose();
        birds.dispose();
        imageBackground.dispose();
        imageGameOver.dispose();
        floor.dispose();
        number.dispose();
    }

}
