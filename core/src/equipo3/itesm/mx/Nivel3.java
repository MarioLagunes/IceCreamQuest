package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
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
    private EstadosJuego estadoJuego;
    private Sprite sprite,marcador;
    private Boomerang boomerang;
    private OrthographicCamera camara;
    private Viewport vista;
    private StretchViewport vistaHUD;
    private OrthographicCamera camaraHUD;
    private OrthogonalTiledMapRenderer rendererMapa;
    private SpriteBatch batch;
    private Personaje pinguino;
    private Fondo fondo1, fondo2,fondo3, fondo4,fondoPausa;
    private Texture fondo,fondoFin, fondoLopp, fondoUltimo, texuturaPersonaje ,texturaSal,texturaSalto,texturaDisparo,texturaIzquierda, texturaDerecha,
    texturaBoomeran,textIzq,textPinSalIzq,texturaPausa,texturaPausado,texturaResumen,texturaPerdiste,texturaSalir,texturaScore;
    private TiledMap mapa;
    private static final int celda = 64;
    private static final float ancho = 800;
    private static final float alto = 1280;
    public static final float alto_mapa = 3840;
    private Boton btnSalto,btnDisparar,btnDer,btnIzq,btnPausa,btnResumen,btnSalir,btnRegresar;
    private EstadosJuego estadosJuego;
    private int velocidadX = 5, velocidadY = -5,velocidadItemY = -5,velocidadPosteX = 5,velocidadPosteY = -3,velocidadPinguino = 10;
    public static final int TAM_CELDA = 16;
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 2;
    private Vector3 coordenadas = new Vector3();
    private float x,y;

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

    private  void cargarObjetos(){
        AssetManager manager = juego.getManager();
        fondo = manager.get("Fondo3.png");
        fondoFin = manager.get("Fondo3fin.png");
        fondoLopp = manager.get("Fondo3loop.png");
        fondoUltimo = manager.get("Fondo3ultima.png");

        mapa = manager.get("Mapanivel3.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
        rendererMapa.setView(camara);

        //Pinguino
        /*texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,textIzq);
        pinguino.getSprite().setPosition(0,128);
        pinguino.getSprite().setAlpha(1);
        ;*/

        fondo1 = new Fondo(fondo);
        fondo2 = new Fondo(fondoFin);
        fondo3 = new Fondo(fondoLopp);
        fondo4 = new Fondo(fondoUltimo);

        fondo1.setPosicion(0,0);
        fondo2.setPosicion(0,1280);
        fondo3.setPosicion(0,2560);
        fondo4.setPosicion(0,3840);

        //Pinguino
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        textIzq = manager.get("Walkgud_IZQ.png");
        textPinSalIzq = manager.get("SaltarIZQ.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,textIzq,textPinSalIzq);
        pinguino.getSprite().setPosition(500,60);

        texturaBoomeran = manager.get("SpriteBoom_ver.png");
        boomerang = new Boomerang(texturaBoomeran,0);

        //botones
        texturaSalto = manager.get("BtnArriba.png");
        texturaDisparo = manager.get("BtnBoom.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(690,-20);
        //btnSalto.setPosicion(ancho/2,alto/2);
        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(690,90);
        texturaDerecha = manager.get("BtnDerecha.png");
        texturaIzquierda = manager.get("BtnIzquierda.png");
        btnDer = new Boton(texturaDerecha);
        btnDer.setPosicion(150,-20);
        btnIzq = new Boton(texturaIzquierda);
        btnIzq.setPosicion(-20,-20);

        texturaPausa = manager.get("BtnPausa.png");
        btnPausa = new Boton((texturaPausa));
        btnPausa.setPosicion(690,1150);
        texturaPausado = manager.get("Pausa.png");
        fondoPausa = new Fondo(texturaPausado);
        texturaResumen = manager.get("BTN_Resumen.png");
        btnResumen = new Boton(texturaResumen);
        texturaSalir = manager.get("BTN_Salir.png");
        btnSalir = new Boton(texturaSalir);

        texturaScore = manager.get("CuadroScore.png");
        marcador = new Sprite(texturaScore);
        marcador.setPosition(0,1050);


    }
    @Override
    public void show() {
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
        PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);

        camara = camara1.crearCamaraNivel3(camara);
        camara.rotate(90);
        camaraHUD = camaraHUD1.crearCamaraNivel3(camaraHUD);
        camaraHUD.rotate(90);
        //vistaHUD = vistaHUD1.crearVistaHUDNivel33(camaraHUD,vistaHUD);


        batch = new SpriteBatch();
        vista = new FitViewport(alto,ancho,camara);
        vistaHUD = new StretchViewport(alto,ancho,camaraHUD);

        Gdx.input.setInputProcessor(this);

        //crearTexturas();
        cargarObjetos();
        estadosJuego = EstadosJuego.JUGANDO;

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
        batch.setProjectionMatrix(camaraHUD.combined);
        rendererMapa.setView(camara);
        //rendererMapa.setView(camaraHUD);

         batch.begin();
            fondo1.draw(batch);
            fondo2.draw(batch);
            fondo3.draw(batch);
            fondo4.draw(batch);
        batch.end();
        rendererMapa.render();

        batch.begin();
            pinguino.render(batch);
        batch.end();

        batch.begin();
            if(estadoJuego == EstadosJuego.PAUSADO){
                fondoPausa.setPosicion(Juego.alto/16,Juego.ancho/4);
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                fondoPausa.draw(batch);
                btnResumen.setPosicion(Juego.ancho/7.6f,Juego.ancho/2);
                btnSalir.setPosicion(Juego.ancho/7.6f,Juego.alto/2);
                btnResumen.render(batch);
                btnSalir.render(batch);
            }
            else if(estadoJuego == EstadosJuego.PERDIO){
                btnRegresar.render(batch);
                btnRegresar.setPosicion(0,0);
            }
            else{
                btnSalto.render(batch);
                btnDisparar.render(batch);
                btnIzq.render(batch);
                btnDer.render(batch);
                btnPausa.render(batch);
                marcador.draw(batch);
            }


        batch.end();




    }
    private void actualizarCamara(){
        float PosY = pinguino.getY();
        if(PosY >= Juego.alto/2 && PosY <= alto_mapa - Juego.alto/2 ){
            camara.position.set(camara.position.x,(int)PosY,0);
        }
        else if (PosY > alto_mapa - Juego.alto/2){
            camara.position.set(camara.position.x,alto_mapa-Juego.alto/2,0);
        }
        else if (PosY < Juego.alto/2){
            camara.position.set(Juego.ancho/2,Juego.alto/2,0);

        }
        camara.update();
    }



    public void moverPersonaje() {
        switch (pinguino.getEstadoMovimiento()) {
            case DER:
                float x = 0;
                if (pinguino.getX() >= 745) {
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
            if((celdaAbajo == null && celdaDerecha == null)){
                pinguino.caer();
                pinguino.setEstadoSalto(Personaje.EstadoSalto.CAIDALIBRE);
            }
            else if((celdaAbajo == null && celdaDerecha != null)){
                pinguino.caer();
            }
            else{
                pinguino.setPosicion(pinguino.getX(),(celdaY +1)*celda);
                pinguino.setEstadoSalto(Personaje.EstadoSalto.ABAJO);
            }
        }
        switch(pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto();
                break;
        }
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

    }

    @Override
    public void dispose() {

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
            boomerang = new Boomerang(texturaBoomeran);
            boomerang.setPosicion(pinguino.getX(),(int)pinguino.getY());
            boomerang.salir();
            //btnDisparar.setPosicion(0,-100);

        }
        else if (btnDer.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
        }
        else if (btnIzq.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.IZQ);
        }
        else if(btnPausa.contiene(x,y)){
            estadoJuego = estadoJuego.PAUSADO;

        }
        else if(btnResumen.contiene(x,y)){
            estadoJuego = estadoJuego.JUGANDO;
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            fondoPausa.setPosicion(-13444,-12435);}
        else if(btnSalir.contiene(x,y)){
            juego.setScreen(new MenuPrincipal(juego));
        }


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
