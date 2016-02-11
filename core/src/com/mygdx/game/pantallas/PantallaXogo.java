package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.controlador.ControladorXogo;
import com.mygdx.game.modelo.Lisa;
import com.mygdx.game.modelo.Mundo;
import com.mygdx.game.renderer.RendererXogo;

import java.security.Key;


/**
 * Created by Sam on 24/01/2016.
 */
public class PantallaXogo implements Screen, InputProcessor {


    private Mundo meuMundo;
    private ShadowGame meuxogogame;
    private RendererXogo rendererxogo;
    private ControladorXogo controladorXogo;
    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));
    private boolean pausa = false;
    static float volumen;

    /**
     * Metodo constructor de la pantalla del juego
     * @param meuxogogame una instancia del juego
     */
    public PantallaXogo(ShadowGame meuxogogame) {
        meuMundo = new Mundo();
        this.meuxogogame = meuxogogame;
        rendererxogo = new RendererXogo(meuMundo);
        controladorXogo = new ControladorXogo(meuMundo,meuxogogame);
        Preferences prefs = Gdx.app.getPreferences("preferencias");
        int sonido = prefs.getInteger("volumen",10);
        volumen = (float)sonido / 10f;
    }

    /**
     * Metodo que hace que suene el sonido de daño
     */
    public static void dmg() {

        Sound dmgSound = Gdx.audio.newSound(Gdx.files.internal("SONIDOS/dmg.mp3"));
        dmgSound.play(volumen);
    }

    /**
     * Metodo que hace que suene el sonido de coger una vida
     */
    public static void estrella(){
        Sound up = Gdx.audio.newSound(Gdx.files.internal("SONIDOS/dmg.mp3"));
        up.play(volumen);
    }

    /**
     * Metodo que nos permite decidir si el juego esta en pausa
     * @param nuevo true si esta falso si no
     */
    public void setPausa(boolean nuevo) {
        pausa = nuevo;
    }


    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub

        rendererxogo.render(delta);
        controladorXogo.update(delta);
        musica.setVolume(volumen);
        musica.play();

        if(pausa){
            meuxogogame.setScreen(new PantallaPausa(meuxogogame,this));
            musica.pause();
            return;
        }


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
        Gdx.input.setCatchBackKey(true);



        pausa = false;
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        Gdx.input.setInputProcessor(null);
        pausa = true;

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
        pausa = false;

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
            case Input.Keys.BACK:
                pausa = true;
                controladorXogo.pulsarTecla(ControladorXogo.Keys.PAUSA);
                break;
            case Input.Keys.ESCAPE:
                pausa = true;
                controladorXogo.pulsarTecla(ControladorXogo.Keys.PAUSA);

        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                controladorXogo.liberarTecla(ControladorXogo.Keys.ARRIBA);
                break;
            case Input.Keys.ESCAPE | Input.Keys.BACK:
                controladorXogo.liberarTecla(ControladorXogo.Keys.PAUSA);
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
