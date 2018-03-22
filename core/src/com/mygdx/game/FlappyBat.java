package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class FlappyBat extends ApplicationAdapter {


	//Creates the Game Window
	public static final int width = 420;
	public static final int hight = 800;

	//sets the title for the game
	public static final  String title = "Flappy Bat";
	private GameStateManager gsm;

	//sprite batch
	private SpriteBatch batch;
	/**
	 * creates spriteBatch
	 * Game State manager
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	/**
	 *
	 */
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	/**
	 *
	 */
	@Override
	public void dispose () {
		super.dispose();
	}
}
