package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Sam on 24/01/2016.
 */
public class Lisa extends Personaje {

    private Vector2 velocidade;
    private boolean saltando = false;
    private boolean saltandoNaranja = false;
    private Rectangle colisionPersonaje;
    private boolean enPlataforma = false;
    private boolean lisaISALIVE = true;
    private float ultimaAltura = 0;
    private boolean enAzul = false;
    private float puntuacion = 0;
    private int vida = 3;
    private int newVida = 1;

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        if(this.vida == 0){
            lisaISALIVE = false;
        }
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isEnAzul() {
        return enAzul;
    }

    public void setEnAzul(boolean enAzul) {
        this.enAzul = enAzul;

    }

    public boolean isSaltandoNaranja() {
        return saltandoNaranja;
    }

    public void setSaltandoNaranja(boolean saltandoNaranja) {
        this.saltandoNaranja = saltandoNaranja;

    }

    public float getUltimaAltura() {
        return ultimaAltura;
    }

    public void setUltimaAltura(float ultimaAltura) {
        this.ultimaAltura = ultimaAltura;
    }

    public boolean isLisaISALIVE() {
        return lisaISALIVE;
    }

    public void setLisaISALIVE(boolean lisaISALIVE) {
        this.lisaISALIVE = lisaISALIVE;
    }

    public boolean isEnPlataforma() {
        return enPlataforma;
    }

    public void setEnPlataforma(boolean enPlataforma) {
        this.enPlataforma = enPlataforma;
    }

    public boolean isSaltando() {
        return saltando;
    }

    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    public Lisa(Vector2 posicion, Vector2 tamano, float velocidade_max) {

        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(0,0);



    }

    public int getNewVida() {
        return newVida;
    }

    public void setNewVida(int newVida) {
        if(this.newVida < 3) {
            this.newVida = newVida;
        }
        if(this.newVida == 3 && vida < 3){
            vida++;
            this.newVida = 0;
        }
    }

    public void setVelocidadeX(float x){
        velocidade.x = x;

    }
    public void setVelocidadeY(float y){
        velocidade.y = y;
    }


    public Rectangle getColisionesLisa(){
        this.colisionPersonaje = new Rectangle((int)this.getPosicion().x+3,(int)this.getPosicion().y+5,(int)this.getTamano().x-3,(int)this.getTamano().y-4);
        return colisionPersonaje;
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }
}
