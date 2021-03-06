package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Mario Lagunes on 26/09/2016.
 */
public class Nivel1 implements Screen,InputProcessor {
    public static final float ancho_mapa = 12800;
    private Juego juego;
    private Music musica;
    private Musica musica1;
    private OrthographicCamera camara;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;
    private Viewport vista;
    private SpriteBatch batch;
    private Texture texuturaPersonaje;
    private mx.itesm.icecreamquest.Personaje pinguino;
    private OrthographicCamera camaraHUD;
    private StretchViewport vistaHUD;
    private EstadosJuego estadoJuego;
    private Texto texto;
    private Texture texturaSalto,texturaBoomeran,texturaGano,texturaDisparo,texturaPausa,texturaPausado,texturaEnemigo,texturaFondo1,texturaFondo2,texturaFondo3,texturaScore,texturaDardo,
            texturaSal,texturaSalir,texturaResumen,texturaPerdiste,texturaEnemigoReg,textPinIzq,textPinSalIzq,textSiguiente,textRegresar;
    private Boton btnSalto,btnDisparar,btnGanar,btnPausa,btnResumen,btnScore,btnSalir,btnPerdiste,btnSiguiente,btnRegresar;
    private int heladosRecolectados = 0;
    private int vidas = 5;
    private static final int tamcelda = 64;
    private Boomerang boomerang;
    private mx.itesm.icecreamquest.Enemigos enemigo,enemigo1,enemigo2,enemigo3;
    //private Personaje enemigo1,enemigo2,enemigo3,enemigo4,enemigo5;
    private mx.itesm.icecreamquest.Fondo fondo,fondo2,fondo3,fondoPausa;
    private mx.itesm.icecreamquest.Dardos dardo,dardo1,dardo2,dardo3;
    private Sound muere,helado,heladoEspecial;
    private float tiempo = 0,tiempo2 = 0;
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    public static Boolean ajuste = false;
    public static Boolean ajusteSonido = false;
    private boolean banderaGano = false, banderaPerdio = false;
    public static int puntaje;
    public static final int HIGSCORE = 2348;
    public Preferences score = Gdx.app.getPreferences("ScoreNivel1");

    public Nivel1(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {
        //*** CREAR CAMARA ***\\
            PantallaDatos camara1 = new PantallaDatos(camara);
            PantallaDatos vista1 = new PantallaDatos(vista);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);

        batch = new SpriteBatch();
        PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
        PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);
        camaraHUD = camaraHUD1.crearCamara(camaraHUD);
        vistaHUD = vistaHUD1.crearVistaHUD(camaraHUD,vistaHUD);
        cargarTexturas();
        crearObjetos();
        Gdx.input.setInputProcessor(this);
        estadoJuego = EstadosJuego.JUGANDO;
        texto = new Texto();
        pinguino.setEstadoMovimiento(mx.itesm.icecreamquest.Personaje.EstadoMovimiento.DER);
        /*musica.setLooping(true);
        musica.setVolume(1.5f);
        musica.play();*/
        Gdx.input.setCatchBackKey(true);
    }

    private void cargarTexturas() {
        /*AssetManager manager = juego.getManager();
        manager.load("Fondo64.tmx",TiledMap.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("BtnBoom.png",Texture.class);
        manager.load("SpriteBoom.png",Texture.class);
        manager.load("BtnArriba.png",Texture.class);
        manager.load("Ganaste_1.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("SpriteZookDerecha.png",Texture.class);
        manager.load("SpriteZookIzquierda.png",Texture.class);
        manager.load("FondonivelLoop.png",Texture.class);
        manager.load("FondonivelLoop2.png",Texture.class);
        manager.load("Salida.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("dardo.png",Texture.class);
        manager.load("Parado.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("Perdiste_1.png",Texture.class);
        manager.load("Nivel_1.mp3", Music.class);
        manager.load("Helado especial.mp3",Sound.class);
        manager.load("Helado normal.mp3",Sound.class);
        manager.load("Meeehhpp!!.wav",Sound.class);
        manager.load("Walkgud_IZQ.png",Texture.class);
        manager.load("SaltarIZQ.png",Texture.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.finishLoading();*/
    }

    private void crearObjetos(){
        AssetManager manager = juego.getManager();
        mapa = manager.get("Fondo64.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
        rendererMapa.setView(camara);

        textSiguiente = manager.get("botonSiguiente.png");
        btnSiguiente = new Boton(textSiguiente);
        textRegresar = manager.get("botonRegresar.png");
        btnRegresar = new Boton(textRegresar);

        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        textPinIzq = manager.get("Walkgud_IZQ.png");
        textPinSalIzq = manager.get("SaltarIZQ.png");
        pinguino = new mx.itesm.icecreamquest.Personaje(texuturaPersonaje,texturaSal,textPinIzq,textPinSalIzq);
        pinguino.getSprite().setPosition(0,128);
        pinguino.getSprite().setAlpha(1);

        texturaSalto = manager.get("BtnArriba.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(10,Juego.alto * 0.01f);
        texturaBoomeran = manager.get("SpriteBoom.png");
        //boomerang = new Boomerang(texturaBoomeran);
        texturaDisparo = manager.get("BtnBoom.png");
        texturaGano = manager.get("Ganaste_1.png");
        btnGanar = new Boton(texturaGano);
        btnGanar.setPosicion(Juego.ancho/4,Juego.alto/16);

        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(1100,Juego.alto * 0.01f);

        texturaPausa = manager.get("BtnPausa.png");
        btnPausa = new Boton((texturaPausa));
        btnPausa.setPosicion(1100,Juego.alto*0.8f);
        texturaPausado = manager.get("Pausa.png");
        fondoPausa = new mx.itesm.icecreamquest.Fondo(texturaPausado);
        texturaResumen = manager.get("BTN_Resumen.png");
        btnResumen = new Boton(texturaResumen);
        texturaSalir = manager.get("BTN_Salir.png");
        btnSalir = new Boton(texturaSalir);
        texturaEnemigo = manager.get("SpriteZookDerecha.png");
        texturaEnemigoReg = manager.get("SpriteZookIzquierda.png");
        texturaPerdiste = manager.get("Perdiste_1.png");
        btnPerdiste = new Boton(texturaPerdiste);
        //boomerang.setPosicion(-100,0);
        btnPerdiste.setPosicion(Juego.ancho/4,Juego.alto/16);
        enemigo = new mx.itesm.icecreamquest.Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo1 = new mx.itesm.icecreamquest.Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo2 = new mx.itesm.icecreamquest.Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo3 = new mx.itesm.icecreamquest.Enemigos(texturaEnemigo,texturaEnemigoReg);
        //enemigo3 = new Personaje(texturaEnemigo,texturaEnemigoReg);
        //enemigo4 = new Personaje(texturaEnemigo,texturaEnemigoReg);
        //enemigo2 = new Personaje(texturaEnemigo);
        //enemigo3 = new Personaje(texturaEnemigo);
        //enemigo4 = new Personaje(texturaEnemigo);
        // enemigo5 = new Personaje(texturaEnemigo);
        enemigo.setPosicion(1850,64);
        enemigo1.setPosicion(6400,640);
        enemigo2.setPosicion(4608,576);
        enemigo3.setPosicion(10624,128);
        //enemigo.getSprite().setPosition(300,Juego.alto*0.06f);
        //enemigo1.getSprite().setPosition(400,Juego.alto*0.06f);
        //enemigo2.getSprite().setPosition(600,Juego.alto*0.06f);
        //enemigo3.getSprite().setPosition(700,Juego.alto*0.06f);
        //enemigo4.getSprite().setPosition(800,Juego.alto*0.06f);
        //enemigo5.getSprite().setPosition(3000,Juego.alto*0.06f);
        texturaFondo1 = manager.get("FondonivelLoop.png");
        texturaFondo2 = manager.get("FondonivelLoop.png");
        texturaFondo3 = manager.get("Salida.png");
        fondo = new mx.itesm.icecreamquest.Fondo(texturaFondo1);
        fondo2 = new mx.itesm.icecreamquest.Fondo(texturaFondo2);
        fondo3 = new mx.itesm.icecreamquest.Fondo(texturaFondo3);
        texturaScore = manager.get("CuadroScore.png");
        btnScore = new Boton(texturaScore);
        btnScore.setPosicion(0,Juego.alto*0.7f);
        texturaDardo = manager.get("dardo.png");
        dardo = new mx.itesm.icecreamquest.Dardos(texturaDardo);
        dardo1 = new mx.itesm.icecreamquest.Dardos(texturaDardo);
        dardo2 = new mx.itesm.icecreamquest.Dardos(texturaDardo);
        dardo3 = new mx.itesm.icecreamquest.Dardos(texturaDardo);
        dardo.setPosicion(enemigo.getX(),enemigo.getY());
        dardo1.setPosicion(enemigo1.getX(),enemigo1.getY());
        dardo2.setPosicion(enemigo2.getX(),enemigo2.getY());
        dardo3.setPosicion(enemigo3.getX(),enemigo3.getY());
        musica = manager.get("Nivel_1.mp3");
        musica1 = new Musica(musica,true,ajuste);
        helado = manager.get("Helado normal.mp3");
        heladoEspecial = manager.get("Helado especial.mp3");
        muere = manager.get("Meeehhpp!!.wav");

    }

    @Override
    public void render(float delta) {
        if(estadoJuego != EstadosJuego.PERDIO){
            moverPersonaje();
            actualizarCamara();
        }
        borrarPantalla();

        batch.setProjectionMatrix(camara.combined);
        rendererMapa.setView(camara);
        batch.begin();
            fondo.setPosicion(0,0);
            fondo2.setPosicion(3000,0);
            fondo.draw(batch);
            fondo2.draw(batch);
            fondo.setPosicion(6000,0);
            fondo2.setPosicion(9000,0);
            fondo3.setPosicion(12000,0);
            fondo.draw(batch);
            fondo2.draw(batch);
            fondo3.draw(batch);
        batch.end();

        rendererMapa.render();

        batch.begin();
            pinguino.render(batch);
            if(boomerang != null){
                boomerang.render(batch);
                boomerang.tiempo ++;
                if(boomerang.tiempo >= 30){
                    boomerang.setBoom(Boomerang.boom.REGRESANDO);
                    //if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && )
                }
                if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && (boomerang.getX() >= pinguino.getX() && boomerang.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                        (boomerang.getY() >= pinguino.getY() && boomerang.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                    boomerang.setBoom(Boomerang.boom.GUARDADO);
                    boomerang.setPosicion(-1000,0);
                    btnDisparar.setPosicion(1100,Juego.alto * 0.01f);
                    //btnDisparar.setDisabled(false);
                    //btnDisparar.setDisabled(false);
                }
                else if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && boomerang.getX() <= pinguino.getX()){
                    boomerang.setBoom(Boomerang.boom.GUARDADO);
                    boomerang.setPosicion(-1000,0);
                    btnDisparar.setPosicion(1100,Juego.alto * 0.01f);
                    //btnDisparar.setDisabled(false);
                    //btnDisparar.setDisabled(false);
                }

                if((boomerang.getX() >= enemigo.getX() && boomerang.getX()<= (enemigo.getX()+enemigo.getSprite().getWidth()))&&
                        (boomerang.getY() >= enemigo.getY() && boomerang.getY()<= (enemigo.getY()+enemigo.getSprite().getHeight()))){
                    enemigo.setPosicion(10,3000);
                }

                if((boomerang.getX() >= enemigo1.getX() && boomerang.getX()<= (enemigo1.getX()+enemigo1.getSprite().getWidth()))&&
                        (boomerang.getY() >= enemigo1.getY() && boomerang.getY()<= (enemigo1.getY()+enemigo1.getSprite().getHeight()))){
                    enemigo1.setPosicion(10,3000);
                }

                if((boomerang.getX() >= enemigo2.getX() && boomerang.getX()<= (enemigo2.getX()+enemigo2.getSprite().getWidth()))&&
                        (boomerang.getY() >= enemigo2.getY() && boomerang.getY()<= (enemigo2.getY()+enemigo2.getSprite().getHeight()))){
                    enemigo2.setPosicion(10,3000);
                }

                if((boomerang.getX() >= enemigo3.getX() && boomerang.getX()<= (enemigo3.getX()+enemigo3.getSprite().getWidth()))&&
                        (boomerang.getY() >= enemigo3.getY() && boomerang.getY()<= (enemigo3.getY()+enemigo3.getSprite().getHeight()))){
                    enemigo3.setPosicion(10,3000);
                }
                if(pinguino.getX() <= 0){
                    boomerang.setPosicion(-100,0);
                    boomerang.setBoom(Boomerang.boom.GUARDADO);
                }
            }

            enemigo.render(batch);
            enemigo1.render(batch);
            enemigo2.render(batch);
            enemigo3.render(batch);

            if(enemigo.getSprite().getX()>= 2240){
                enemigo.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo.getSprite().getX() <= 1850){
                enemigo.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo1.getSprite().getX()>= 6720){
                enemigo1.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo1.getSprite().getX() <= 6400){
                enemigo1.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo2.getSprite().getX()>= 4928){
                enemigo2.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo2.getSprite().getX() <= 4608){
                enemigo2.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo3.getSprite().getX()>= 10816){
                enemigo3.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo3.getSprite().getX() <= 10624){
                enemigo3.setEstadoEnemigo(mx.itesm.icecreamquest.Enemigos.EstadoEnemigo.DERECHA);
            }

            dardo.render(batch);
            dardo1.render(batch);
            dardo2.render(batch);
            dardo3.render(batch);
            //tiempo += Gdx.graphics.getDeltaTime();
            if((dardo.getX() >= pinguino.getX() && dardo.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo.getY() >= pinguino.getY() && dardo.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))) {
                vidas--;
                dardo.velocidadX = 0;
                if(Nivel1.ajusteSonido == false){
                    muere.play();
                }
                dardo.setPosicion(-100, 0);
                //  tiempo2 += Gdx.graphics.getDeltaTime();

                /*if( tiempo2 > 0) {
                    System.out.println("spy el tiempo2" + tiempo2);
                    pinguino.getSprite().setAlpha(0.5f);
                }
                //pinguino.getSprite().setAlpha(0.5f);
            }
            if(tiempo > 10 && tiempo2 > 0){
                System.out.println("soy el tiempo" + tiempo);
                pinguino.getSprite().setAlpha(1);
            }*/
            }
            if((dardo1.getX() >= pinguino.getX() && dardo1.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo1.getY() >= pinguino.getY() && dardo1.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo1.velocidadX = 0;
                if(Nivel1.ajusteSonido == false){
                    muere.play();
                }
                dardo1.setPosicion(-100,0);
                //pinguino.getSprite().setAlpha(0.5f);
            }

        //Gdx.app.log("boomerang",""+boomerang.getX()+"enemigo "+enemigo.getXEnemiga());
            if((dardo2.getX() >= pinguino.getX() && dardo2.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo2.getY() >= pinguino.getY() && dardo2.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo2.velocidadX = 0;
                if(Nivel1.ajusteSonido == false){
                    muere.play();
                }
                dardo2.setPosicion(-100,0);

                //pinguino.getSprite().setAlpha(0.5f);
            }

            if((dardo3.getX() >= pinguino.getX() && dardo3.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo3.getY() >= pinguino.getY() && dardo3.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo3.velocidadX = 0;
                if(Nivel1.ajusteSonido == false){
                    muere.play();
                }
                dardo3.setPosicion(-100,0);
                //pinguino.getSprite().setAlpha(0.5f);
            }

            if((pinguino.getX() >= enemigo.getX() && pinguino.getX()<= (enemigo.getX()+enemigo.getSprite().getWidth()))&&
                    (pinguino.getY() >= enemigo.getY() && pinguino.getY()<= (enemigo.getY()+enemigo.getSprite().getHeight()))){
                pinguino.puntos -=50;
            }
            if((pinguino.getX() >= enemigo1.getX() && pinguino.getX()<= (enemigo1.getX()+enemigo1.getSprite().getWidth()))&&
                    (pinguino.getY() >= enemigo1.getY() && pinguino.getY()<= (enemigo1.getY()+enemigo1.getSprite().getHeight()))){
                pinguino.puntos -=50;
            }
            if((pinguino.getX() >= enemigo2.getX() && pinguino.getX()<= (enemigo2.getX()+enemigo2.getSprite().getWidth()))&&
                    (pinguino.getY() >= enemigo2.getY() && pinguino.getY()<= (enemigo2.getY()+enemigo2.getSprite().getHeight()))){
                pinguino.puntos -=50;
            }
            if((pinguino.getX() >= enemigo3.getX() && pinguino.getX()<= (enemigo3.getX()+enemigo3.getSprite().getWidth()))&&
                    (pinguino.getY() >= enemigo3.getY() && pinguino.getY()<= (enemigo3.getY()+enemigo3.getSprite().getHeight()))){
                pinguino.puntos -=50;
            }
        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
            if(estadoJuego == EstadosJuego.GANO ){
                int scoreA = score.getInteger("Nivel1",0);
                if (pinguino.puntos > scoreA) {
                    score.clear();
                    score.putInteger("Nivel1",pinguino.puntos);
                    score.flush();
                }
                btnGanar.render(batch);
                btnSiguiente.render(batch);
                btnSiguiente.setPosicion(1100,0);
            }
            else if(estadoJuego == EstadosJuego.PAUSADO){
                fondoPausa.setPosicion(Juego.ancho/4,Juego.alto/16);
                pinguino.setEstadoMovimiento(mx.itesm.icecreamquest.Personaje.EstadoMovimiento.QUIETO);
                fondoPausa.draw(batch);
                dardo.velocidadX = 0;
                dardo1.velocidadX = 0;
                dardo2.velocidadX = 0;
                dardo3.velocidadX = 0;
                enemigo.velocidad = 0;
                enemigo1.velocidad = 0;
                enemigo2.velocidad = 0;
                enemigo3.velocidad = 0;
                if(boomerang != null){
                boomerang.velocidad = 0;}
                btnResumen.setPosicion(Juego.ancho/3,Juego.alto*0.45f);
                btnSalir.setPosicion(Juego.ancho/3,Juego.alto*0.15f);
                btnResumen.render(batch);
                btnSalir.render(batch);
            }
            else if(estadoJuego == EstadosJuego.PERDIO){
                    btnRegresar.render(batch);
                    btnRegresar.setPosicion(0,0);
                    btnPerdiste.render(batch);
            }
            else{
                btnSalto.render(batch);
                btnDisparar.render(batch);
                btnPausa.render(batch);
                btnScore.render(batch);
                texto.mostrarMensaje(batch," " + pinguino.puntos,150,Juego.alto * 0.93f);
                texto.mostrarMensaje(batch," " + vidas,150,Juego.alto * 0.85f);
            }
            puntaje = pinguino.puntos;
        batch.end();

    }

    private void actualizarCamara(){
        float posX = pinguino.getX();
        if(posX >= Juego.ancho/2 && posX <= ancho_mapa - Juego.ancho/2){
            camara.position.set((int)posX,camara.position.y,0);
        }
        else if(posX > ancho_mapa - Juego.ancho/2){
            camara.position.set(ancho_mapa-Juego.ancho/2, camara.position.y,0);
        }
        else if(posX < Juego.ancho/2){
            camara.position.set(Juego.ancho/2,Juego.alto/2,0);
        }
        camara.update();
    }

   private void moverPersonaje(){
        switch (pinguino.getEstadoMovimiento()) {
            /*case INICIANDO:
                int celdaX = (int)((pinguino.getSprite().getX())/celda);
                int celdaY = (int)((pinguino.getSprite().getY() + pinguino.velocidadY)/celda);
                TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(0);
                TiledMapTileLayer.Cell celda1 = capa.getCell(celdaX,celdaY);
                if(celda1 == null){
                    pinguino.caer();
                }
                else if(!esHelado(celda1) || !esHeladoEspecial(celda1)){
                    pinguino.setPosicion(pinguino.getX(),(celdaY+1)* celda);
                    pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                }
                break;*/
            case DER:
                moverHorizontal();
                break;
        }

        if(pinguino.getEstadoMovimiento() != mx.itesm.icecreamquest.Personaje.EstadoMovimiento.INICIANDO && (pinguino.getEstadoSalto() != mx.itesm.icecreamquest.Personaje.EstadoSalto.SUBIENDO)){
            int celdaX = (int) ((pinguino.getSprite().getX()+pinguino.getSprite().getWidth()/2)/tamcelda);
            int celdaY = (int) ((pinguino.getY()+pinguino.velocidadY)/ tamcelda);
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(0);
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX,celdaY);

            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX+1,celdaY);
            TiledMapTileLayer.Cell celdaAbajoAbajo = capa.getCell(celdaX,celdaY-1);
            TiledMapTileLayer.Cell celdaAbajoDerecha = capa.getCell(celdaX+1,celdaY-1);

            if((celdaAbajo == null && (celdaDerecha == null || celdaDerecha != null)) || esHelado(celdaAbajo) || esHelado(celdaDerecha)||esHeladoEspecial(celdaAbajo)||esHeladoEspecial(celdaDerecha)){
                pinguino.caer();
                pinguino.setEstadoSalto(mx.itesm.icecreamquest.Personaje.EstadoSalto.CAIDALIBRE);
                //pinguino.probarCaida(mapa);
            }
            else{
                pinguino.setPosicion(pinguino.getX(),(celdaY +1)*tamcelda);
                pinguino.setEstadoSalto(mx.itesm.icecreamquest.Personaje.EstadoSalto.ABAJO);
            }
            if(pinguino.getX() >= 12650){
                pinguino.velocidadX = 0;
                pinguino.setEstadoMovimiento(mx.itesm.icecreamquest.Personaje.EstadoMovimiento.QUIETO);
                pinguino.setPosicion(0,10000);
                estadoJuego = estadoJuego.GANO;
                dardo.velocidadX = 0;
                dardo1.velocidadX = 0;
                dardo2.velocidadX = 0;
                dardo3.velocidadX = 0;
                enemigo.velocidad = 0;
                int scoreA = score.getInteger("Nivel1",0);
                if (pinguino.puntos > scoreA) {
                    score.clear();
                    score.putInteger("Nivel1",pinguino.puntos);
                    score.flush();
                }
            }
            else if(pinguino.getY() <= 0){
                vidas --;
                pinguino.getSprite().setPosition(Juego.ancho/10,Juego.alto * 0.1f);
                camara.position.set(Juego.ancho/pinguino.getX(),camara.position.y,0);
                camaraHUD.position.set(Juego.ancho/2,camaraHUD.position.y,0);
                camara.update();
                camaraHUD.update();
                if(boomerang != null){
                boomerang.setPosicion(-1000,0);}
                btnDisparar.setPosicion(1100,Juego.alto * 0.01f);
                actualizarCamara();
            }
            if(vidas <= 0 || pinguino.puntos < 0){
                pinguino.velocidadX=0;
                estadoJuego = estadoJuego.PERDIO;
                pinguino.setPosicion(0,10000);
            }
        }
        switch (pinguino.getEstadoSalto()){
            case SUBIENDO:
            case BAJANDO:
                pinguino.actualizarSalto(mapa);
                break;
            //case BAJANDO:
            case CAIDALIBRE:
                pinguino.probarCaida(mapa);
        }

        pinguino.recolectarHelados(mapa,helado,heladoEspecial);
    }

    private void moverHorizontal(){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(0);
        float nuevaX = pinguino.getSprite().getX();
        if(pinguino.getEstadoMovimiento() == mx.itesm.icecreamquest.Personaje.EstadoMovimiento.DER){
            int x = (int)((pinguino.getSprite().getX()+64)/64);
            int y = (int)(pinguino.getSprite().getY()/64);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(x,y);
            if(celdaDerecha != null){
                pinguino.timerAnimacion=0;
                //pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                Object tipo = celdaDerecha.getTile().getProperties().get("tipo");
                if(!"esCuadroPiso".equals(tipo)){
                    celdaDerecha = null;
                }
            }
            if(celdaDerecha == null){
                //pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
                nuevaX += pinguino.velocidadX;
                //if(nuevaX <= Juego.ancho - pinguino.getSprite().getWidth()){
                    pinguino.getSprite().setX(nuevaX);
                    //pinguino.caer();
                    //pinguino.probarCaida(mapa);
                }
            }

    }

    private boolean esHelado(TiledMapTileLayer.Cell cell) {
        if(cell == null){
            return  false;
        }
        Object propiedad = cell.getTile().getProperties().get("tipo");
        return "helado".equals(propiedad);
    }

    private boolean esHeladoEspecial(TiledMapTileLayer.Cell cell){
        if(cell == null){
            return false;
        }
        Object propiedad = cell.getTile().getProperties().get("tipo");
        return "heladoespecial".equals(propiedad);
    }

    private boolean esCuadroPiso(TiledMapTileLayer.Cell cell) {
        if(cell == null){
            return false;
        }
        Object propiedad = cell.getTile().getProperties().get("tipo");
        return "cuadroPiso".equals(propiedad);
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
        vistaHUD.update(width, height);
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
        juego.getManager().unload("Fondo64.tmx");
        juego.getManager().unload("PinguinoChido2.png");
        juego.getManager().unload("BtnBoom.png");
        juego.getManager().unload("SpriteBoom.png");
        juego.getManager().unload("BtnArriba.png");
        juego.getManager().unload("Ganaste_1.png");
        juego.getManager().unload("BtnPausa.png");
        juego.getManager().unload("Pausa.png");
        juego.getManager().unload("SpriteZookDerecha.png");
        juego.getManager().unload("SpriteZookIzquierda.png");
        juego.getManager().unload("FondonivelLoop.png");
        juego.getManager().unload("FondonivelLoop2.png");
        juego.getManager().unload("Salida.png");
        juego.getManager().unload("CuadroScore.png");
        juego.getManager().unload("dardo.png");
        juego.getManager().unload("Saltar.png");
        juego.getManager().unload("BTN_Resumen.png");
        juego.getManager().unload("BTN_Salir.png");
        juego.getManager().unload("Perdiste_1.png");
        juego.getManager().unload("Nivel_1.mp3");
        juego.getManager().unload("Helado especial.mp3");
        juego.getManager().unload("Helado normal.mp3");
        juego.getManager().unload("Meeehhpp!!.wav");
        juego.getManager().unload("Walkgud_IZQ.png");
        juego.getManager().unload("SaltarIZQ.png");
        juego.getManager().unload("botonSiguiente.png");
        juego.getManager().unload("botonRegresar.png");
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
            transformarCoordenadas(screenX,screenY);
            if(btnSalto.contiene(x,y)){
                pinguino.saltar();
            }
            else if(btnDisparar.contiene(x,y)){
                boomerang = new Boomerang(texturaBoomeran);
                boomerang.setPosicion(pinguino.getX(),(int)pinguino.getY());
                boomerang.salir();
                btnDisparar.setPosicion(0,-100);
            }

            else if(btnPausa.contiene(x,y)){
                estadoJuego = estadoJuego.PAUSADO;

            }
            else if(btnResumen.contiene(x,y)){
                estadoJuego = estadoJuego.JUGANDO;
                pinguino.setEstadoMovimiento(mx.itesm.icecreamquest.Personaje.EstadoMovimiento.DER);
                fondoPausa.setPosicion(-13444,-12435);
                dardo.velocidadX = -2;
                dardo1.velocidadX = -2;
                dardo2.velocidadX = -2;
                dardo3.velocidadX = -2;
                enemigo.velocidad = 2;
                enemigo1.velocidad = 2;
                enemigo2.velocidad = 2;
                enemigo3.velocidad = 2;
            }
            else if(btnSalir.contiene(x,y)){
                juego.setScreen(new MenuPrincipal(juego));
                musica.setVolume(0);
                musica.stop();
            }
            if(estadoJuego == EstadosJuego.PERDIO && btnRegresar.contiene(x,y)){
                juego.setScreen(new mx.itesm.icecreamquest.PantallaCargando(juego));
                musica.stop();
            }
            if(estadoJuego == EstadosJuego.GANO && btnSiguiente.contiene(x,y)){
                juego.setScreen(new PantallaInstrucciones2(juego));
                musica.setVolume(0);
                musica.stop();
            }
            return true;
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

    private void transformarCoordenadas(int screenX, int screenY){
            coordenadas.set(screenX, screenY,0);
            camaraHUD.unproject(coordenadas);
            x = coordenadas.x;
            y = coordenadas.y;
        }


    private void borrarPantalla(){
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


}
