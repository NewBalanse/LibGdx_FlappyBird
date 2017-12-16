package com.mygdx.game.GameObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by NewBalanse on 12.12.2017.
 */

public class Floor {
    Texture floor;

    Vector2 position1;
    Vector2 position2;
    Vector2 position3;

    Vector3 velocity;

    float speed;


    public Texture getFloor() {
        return floor;
    }

    public Vector2 getPosition1() {
        return position1;
    }

    public Vector2 getPosition2() {
        return position2;
    }

    public Vector2 getPosition3() {
        return position3;
    }


    public Floor(float position1, float speed) {

        floor = new Texture("ground.png");
        this.position1 = new Vector2(position1, 0);
        position2 = new Vector2(position1 + floor.getWidth(), 0);
        position3 = new Vector2(position2.x + floor.getWidth(), 0);

        velocity = new Vector3(0, 0, 0);
        this.speed = speed;
    }

    public void update(float deltatime) {
        if (position1.x > 0 - floor.getWidth() * 2)
            velocity.add(speed, 0, 0);

        velocity.scl(deltatime);
        position1.add(velocity.x, 0);
        position2.add(velocity.x, 0);
        position3.add(velocity.x, 0);

        if (position1.x < 0 - floor.getWidth())
            position1.set(position3.x + floor.getWidth(), 0);
        if (position2.x < 0 - floor.getWidth())
            position2.set(position1.x + floor.getWidth(), 0);
        if (position3.x < 0 - floor.getWidth())
            position3.set(position2.x + floor.getWidth(), 0);
    }
     public void dispose(){
        floor.dispose();
     }
}
