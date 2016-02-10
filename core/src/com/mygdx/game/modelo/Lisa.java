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
    private boolean lisaISALIVE = true;
    private float ultimaAltura = 0;
    private boolean enAzul = false;
    private float puntuacion = 0;
    private int vida = 3;
    private int newVida = 1;

    /**
     * Constructor del personaje
     * @param posicion posicion original del personaje
     * @param tamano tamano del personaje
     * @param velocidade_max velocidad del personaje
     */
    public Lisa(Vector2 posicion, Vector2 tamano, float velocidade_max) {
        super(posicion, tamano, velocidade_max);
        velocidade = new Vector2(0,0);

    }

    /**
     * Metodo para obtener la vida del personaje
     * @return
     */
    public int getVida() {
        return vida;
    }

    /**
     * Metodo que nos permite modificar la vida del personaje
     * @param vida vida que se le va a poner
     */
    public void setVida(int vida) {
        this.vida = vida;
        if(this.vida == 0){
            lisaISALIVE = false;
        }
    }

    /**
     * Metodo que obtiene la puntuacion de la partida
     * @return puntuacion de la partida
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
     * Metodo para establecer la puncuacion del personaje
     * @param puntuacion
     */
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Metodo que nos dice si el personaje esta sobre una plataforma azul o no
     * @return true si esta en una azul false si no
     */
    public boolean isEnAzul() {
        return enAzul;
    }

    /**
     * Metodo que pone el boolean indicando si esta sobre una azul
     * @param enAzul true si esta false si no
     */
    public void setEnAzul(boolean enAzul) {
        this.enAzul = enAzul;

    }

    /**
     * Metodo que nos dice si el personaje esta sobre una naranja
     * @return true si esta false si no
     */
    public boolean isSaltandoNaranja() {
        return saltandoNaranja;
    }

    /**
     * Metodo que nos permite indicar cuando esta y cuando no esta sobre una naranja
     * @param saltandoNaranja
     */
    public void setSaltandoNaranja(boolean saltandoNaranja) {
        this.saltandoNaranja = saltandoNaranja;

    }

    /**
     * Metodo que nos devuelve la ultima altura conocida (Sirve para controlar la altura maxima del salto)
     * @return la ultima altura conocida
     */
    public float getUltimaAltura() {
        return ultimaAltura;
    }

    /**
     * Metodo que nos permite establecer una ultima altura (util a la altura del salto)
     * @param ultimaAltura altura que le pasamos
     */
    public void setUltimaAltura(float ultimaAltura) {
        this.ultimaAltura = ultimaAltura;
    }

    /**
     * Metodo que nos indica si el personaje esta vivo o no
     * @return true si lo esta false si esta muerta
     */
    public boolean isLisaISALIVE() {
        return lisaISALIVE;
    }

    /**
     * Metodo que nos permite cambiar el estado del personaje de vivo a muerto
     * @param lisaISALIVE un boolean true si esta viva false si no
     */
    public void setLisaISALIVE(boolean lisaISALIVE) {
        this.lisaISALIVE = lisaISALIVE;
    }

    /**
     * Metodo que nos dice si el personaje esta saltando o no
     * @return true si esta false si no
     */
    public boolean isSaltando() {
        return saltando;
    }

    /**
     * Metodo que nos permite establecer si el personaje esta saltando
     * @param saltando boolean a true si esta saltando false si no
     */
    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    /**
     * Metodo que nos permite obtener la "nueva vida"
     * @return un numero de 1 a 3
     */
    public int getNewVida() {
        return newVida;
    }

    /**
     * Metodo en la que modificamos la "nueva vida" cuando la nueva vida es 3 y tenemos menos de 3 vidas nos da una extra
     * @param newVida nuevo valor de la "nueva vida"
     */
    public void setNewVida(int newVida) {
        if(this.newVida < 3) {
            this.newVida = newVida;
        }
        if(this.newVida == 3 && vida < 3){
            vida++;
            this.newVida = 0;
        }
    }

    /**
     * Metodo para darle al personaje la velocidad y
     * @param y la nueva velocidad
     */
    public void setVelocidadeY(float y){
        velocidade.y = y;
    }

    /**
     * Metodo que nos devuelve el cuadrado de colisione
     * @return el rectangulo a partir de la posicion del personaje
     */
    public Rectangle getColisionesLisa(){
        this.colisionPersonaje = new Rectangle((int)this.getPosicion().x+3,(int)this.getPosicion().y+5,(int)this.getTamano().x-3,(int)this.getTamano().y-4);
        return colisionPersonaje;
    }

    @Override
    public void update(float delta) {
        setPosicion(getPosicion().x+velocidade.x*delta, getPosicion().y+velocidade.y*delta);
    }
}
