package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.logging.FileHandler;


/**
 * Created by Sam on 24/01/2016.
 */
public class AssetsXogo {

    public static Texture menuprincipal;

    private static TextureAtlas atlas;
    private static TextureAtlas animAtlas;

    public static TextureRegion morado;
    public static TextureRegion lisa;
    public static TextureRegion pinchoNegro;
    public static TextureRegion plataformaNegra;
    public static TextureRegion plataformaAzul;
    public static TextureRegion plataformaNaranja;
    public static TextureRegion muerte;
    public static TextureRegion pinchoInverso;
    public static TextureRegion puntoNegro;
    public static TextureRegion star;
    public static TextureRegion star1;
    public static TextureRegion star2;
    public static TextureRegion pausa;

    public static TextureRegion[] animacion = new TextureRegion[4];


    /**
     * Metodo que carga todas las texturas del atlas
     */
    public static void cargarTexturas() {

        atlas = new TextureAtlas("GRAFICOS/atlas.atlas");
        animAtlas = new TextureAtlas("GRAFICOS/moneda_H.atlas");

        morado = atlas.findRegion("Violeta");
        lisa = atlas.findRegion("lisa");
        pausa = atlas.findRegion("menu");
        muerte = atlas.findRegion("muerte");
        star = atlas.findRegion("star");
        star1 = atlas.findRegion("star1");
        star2 = atlas.findRegion("star2");
        pinchoNegro = atlas.findRegion("pinchoNegro");
        plataformaNegra = atlas.findRegion("plataformanegra");
        plataformaAzul = atlas.findRegion("platafotmaazul");
        plataformaNaranja = atlas.findRegion("platafotmanaranja");
        pinchoInverso = atlas.findRegion("pinchoInverso");
        puntoNegro = atlas.findRegion("plataformanegra");

        animacion = cargarAnimacion();

        FileHandle fileHandler = Gdx.files.internal("GRAFICOS/menuprincipal.jpg");
        menuprincipal = new Texture(fileHandler);

    }

    /**
     * Metodo que libera todas las texturas
     */
    public static void liberarTexturas() {

        morado = null;
        lisa = null;
        pinchoNegro = null;
        plataformaNegra = null;
        plataformaAzul = null;
        plataformaNaranja = null;
        muerte = null;
        pinchoInverso = null;
        puntoNegro = null;
        star = null;
        star1 = null;
        star2 = null;
        pausa = null;

    }

    /**
     * Metodo que carga la animacion
     * @return
     */
    private static TextureRegion[] cargarAnimacion() {
        TextureRegion[] animacion1 = new TextureRegion[4];
        animacion1[0] = animAtlas.findRegion("moneda_1_H");
        animacion1[1] = animAtlas.findRegion("moneda_2_H");
        animacion1[2] = animAtlas.findRegion("moneda_3_H");
        animacion1[3] = animAtlas.findRegion("moneda_4_H");

        return animacion1;
    }
}
