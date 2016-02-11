package com.mygdx.game.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetsXogo;
import com.mygdx.game.ShadowGame;
import com.mygdx.game.modelo.Estrellas;
import com.mygdx.game.modelo.Mundo;
import com.mygdx.game.modelo.Lisa;
import com.mygdx.game.modelo.Obstaculo;
import com.mygdx.game.modelo.Plataformas;
import com.mygdx.game.pantallas.PantallaPausa;
import com.mygdx.game.pantallas.PantallaXogo;


import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Sam on 24/01/2016.
 */
public class ControladorXogo {
    Mundo meuMundo;
    private Lisa lisa;
    boolean sobrePlataforma = false;
    private float tiempo = 0;
    private boolean invencible = false; //Cuando colisiona se vuelve "invencible" durante unos instantes porque colisiona mucho por segundo
    private float invencibleTiempo = 0;
    private ShadowGame juego;

    public enum Keys {
        ESQUERDA, DEREITA, ARRIBA, ABAIXO, PAUSA
    }

    HashMap<Keys, Boolean> keys;

    /**
     * Constructor del controlador del juego, esta clase lleva toda la logica del juego
     *
     * @param mundo Nuestro mundo donde estan todos los objetos
     * @param juego Nuestro juego con su configuracion
     */
    public ControladorXogo(Mundo mundo, ShadowGame juego) {
        this.meuMundo = mundo;
        this.lisa = meuMundo.getLisa();
        this.juego = juego;


        keys = new HashMap<ControladorXogo.Keys, Boolean>();
        {
            keys.put(Keys.ESQUERDA, false);
            keys.put(Keys.DEREITA, false);
            keys.put(Keys.ARRIBA, false);
            keys.put(Keys.ABAIXO, false);
            keys.put(Keys.PAUSA, false);
        }

    }

    /**
     * Metodo que llama al control de todo lo que tenemos en nuestro mundo
     *
     * @param delta tiempo que pasa entre frame y frame
     */
    public void update(float delta) {

        generacionPorTiempo(tiempo += delta);
        lisa.setPuntuacion(lisa.getPuntuacion() + 0.2f);
        controlarLisa(delta);
        controlarObstaculos(delta);
        controlarPlataformas(delta);
        controlarEstrellas(delta);
        gestionarReset(delta);
        gestionarInvencible(delta);
        procesarEntradas();
    }

    /**
     * Metodo que gestiona nuestro personaje y todas sus colisiones
     *
     * @param delta tiempo que pasa entre frame y frame
     */
    private void controlarLisa(float delta) {
        lisa.update(delta);


        //Lisa llega al tope de altura de salto: su altura antes de saltar + 60 en normal
        if (!lisa.isSaltandoNaranja()) {
            if (lisa.isSaltando() && lisa.getPosicion().y > lisa.getUltimaAltura() + 60) {
                lisa.setVelocidadeY(-lisa.velocidade_max);
            }
        } else {
            //Si esta sobre una naranja salta menos, tope: 65
            if (lisa.isSaltando() && lisa.getPosicion().y > lisa.getUltimaAltura() + 45) {
                lisa.setVelocidadeY(-lisa.velocidade_max);

            }
        }

        //Toca con la parte negra de abajo y deja de saltar
        if (Intersector.overlaps(lisa.getColisionesLisa(), meuMundo.PARTEINFERIOR)) {
            lisa.setPosicion(50, meuMundo.SUELO);
            lisa.setSaltando(false);
            lisa.setSaltandoNaranja(false);
        }

        //Llega al tope del mapa
        if (lisa.getPosicion().y + lisa.getTamano().y > 3 * (Mundo.TAMANO_MUNDO_ALTO / 4)) {
            lisa.setVelocidadeY(-lisa.velocidade_max);
        }

        //Toca con una plataforma y se queda sobre ella o si toca con una "soporte" va hacia abajo
        for (Plataformas p : meuMundo.getPlataformas()) {
            if (Intersector.overlaps(lisa.getColisionesLisa(), p.getColisionPlataforma()[0])) {
                //Esta sobre una normal
                if (p.getTipo().equals(Plataformas.Tipo.NORMAL)) {
                    //CONTROLA LAS COLISIONES D ELAS AZULES CON LAS NEGRAS EN EL AIRE PARA QUE DESAPAREZCAN LAS AZULES
                    if (p.getPosicion().y > Mundo.SUELO + 50) {
                        lisa.setVelocidadeY(-lisa.velocidade_max);
                        lisa.setEnAzul(false);
                        Iterator<Plataformas> i = meuMundo.getPlataformas().iterator();
                        while (i.hasNext()) {
                            Plataformas j = i.next();
                            if (j.getTipo().equals(Plataformas.Tipo.AZUL)) {
                                j.setVelocidadeX(-100000); //EL DISPOSE DE LOS POBRES
                            }
                        }
                        lisa.setSaltando(true);
                    }
                    lisa.setPosicion(lisa.getPosicion().x, p.getPosicion().y + p.getTamano().y);
                    sobrePlataforma = true;
                    lisa.setSaltando(false);
                    lisa.setSaltandoNaranja(false);
                    lisa.setEnAzul(false);

                }
                if (p.getTipo().equals(Plataformas.Tipo.SOPORTE)) {
                    lisa.setVelocidadeY(-lisa.velocidade_max);
                }
                //Esta sobre una naranja
                if (p.getTipo().equals(Plataformas.Tipo.NARANJA)) {
                    lisa.setPosicion(lisa.getPosicion().x, p.getPosicion().y + p.getTamano().y);
                    sobrePlataforma = true;
                    lisa.setSaltando(false);
                    lisa.setSaltandoNaranja(true);
                    lisa.setEnAzul(false);
                }
                //Se sube a una azul
                if (p.getTipo().equals(Plataformas.Tipo.AZUL) && !lisa.isEnAzul()) {
                    lisa.setPosicion(lisa.getPosicion().x, p.getPosicion().y + p.getTamano().y);
                    sobrePlataforma = true;
                    lisa.setSaltando(false);
                    lisa.setSaltandoNaranja(false);
                    p.setVelocidadeY(150);
                    p.setVelocidadeX(0);
                    lisa.setVelocidadeY(150);
                    lisa.setEnAzul(true);
                }
            }

            if (Intersector.overlaps(lisa.getColisionesLisa(), p.getColisionPlataforma()[1]) && !p.getTipo().equals(Plataformas.Tipo.AZUL)) {
                if (!lisa.isEnAzul()) {
                    //lisa.setLisaISALIVE(false);
                    if (!invencible) {
                        invencible = true;
                        lisa.setVida(lisa.getVida() - 1);
                        PantallaXogo.dmg();
                    }


                }
            }

        }


        //Toca con un obstaculo
        Rectangle colisionLisa = lisa.getColisionesLisa();
        for (Obstaculo o : meuMundo.getObstaculos()) {
            if (Intersector.overlaps(o.getColisionObstaculo(), colisionLisa)) {
                //lisa.setLisaISALIVE(false);+

                if (!invencible) {
                    lisa.setVida(lisa.getVida() - 1);
                    PantallaXogo.dmg();
                    invencible = true;
                }
            }
        }

    }

    /**
     * Modifica o estado do mapa de teclas e pon a true
     *
     * @param tecla: tecla pulsada
     */
    public void pulsarTecla(Keys tecla) {
        keys.put(tecla, true);
    }

    /**
     * Modifica o estado do mapa de teclas e pon a false
     *
     * @param tecla: tecla liberada
     */
    public void liberarTecla(Keys tecla) {
        keys.put(tecla, false);
    }

    /**
     * Metodo que gestiona las entradas que hacemos al juego, como si pulsamos arriba o mandamos pausar etc
     */
    private void procesarEntradas() {


        if (keys.get(Keys.ARRIBA)) {
            if (!lisa.isSaltando() && !lisa.isEnAzul()) {
                lisa.setUltimaAltura(lisa.getPosicion().y);
                if (lisa.isSaltandoNaranja()) {
                    lisa.setVelocidadeY(lisa.velocidade_max - 150);
                } else {
                    lisa.setVelocidadeY(lisa.velocidade_max);
                }
                lisa.setSaltando(true);
                //Activa el estado saltando sobre naranja
                for (Plataformas p : meuMundo.getPlataformas()) {
                    if (p.getTipo().equals(Plataformas.Tipo.NARANJA) && Intersector.overlaps(lisa.getColisionesLisa(), p.getColisionPlataforma()[0])) {
                        lisa.setSaltandoNaranja(true);
                    }
                }
            }
        }
    }

    /**
     * Metodo que se ejecuta cada 6 segundos generando un nuevo paquete de enemigos
     *
     * @param delta este valor se suma en su llamada en el metodo update al cronometro para que pasen 6 segundos y se genere el paquete
     */
    private void generacionPorTiempo(float delta) {
        if (tiempo >= 6) {
            meuMundo.generacion();
            lisa.setPuntuacion(lisa.getPuntuacion() + 50);
            tiempo = 0;
        }
    }

    /**
     * Metodo que mueve y gestiona las plataformas
     *
     * @param delta es el tiempo que pasa entre frame y frame
     */
    private void controlarPlataformas(float delta) {
        Iterator<Plataformas> i = meuMundo.getPlataformas().iterator();
        Plataformas platAzul = null;
        //Si esta sobre una azul la guardo en memoria para operar con ella
        if (lisa.isEnAzul()) {
            for (Plataformas j : meuMundo.getPlataformas()) {
                if (j.getTipo().equals(Plataformas.Tipo.AZUL)) {
                    platAzul = j;
                }
            }
        }

        while (i.hasNext()) {
            Plataformas p = i.next();
            p.update(delta);
            //Si la plataforma azul existe(Esta sobre una azul)
            if (platAzul != null) {
                //Choca con otra plataforma
                if (!p.getTipo().equals(Plataformas.Tipo.AZUL) && Intersector.overlaps(platAzul.getColisionPlataforma()[0], p.getColisionPlataforma()[0])) {
                    lisa.setVelocidadeY(-lisa.velocidade_max);
                    platAzul.setVelocidadeX(-platAzul.velocidade_max);
                    lisa.setEnAzul(false);
                }
            }

            //Si es azul y llega a suelo +87 para de subir
            if (p.getTipo().equals(Plataformas.Tipo.AZUL)) {
                if (p.getPosicion().y >= Mundo.SUELO + 87) {
                    p.setVelocidadeY(0);
                    lisa.setVelocidadeY(0);
                }
                //Si pasa por una azul y lisa no esta sobre ella, la plataforma se va
                if (!lisa.isEnAzul()) {
                    p.setVelocidadeX(-p.velocidade_max);
                    lisa.setEnAzul(false);
                }
            }
            //Si sale del mapa se borra
            if (p.getPosicion().x + p.getTamano().x < 0) {

                i.remove();
            }


        }
    }

    /**
     * Metodo que mueve y gestiona los obstaculos y sus colisiones
     *
     * @param delta tiempo que pasa entre frame y frame
     */
    private void controlarObstaculos(float delta) {
        Iterator<Obstaculo> i = meuMundo.getObstaculos().iterator();
        while (i.hasNext()) {
            Obstaculo o = i.next();
            o.update(delta);
            if (o.getPosicion().x + o.getTamano().x < 0) {
                i.remove();

            }
        }
    }

    /**
     * Metodo que gestiona las estrellas que nos aparecen y sus colisiones
     *
     * @param delta tiempo que pasa entre frame y frame
     */
    private void controlarEstrellas(float delta) {
        Iterator<Estrellas> i = meuMundo.getEstrellas().iterator();
        while (i.hasNext()) {
            Estrellas e = i.next();
            e.update(delta);
            if (Intersector.overlaps(lisa.getColisionesLisa(), e.getColisionEstrella())) {
                PantallaXogo.estrella();
                lisa.setNewVida(lisa.getNewVida() + 1);
                i.remove();
            }
        }
    }

    /**
     * Metodo que hace que si recibimos daño no podamos recibir todo el daño de golpe y solo pueda pasar 2.5 segundos antes del siguiente daño
     *
     * @param delta tiempo entre frame y frame
     */
    private void gestionarInvencible(float delta) {
        if (invencible) {
            tiempo += delta;
            if (tiempo > 2.5) {
                invencible = false;
                tiempo = 0;
            }
        }
    }


    private void gestionarReset(float delta) {
        if (!lisa.isLisaISALIVE() && keys.get(Keys.ARRIBA)) {
            if (Intersector.overlaps(lisa.getColisionesLisa(), new Rectangle(0, 0, meuMundo.TAMANO_MUNDO_ANCHO, meuMundo.TAMANO_MUNDO_ALTO))) {
                lisa.setLisaISALIVE(true);
                lisa.setVida(3);
            }
        }
    }

}
