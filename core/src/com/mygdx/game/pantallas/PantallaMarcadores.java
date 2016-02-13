package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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


    //Variables
    private SpriteBatch spritebatch;
    private OrthographicCamera camara2d;
    private ShadowGame juego;
    private FileHandle archivo;
    private Lisa lisa;
    private BitmapFont bmf;
    
    //Rectangulos de colision
    private Rectangle salir = new Rectangle(536,0,74,49);
    private Rectangle restart = new Rectangle(227,0,167,30);

    /**
     * Constructor de la pantalla
     * @param meuxogogame una instancia del juego
     * @param lisa el personaje para obtener el score
     */
    public PantallaMarcadores(ShadowGame meuxogogame, Lisa lisa) {

        PantallaXogo.musica.pause();
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        this.juego = meuxogogame;
        bmf = new BitmapFont(Gdx.files.internal("fuentes.fnt"), false);
        this.lisa = lisa;
        if (Gdx.files.isExternalStorageAvailable()) {
            archivo = Gdx.files.external("puntuaciones.txt");
            if (!archivo.exists()) {
                try {
                    Gdx.files.external("puntuaciones.txt").file().createNewFile();
                    archivo = Gdx.files.external("puntuaciones.txt");
                    archivo.writeString((int)lisa.getPuntuacion()+":", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                ponerMarcador((int) lisa.getPuntuacion());
            }
        } else {

            System.out.println("NO DISPONIBLE");
        }






    }

    /**
     * Metodo que nos pone en el archivo de texto nuestra puntuacion
     * @param score la puntuacion hecha esta partida
     */
    public void ponerMarcador(int score) {
        String actual = archivo.readString();
        String[] puntuaciones = actual.split(":");

        ArrayList<Integer> puntosActuales = new ArrayList<Integer>();
        for(int i = 0; i < puntuaciones.length; i++){
            if(!puntuaciones[i].equals(""))
                System.out.println(puntuaciones[i]);
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
        bmf.draw(spritebatch, (i+1) + "   -   " + puntuaciones[i], Mundo.TAMANO_MUNDO_ANCHO / 2 - 70, posy);
        posy -= 30;
        }
        bmf.draw(spritebatch,"Score esta partida: " + (int) lisa.getPuntuacion(), 0, Mundo.TAMANO_MUNDO_ALTO);
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
        Circle dedo = new Circle(temporal.x,temporal.y,10);
        if(Intersector.overlaps(dedo,salir)) {
            Gdx.app.exit();
        }
        if(Intersector.overlaps(dedo,restart)){
            juego.setScreen(new PantallaXogo(juego));
        }
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
