package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.AssetsXogo;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.modelo.Mundo;



/**
 * Created by Sam on 10/02/2016.
 */
public class PantallaPresentacion implements Screen, InputProcessor {


    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));

    private ShapeRenderer shaperender;

    private SpriteBatch spritebatch;

    private OrthographicCamera camara2d;


    private ShadowGame juego;

    private Rectangle play = new Rectangle(243,133,108,40);
    private Rectangle opciones = new Rectangle(251,53,108,40);
    /**
     * Metodo constructor de la pantalla pausa
     *
     * @param meuxogogame una instancia del juego
     */
    public PantallaPresentacion(ShadowGame meuxogogame) {

        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
        this.juego = meuxogogame;
    }

    @Override
    public void render(float delta) {
        spritebatch.begin();
        spritebatch.draw(AssetsXogo.menuprincipal, 0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
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

        Vector3 temporal = new Vector3(screenX,screenY,0);
        camara2d.unproject(temporal);
        Circle dedo = new Circle(new Vector2(temporal.x,temporal.y),10);
        //Play
        if(Intersector.overlaps(dedo,play)){
            juego.setScreen(new PantallaXogo(juego));
        }
        //Opciones
        if(Intersector.overlaps(dedo, opciones)){
            juego.setScreen(new PantallaOpciones(juego));

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
