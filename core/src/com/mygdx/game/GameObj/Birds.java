package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by NewBalanse on 04.12.2017.
 */

public class Birds {
    final int GRAVITY = -25;

    Vector3 postitionGameObject;
    Vector3 velocity;

    Rectangle rectangleBird;
    float floorStop;
    boolean isFly;

    Texture imageBird;

    public float getFloorStop() {
        return floorStop;
    }

    public void setFloorStop(float floorStop) {
        this.floorStop = floorStop;
    }

    public void setFly(boolean fly) {
        isFly = fly;
    }

    public Birds(float positionX, float positionY) {
        postitionGameObject = new Vector3(positionX, positionY, 0);
        velocity = new Vector3(0, 0, 0);
        imageBird = new Texture("bird.png");
        rectangleBird = new Rectangle(positionX, positionY, imageBird.getWidth() * 2, imageBird.getHeight() * 2);
    }

    public Vector3 getPostitionGameObject() {
        return postitionGameObject;
    }

    public Texture getImageBird() {
        return imageBird;
    }

    public void update(float deltatime) {

        velocity.add(0, GRAVITY, 0);
        velocity.scl(deltatime);
        if (!isFly)
            postitionGameObject.add(0, velocity.y, 0);
        if (isFly)
            postitionGameObject.set((float) (Gdx.graphics.getWidth() / 4.6), Gdx.graphics.getHeight() / 3, 0);
        if (postitionGameObject.y < floorStop)
            postitionGameObject.y = floorStop;

        velocity.scl(1 / deltatime);
        rectangleBird.setPosition(postitionGameObject.x, postitionGameObject.y);
    }

    public void jump() {
        velocity.y = (float) (((-1) * GRAVITY) * 14.8);
    }

    public Rectangle getRectangleBird() {
        return rectangleBird;
    }

    public void dispose() {
        imageBird.dispose();
    }

}
