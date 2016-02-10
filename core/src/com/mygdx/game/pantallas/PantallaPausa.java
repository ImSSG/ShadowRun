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

    private PantallaXogo pantallaXogo;

    private ShadowGame juego;

    public PantallaPausa(ShadowGame meuxogogame, PantallaXogo pantalla) {
        meuMundo = new Mundo();

        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
        this.pantallaXogo = pantalla;
        this.juego = meuxogogame;
        pantalla.setPausa(false);
    }

    @Override
    public void render(float delta) {
        spritebatch.begin();
        spritebatch.draw(AssetsXogo.texturePausa, 0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

        camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2d.update();

        spritebatch.setProjectionMatrix(camara2d.combined);
        spritebatch.disableBlending();

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(null);

        spritebatch.dispose();


    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        juego.setScreen(pantallaXogo);
        pantallaXogo.setPausa(false);
        return false;

    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        juego.setScreen(pantallaXogo);
        pantallaXogo.setPausa(false);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }


}

