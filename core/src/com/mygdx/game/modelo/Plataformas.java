package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Sam on 28/01/2016.
 */
public class Plataformas extends Personaje{


    private Vector2 velocidade;

    private Tipo tipo;

    public enum Tipo {
        NORMAL, SOPORTE, AZUL, NARANJA
    }



    public Plataformas(Vector2 posicion, Vector2 tamano, float velocidade_max, Tipo tipo) {
        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(-velocidade_max,0);
        this.tipo = tipo;


    }


    public void setVelocidadeX(float x){
        velocidade.x = x;

    }
    public void setVelocidadeY(float y){
        velocidade.y = y;
    }

    /**
     * Metodo que da las colisiones de la plataforma la 0 es la grande
     * @return
     */
    public Rectangle[] getColisionPlataforma() {
        Rectangle[] colisiones = new Rectangle[2];
        colisiones[0] = new Rectangle((int)this.getPosicion().x+3,(int)this.getPosicion().y,(int)this.getTamano().x-3,(int)this.getTamano().y+5);
        colisiones[1] = new Rectangle((int)this.getPosicion().x,(int) this.getPosicion().y,3 ,(int)(this.getTamano().y+5));
        return  colisiones;
    }


    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }


}
