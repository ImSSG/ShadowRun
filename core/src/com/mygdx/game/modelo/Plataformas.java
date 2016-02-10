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


    /**
     * Metodo para crear una nueva plaraforma
     * @param posicion la posicion original del objeto
     * @param tamano el tamaño del objeto
     * @param velocidade_max la velocidad maxima del objeto
     * @param tipo el tipo de plataforma (Normal, Aerea, Azul o naranja)
     */
    public Plataformas(Vector2 posicion, Vector2 tamano, float velocidade_max, Tipo tipo) {
        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(-velocidade_max,0);
        this.tipo = tipo;


    }


    /**
     * Metodo para darle una nueva velocidad lateral al objeto
     * @param x nueva velocidad en horizontal
     */
    public void setVelocidadeX(float x){
        velocidade.x = x;
    }

    /**
     * Metodo para darle una nueva velocidad vertical al objeto
     * @param y la nueva velocidad en vertical
     */
    public void setVelocidadeY(float y){
        velocidade.y = y;
    }

    /**
     * Metodo que da las colisiones de la plataforma la 0 es la grande
     * @return dos rectangulos de colision uno de la plataforma entera y otra del borde izquierdo
     */
    public Rectangle[] getColisionPlataforma() {
        Rectangle[] colisiones = new Rectangle[2];
        colisiones[0] = new Rectangle((int)this.getPosicion().x+3,(int)this.getPosicion().y,(int)this.getTamano().x-3,(int)this.getTamano().y+5);
        colisiones[1] = new Rectangle((int)this.getPosicion().x,(int) this.getPosicion().y,3 ,(int)(this.getTamano().y+5));
        return  colisiones;
    }


    /**
     * Metodo para obtener el tipo del objeto
     * @return tipo el tipo de plataforma (Normal, Aerea, Azul o naranja)
     */
    public Tipo getTipo() {
        return tipo;
    }


    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }


}
