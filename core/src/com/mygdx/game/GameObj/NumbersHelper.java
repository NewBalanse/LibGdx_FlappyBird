package com.mygdx.game.GameObj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import javax.xml.soap.Text;

/**
 * Created by NewBalanse on 18.12.2017.
 */

public class NumbersHelper {
    ArrayList<Texture> Numbers;
    int nameTextureNumbers;

    public NumbersHelper() {

        Numbers = new ArrayList<Texture>();

        for (int i = 0; i < 10; i++) {
            String tmp = String.valueOf(i);
            Numbers.add(new Texture("numbers/"+tmp +".png"));
        }
    }

    public Texture update(int count){
        nameTextureNumbers = count;
       return drow();
    }

    Texture drow(){
        return Numbers.get(nameTextureNumbers);
    }

    public void dispose(){
        for (Texture item : Numbers){
            item.dispose();
        }
    }
}
