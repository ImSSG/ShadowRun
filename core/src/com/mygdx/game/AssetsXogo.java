package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.logging.FileHandler;


/**
 * Created by Sam on 24/01/2016.
 */
public class AssetsXogo {

    public static Texture texturaMorada;
    public static Texture textureRunner;
    public static Texture texturePinchoNegro;
    public static Texture texturePlataformaNegra;
    public static Texture texturePlataformaAzul;
    public static Texture texturePlataformaNaranja;
    public static Texture texuteMuerte;
    public static Texture texturePinchoInverso;
    public static Texture texturePuntoNegro;
    public static Texture textureStar;
    public static Texture textureStar1;
    public static Texture textureStar2;
    public static Texture texturePausa;



    public static void cargarTexturas() {

        FileHandle moradoHandle = Gdx.files.internal("GRAFICOS\\Violeta.jpg");
        texturaMorada = new Texture(moradoHandle);

        FileHandle runnerHandle = Gdx.files.internal("GRAFICOS/lisa.png");
        textureRunner = new Texture(runnerHandle);

        FileHandle pinchoNegroHandle = Gdx.files.internal("GRAFICOS/pinchoNegro.png");
        texturePinchoNegro = new Texture(pinchoNegroHandle);

        FileHandle platNegraHandle = Gdx.files.internal("GRAFICOS/plataformanegra.png");
        texturePlataformaNegra = new Texture(platNegraHandle);

        FileHandle muerteHandle = Gdx.files.internal("GRAFICOS/muerte.jpg");
        texuteMuerte = new Texture(muerteHandle);

        FileHandle pinchoInversoHandle = Gdx.files.internal("GRAFICOS/pinchoInverso.png");
        texturePinchoInverso = new Texture(pinchoInversoHandle);

        FileHandle platNaranjaHandle = Gdx.files.internal("GRAFICOS/platafotmanaranja.png");
        texturePlataformaNaranja = new Texture(platNaranjaHandle);

        FileHandle platAzulHandle = Gdx.files.internal("GRAFICOS/platafotmaazul.png");
        texturePlataformaAzul = new Texture(platAzulHandle);

        FileHandle negroHandle = Gdx.files.internal("GRAFICOS/Negro.jpg");
        texturePuntoNegro = new Texture(negroHandle);

        FileHandle starHandle = Gdx.files.internal("GRAFICOS/star.png");
        textureStar = new Texture(starHandle);

        FileHandle starHandle1 = Gdx.files.internal("GRAFICOS/star1.png");
        textureStar1 = new Texture(starHandle1);

        FileHandle starHandle2 = Gdx.files.internal("GRAFICOS/star2.png");
        textureStar2 = new Texture(starHandle2);

        FileHandle menu = Gdx.files.internal("GRAFICOS/menu.png");
        texturePausa = new Texture(menu);




    }


    public static void liberarTexturas() {

    }
}
