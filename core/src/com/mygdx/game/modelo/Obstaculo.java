package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by SAMUELRD on 28/01/2016.
 */
public class Obstaculo extends Personaje{

    private Vector2 velocidade;
    private Tipo tipo;
    public enum Tipo {
        NORMAL, INVERSO
    }


    /**
     * Constructor de los obstaculos(Pinchos)
     * @param posicion posicion original del obstaculo
     * @param tamano tamano original del obstaculo
     * @param velocidade_max velocidad maxima del objeto
     * @param tipo Tipo de obstaculo Normal o Inverso
     */
    public Obstaculo(Vector2 posicion, Vector2 tamano, float velocidade_max, Tipo tipo) {
        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(-velocidade_max,0);
        this.tipo = tipo;
    }


    /**
     * Metodo que nos da el rectangulo para ver si hace colisiones
     * @return Devuelve el rectangulo del obstaculo
     */
    public Rectangle getColisionObstaculo() {
        return  new Rectangle((int)this.getPosicion().x,(int)this.getPosicion().y,(int)this.getTamano().x,(int)this.getTamano().y);

    }

    /**
     * Metodo que nos devuelve el tipo del obstaculo
     * @return el tipo del obstaculo
     */
    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }
}
