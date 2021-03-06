package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
 * Created by Mario Lagunes on 26/09/2016.
 */
public class PantallaAcercaDe extends PantallaDatos implements Screen {
    private final Juego juego;
    private Texture texturaBack, texturaAcercaDe,mario,infoMario,andres,infoAndres,santi,infoSanti,dany,infoDany,moka,infoMoka;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    public Music musica;
    public Musica musicaAcercaDe;
    public static Boolean ajuste = false;
    private Sound heladoEspecial;
    public static Boolean ajusteSonido = false;

    public PantallaAcercaDe(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        //*** CREAR CAMARA Y VISTA ***\\
            PantallaDatos camara1 = new PantallaDatos(camara);
            PantallaDatos vista1 = new PantallaDatos(vista);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);
        //*** DIN DE CREAR CAMARA Y VISTA ***\\

        //*** CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES ***\\
            cargarImagenes();
            cargarFondo();
            cargarBotones();
        //*** FIN DE CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES***\\
    }



    private void cargarBotones() {
        TextureRegionDrawable back = new TextureRegionDrawable(new TextureRegion(texturaBack));
        ImageButton btonBack = new ImageButton(back);
        btonBack.setPosition(0,0);
        //Botones Nombres

        TextureRegionDrawable mario1 = new TextureRegionDrawable(new TextureRegion(mario));
        ImageButton btnMario = new ImageButton(mario1);
        btnMario.setPosition(435,385);

        TextureRegionDrawable andres1 = new TextureRegionDrawable(new TextureRegion(andres));
        ImageButton btnAndres = new ImageButton(andres1);
        btnAndres.setPosition(660,385);

        TextureRegionDrawable santi1 = new TextureRegionDrawable(new TextureRegion(santi));
        ImageButton btnSanti = new ImageButton(santi1);
        btnSanti.setPosition(290,185);

        TextureRegionDrawable dany1 = new TextureRegionDrawable(new TextureRegion(dany));
        ImageButton btnDany = new ImageButton(dany1);
        btnDany.setPosition(520,186);

        TextureRegionDrawable moka1 = new TextureRegionDrawable(new TextureRegion(moka));
        ImageButton btnMoka = new ImageButton(moka1);
        btnMoka.setPosition(780,186);

        //Botones Info Texturas
        TextureRegionDrawable marioinf = new TextureRegionDrawable(new TextureRegion(infoMario));
        final ImageButton btnMarioInf = new ImageButton(marioinf);
        btnMarioInf.setPosition(0,0);
        btnMarioInf.setVisible(false);

        TextureRegionDrawable andresinf = new TextureRegionDrawable(new TextureRegion(infoAndres));
        final ImageButton btnAndresInf = new ImageButton(andresinf);
        btnAndresInf.setPosition(0,0);
        btnAndresInf.setVisible(false);

        TextureRegionDrawable santiinf = new TextureRegionDrawable(new TextureRegion(infoSanti));
        final ImageButton btnSantiInf = new ImageButton(santiinf);
        btnSantiInf.setPosition(0,0);
        btnSantiInf.setVisible(false);

        TextureRegionDrawable danyinf = new TextureRegionDrawable(new TextureRegion(infoDany));
        final ImageButton btnDanyInf= new ImageButton(danyinf);
        btnDanyInf.setPosition(0,0);
        btnDanyInf.setVisible(false);

        TextureRegionDrawable mokainf = new TextureRegionDrawable(new TextureRegion(infoMoka));
        final ImageButton btnMokaInf = new ImageButton(mokainf);
        btnMokaInf.setPosition(0,0);
        btnMokaInf.setVisible(false);

        escena.addActor(btonBack);
        escena.addActor(btnMario);
        escena.addActor(btnAndres);
        escena.addActor(btnSanti);
        escena.addActor(btnDany);
        escena.addActor(btnMoka);

        escena.addActor(btnMarioInf);
        escena.addActor(btnAndresInf);
        escena.addActor(btnSantiInf);
        escena.addActor(btnDanyInf);
        escena.addActor(btnMokaInf);

        btonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }

            }
        });

        //Botones Nombres
        btnMario.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMarioInf.setVisible(true);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }

            }
        });

        btnAndres.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAndresInf.setVisible(true);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnSanti.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSantiInf.setVisible(true);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnDany.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnDanyInf.setVisible(true);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnMoka.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMokaInf.setVisible(true);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        //Botones Texturas
        btnMarioInf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMarioInf.setVisible(false);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnAndresInf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnAndresInf.setVisible(false);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnSantiInf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnSantiInf.setVisible(false);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnDanyInf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnDanyInf.setVisible(false);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

        btnMokaInf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                btnMokaInf.setVisible(false);
                if(ajusteSonido == false){
                    heladoEspecial.play();
                }
            }
        });

    }

    private void cargarFondo() {
        escena = new Stage();
        Gdx.input.setInputProcessor(escena);
        Image fondo = new Image(texturaAcercaDe);
        escena.addActor(fondo);
        Gdx.input.setCatchBackKey(true);
    }

    private void cargarImagenes() {
        AssetManager manager = juego.getManager();
        manager.load("AcercaDe.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);

        manager.load("Boton_mario.png",Texture.class);
        manager.load("AcercaDeMarioSF-01.png",Texture.class);

        manager.load("Boton_andres.png",Texture.class);
        manager.load("AcercaDeAndresSF-01.png",Texture.class);

        manager.load("Boton_santi.png",Texture.class);
        manager.load("AcercaDeSantiSF-01.png",Texture.class);

        manager.load("Boton_dany.png",Texture.class);
        manager.load("AcercaDeDanySF-01.png",Texture.class);

        manager.load("Boton_moka.png",Texture.class);
        manager.load("AcercaDeMokaSF-01.png",Texture.class);

        manager.load("Score.mp3",Music.class);
        manager.load("Helado acerca de.mp3",Sound.class);

        manager.finishLoading();

        texturaAcercaDe = manager.get("AcercaDe.png");
        texturaBack = manager.get("botonRegresar.png");

        mario = manager.get("Boton_mario.png");
        infoMario = manager.get("AcercaDeMarioSF-01.png");

        andres = manager.get("Boton_andres.png");
        infoAndres = manager.get("AcercaDeAndresSF-01.png");

        santi = manager.get("Boton_santi.png");
        infoSanti = manager.get("AcercaDeSantiSF-01.png");

        dany = manager.get("Boton_dany.png");
        infoDany = manager.get("AcercaDeDanySF-01.png");

        moka = manager.get("Boton_moka.png");
        infoMoka = manager.get("AcercaDeMokaSF-01.png");

        musica = manager.get("Score.mp3");
        musicaAcercaDe = new Musica(musica,true,ajuste);
        heladoEspecial = manager.get("Helado acerca de.mp3");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            juego.setScreen(new MenuPrincipal(juego));
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
        manager.unload("AcercaDe.png");
        manager.unload("botonRegresar.png");
        manager.unload("Boton_mario.png");
        manager.unload("AcercaDeMarioSF-01.png");
        manager.unload("Boton_andres.png");
        manager.unload("AcercaDeAndresSF-01.png");
        manager.unload("Boton_santi.png");
        manager.unload("AcercaDeSantiSF-01.png");
        manager.unload("Boton_dany.png");
        manager.unload("AcercaDeDanySF-01.png");
        manager.unload("Boton_moka.png");
        manager.unload("AcercaDeMokaSF-01.png");
        manager.unload("Score.mp3");
    }

}
