package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	Background bg;
	Bird bird;
	Obstacles obstacles;
	boolean gameOver;
	Texture restart;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		bird = new Bird();
		obstacles = new Obstacles();
		gameOver = false;
		restart  = new Texture("RestartBtn.png");
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 1, 1, 0.5f);
		batch.begin();
		bg.render(batch);
		obstacles.render(batch);
		if(!gameOver) bird.render(batch);
		else batch.draw(restart,200, 200);
		batch.end();
	}


	public void update() {
		bg.update();
		bird.update();
		obstacles.update();
		for(int i = 0; i < Obstacles.obs.length; i++) {
			if(bird.position.x >= Obstacles.obs[i].position.x && bird.position.x <= Obstacles.obs[i].position.x + 50) {
				if(!Obstacles.obs[i].emptySpace.contains(bird.position)) {
					gameOver = true;
				}
			}
		}
		if(bird.position.y < 0 || bird.position.y > 600) {
			gameOver = true;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver) {
			bird.recreate();
			obstacles.recreate();
			gameOver = false;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
