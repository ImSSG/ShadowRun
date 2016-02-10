package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.pantallas.PantallaXogo;

public class ShadowGame extends Game {
	
	@Override
	public void create () {
		AssetsXogo.cargarTexturas();
		setScreen(new PantallaXogo(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetsXogo.liberarTexturas();
	}


}
