package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by andrescalvavalencia on 08/11/16.
 */

public class Nivel3 implements Screen,InputProcessor {
    private Juego juego;
    private Music musica;
    private Musica musicaNivel3;

    private Sprite sprite,marcador;
    private Boomerang boomerang;
    private OrthographicCamera camara;
    private Viewport vista;

    private static final float ANCHO=800;
    private static final float ALTO=1280;

    private StretchViewport vistaHUD;
    private OrthographicCamera camaraHUD;
    private OrthogonalTiledMapRenderer rendererMapa;
    private SpriteBatch batch;
    private Personaje pinguino;
    private Fondo fondo1, fondo2,fondo3, fondo4,fondoPausa,fondo22;
    private Texture fondo,fondoFin, fondoLopp, fondoUltimo, texuturaPersonaje ,texturaSal,texturaSalto,texturaDisparo,texturaIzquierda, texturaDerecha,
            texturaBoomeran,textIzq,textPinSalIzq,texturaPausa,texturaPausado,texturaResumen,texturaSalir,texturaScore,texturaBtnSiguiente,textBtnRegresar,texturaBtnGanaste,texturaBotonPerdiste,texturaKicha;
    private TiledMap mapa;
    private static final int celda = 64;
    private static final float ancho = 896;
    private static final float alto = 1280;
    public static final float alto_mapa = 12800;
    private Boton btnSalto,btnDisparar,btnDer,btnIzq,btnPausa,btnResumen,btnSalir,btnRegresar,btnSiguiente,btnGanaste,btnPerdiste;
    private EstadosJuego estadosJuego;
    private int velocidadX = 5, velocidadY = -5,velocidadItemY = -5,velocidadPosteX = 5,velocidadPosteY = -3,velocidadPinguino = 10;
    public static final int TAM_CELDA = 16;
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 2;
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    public static boolean ajusteSonido = false;
    public static boolean ajuste = false;
    private Sound musicaTopping,muerte;
    private Texto texto;
    private int vidas = 5;
    private Enemigos kicha,kicha1,kicha2,kicha3,kicha4,kicha5,kicha6,kicha7,kicha8,kicha9,kicha10;
    public Preferences score3 = Gdx.app.getPreferences("ScoreNivel3");
    public Nivel3(Juego juego){
        this.juego = juego;
    }

    /*private void crearTexturas(){
        AssetManager manager = juego.getManager();
        manager.load("Fondo3.png",Texture.class);
        manager.load("Fondo3fin.png",Texture.class);
        manager.load("Fondo3loop.png",Texture.class);
        manager.load("Fondo3ultima.png",Texture.class);
        manager.load("Mapanivel3.tmx",TiledMap.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("BtnArriba.png",Texture.class);
        manager.load("BtnBoom.png",Texture.class);
        manager.load("BtnDerecha.png",Texture.class);
        manager.load("BtnIzquierda.png",Texture.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("SaltarIZQ.png",Texture.class);
        manager.load("Walkgud_IZQ.png",Texture.class);
        manager.load("SpriteBoom_ver.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.finishLoading();
    }*/

    private  void cargarObjetos() {
        AssetManager manager = juego.getManager();
        fondo = manager.get("Fondo3.png");
        fondoFin = manager.get("Fondo3fin.png");
        fondoLopp = manager.get("Fondo3loop.png");
        fondoUltimo = manager.get("Fondo3ultima.png");

        mapa = manager.get("Mapanivel3.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa, batch);
        rendererMapa.setView(camara);
        //rendererMapa.setView(camaraHUD);

        //Pinguino
        /*texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,textIzq);
        pinguino.getSprite().setPosition(0,128);
        pinguino.getSprite().setAlpha(1);
        ;*/

        fondo1 = new Fondo(fondo);
        fondo2 = new Fondo(fondoLopp);
        fondo22 = new Fondo(fondoLopp);
        fondo3 = new Fondo(fondoUltimo);
        fondo4 = new Fondo(fondoFin);

        fondo1.setPosicion(0, 0);
        fondo2.setPosicion(0, 3000);
        fondo22.setPosicion(0, 6000);
        fondo3.setPosicion(0, 9000);
        fondo4.setPosicion(0, 12000);

        muerte = manager.get("Meeehhpp!!.wav");

        //Pinguino
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        textIzq = manager.get("Walkgud_IZQ.png");
        textPinSalIzq = manager.get("SaltarIZQ.png");
        pinguino = new Personaje(texuturaPersonaje, texturaSal, textIzq, textPinSalIzq);
        pinguino.getSprite().setPosition(500, 60);

        texturaBoomeran = manager.get("SpriteBoom_ver.png");
        //boomerang = new Boomerang(texturaBoomeran,0);

        //botones
        texturaSalto = manager.get("Btnsalto3.png");
        texturaDisparo = manager.get("BtnBoom3.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(740, -20);
        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(740, 90);
        texturaDerecha = manager.get("BtnDer3.png");
        texturaIzquierda = manager.get("Btnizq3.png");
        btnDer = new Boton(texturaDerecha);
        btnDer.setPosicion(150, -20);
        btnIzq = new Boton(texturaIzquierda);
        btnIzq.setPosicion(20, -20);

        texturaPausa = manager.get("BtnPausa_azul.png");
        btnPausa = new Boton((texturaPausa));
        btnPausa.setPosicion(740, 1150);
        texturaPausado = manager.get("Pausa.png");
        fondoPausa = new Fondo(texturaPausado);
        texturaResumen = manager.get("BTN_Resumen.png");
        btnResumen = new Boton(texturaResumen);
        texturaSalir = manager.get("BTN_Salir.png");
        btnSalir = new Boton(texturaSalir);

        texturaScore = manager.get("CuadroScore.png");
        marcador = new Sprite(texturaScore);
        marcador.setPosition(20, 1050);
        musicaTopping = manager.get("Toppings.wav");
        musica = manager.get("Nivel3.mp3");
        musicaNivel3 = new Musica(musica, true, ajuste);

        texturaBtnSiguiente = manager.get("BtnSig_azul.png");
        textBtnRegresar = manager.get("BtnRegresar_azul.png");
        texturaBtnGanaste = manager.get("Ganaste_1.png");
        texturaBotonPerdiste = manager.get("Perdiste_1.png");

        btnSiguiente = new Boton(texturaBtnSiguiente);
        btnRegresar = new Boton(textBtnRegresar);
        btnGanaste = new Boton(texturaBtnGanaste);
        btnPerdiste = new Boton(texturaBotonPerdiste);
        btnPerdiste.setPosicion(Juego.alto / 8, Juego.ancho / 4);
        btnGanaste.setPosicion(Juego.alto / 8, Juego.ancho / 4);

        //*** KICHAS ***\\
        texturaKicha = manager.get("SpriteKicha.png");
        kicha = new Enemigos(texturaKicha);
        kicha1 = new Enemigos(texturaKicha);
        kicha2 = new Enemigos(texturaKicha);
        kicha3 = new Enemigos(texturaKicha);
        kicha4 = new Enemigos(texturaKicha);
        kicha5 = new Enemigos(texturaKicha);
        kicha6 = new Enemigos(texturaKicha);
        kicha7 = new Enemigos(texturaKicha);
        kicha8 = new Enemigos(texturaKicha);
        kicha9 = new Enemigos(texturaKicha);
        kicha10 = new Enemigos(texturaKicha);
        kicha.setPosicionKicha(512,1408);
        kicha1.setPosicionKicha(128,2304);
        kicha2.setPosicionKicha(320,3246);
        kicha3.setPosicionKicha(256,4096);
        kicha4.setPosicionKicha(448,4864);
        kicha5.setPosicionKicha(512,6848);
        kicha6.setPosicionKicha(448,8832);
        kicha7.setPosicionKicha(128,10112);
        kicha8.setPosicionKicha(192,12224);
        kicha9.setPosicionKicha(448,12096);
        kicha10.setPosicionKicha(700,12224);
        kicha.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha1.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha2.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha3.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha4.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha5.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha6.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha7.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha8.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha9.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        kicha10.setEstadoKicha(Enemigos.EstadoKicha.BAJANDO);
        //*** FIN DE KICHAS ***\\
    }
    @Override
    public void show() {

        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
        PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);

        camara = camara1.crearCamaraNivel3();

        camaraHUD = camaraHUD1.crearCamaraNivel3();

        //vistaHUD = vistaHUD1.crearVistaHUDNivel33(camaraHUD,vistaHUD);


        batch = new SpriteBatch();
        vista = new StretchViewport(ALTO,ANCHO,camara);
        vistaHUD = new StretchViewport(ALTO,ANCHO,camaraHUD);

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        //crearTexturas();
        cargarObjetos();
        estadosJuego = EstadosJuego.JUGANDO;
        texto = new Texto();

        pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
    }

    @Override
    public void render(float delta) {
        if(estadosJuego != EstadosJuego.PERDIO){
            moverPersonaje();
            actualizarCamara();
        }
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        rendererMapa.setView(camara);
        //rendererMapa.setView(camara);
        batch.begin();
        fondo1.draw(batch);
        fondo2.draw(batch);
        fondo22.draw(batch);
        fondo3.draw(batch);
        fondo4.draw(batch);
        batch.end();
        rendererMapa.render();

        batch.begin();
        pinguino.render(batch);
        kicha.renderKicha(batch);
        kicha1.renderKicha(batch);
        kicha2.renderKicha(batch);
        kicha3.renderKicha(batch);
        kicha4.renderKicha(batch);
        kicha5.renderKicha(batch);
        kicha6.renderKicha(batch);
        kicha7.renderKicha(batch);
        kicha8.renderKicha(batch);
        kicha9.renderKicha(batch);
        kicha10.renderKicha(batch);
        if(boomerang != null){
            boomerang.renderNivel3(batch);
            boomerang.tiempo ++;
            if(boomerang.tiempo >= 30){
                boomerang.setBoom(Boomerang.boom.REGRESANDO);
                //if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && )
            }
            if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && (boomerang.getY() >= pinguino.getY() && boomerang.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))&&
                    (boomerang.getX() >= pinguino.getX() && boomerang.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))){
                boomerang.setBoom(Boomerang.boom.GUARDADO);
                boomerang.setPosicion(-1000,0);
                btnDisparar.setPosicion(780,90);
                //btnDisparar.setDisabled(false);
                //btnDisparar.setDisabled(false);
            }
            else if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && boomerang.getY() <= pinguino.getY()){
                boomerang.setBoom(Boomerang.boom.GUARDADO);
                boomerang.setPosicion(-1000,0);
                btnDisparar.setPosicion(740,90);
                //btnDisparar.setDisabled(false);
                //btnDisparar.setDisabled(false);
            }

            if((boomerang.getX() >= kicha.getXKicha() && boomerang.getX()<= (kicha.getXKicha()+kicha.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha.getYKicha() && boomerang.getY()<= (kicha.getYKicha()+kicha.getSpriteKicha().getHeight()))){
                kicha.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha1.getXKicha() && boomerang.getX()<= (kicha1.getXKicha()+kicha1.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha1.getYKicha() && boomerang.getY()<= (kicha1.getYKicha()+kicha1.getSpriteKicha().getHeight()))){
                kicha1.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha2.getXKicha() && boomerang.getX()<= (kicha2.getXKicha()+kicha2.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha2.getYKicha() && boomerang.getY()<= (kicha2.getYKicha()+kicha2.getSpriteKicha().getHeight()))){
                kicha2.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha3.getXKicha() && boomerang.getX()<= (kicha3.getXKicha()+kicha3.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha3.getYKicha() && boomerang.getY()<= (kicha3.getYKicha()+kicha3.getSpriteKicha().getHeight()))){
                kicha3.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha4.getXKicha() && boomerang.getX()<= (kicha4.getXKicha()+kicha4.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha4.getYKicha() && boomerang.getY()<= (kicha4.getYKicha()+kicha4.getSpriteKicha().getHeight()))){
                kicha4.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha5.getXKicha() && boomerang.getX()<= (kicha5.getXKicha()+kicha5.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha5.getYKicha() && boomerang.getY()<= (kicha5.getYKicha()+kicha5.getSpriteKicha().getHeight()))){
                kicha5.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha6.getXKicha() && boomerang.getX()<= (kicha6.getXKicha()+kicha6.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha6.getYKicha() && boomerang.getY()<= (kicha6.getYKicha()+kicha6.getSpriteKicha().getHeight()))){
                kicha6.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha7.getXKicha() && boomerang.getX()<= (kicha7.getXKicha()+kicha7.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha7.getYKicha() && boomerang.getY()<= (kicha7.getYKicha()+kicha7.getSpriteKicha().getHeight()))){
                kicha7.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha8.getXKicha() && boomerang.getX()<= (kicha8.getXKicha()+kicha8.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha8.getYKicha() && boomerang.getY()<= (kicha8.getYKicha()+kicha8.getSpriteKicha().getHeight()))){
                kicha8.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha9.getXKicha() && boomerang.getX()<= (kicha9.getXKicha()+kicha9.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha9.getYKicha() && boomerang.getY()<= (kicha9.getYKicha()+kicha9.getSpriteKicha().getHeight()))){
                kicha9.setPosicionKicha(-10000,0);
            }
            if((boomerang.getX() >= kicha10.getXKicha() && boomerang.getX()<= (kicha10.getXKicha()+kicha10.getSpriteKicha().getWidth()))&&
                    (boomerang.getY() >= kicha10.getYKicha() && boomerang.getY()<= (kicha10.getYKicha()+kicha10.getSpriteKicha().getHeight()))){
                kicha10.setPosicionKicha(-10000,0);
            }

        }
        if((pinguino.getX() >= kicha.getXKicha() && pinguino.getX()<= (kicha.getXKicha()+kicha.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha.getYKicha()-kicha.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha1.getXKicha() && pinguino.getX()<= (kicha1.getXKicha()+kicha1.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha1.getYKicha()-kicha1.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha1.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha1.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha2.getXKicha() && pinguino.getX()<= (kicha2.getXKicha()+kicha2.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha2.getYKicha()-kicha2.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha2.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha2.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha3.getXKicha() && pinguino.getX()<= (kicha3.getXKicha()+kicha3.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha3.getYKicha()-kicha3.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha3.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha3.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha4.getXKicha() && pinguino.getX()<= (kicha4.getXKicha()+kicha4.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha4.getYKicha()-kicha4.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha4.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha4.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha5.getXKicha() && pinguino.getX()<= (kicha5.getXKicha()+kicha5.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha5.getYKicha()-kicha5.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha5.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha5.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha6.getXKicha() && pinguino.getX()<= (kicha6.getXKicha()+kicha6.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha6.getYKicha()-kicha6.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha6.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha6.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha7.getXKicha() && pinguino.getX()<= (kicha7.getXKicha()+kicha7.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha7.getYKicha()-kicha7.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha7.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha7.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha8.getXKicha() && pinguino.getX()<= (kicha8.getXKicha()+kicha8.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha8.getYKicha()-kicha8.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha8.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha8.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha9.getXKicha() && pinguino.getX()<= (kicha9.getXKicha()+kicha9.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha9.getYKicha()-kicha9.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha9.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha9.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }
        if((pinguino.getX() >= kicha10.getXKicha() && pinguino.getX()<= (kicha10.getXKicha()+kicha10.getSpriteKicha().getWidth()))&&
                (pinguino.getY() >= (kicha10.getYKicha()-kicha10.getSpriteKicha().getHeight()) && pinguino.getY() <= kicha10.getYKicha())){
            pinguino.puntos -=500;
            vidas --;
            kicha10.setPosicionKicha(-100000,0);
            if(ajusteSonido == false){
                muerte.play();
            }
        }

        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
        if(estadosJuego == EstadosJuego.PAUSADO){
            fondoPausa.setPosicion(Juego.alto/8,Juego.ancho/4);
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            fondoPausa.draw(batch);
            btnResumen.setPosicion(Juego.ancho/6.5f,Juego.ancho/2);
            btnSalir.setPosicion(Juego.ancho/6.5f,Juego.alto/2);
            btnResumen.render(batch);
            btnSalir.render(batch);
        }
        else if(estadosJuego == EstadosJuego.GANO){
            int scoreA = score3.getInteger("Nivel3",0);
            if (pinguino.puntos > scoreA) {
                score3.clear();
                score3.putInteger("Nivel3",pinguino.puntos);
                score3.flush();
            }
            btnGanaste.render(batch);
            btnSiguiente.setPosicion(700,0);
            btnSiguiente.render(batch);
        }
        else if(estadosJuego == EstadosJuego.PERDIO){
            btnPerdiste.render(batch);
            btnRegresar.setPosicion(50,0);
            btnRegresar.render(batch);
        }
        else{
            btnSalto.render(batch);
            btnDisparar.render(batch);
            btnIzq.render(batch);
            btnDer.render(batch);
            btnPausa.render(batch);
            marcador.draw(batch);
            texto.mostrarMensaje(batch," " + pinguino.puntos,150,1240);
            texto.mostrarMensaje(batch," " + vidas,150,1160);
        }


        batch.end();


    }
    private void actualizarCamara(){
        float PosY = pinguino.getY();
        if (PosY > 12155){
            camara.position.set(camara.position.x,12155,0);
            Gdx.app.log("actualizarCamara", String.valueOf(PosY));


        }
        else if(PosY >= alto/2 && PosY <= alto_mapa - alto/2 ){
            camara.position.set(camara.position.x,(int)PosY,0);


        }
        else if (PosY >= alto_mapa - Juego.alto/2){
            camara.position.set(camara.position.x,alto_mapa-Juego.alto/2,0);
        }
        camara.update();
    }



    public void moverPersonaje() {
        switch (pinguino.getEstadoMovimiento()) {
            case DER:
                float x = 0;
                if (pinguino.getX() >= 740) {
                    x = pinguino.getSprite().getX();
                    pinguino.getSprite().setX(x);
                } else {
                    x = pinguino.getSprite().getX() + velocidadPinguino;
                    pinguino.getSprite().setX(x);
                }
                break;

            case IZQ:
                float x1 = 0;
                if (pinguino.getX() <= 0) {
                    x = pinguino.getSprite().getX();
                    pinguino.getSprite().setX(x);
                } else {
                    x1 = pinguino.getSprite().getX() - velocidadPinguino;
                    pinguino.getSprite().setX(x1);
                }
                break;

        }
        if(pinguino.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)){
            int celdaX = (int) ((pinguino.getSprite().getX())/celda);
            int celdaY = (int) ((pinguino.getY()+pinguino.velocidadY) / celda);
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(0);
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX,celdaY);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX+1,celdaY);
            TiledMapTileLayer.Cell celdaIzq = capa.getCell(celdaX-1,celdaY);

            if((celdaIzq == null && celdaAbajo == null && celdaDerecha == null) || esCereza(celdaAbajo) || esCereza(celdaDerecha) || esCereza(celdaIzq) || esChispa(celdaAbajo) || esChispa(celdaDerecha)|| esChispa(celdaIzq)){
                pinguino.caer();
                pinguino.setEstadoSalto(Personaje.EstadoSalto.CAIDALIBRE);

            }
            else if ((celdaIzq!= null && celdaAbajo == null) || esCereza(celdaAbajo) || esCereza(celdaDerecha) || esCereza(celdaIzq) || esChispa(celdaAbajo) || esChispa(celdaDerecha)|| esChispa(celdaIzq) ){
                pinguino.caer();
            }
            else{
                pinguino.setPosicion(pinguino.getX(),(celdaY +1)*celda);
                pinguino.setEstadoSalto(Personaje.EstadoSalto.ABAJO);
            }
            if(vidas <= 0){
                estadosJuego = EstadosJuego.PERDIO;
                pinguino.velocidadX = 0;
                pinguino.setPosicion(-1000,10000);
            }
            if(pinguino.getY() >= 12800){
                pinguino.velocidadX = 0;
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                pinguino.setPosicion(-1000,10000);
                estadosJuego = EstadosJuego.GANO;
                int scoreA = score3.getInteger("Nivel3",0);
                if (pinguino.puntos > scoreA) {
                    score3.clear();
                    score3.putInteger("Nivel3",pinguino.puntos);
                    score3.flush();
                }
            }
        }
        switch(pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto(mapa);
                break;
        }
        pinguino.recolectarToppings(mapa,musicaTopping);
    }

    private boolean esCereza(TiledMapTileLayer.Cell cell) {
        if(cell == null){
            return  false;
        }
        Object propiedad = cell.getTile().getProperties().get("tipo");
        return "cereza".equals(propiedad);
    }

    private boolean esChispa(TiledMapTileLayer.Cell cell){
        if(cell == null){
            return false;
        }
        Object propiedad = cell.getTile().getProperties().get("tipo");
        return "chispas".equals(propiedad);
    }


    @Override
    public void resize(int width, int height) {
        vista.update(height,width);
        vistaHUD.update(width,height);

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
        juego.getManager().unload("Fondo3.png");
        juego.getManager().unload("Fondo3fin.png");
        juego.getManager().unload("Fondo3loop.png");
        juego.getManager().unload("Fondo3ultima.png");
        juego.getManager().unload("Mapanivel3.tmx");
        juego.getManager().unload("PinguinoChido2.png");
        juego.getManager().unload("Saltar.png");
        juego.getManager().unload("Btnsalto3.png");
        juego.getManager().unload("BtnBoom3.png");
        juego.getManager().unload("BtnDer3.png");
        juego.getManager().unload("Btnizq3.png");
        juego.getManager().unload("PinguinoChido2.png");
        juego.getManager().unload("Saltar.png");
        juego.getManager().unload("SaltarIZQ.png");
        juego.getManager().unload("Walkgud_IZQ.png");
        juego.getManager().unload("SpriteBoom_ver.png");
        juego.getManager().unload("BTN_Resumen.png");
        juego.getManager().unload("BTN_Salir.png");
        juego.getManager().unload("BtnPausa_azul.png");
        juego.getManager().unload("Pausa.png");
        juego.getManager().unload("CuadroScore.png");
        juego.getManager().unload("Toppings.wav");
        juego.getManager().unload("Nivel3.mp3");
        juego.getManager().unload("SpriteKicha.png");
        juego.getManager().unload("BtnSig_azul.png");
        juego.getManager().unload("BtnRegresar_azul.png");
        juego.getManager().unload("Ganaste_1.png");
        juego.getManager().unload("Perdiste_1.png");
        juego.getManager().unload("Meeehhpp!!.wav");
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
        transformarCoordenadas(screenX, screenY);
        if (btnSalto.contiene(x, y)) {
            pinguino.saltar();
        }
        else if(btnDisparar.contiene(x,y)){
            boomerang = new Boomerang(texturaBoomeran,0);
            boomerang.setPosicion(pinguino.getX(),(int)pinguino.getY());
            boomerang.salir();
            //btnDisparar.setPosicion(0,-100);
            btnDisparar.setPosicion(0,-100);

        }
        else if (btnDer.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
        }
        else if (btnIzq.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.IZQ);
        }
        else if(btnPausa.contiene(x,y)){
            estadosJuego = EstadosJuego.PAUSADO;

        }
        else if(btnResumen.contiene(x,y)){
            estadosJuego = EstadosJuego.JUGANDO;
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            fondoPausa.setPosicion(-13444,-12435);}
        else if(btnSalir.contiene(x,y)){
            juego.setScreen(new MenuPrincipal(juego));
        }
        /*else if(btnRegresar.contiene(x,y) && estadosJuego == EstadosJuego.PERDIO){
            System.out.println("me puchaste");
            juego.setScreen(new PantallaCargando3(juego));
        }
        else if(btnSiguiente.contiene(x,y) && estadosJuego == EstadosJuego.GANO){
            System.out.println("me tocaste");
            juego.setScreen(new PantallaPuntaje(juego));
        }*/


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        transformarCoordenadas(screenX,screenY);
        if(btnDer.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
        }
        if(btnIzq.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
        }
        if(btnRegresar.contiene(x,y) && estadosJuego == EstadosJuego.PERDIO){
            juego.setScreen(new PantallaCargando3(juego));
        }
        if(btnSiguiente.contiene(x,y) && estadosJuego == EstadosJuego.GANO) {
            juego.setScreen(new PantallaPuntaje(juego));
        }
        return true;
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

    private void transformarCoordenadas(int screenX, int screenY){
        coordenadas.set(screenX, screenY,0);
        camaraHUD.unproject(coordenadas);
        x = coordenadas.x;
        y = coordenadas.y;
    }


}
