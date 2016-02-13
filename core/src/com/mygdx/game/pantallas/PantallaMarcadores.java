package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.AssetsXogo;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.modelo.Lisa;
import com.mygdx.game.modelo.Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.FileHandler;

/**
 * Created by Sam on 10/02/2016.
 */
public class PantallaMarcadores implements Screen, InputProcessor {


    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));
    private SpriteBatch spritebatch;
    private OrthographicCamera camara2d;
    private ShadowGame juego;
    private FileHandle archivo;
    private Lisa lisa;
    private BitmapFont bmf;


    /**
     * Metodo constructor de la pantalla pausa
     *
     * @param meuxogogame una instancia del juego
     */
    public PantallaMarcadores(ShadowGame meuxogogame, Lisa lisa) {


        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        this.juego = meuxogogame;
        bmf = new BitmapFont(Gdx.files.internal("fuentes.fnt"), false);

        if (Gdx.files.isExternalStorageAvailable()) {
            archivo = Gdx.files.external("puntuaciones.txt");
            if (!archivo.exists()) {
                try {
                    Gdx.files.external("puntuaciones.txt").file().createNewFile();
                    archivo = Gdx.files.external("puntuaciones.txt");
                    archivo.writeString(lisa.getPuntuacion()+":", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("NO DISPONIBLE");
        }


        this.lisa = lisa;
        ponerMarcador((int) lisa.getPuntuacion());


    }

    public void ponerMarcador(int score) {
        String actual = archivo.readString();
        String[] puntuaciones = actual.split(":");

        ArrayList<Integer> puntosActuales = new ArrayList<Integer>();
        for(int i = 0; i < puntuaciones.length; i++){
            if(!puntuaciones[i].equals(""))
            puntosActuales.add(Integer.valueOf(puntuaciones[i]));
        }
        puntosActuales.add(score);
        Collections.sort(puntosActuales);
        Collections.reverse(puntosActuales);
        archivo.writeString("",false);
        for(int i = 0; i < puntosActuales.size(); i++) {
            archivo.writeString(puntosActuales.get(i)+":", true);
        }
    }

    @Override
    public void render(float delta) {
        spritebatch.begin();
        String actual = archivo.readString();
        String[] puntuaciones = actual.split(":");
        int posy = 220;
        int tamano = puntuaciones.length;
        spritebatch.draw(AssetsXogo.marcadores, 0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        if(tamano>5){
            tamano = 5;
        }
        for(int i = 0; i < tamano; i ++) {
        bmf.draw(spritebatch, (i+1) + "   -   " + puntuaciones[i], Mundo.TAMANO_MUNDO_ANCHO / 2 - 35, posy);
        posy -= 30;
        }
        spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

        camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2d.update();

        spritebatch.setProjectionMatrix(camara2d.combined);


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
        Vector3 temporal = new Vector3(screenX, screenY, 0);
        camara2d.unproject(temporal);

        System.out.println("x: " + temporal.x + " y:" + temporal.y);
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
