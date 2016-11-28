package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Mario Lagunes on 25/09/2016.
 */
public class PantallaAjustes  extends mx.itesm.icecreamquest.PantallaDatos implements Screen {
    private final Juego juego;
    private Texture texturaBack, texturaAjustes, texturaSonido, texturaMusica,texturaSonidoO,texturaMusicaO,texturaBtnPostit,texturaPostit;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    private int contador=0,contador2=0;
    private Music musica;
    private mx.itesm.icecreamquest.Musica musicaAjustes;
    private static Boolean ajuste = false,ajusteBandera = false;

    public PantallaAjustes(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        //*** CREAR CAMARA Y VISTA ***\\
            mx.itesm.icecreamquest.PantallaDatos camara1 = new mx.itesm.icecreamquest.PantallaDatos(camara);
            mx.itesm.icecreamquest.PantallaDatos vista1 = new mx.itesm.icecreamquest.PantallaDatos(vista);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);
        //*** FIN DE CREAR CAMARA Y VISTA ***\\

        //*** CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES ***\\
            cargarImagenes();
            cargarFondo();
            cargarBotones(musica);
            /*if(ajusteBandera == false){
                musica.play();
            }
            else if(ajusteBandera == true){
                musica.stop();
            }*/

        //*** FIN DE CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES***\\

    }

    public void cargarBotones(final Music musica2) {
        TextureRegionDrawable back = new TextureRegionDrawable(new TextureRegion(texturaBack));
        ImageButton btonBack = new ImageButton(back);
        btonBack.setPosition(0,0);
        escena.addActor(btonBack);

        final TextureRegionDrawable musica = new TextureRegionDrawable(new TextureRegion(texturaMusica));
        final ImageButton btnMusica = new ImageButton(musica);
        btnMusica.setPosition(400,300);

        final TextureRegionDrawable musica1 = new TextureRegionDrawable(new TextureRegion(texturaMusicaO));
        final ImageButton btnMusica1 = new ImageButton(musica1);
        btnMusica1.setPosition(400,300);
        btnMusica1.setVisible(false);

        TextureRegionDrawable sonido = new TextureRegionDrawable(new TextureRegion(texturaSonido));
        ImageButton btnSonido = new ImageButton(sonido);
        btnSonido.setPosition(700,300);


        TextureRegionDrawable sonido1 = new TextureRegionDrawable(new TextureRegion(texturaSonidoO));
        final ImageButton btnSonido1 = new ImageButton(sonido1);
        btnSonido1.setPosition(700,300);
        btnSonido1.setVisible(false);

        TextureRegionDrawable postitBoton = new TextureRegionDrawable(new TextureRegion(texturaBtnPostit));
        ImageButton btonPostit = new ImageButton(postitBoton);
        btonPostit.setPosition(150,50);
        escena.addActor(btonPostit);


        TextureRegionDrawable postitBoton1 = new TextureRegionDrawable(new TextureRegion(texturaPostit));
        final ImageButton btonPostit1 = new ImageButton(postitBoton1);
        btonPostit1.setPosition(280,10);
        btonPostit1.setVisible(false);


        escena.addActor(btnMusica);
        escena.addActor(btnMusica1);
        escena.addActor(btnSonido);

        escena.addActor(btnSonido1);
        escena.addActor(btonPostit1);


        btonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new mx.itesm.icecreamquest.MenuPrincipal(juego));

            }
        });

        btonPostit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btonPostit1.setVisible(true);
            }
        });

        btonPostit1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btonPostit1.setVisible(false);
            }
        });

        if(mx.itesm.icecreamquest.Nivel1.ajuste == true && mx.itesm.icecreamquest.MenuPrincipal.ajuste == true && mx.itesm.icecreamquest.PantallaPuntaje.ajuste == true  && mx.itesm.icecreamquest.PantallaAcercaDe.ajuste == true && mx.itesm.icecreamquest.Nivel2.ajuste == true
                && mx.itesm.icecreamquest.Comic.ajuste == true && mx.itesm.icecreamquest.Nivel3.ajuste == true){
            btnMusica1.setVisible(true);
            musica2.stop();
            contador = 1;
        }
        else if(mx.itesm.icecreamquest.Nivel1.ajuste == false && mx.itesm.icecreamquest.MenuPrincipal.ajuste == false && mx.itesm.icecreamquest.PantallaPuntaje.ajuste == false  && mx.itesm.icecreamquest.PantallaAcercaDe.ajuste == false && mx.itesm.icecreamquest.Nivel2.ajuste == false
                && mx.itesm.icecreamquest.Comic.ajuste == false && mx.itesm.icecreamquest.Nivel3.ajuste ==  false){
            btnMusica1.setVisible(false);
            musica2.play();
            contador = 0;
        }

        btnMusica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMusica1.setVisible(true);
                if(contador == 0){
                    musica2.stop();
                    mx.itesm.icecreamquest.Nivel1.ajuste = true;
                    mx.itesm.icecreamquest.MenuPrincipal.ajuste = true;
                    mx.itesm.icecreamquest.PantallaPuntaje.ajuste = true;
                    //PantallaAjustes.ajuste = true;
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajuste = true;
                    mx.itesm.icecreamquest.Nivel2.ajuste = true;
                    mx.itesm.icecreamquest.Comic.ajuste = true;
                    mx.itesm.icecreamquest.Nivel3.ajuste = true;
                    //ajusteBandera = true;
                    contador ++;
                }
                else if(contador == 1){
                    musica2.play();
                    mx.itesm.icecreamquest.Nivel1.ajuste = false;
                    mx.itesm.icecreamquest.MenuPrincipal.ajuste = false;
                    mx.itesm.icecreamquest.PantallaPuntaje.ajuste = false;
                    //PantallaAjustes.ajuste = false;
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajuste = false;
                    mx.itesm.icecreamquest.Nivel2.ajuste = false;
                    mx.itesm.icecreamquest.Comic.ajuste = false;
                    mx.itesm.icecreamquest.Nivel3.ajuste = false;
                    contador --;
                    //ajusteBandera = false;
                }
            }
        });

        btnMusica1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMusica1.setVisible(false);
                if(contador == 0){
                    musica2.stop();
                    mx.itesm.icecreamquest.Nivel1.ajuste = true;
                    mx.itesm.icecreamquest.MenuPrincipal.ajuste = true;
                    mx.itesm.icecreamquest.PantallaPuntaje.ajuste = true;
                    //PantallaAjustes.ajuste = true;
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajuste = true;
                    mx.itesm.icecreamquest.Nivel2.ajuste = true;
                    mx.itesm.icecreamquest.Comic.ajuste = true;
                    mx.itesm.icecreamquest.Nivel3.ajuste = true;
                    //ajusteBandera = true;
                    contador ++;
                }
                else if(contador == 1){
                    musica2.play();
                    mx.itesm.icecreamquest.Nivel1.ajuste = false;
                    mx.itesm.icecreamquest.MenuPrincipal.ajuste = false;
                    mx.itesm.icecreamquest.PantallaPuntaje.ajuste = false;
                    //PantallaAjustes.ajuste = false;
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajuste = false;
                    mx.itesm.icecreamquest.Nivel2.ajuste = false;
                    mx.itesm.icecreamquest.Comic.ajuste = false;
                    mx.itesm.icecreamquest.Nivel3.ajuste = false;
                    contador --;
                    //ajusteBandera = false;
                }
            }
        });

        if(mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido == true && mx.itesm.icecreamquest.Nivel1.ajusteSonido == true && mx.itesm.icecreamquest.Nivel2.ajusteSonido == true && mx.itesm.icecreamquest.Nivel3.ajusteSonido == true){
            btnSonido1.setVisible(true);
            contador2 = 1;
        }
        else if(mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido == false && mx.itesm.icecreamquest.Nivel1.ajusteSonido == false && mx.itesm.icecreamquest.Nivel2.ajusteSonido == false && mx.itesm.icecreamquest.Nivel3.ajusteSonido == false){
            btnSonido1.setVisible(false);
            contador2 = 0;
        }
        btnSonido.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonido1.setVisible(true);
                if(contador2 == 0){
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel1.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel2.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel3.ajusteSonido = true;
                    contador2 ++;
                }
                else if(contador2 == 1){
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel1.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel2.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel3.ajusteSonido = false;
                    contador2 --;
                }

            }
        });

        btnSonido1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonido1.setVisible(false);
                if(contador2 == 0){
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel1.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel2.ajusteSonido = true;
                    mx.itesm.icecreamquest.Nivel3.ajusteSonido = true;
                    contador2 ++;
                }
                else if(contador2 == 1){
                    mx.itesm.icecreamquest.PantallaAcercaDe.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel1.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel2.ajusteSonido = false;
                    mx.itesm.icecreamquest.Nivel3.ajusteSonido = false;
                    contador2 --;
                }


            }
        });

    }

    private void cargarFondo() {
        escena = new Stage();
        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(true);
        Image fondo = new Image(texturaAjustes);
        escena.addActor(fondo);
    }

    private void cargarImagenes() {
        AssetManager manager = juego.getManager();
        manager.load("menu-ajustes.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.load("BtnMusica.png",Texture.class);
        manager.load("BtnMusica_Osc.png",Texture.class);
        manager.load("BtnSonido.png",Texture.class);
        manager.load("BtnSonido_Osc.png",Texture.class);
        manager.load("Score.mp3",Music.class);
        manager.load("Btn_creditos.png",Texture.class);
        manager.load("postit.png",Texture.class);
        manager.finishLoading();


        texturaAjustes = manager.get("menu-ajustes.png");
        texturaBack = manager.get("botonRegresar.png");
        texturaSonido = manager.get("BtnSonido.png");
        texturaMusica = manager.get("BtnMusica.png");
        texturaSonidoO = manager.get("BtnSonido_Osc.png");
        texturaMusicaO = manager.get("BtnMusica_Osc.png");
        texturaPostit = manager.get("postit.png");
        texturaBtnPostit = manager.get("Btn_creditos.png");
        musica = manager.get("Score.mp3");
        musicaAjustes = new mx.itesm.icecreamquest.Musica(musica,true,ajuste);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            juego.setScreen(new mx.itesm.icecreamquest.MenuPrincipal(juego));
        }
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        AssetManager manager = juego.getManager();
        manager.unload("menu-ajustes.png");
        manager.unload("botonRegresar.png");
        manager.unload("BtnMusica.png");
        manager.unload("BtnMusica_Osc.png");
        manager.unload("BtnSonido.png");
        manager.unload("BtnSonido_Osc.png");
        manager.unload("Score.mp3");
        manager.unload("postit.png");
        manager.unload("Btn_creditos.png");
    }
}
