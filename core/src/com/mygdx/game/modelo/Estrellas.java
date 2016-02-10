package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Sam on 10/02/2016.
 */
public class Estrellas extends Personaje{

    private Vector2 velocidade;

    /**
     * Constructor de las estrellas
     * @param posicion posicion en la que se encuentra el objeto
     * @param tamano tamano del objeto
     * @param velocidade_max velocidad maxima del objeto
     */
    public Estrellas(Vector2 posicion, Vector2 tamano, float velocidade_max) {
        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(-velocidade_max,0);
    }

    /**
     * Metodo para obtener el rectangulo de la estrella
     * @return el rectangulo donde se encuentra actualmente la estrella
     */
    public Rectangle getColisionEstrella() {
        return  new Rectangle((int)this.getPosicion().x,(int)this.getPosicion().y,(int)this.getTamano().x,(int)this.getTamano().y);

    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }
}
