package com.mygdx.game.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.AssetsXogo;
import com.mygdx.game.modelo.Mundo;
import com.mygdx.game.modelo.Lisa;
import com.mygdx.game.modelo.Obstaculo;
import com.mygdx.game.modelo.Plataformas;

import java.util.ArrayList;

/**
 * Created by Sam on 24/01/2016.
 */
public class RendererXogo implements InputProcessor {



    private ShapeRenderer shaperender;

    private SpriteBatch spritebatch;

    private OrthographicCamera camara2d;


    private boolean debugger = false ;

    private Mundo meuMundo;

    private BitmapFont bmf;



    public RendererXogo(Mundo mundo) {

        meuMundo = mundo;
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();
        shaperender = new ShapeRenderer();
        bmf = new BitmapFont(Gdx.files.internal("fuentes.fnt"),false);

    }


    public void render(float delta) {
        spritebatch.begin();

        if (meuMundo.getLisa().isLisaISALIVE()) {
            dibujarFondo();
            dibujarRunner();
            dibujarObstaculos();
            dibujarPlataformas();
            dibujarPuntuacion();
            dibujarVidas();
            if (debugger) {
                debugger();
            }

        } else {
            dibujarMuerte();
        }


        spritebatch.end();
    }




    private void dibujarFondo() {
        spritebatch.draw(AssetsXogo.texturaMorada, Mundo.FONDO_MORADO.x, Mundo.FONDO_MORADO.y, Mundo.FONDO_MORADO.width, Mundo.FONDO_MORADO.height);
        spritebatch.draw(AssetsXogo.texturePuntoNegro, 0,0,Mundo.TAMANO_MUNDO_ANCHO,Mundo.SUELO);
        spritebatch.draw(AssetsXogo.texturePuntoNegro, 0,Mundo.SUELO+Mundo.FONDO_MORADO.height,Mundo.TAMANO_MUNDO_ANCHO,Mundo.TAMANO_MUNDO_ALTO - (Mundo.SUELO+Mundo.FONDO_MORADO.height));
    }

    private void dibujarRunner() {
        Lisa lisa = meuMundo.getLisa();
        spritebatch.draw(AssetsXogo.textureRunner, lisa.getPosicion().x, lisa.getPosicion().y, lisa.getTamano().x, lisa.getTamano().y);
    }

    private void dibujarObstaculos() {
        ArrayList<Obstaculo> obstaculos = meuMundo.getObstaculos();
        for (Obstaculo o : obstaculos) {
            if (o.getTipo().equals(Obstaculo.Tipo.NORMAL)) {
                spritebatch.draw(AssetsXogo.texturePinchoNegro, o.getPosicion().x, o.getPosicion().y, o.getTamano().x, o.getTamano().y);
            }
            if (o.getTipo().equals(Obstaculo.Tipo.INVERSO)) {
                spritebatch.draw(AssetsXogo.texturePinchoInverso, o.getPosicion().x, o.getPosicion().y, o.getTamano().x, o.getTamano().y);
            }
        }
    }

    private void dibujarPlataformas() {
        for (Plataformas p : meuMundo.getPlataformas()) {
            if (p.getTipo().equals(Plataformas.Tipo.NORMAL) || p.getTipo().equals(Plataformas.Tipo.SOPORTE)) {
                spritebatch.draw(AssetsXogo.texturePlataformaNegra, p.getPosicion().x, p.getPosicion().y, p.getTamano().x, p.getTamano().y);
            }
            if(p.getTipo().equals(Plataformas.Tipo.NARANJA)){
                spritebatch.draw(AssetsXogo.texturePlataformaNaranja,p.getPosicion().x,p.getPosicion().y,p.getTamano().x,p.getTamano().y);
            }
            if(p.getTipo().equals(Plataformas.Tipo.AZUL)){
                spritebatch.draw(AssetsXogo.texturePlataformaAzul, p.getPosicion().x,p.getPosicion().y,p.getTamano().x,p.getTamano().y);
            }
        }
    }

    private void dibujarPuntuacion(){

        bmf.draw(spritebatch,"SCORE: " +(int) meuMundo.getLisa().getPuntuacion() ,175,275);


    }

    private void dibujarVidas(){
        int total =  meuMundo.getLisa().getVida();
        for(int i = 0; i < total; i++){
            if(i == 0){
                spritebatch.draw(AssetsXogo.textureStar, 200, Mundo.SUELO - 55, 32,32);
            }
            if(i == 1){
                spritebatch.draw(AssetsXogo.textureStar, 250, Mundo.SUELO - 55, 32,32);
            }
            if(i == 2){
                spritebatch.draw(AssetsXogo.textureStar, 300, Mundo.SUELO - 55, 32,32);
            }
        }
    }

    private void dibujarMuerte() {
        spritebatch.draw(AssetsXogo.texuteMuerte, 0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
    }

    private void debugger() {

        shaperender.begin(ShapeRenderer.ShapeType.Line);
        shaperender.setColor(Color.BLUE);
        for (Obstaculo p : meuMundo.getObstaculos()) {
            shaperender.rect(p.getPosicion().x, p.getPosicion().y, p.getTamano().x, p.getTamano().y);
        }
        for (Plataformas p : meuMundo.getPlataformas()){
            Rectangle x = p.getColisionPlataforma()[1];
            shaperender.rect(x.x,x.y,x.width,x.height);
            x = p.getColisionPlataforma()[0];
            shaperender.rect(x.x,x.y,x.width,x.height);

        }
        Lisa lisa = meuMundo.getLisa();
        shaperender.rect((int) lisa.getPosicion().x, (int) lisa.getPosicion().y, (int) lisa.getTamano().x - 3, (int) lisa.getTamano().y - 8);
        shaperender.end();

    }



    public void resize(int width, int height) {

        camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2d.update();

        spritebatch.setProjectionMatrix(camara2d.combined);
        shaperender.setProjectionMatrix(camara2d.combined);

    }

    public void dispose() {
        spritebatch.dispose();
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

}
