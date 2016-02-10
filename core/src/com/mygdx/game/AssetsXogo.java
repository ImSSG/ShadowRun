package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.File;
import java.util.logging.FileHandler;


/**
 * Created by Sam on 24/01/2016.
 */
public class AssetsXogo {

    //Atlas 15

    private static TextureAtlas atlas;


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



    public static void cargarTexturas() {

        atlas = new TextureAtlas("GRAFICOS/atlas.atlas");

        morado = atlas.findRegion("Violeta");
        lisa=atlas.findRegion("lisa");
        pausa=atlas.findRegion("menu");
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


    }


    public static void liberarTexturas() {

    }
}
