package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
public class PantallaAjustes  extends PantallaDatos implements Screen,InputProcessor {
    private final Juego juego;
    private Texture texturaBack, texturaAjustes, texturaSonido, texturaMusica,texturaSonidoO,texturaMusicaO;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    private int contador=0,contador2=0;
    private Music musica;
    private Musica musicaAjustes;
    private static Boolean ajuste = false;

    public PantallaAjustes(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        //*** CREAR CAMARA Y VISTA ***\\
            PantallaDatos camara1 = new PantallaDatos(camara);
            PantallaDatos vista1 = new PantallaDatos(vista);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);
        //*** FIN DE CREAR CAMARA Y VISTA ***\\

        //*** CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES ***\\
            cargarImagenes();
            cargarFondo();
            cargarBotones();
        //*** FIN DE CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES***\\

    }

    public void cargarBotones() {
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


        escena.addActor(btnMusica);
        escena.addActor(btnMusica1);
        escena.addActor(btnSonido);

        escena.addActor(btnSonido1);


        btonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));

            }
        });

        if(Nivel1.ajuste == true && MenuPrincipal.ajuste == true && PantallaPuntaje.ajuste == true && PantallaAjustes.ajuste == true && PantallaAcercaDe.ajuste == true){
            btnMusica1.setVisible(true);
            contador = 1;
        }
        else if(Nivel1.ajuste == false && MenuPrincipal.ajuste == false && PantallaPuntaje.ajuste == false && PantallaAjustes.ajuste == false && PantallaAcercaDe.ajuste == false){
            btnMusica1.setVisible(false);
            contador = 0;
        }

        btnMusica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMusica1.setVisible(true);
                if(contador == 0){
                    Nivel1.ajuste = true;
                    MenuPrincipal.ajuste = true;
                    PantallaPuntaje.ajuste = true;
                    PantallaAjustes.ajuste = true;
                    PantallaAcercaDe.ajuste = true;
                    contador ++;
                }
                else if(contador == 1){
                    Nivel1.ajuste = false;
                    MenuPrincipal.ajuste = false;
                    PantallaPuntaje.ajuste = false;
                    PantallaAjustes.ajuste = false;
                    PantallaAcercaDe.ajuste = false;
                    contador --;
                }
                Gdx.app.log("clicked","TAP sobre el boton de no sonido");


            }
        });

        btnMusica1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMusica1.setVisible(false);
                if(contador == 0){
                    Nivel1.ajuste = true;
                    MenuPrincipal.ajuste = true;
                    PantallaPuntaje.ajuste = true;
                    PantallaAjustes.ajuste = true;
                    PantallaAcercaDe.ajuste = true;
                    contador ++;
                }
                else if(contador == 1){
                    Nivel1.ajuste = false;
                    MenuPrincipal.ajuste = false;
                    PantallaPuntaje.ajuste = false;
                    PantallaAjustes.ajuste = false;
                    PantallaAcercaDe.ajuste = false;
                    contador --;
                }
            }
        });

        if(PantallaAcercaDe.ajusteSonido == true){
            btnSonido1.setVisible(true);
            contador2 = 1;
        }
        else if(PantallaAcercaDe.ajusteSonido == false){
            btnSonido1.setVisible(false);
            contador2 = 0;
        }
        btnSonido.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonido1.setVisible(true);
                if(contador2 == 0){
                    PantallaAcercaDe.ajusteSonido = true;
                    contador2 ++;
                }
                else if(contador2 == 1){
                    PantallaAcercaDe.ajusteSonido = false;
                    contador2 --;
                }

            }
        });

        btnSonido1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSonido1.setVisible(false);
                if(contador2 == 0){
                    PantallaAcercaDe.ajusteSonido = true;
                    contador2 ++;
                }
                else if(contador2 == 1){
                    PantallaAcercaDe.ajusteSonido = false;
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
        manager.load("Ajustes.mp3",Music.class);
        manager.finishLoading();


        texturaAjustes = manager.get("menu-ajustes.png");
        texturaBack = manager.get("botonRegresar.png");
        texturaSonido = manager.get("BtnSonido.png");
        texturaMusica = manager.get("BtnMusica.png");
        texturaSonidoO = manager.get("BtnSonido_Osc.png");
        texturaMusicaO = manager.get("BtnMusica_Osc.png");
        musica = manager.get("Ajustes.mp3");
        musicaAjustes = new Musica(musica,true,ajuste);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
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
        manager.unload("Ajustes.mp3");
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            juego.setScreen(new MenuPrincipal(juego));
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
