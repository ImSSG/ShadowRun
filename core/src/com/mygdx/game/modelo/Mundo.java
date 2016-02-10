package com.mygdx.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Sam on 24/01/2016.
 */
public class Mundo {

    public static final int TAMANO_MUNDO_ANCHO = 600;
    public static final int TAMANO_MUNDO_ALTO = 300;
    public static final int SUELO = TAMANO_MUNDO_ALTO / 4;

    //GRAFICOS
    public final static Rectangle FONDO_MORADO = new Rectangle(0, SUELO, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO / 2);
    public final static Rectangle PARTEINFERIOR = new Rectangle(0, 0, Mundo.TAMANO_MUNDO_ANCHO, Mundo.SUELO + 5);
    private Lisa lisa;
    private ArrayList<Obstaculo> obstaculos;
    private ArrayList<Plataformas> plataformas;

    public Mundo() {

        lisa = new Lisa(new Vector2(50, Mundo.SUELO), new Vector2(30, 45), 350);

        generacion();

    }

    public Lisa getLisa() {
        return lisa;
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public ArrayList<Plataformas> getPlataformas() {
        return plataformas;
    }

    public void generacion() {
        ArrayList<Plataformas> plataformas1 = new ArrayList<Plataformas>();
        ArrayList<Obstaculo> obstaculos1 = new ArrayList<Obstaculo>();

        int paquete = (int) (Math.random() * 100);
        paquete*=3;

        //paquete = 300; //PARA PROBAR EL PAQUETE INDICADO

        if(paquete < 100) {

            int velocidadPaq = 225;

            Obstaculo pincho = new Obstaculo(new Vector2(TAMANO_MUNDO_ANCHO, Mundo.SUELO), new Vector2(16, 16), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            Plataformas plataforma = new Plataformas(new Vector2(pincho.getPosicion().x + pincho.getTamano().x, pincho.getPosicion().y), new Vector2(75, 16), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);

            Plataformas plataforma1 = new Plataformas(new Vector2(plataforma.getPosicion().x + plataforma.getTamano().x, plataforma.getPosicion().y), new Vector2(250, 32), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma1);

            pincho = new Obstaculo(new Vector2(plataforma1.getPosicion().x + plataforma1.getTamano().x / 3, plataforma1.getPosicion().y + plataforma1.getTamano().y), new Vector2(16, 16), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            Obstaculo pincho1 = new Obstaculo(new Vector2(plataforma1.getPosicion().x + plataforma1.getTamano().x - pincho.getTamano().x, plataforma1.getPosicion().y + plataforma1.getTamano().y), new Vector2(12, 12), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho1);

            plataforma = new Plataformas(new Vector2(plataforma1.getPosicion().x + plataforma1.getTamano().x, plataforma.getPosicion().y), new Vector2(16, 16), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);


            pincho1 = new Obstaculo(new Vector2(plataforma.getPosicion().x, plataforma.getPosicion().y + plataforma.getTamano().y), new Vector2(16, 16), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho1);

            pincho = new Obstaculo(new Vector2(pincho1.getPosicion().x + 95, plataforma.getPosicion().y), new Vector2(25, 25), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

        }
        if(paquete>100 && paquete<=200){
            int velocidadPaq = 255;
            Obstaculo pincho = new Obstaculo(new Vector2(TAMANO_MUNDO_ANCHO,Mundo.SUELO), new Vector2(16,16),velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            Plataformas plataforma = new Plataformas(new Vector2(pincho.getPosicion().x+100,pincho.getPosicion().y), new Vector2(125,16), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);

            pincho = new Obstaculo(new Vector2(plataforma.getPosicion().x+plataforma.getTamano().x - 16,plataforma.getPosicion().y+plataforma.getTamano().y), new Vector2(16,16),velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            pincho = new Obstaculo(new Vector2(plataforma.getPosicion().x+plataforma.getTamano().x + 100,plataforma.getPosicion().y), new Vector2(16,16),velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            Obstaculo pincho1 = new Obstaculo(new Vector2(pincho.getPosicion().x+100, pincho.getPosicion().y+lisa.getTamano().y+25), new Vector2(20,20), velocidadPaq, Obstaculo.Tipo.INVERSO);
            obstaculos1.add(pincho1);

            plataforma = new Plataformas(new Vector2(pincho1.getPosicion().x,pincho1.getPosicion().y+pincho1.getTamano().y), new Vector2(75,20), velocidadPaq, Plataformas.Tipo.SOPORTE);
            plataformas1.add(plataforma);

            Plataformas plataforma1 = new Plataformas(new Vector2(plataforma.getPosicion().x+plataforma.getTamano().x+50,Mundo.SUELO), new Vector2(75,16), velocidadPaq,Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma1);

            plataforma = new Plataformas(new Vector2(plataforma1.getPosicion().x+plataforma1.getTamano().x, plataforma1.getPosicion().y), new Vector2(75,32),velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);

            plataforma1 = new Plataformas(new Vector2(plataforma.getPosicion().x + plataforma.getTamano().x + 30, plataforma.getPosicion().y),new Vector2(32,10), velocidadPaq, Plataformas.Tipo.AZUL);
            plataformas1.add(plataforma1);

            plataforma = new Plataformas(new Vector2(plataforma1.getPosicion().x + plataforma1.getTamano().x + 100, Mundo.SUELO + 80),new Vector2(32,16),velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);

        }
        if(paquete>200 && paquete<=300){
            int velocidadPaq = 240;
            Plataformas plataforma = new Plataformas(new Vector2(TAMANO_MUNDO_ANCHO ,Mundo.SUELO), new Vector2(750,16), velocidadPaq, Plataformas.Tipo.NARANJA);
            plataformas1.add(plataforma);

            Obstaculo pincho = new Obstaculo(new Vector2(plataforma.getPosicion().x+65, plataforma.getPosicion().y+16), new Vector2(16,16), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho);

            Obstaculo pincho2 = new Obstaculo (new Vector2(pincho.getPosicion().x+100, pincho.getPosicion().y), new Vector2(16,16), velocidadPaq, Obstaculo.Tipo.NORMAL);
            obstaculos1.add(pincho2);

            plataforma = new Plataformas(new Vector2(pincho.getPosicion().x+165, pincho.getPosicion().y + 40 + lisa.getTamano().y), new Vector2(48,16), velocidadPaq, Plataformas.Tipo.SOPORTE);
            plataformas1.add(plataforma);

            pincho = new Obstaculo(new Vector2(plataforma.getPosicion().x + plataforma.getTamano().x/2, plataforma.getPosicion().y-plataforma.getTamano().y),new Vector2(16,16), velocidadPaq, Obstaculo.Tipo.INVERSO);
            obstaculos1.add(pincho);

            Plataformas plataforma1 = new Plataformas(new Vector2(plataforma.getPosicion().x+plataforma.getTamano().x+55, Mundo.SUELO + 16), new Vector2(32,16), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma1);

            plataforma = new Plataformas (new Vector2(plataforma1.getPosicion().x+plataforma1.getTamano().x, plataforma1.getPosicion().y), new Vector2(60,32), velocidadPaq, Plataformas.Tipo.NARANJA);
            plataformas1.add(plataforma);

            plataforma1 = new Plataformas(new Vector2(plataforma.getPosicion().x+plataforma.getTamano().x + 20, Mundo.SUELO + 16), new Vector2(32,16), velocidadPaq, Plataformas.Tipo.AZUL);
            plataformas1.add(plataforma1);

            //Item sobre esta
            plataforma = new Plataformas(new Vector2(plataforma1.getPosicion().x + plataforma1.getTamano().x + 80, Mundo.SUELO + 80) , new Vector2(32,16), velocidadPaq, Plataformas.Tipo.NORMAL);
            plataformas1.add(plataforma);


        }
        this.plataformas = plataformas1;
        this.obstaculos = obstaculos1;

    }
}
