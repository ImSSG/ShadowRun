package com.mygdx.game.pantallas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;


/**
 * Created by Sam on 11/02/2016.
 */
public class Scene2DUI extends ApplicationAdapter{

    private AssetManager assetManager;
    private Skin skin;
    private Stage stage;
    private String nPantalla;


   public Scene2DUI(){

   }

    public String getnPantalla() {
        return nPantalla;
    }

    public void setnPantalla(String nPantalla) {
        this.nPantalla = nPantalla;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void create () {

        assetManager = new AssetManager();
        assetManager.load("fontes/uiskin.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        TextureAtlas atlas = assetManager.get("fontes/uiskin.atlas", TextureAtlas.class);
        skin = new Skin(Gdx.files.internal("fontes/uiskin.json"), atlas); // Cargamos os estilos
        stage=new Stage();
        if(nPantalla.equals("presentacion")) {
            cargarElementosGraficosPresentacion();
        }
        if(nPantalla.equals("xogo")){
            dialogoNombre();
        }

    }

    private void cargarElementosGraficosPresentacion(){
        Gdx.input.setCatchBackKey(true);
        stage = new Stage(){
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.BACK) {
                    avisarSair();
                }
                return super.keyDown(keyCode);
            }
        };
        Gdx.input.setInputProcessor(stage);

        TextButton botonNueva = new TextButton("Nueva Partida", skin);
        botonNueva.setScale(0.8f);
        botonNueva.setSize(Gdx.graphics.getWidth()*(0.4f), Gdx.graphics.getHeight() / 10);
        botonNueva.setPosition(Gdx.graphics.getWidth()*(0.3f), Gdx.graphics.getHeight() * (0.55f));
        botonNueva.setColor(com.badlogic.gdx.graphics.Color.RED);
        botonNueva.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                //pantalla.dispose();

            }
        });
        stage.addActor(botonNueva);

        TextButton botonMarcadores = new TextButton("Puntuaciones", skin);
        botonMarcadores.setScale(0.8f);
        botonMarcadores.setSize(Gdx.graphics.getWidth()*(0.4f), Gdx.graphics.getHeight() / 10);
        botonMarcadores.setPosition(botonNueva.getX(), botonNueva.getY() - botonNueva.getHeight() - 10);
        botonMarcadores.setColor(com.badlogic.gdx.graphics.Color.RED);
        botonMarcadores.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //pantalla.getGame().setScreen(new PantallaMarcadores(pantalla.getGame()));
            }
        });
        stage.addActor(botonMarcadores);

        TextButton botonAjustes = new TextButton("Ajustes", skin);
        botonAjustes.setScale(0.8f);
        botonAjustes.setSize(Gdx.graphics.getWidth()*(0.4f), Gdx.graphics.getHeight() / 10);
        botonAjustes.setPosition(botonMarcadores.getX(), botonMarcadores.getY() - botonMarcadores.getHeight() - 10);
        botonAjustes.setColor(com.badlogic.gdx.graphics.Color.RED);
        botonAjustes.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                avisarSair();
            }
        });
        stage.addActor(botonAjustes);

        TextButton botonAyuda = new TextButton("Ayuda", skin);
        botonAyuda.setScale(0.8f);
        botonAyuda.setSize(Gdx.graphics.getWidth()*(0.4f), Gdx.graphics.getHeight() / 10);
        botonAyuda.setPosition(botonAjustes.getX(), botonAjustes.getY() - botonAjustes.getHeight() -10);
        botonAyuda.setColor(com.badlogic.gdx.graphics.Color.RED);
        botonAyuda.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                avisarSair();
            }
        });
        stage.addActor(botonAyuda);

        TextButton botonSair = new TextButton("Salir", skin);
        botonSair.setScale(0.8f);
        botonSair.setSize(Gdx.graphics.getWidth()*(0.4f), Gdx.graphics.getHeight() / 10);
        botonSair.setPosition(botonAjustes.getX(), botonAyuda.getY() - botonAyuda.getHeight() -10);
        botonSair.setColor(com.badlogic.gdx.graphics.Color.RED);
        botonSair.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                avisarSair();
            }
        });
        stage.addActor(botonSair);
    }

    private void avisarSair(){
        Dialog d=new Dialog("Salir del juego", skin, "dialog") {
            protected void result (Object object) {
                if ((Boolean) object)
                    Gdx.app.exit();
            }
        }.text("EstÃ¡s seguro de que quieres salir ?")
                .button("Si", true)
                .button("No", false)
                .key(Input.Keys.ENTER, true)
                .key(Input.Keys.ESCAPE, false)
                .show(stage);
        d.setColor(com.badlogic.gdx.graphics.Color.RED);
        d.setSize(d.getWidth()+10,d.getHeight()+100);

    }
    private void dialogoNombre(){
        Dialog d=new Dialog("Â¿Deseas guardar tu puntuaciÃ³n?", skin, "dialog")
                .text("Escribe tu nombre:")
                .show(stage);
        final TextField texto = new TextField("", skin);
        final Label aviso=new Label("",skin);
        aviso.setFontScale(0.7f);
        TextButton botonAceptar=new TextButton("Aceptar",skin);
        botonAceptar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(texto.getText().trim().isEmpty()){
                    aviso.setText("El nombre no puede estar vacÃ­o.");
                }else if(texto.getText().length()>10){
                    aviso.setText("El nombre no puede tener \n mÃ¡s de 10 caracteres.");
                }else{
                    //Puntuacion.nuevaPuntuacion(100,texto.getText()));//Guardo la puntuacion
                    //pantallaXogo.getGame().setScreen(new PantallaMarcadores(pantallaXogo.getGame()));
                }
            }
        });
        TextButton botonCancelar=new TextButton("Cancelar",skin);
        botonCancelar.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //pantallaXogo.dispose();
                //pantallaXogo.getGame().setScreen(new PantallaPresentacion(pantallaXogo.getGame()));
            }
        });


        Table tabla = new Table(skin);
        tabla.defaults().space(5);
        tabla.defaults().fillX();
        tabla.align(Align.center);
        tabla.add(texto).colspan(2);
        tabla.row();
        tabla.add(aviso).colspan(2);
        tabla.row();
        tabla.add(botonAceptar);
        tabla.add(botonCancelar);

        d.add(tabla);
        d.setColor(com.badlogic.gdx.graphics.Color.RED);
        d.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);
        d.setPosition(0, Gdx.graphics.getHeight() / 3);
        stage.setKeyboardFocus(texto);  // PoÃ±emos o foco na caixa de texto
    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

}
