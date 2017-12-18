package com.mygdx.game.Scale;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameSetting.GameSetting;

import java.util.Stack;

/**
 * Created by NewBalanse on 04.12.2017.
 */

/**
 * this class have a stack gameState
 * and public methods pop and push for a class GameState
 */
public class GameManager {
    private Stack<GameState> gameStates;

    /**
     * constructor Stack<GameState>();
     */
    public GameManager() {
        gameStates = new Stack<GameState>();
    }

    public void push(GameState gameState) {
        gameStates.push(gameState);
    }

    public void pop() {
        gameStates.pop().dispose();
    }

    public void set(GameState gameState) {
        gameStates.pop();
        gameStates.push(gameState);
    }

    public void update(float deltaTime) {
        gameStates.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch) {
        gameStates.peek().render(spriteBatch);
    }
}
