package com.mygdx.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
public class PantallaOpciones implements Screen, InputProcessor {


    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("SONIDOS/main.mp3"));
    private ShapeRenderer shaperender;
    private SpriteBatch spritebatch;
    private OrthographicCamera camara2d;
    private BitmapFont bmf;
    private ShadowGame juego;

    private Rectangle masVolumen = new Rectangle(134,175,71,51);
    private Rectangle menosVolumen = new Rectangle(362,178,54,74);
    private Rectangle masVidas = new Rectangle(131,53,71,51);
    private Rectangle menosVidas = new Rectangle(369,50,54,74);
    private Rectangle atras = new Rectangle(0,0,63,40);
    private Rectangle tutorial = new Rectangle(209,0,160,34);
    private Rectangle salir = new Rectangle(521,0,80,50);


    public PantallaOpciones(ShadowGame juego) {
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();

        bmf = new BitmapFont(Gdx.files.internal("fuentes.fnt"), false);
        this.juego = juego;
    }

    @Override
    public void render(float delta) {
        spritebatch.begin();
        //bmf.draw(spritebatch, "Volumen", Mundo.TAMANO_MUNDO_ANCHO/2 - 40, 275);
        spritebatch.draw(AssetsXogo.pantallaOpciones, 0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        bmf.draw(spritebatch, "10", 275, 220);
        bmf.draw(spritebatch, "3", 280, 85);
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
        Circle dedo = new Circle(new Vector2(temporal.x,temporal.y),10);

        if(Intersector.overlaps(dedo,masVolumen)){

        }

        if(Intersector.overlaps(dedo,menosVolumen)){

        }

        if(Intersector.overlaps(dedo,masVidas)){

        }

        if(Intersector.overlaps(dedo,menosVidas)){

        }

        if(Intersector.overlaps(dedo,atras)){
            juego.setScreen(new PantallaPresentacion(juego));
        }

        if(Intersector.overlaps(dedo,tutorial)){

        }

        if(Intersector.overlaps(dedo,salir)){
            Gdx.app.exit();
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
