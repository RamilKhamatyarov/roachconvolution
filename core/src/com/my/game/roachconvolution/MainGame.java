package com.my.game.roachconvolution;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Guideline "LibGDX Cross-Platform Development Blueprints".


public class MainGame extends Game {
	private SpriteBatch batch;
	private Texture img;

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}
}
