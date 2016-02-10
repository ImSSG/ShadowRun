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



    public Obstaculo(Vector2 posicion, Vector2 tamano, float velocidade_max, Tipo tipo) {
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

    public Rectangle getColisionObstaculo() {
        return  new Rectangle((int)this.getPosicion().x,(int)this.getPosicion().y,(int)this.getTamano().x,(int)this.getTamano().y);

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
