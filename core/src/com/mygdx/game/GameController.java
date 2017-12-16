package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scale.GameManager;
import com.mygdx.game.Scale.SceneMenu;

public class GameController extends ApplicationAdapter {
	SpriteBatch batch;
	private GameManager gameManager;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameManager = new GameManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gameManager.push(new SceneMenu(gameManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameManager.update(Gdx.graphics.getDeltaTime());
		gameManager.render(batch);
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose () {
		batch.dispose();
		gameManager = null;
	}
}
