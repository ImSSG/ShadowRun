package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AssetsXogo;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.controlador.ControladorXogo;
import com.mygdx.game.modelo.Mundo;
import com.mygdx.game.renderer.RendererXogo;

/**
 * Created by Sam on 09/02/2016.
 */
public class PantallaPausa implements InputProcessor, Screen {

    private Mundo meuMundo;
    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));

    private ShapeRenderer shaperender;

    private SpriteBatch spritebatch;

    private OrthographicCamera camara2d;

    public PantallaPausa(ShadowGame meuxogogame) {
        meuMundo = new Mundo();

        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        spritebatch.draw(AssetsXogo.texturePausa, 100,50, 200,150);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
