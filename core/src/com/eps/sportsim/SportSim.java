package com.eps.sportsim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SportSim extends Game {
	SpriteBatch batch;
	Texture img;

	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new ScoutScreen());

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.getScreen().dispose();
	}
	

}
