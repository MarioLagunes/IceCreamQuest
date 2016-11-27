package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Musica musicaNivel3;
    private EstadosJuego estadoJuego;
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
            texturaBoomeran,textIzq,textPinSalIzq,texturaPausa,texturaPausado,texturaResumen,texturaPerdiste,texturaSalir,texturaScore;
    private TiledMap mapa;
    private static final int celda = 64;
    private static final float ancho = 896;
    private static final float alto = 1280;
    public static final float alto_mapa = 12800;
    private Boton btnSalto,btnDisparar,btnDer,btnIzq,btnPausa,btnResumen,btnSalir,btnRegresar;
    private EstadosJuego estadosJuego;
    private int velocidadX = 5, velocidadY = -5,velocidadItemY = -5,velocidadPosteX = 5,velocidadPosteY = -3,velocidadPinguino = 10;
    public static final int TAM_CELDA = 16;
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 2;
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    public static boolean ajusteSonido = false;
    public static boolean ajuste = false;
    private Sound musicaTopping;
    private Texto texto;
    private int vidas = 5;
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

        fondo1.setPosicion(0,0);
        fondo2.setPosicion(0,3000);
        fondo22.setPosicion(0,6000);
        fondo3.setPosicion(0,9000);
        fondo4.setPosicion(0,12000);

        //Pinguino
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        textIzq = manager.get("Walkgud_IZQ.png");
        textPinSalIzq = manager.get("SaltarIZQ.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,textIzq,textPinSalIzq);
        pinguino.getSprite().setPosition(500,60);

        texturaBoomeran = manager.get("SpriteBoom_ver.png");
        //boomerang = new Boomerang(texturaBoomeran,0);

        //botones
        texturaSalto = manager.get("BtnArriba.png");
        texturaDisparo = manager.get("BtnBoom.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(740,-20);
        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(740,90);
        texturaDerecha = manager.get("BtnDerecha.png");
        texturaIzquierda = manager.get("BtnIzquierda.png");
        btnDer = new Boton(texturaDerecha);
        btnDer.setPosicion(150,-20);
        btnIzq = new Boton(texturaIzquierda);
        btnIzq.setPosicion(20,-20);

        texturaPausa = manager.get("BtnPausa.png");
        btnPausa = new Boton((texturaPausa));
        btnPausa.setPosicion(740,1150);
        texturaPausado = manager.get("Pausa.png");
        fondoPausa = new Fondo(texturaPausado);
        texturaResumen = manager.get("BTN_Resumen.png");
        btnResumen = new Boton(texturaResumen);
        texturaSalir = manager.get("BTN_Salir.png");
        btnSalir = new Boton(texturaSalir);

        texturaScore = manager.get("CuadroScore.png");
        marcador = new Sprite(texturaScore);
        marcador.setPosition(20,1050);
        musicaTopping = manager.get("Toppings.wav");
        musica = manager.get("Nivel3.mp3");
        musicaNivel3 = new Musica(musica,true,ajuste);

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

            /*if((boomerang.getX() >= enemigo.getX() && boomerang.getX()<= (enemigo.getX()+enemigo.getSprite().getWidth()))&&
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
            }*/
            if(pinguino.getX() <= 0){
                boomerang.setPosicion(-100,0);
                boomerang.setBoom(Boomerang.boom.GUARDADO);
            }
        }
        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
        if(estadoJuego == EstadosJuego.PAUSADO){
            fondoPausa.setPosicion(Juego.alto/8,Juego.ancho/4);
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            fondoPausa.draw(batch);
            btnResumen.setPosicion(Juego.ancho/6.5f,Juego.ancho/2);
            btnSalir.setPosicion(Juego.ancho/6.5f,Juego.alto/2);
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

            if(celdaIzq == null && celdaAbajo == null && celdaDerecha == null){
                pinguino.caer();
                pinguino.setEstadoSalto(Personaje.EstadoSalto.CAIDALIBRE);

            }
            else if (celdaIzq!= null && celdaAbajo == null ){
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
        pinguino.recolectarToppings(mapa,musicaTopping);
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
        juego.getManager().unload("BtnArriba.png");
        juego.getManager().unload("BtnBoom.png");
        juego.getManager().unload("BtnDerecha.png");
        juego.getManager().unload("BtnIzquierda.png");
        juego.getManager().unload("PinguinoChido2.png");
        juego.getManager().unload("Saltar.png");
        juego.getManager().unload("SaltarIZQ.png");
        juego.getManager().unload("Walkgud_IZQ.png");
        juego.getManager().unload("SpriteBoom_ver.png");
        juego.getManager().unload("BTN_Resumen.png");
        juego.getManager().unload("BTN_Salir.png");
        juego.getManager().unload("BtnPausa.png");
        juego.getManager().unload("Pausa.png");
        juego.getManager().unload("CuadroScore.png");
        juego.getManager().unload("Toppings.wav");
        juego.getManager().unload("Nivel3.mp3");
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
