package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * Created by Mario Lagunes on 26/09/2016.
 */
public class Nivel1 implements Screen {
    public static final float ancho_mapa = 12800;
    private Juego juego;
    private Music musica;
    private OrthographicCamera camara;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;
    private Viewport vista;
    private SpriteBatch batch;
    private Texture texuturaPersonaje;
    private Personaje pinguino;
    private OrthographicCamera camaraHUD;
    private StretchViewport vistaHUD;
    private EstadosJuego estadoJuego;
    private Texto texto;
    private Texture texturaSalto,texturaBoomeran,texturaGano,texturaDisparo,texturaPausa,texturaPausado,texturaEnemigo,texturaFondo1,texturaFondo2,texturaFondo3,texturaScore,texturaDardo,
            texturaSal,texturaQui,texturaSalir,texturaResumen,texturaPerdiste,texturaEnemigoReg;
    private Boton btnSalto,btnDisparar,btnGanar,btnPausa,btnResumen,btnScore,btnSalir,btnPerdiste;
    private int heladosRecolectados = 0;
    private int vidas = 5;
    private static final int celda = 64;
    private Boomerang boomerang;
    private Enemigos enemigo,enemigo1,enemigo2,enemigo3;
    //private Personaje enemigo1,enemigo2,enemigo3,enemigo4,enemigo5;
    private Fondo fondo,fondo2,fondo3,fondoPausa;
    private Dardos dardo,dardo1,dardo2,dardo3;
    private Sound muere,helado,heladoEspecial;

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
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
        estadoJuego = EstadosJuego.JUGANDO;
        texto = new Texto();
        pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
        enemigo.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
        enemigo1.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
        enemigo2.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
        enemigo3.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
        musica.setLooping(true);
        musica.setVolume(1.5f);
        musica.play();
    }

    private void cargarTexturas() {
        AssetManager manager = juego.getManager();
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
        manager.finishLoading();
    }

    private void crearObjetos(){
        AssetManager manager = juego.getManager();
        mapa = manager.get("Fondo64.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
        rendererMapa.setView(camara);
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,1.1f);
        pinguino.getSprite().setPosition(0,128);
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
        fondoPausa = new Fondo(texturaPausado);
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
        enemigo = new Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo1 = new Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo2 = new Enemigos(texturaEnemigo,texturaEnemigoReg);
        enemigo3 = new Enemigos(texturaEnemigo,texturaEnemigoReg);
        //enemigo3 = new Personaje(texturaEnemigo,texturaEnemigoReg);
        //enemigo4 = new Personaje(texturaEnemigo,texturaEnemigoReg);
        //enemigo2 = new Personaje(texturaEnemigo);
        //enemigo3 = new Personaje(texturaEnemigo);
        //enemigo4 = new Personaje(texturaEnemigo);
        // enemigo5 = new Personaje(texturaEnemigo);
        enemigo.setPosicion(1850,128);
        enemigo1.setPosicion(6080,704);
        enemigo2.setPosicion(4608,576);
        enemigo3.setPosicion(10560,128);
        //enemigo.getSprite().setPosition(300,Juego.alto*0.06f);
        //enemigo1.getSprite().setPosition(400,Juego.alto*0.06f);
        //enemigo2.getSprite().setPosition(600,Juego.alto*0.06f);
        //enemigo3.getSprite().setPosition(700,Juego.alto*0.06f);
        //enemigo4.getSprite().setPosition(800,Juego.alto*0.06f);
        //enemigo5.getSprite().setPosition(3000,Juego.alto*0.06f);
        texturaFondo1 = manager.get("FondonivelLoop.png");
        texturaFondo2 = manager.get("FondonivelLoop.png");
        texturaFondo3 = manager.get("Salida.png");
        fondo = new Fondo(texturaFondo1);
        fondo2 = new Fondo(texturaFondo2);
        fondo3 = new Fondo(texturaFondo3);
        texturaScore = manager.get("CuadroScore.png");
        btnScore = new Boton(texturaScore);
        btnScore.setPosicion(0,Juego.alto*0.7f);
        texturaDardo = manager.get("dardo.png");
        dardo = new Dardos(texturaDardo);
        dardo1 = new Dardos(texturaDardo);
        dardo2 = new Dardos(texturaDardo);
        dardo3 = new Dardos(texturaDardo);
        dardo.setPosicion(enemigo.getX(),enemigo3.getY());
        dardo1.setPosicion(enemigo1.getX(),enemigo1.getY());
        dardo2.setPosicion(enemigo2.getX(),enemigo2.getY());
        dardo3.setPosicion(enemigo3.getX(),enemigo3.getY());
        musica = manager.get("Nivel_1.mp3");
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
                if(boomerang.tiempo >= 50){
                    boomerang.setBoom(Boomerang.boom.REGRESANDO);
                    //if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && )
                }
                if(boomerang.getBoom() == Boomerang.boom.REGRESANDO && (boomerang.getX() >= pinguino.getX() && boomerang.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                        (boomerang.getY() >= pinguino.getY() && boomerang.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                    boomerang.setBoom(Boomerang.boom.GUARDADO);
                    boomerang.setPosicion(-1000,0);
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
                enemigo.setEstadoEnemigo(Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo.getSprite().getX() <= 1850){
                enemigo.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo1.getSprite().getX()>= 6592){
                enemigo1.setEstadoEnemigo(Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo1.getSprite().getX() <= 6080){
                enemigo1.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo2.getSprite().getX()>= 4928){
                enemigo2.setEstadoEnemigo(Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo2.getSprite().getX() <= 4608){
                enemigo2.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
            }
            if(enemigo3.getSprite().getX()>= 10816){
                enemigo3.setEstadoEnemigo(Enemigos.EstadoEnemigo.IZQUIERDA);
            }
            else if(enemigo3.getSprite().getX() == 10560){
                enemigo3.setEstadoEnemigo(Enemigos.EstadoEnemigo.DERECHA);
            }

            dardo.render(batch);
            dardo1.render(batch);
            dardo2.render(batch);
            if((dardo.getX() >= pinguino.getX() && dardo.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo.getY() >= pinguino.getY() && dardo.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo.velocidadX = 0;
                muere.play();
                dardo.setPosicion(0,3000);
            }

        //Gdx.app.log("boomerang",""+boomerang.getX()+"enemigo "+enemigo.getXEnemiga());
            if((dardo2.getX() >= pinguino.getX() && dardo2.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo2.getY() >= pinguino.getY() && dardo2.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo2.velocidadX = 0;
                muere.play();
                dardo2.setPosicion(0,3000);
            }
            if((dardo1.getX() >= pinguino.getX() && dardo1.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo1.getY() >= pinguino.getY() && dardo1.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo1.velocidadX = 0;
                muere.play();
                dardo1.setPosicion(0,3000);
            }
            if((dardo3.getX() >= pinguino.getX() && dardo3.getX()<= (pinguino.getX()+pinguino.getSprite().getWidth()))&&
                    (dardo3.getY() >= pinguino.getY() && dardo3.getY()<= (pinguino.getY()+pinguino.getSprite().getHeight()))){
                vidas--;
                dardo3.velocidadX = 0;
                muere.play();
                dardo3.setPosicion(0,3000);
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
                btnGanar.render(batch);
            }
            else if(estadoJuego == EstadosJuego.PAUSADO){
                fondoPausa.setPosicion(Juego.ancho/4,Juego.alto/16);
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                fondoPausa.draw(batch);
                dardo.velocidadX = 0;
                dardo1.velocidadX = 0;
                dardo2.velocidadX = 0;
                dardo3.velocidadX = 0;
                enemigo.velocidad = 0;
                enemigo1.velocidad = 0;
                enemigo2.velocidad = 0;
                enemigo3.velocidad = 0;
                btnResumen.setPosicion(Juego.ancho/3,Juego.alto*0.45f);
                btnSalir.setPosicion(Juego.ancho/3,Juego.alto*0.15f);
                btnResumen.render(batch);
                btnSalir.render(batch);
            }
            else if(estadoJuego == EstadosJuego.PERDIO){
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
        switch (pinguino.getEstadoMovimiento()){
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
        if(pinguino.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO &&
                (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)){
            int celdaX = (int) ((pinguino.getSprite().getX())/celda);
            int celdaY = (int) ((pinguino.getY()+pinguino.velocidadY) / celda);
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(0);
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX,celdaY);
            /*if(celdaAbajo != null){
                Object tipo = celdaAbajo.getTile().getProperties().get("tipo");
                if(!"esCuadroPiso".equals(tipo)){
                    celdaAbajo = null;
                }
            }*/
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX+1,celdaY);
            /*if(celdaDerecha != null){
                Object tipo = celdaDerecha.getTile().getProperties().get("tipo");
                if(!"esCuadroPiso".equals(tipo)){
                    celdaDerecha = null;
                }
            }*/
            if((celdaAbajo == null && celdaDerecha == null) || esHelado(celdaAbajo) || esHelado(celdaDerecha)||esHeladoEspecial(celdaAbajo)||esHeladoEspecial(celdaDerecha)){
                pinguino.caer();
                pinguino.setEstadoSalto(Personaje.EstadoSalto.CAIDALIBRE);
            }
            else if(esCuadroPiso(celdaAbajo) || esCuadroPiso(celdaDerecha)){
                pinguino.setPosicion(pinguino.getSprite().getX(),(celdaY+1)* celda);
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
            else if((celdaAbajo == null && celdaDerecha != null)|| esHelado(celdaAbajo) || esHelado(celdaDerecha)||esHeladoEspecial(celdaAbajo)||esHeladoEspecial(celdaDerecha)){
                pinguino.caer();
            }
            else{
                pinguino.setPosicion(pinguino.getX(),(celdaY +1)*celda);
                pinguino.setEstadoSalto(Personaje.EstadoSalto.ABAJO);

            }
            if(pinguino.getX() >= 12650){
                pinguino.velocidadX = 0;
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                pinguino.setPosicion(0,10000);
                estadoJuego = estadoJuego.GANO;
            }
            else if(pinguino.getY() <= 0){
                vidas --;
                pinguino.getSprite().setPosition(Juego.ancho/10,Juego.alto * 0.1f);
                camara.position.set(Juego.ancho/pinguino.getX(),camara.position.y,0);
                camaraHUD.position.set(Juego.ancho/2,camaraHUD.position.y,0);
                camara.update();
                camaraHUD.update();
                actualizarCamara();
            }
            if(vidas <= 0 || pinguino.puntos < 0){
                pinguino.velocidadX=0;
                estadoJuego = estadoJuego.PERDIO;
                pinguino.setPosicion(0,10000);
            }
        }
        switch (pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto(mapa);
                break;
        }

        pinguino.recolectarHelados(mapa,helado,heladoEspecial);
    }

    private void moverHorizontal(){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(0);
        float nuevaX = pinguino.getSprite().getX();
        if(pinguino.getEstadoMovimiento() == Personaje.EstadoMovimiento.DER){
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
                //}
            }
        }
    }




    /*private void probarColisiones() {
        Personaje.EstadoMovimiento estado = pinguino.getEstadoMovimiento();
        if(estado != Personaje.EstadoMovimiento.DER){
            return;
        }
        float px = pinguino.getSprite().getX();
        //px = pinguino.getEstadoMovimiento() == Personaje.EstadoMovimiento.DER? px + Personaje.velocidadX:
          //  px-Personaje.velocidadX;
        int celdaX = (int)((px+128)/celda);
        if(pinguino.getEstadoMovimiento() == Personaje.EstadoMovimiento.DER){
            celdaX++;
        }
        int celdaY = (int)(pinguino.getSprite().getY()/celda);
        TiledMapTileLayer capaPlataforma1 = (TiledMapTileLayer) mapa.getLayers().get(1);
        if(capaPlataforma1.getCell(celdaX,celdaY) != null || capaPlataforma1.getCell(celdaX,celdaY+1) != null){
            if(esHelado(capaPlataforma1.getCell(celdaX,celdaY))){
                heladosRecolectados +=500;
                capaPlataforma1.setCell(celdaX,celdaY,null);
            }
            else if(esHeladoEspecial(capaPlataforma1.getCell(celdaX,celdaY))){
                heladosRecolectados += 1000;
                capaPlataforma1.setCell(celdaX,celdaY,null);
            }
            else {
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
        }
        else{
            pinguino.actualizar();
        }
        /*TiledMapTileLayer capaPlatadorma2 = (TiledMapTileLayer)mapa.getLayers().get(0);
        if(capaPlatadorma2.getCell(celdaX,celdaY) != null || capaPlatadorma2.getCell(celdaX,celdaY+1) != null){
            if(esCuadroPiso(capaPlatadorma2.getCell(celdaX,celdaY))){
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
        }
        else{
            pinguino.actualizar();
        }*/


    //}

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
        AssetManager manager1 = juego.getManager();
        manager1.unload("Fondo64.tmx");
        manager1.unload("PinguinoChido2.png");
        manager1.unload("BtnBoom.png");
        manager1.unload("SpriteBoom.png");
        manager1.unload("BtnArriba.png");
        manager1.unload("Ganaste_1.png");
        manager1.unload("BtnPausa.png");
        manager1.unload("Pausa.png");
        manager1.unload("SpriteZookDerecha.png");
        manager1.unload("SpriteZookIzquierda.png");
        manager1.unload("FondonivelLoop.png");
        manager1.unload("FondonivelLoop2.png");
        manager1.unload("Salida.png");
        manager1.unload("CuadroScore.png");
        manager1.unload("dardo.png");
        manager1.unload("Saltar.png");
        manager1.unload("BTN_Resumen.png");
        manager1.unload("BTN_Salir.png");
        manager1.unload("Perdiste_1.png");
        manager1.unload("Nivel_1.mp3");
    }

    public class ProcesadorEntrada extends InputAdapter{
        private Vector3 coordenadas = new Vector3();
        private float x,y;

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

            }

            else if(btnPausa.contiene(x,y)){
                estadoJuego = estadoJuego.PAUSADO;

            }
            else if(btnResumen.contiene(x,y)){
                estadoJuego = estadoJuego.JUGANDO;
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
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
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        juego.setScreen(new MenuPrincipal(juego));
                    }
                },1);
                musica.setVolume(0);
                musica.stop();
            }
            else if(btnPerdiste.contiene(x,y)){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        juego.setScreen(new MenuPrincipal(juego));
                    }
                },1);
                musica.setVolume(0);
                musica.stop();
            }
            else if(estadoJuego == EstadosJuego.GANO){
                if(btnGanar.contiene(x,y)){
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            juego.setScreen(new Nivel2(juego));
                    }
                    },1);
                }
                musica.setVolume(0);
                musica.stop();
            }

            return true;
        }

        private void transformarCoordenadas(int screenX, int screenY){
            coordenadas.set(screenX, screenY,0);
            camaraHUD.unproject(coordenadas);
            x = coordenadas.x;
            y = coordenadas.y;
        }

    }

    private void borrarPantalla(){
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


}
