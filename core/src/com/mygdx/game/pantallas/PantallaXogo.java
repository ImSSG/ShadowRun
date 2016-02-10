package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.controlador.ControladorXogo;
import com.mygdx.game.modelo.Mundo;
import com.mygdx.game.renderer.RendererXogo;

/**
 * Created by Sam on 24/01/2016.
 */
public class PantallaXogo implements Screen, InputProcessor {


    private Mundo meuMundo;
    private ShadowGame meuxogogame;
    private RendererXogo rendererxogo;
    private ControladorXogo controladorXogo;
    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));


    public PantallaXogo(ShadowGame meuxogogame) {
        meuMundo = new Mundo();
        this.meuxogogame = meuxogogame;
        rendererxogo = new RendererXogo(meuMundo);
        controladorXogo = new ControladorXogo(meuMundo);
    }

    public static void dmg(){
        Sound dmgSound = Gdx.audio.newSound(Gdx.files.internal("SONIDOS/dmg.mp3"));
        dmgSound.play();

    }


    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub

        rendererxogo.render(delta);
        controladorXogo.update(delta);
        musica.play();


    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        rendererxogo.resize(width, height);
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
        rendererxogo.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        // Liberamos as teclas para que se arrastramos o dedo a outro control sen soltar o anterior non xunte o efecto


        switch (keycode) {
            case Input.Keys.UP:
                if (!meuMundo.getLisa().isSaltando())
                    controladorXogo.pulsarTecla(ControladorXogo.Keys.ARRIBA);

                break;

        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:

                    controladorXogo.liberarTecla(ControladorXogo.Keys.ARRIBA);
                break;

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (!meuMundo.getLisa().isSaltando())
            controladorXogo.pulsarTecla(ControladorXogo.Keys.ARRIBA);


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controladorXogo.liberarTecla(ControladorXogo.Keys.ARRIBA);
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


}
