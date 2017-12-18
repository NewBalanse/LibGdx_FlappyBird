package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by NewBalanse on 04.12.2017.
 */

public class Trubu {
    final int POSITION_SPEED = -200;
    final int START_CORDx = Gdx.graphics.getDisplayMode().width - 550;
    final int MINIMALS_GAP = 200;

    Rectangle rectangleTop, rectangleBottom;

    Texture Truba_top, Truba_bottom;

    Vector2 top, bottom;
    Vector3 velocity;

    Random rn;

    public Texture getTruba_top() {
        return Truba_top;
    }

    public Texture getTruba_bottom() {
        return Truba_bottom;
    }

    public Vector2 getTop() {
        return top;
    }

    public Vector2 getBottom() {
        return bottom;
    }

    public Trubu(float x) {
        try {
            Truba_top = new Texture("toptube.png");
            Truba_bottom = new Texture("bottomtube.png");

            rn = new Random();

            velocity = new Vector3(0, 0, 0);
            top = new Vector2(x, GetRandomInteger());
            bottom = new Vector2(x, top.y - (Truba_bottom.getHeight() * 2) - MINIMALS_GAP);

            rectangleTop = new Rectangle(top.x, top.y, Truba_top.getWidth() * 2, Truba_top.getHeight() * 2);
            rectangleBottom = new Rectangle(bottom.x, bottom.y, Truba_bottom.getWidth() * 2, Truba_bottom.getHeight() * 2);
        } catch (NullPointerException e) {
            top = new Vector2(x, GetRandomInteger());
        }

    }

    private void RandomPosition() {
        try {
            top.set(START_CORDx, GetRandomInteger());
            bottom.set(START_CORDx, top.y - (Truba_bottom.getHeight() * 2) - MINIMALS_GAP);
        } catch (NullPointerException e) {
            top.set(START_CORDx, GetRandomInteger());
            bottom.set(START_CORDx, top.y - (Truba_bottom.getHeight() * 2) - MINIMALS_GAP);
        }
    }

    public void update(float deltatime,float positionBird) {
        if (top.x > (-1) * (Truba_top.getWidth() * 2))
            velocity.add(POSITION_SPEED, 0, 0);
        velocity.scl(deltatime);
        top.add(velocity.x, 0);
        bottom.add(velocity.x, 0);
        if (top.x < (-1) * (Truba_top.getWidth() * 2)) {
            RandomPosition();
        }

        rectangleBottom.setPosition(bottom.x, bottom.y);
        rectangleTop.setPosition(top.x, top.y);
    }

    private int GetRandomInteger() throws NullPointerException {
        int min = Gdx.graphics.getWidth() / 2;
        int max = min + Truba_top.getHeight();

        return rn.nextInt(max - min) + min;
    }

    public boolean collidersTube(Rectangle player) {
        return player.overlaps(rectangleTop) || player.overlaps(rectangleBottom);
    }

    public void dispose() {
        Truba_bottom.dispose();
        Truba_top.dispose();
        rn = null;
    }
}