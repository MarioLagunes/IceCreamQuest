package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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

/**
 * Created by andrescalvavalencia on 08/11/16.
 */

public class Nivel3 implements Screen {
    private Juego juego;
    private Sprite sprite;
    private OrthographicCamera camara;
    private Viewport vista;
    private StretchViewport vistaHUD;
    private OrthographicCamera camaraHUD;
    private OrthogonalTiledMapRenderer rendererMapa;
    private SpriteBatch batch;
    private Personaje pinguino;
    private Fondo fondo1, fondo2,fondo3, fondo4;
    private Texture fondo,fondoFin, fondoLopp, fondoUltimo, texuturaPersonaje ,texturaSal,texturaSalto,texturaDisparo,texturaIzquierda, texturaDerecha,
    texturaBoomeran,textIzq,textPinSalIzq;
    private TiledMap mapa;
    private static final float ancho = 800;
    private static final float alto = 1280;
    private Boton btnSalto,btnDisparar,btnDer,btnIzq;
    private EstadosJuego estadosJuego;
    private int velocidadX = 5, velocidadY = -5,velocidadItemY = -5,velocidadPosteX = 5,velocidadPosteY = -3,velocidadPinguino = 10;
    public static final int TAM_CELDA = 16;
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 2;

    public Nivel3(Juego juego){
        this.juego = juego;
    }

    private void crearTexturas(){
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
        manager.finishLoading();
    }

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

        //botones
        texturaSalto = manager.get("BtnArriba.png");
        texturaDisparo = manager.get("BtnBoom.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(690,-20);
        //btnSalto.setPosicion(ancho/2,alto/2);
        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(690,70);
        texturaDerecha = manager.get("BtnDerecha.png");
        texturaIzquierda = manager.get("BtnIzquierda.png");
        btnDer = new Boton(texturaDerecha);
        btnDer.setPosicion(90,-20);
        btnIzq = new Boton(texturaIzquierda);
        btnIzq.setPosicion(-20,-20);

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
        vistaHUD = vistaHUD1.crearVistaHUDNivel33(camaraHUD,vistaHUD);


        batch = new SpriteBatch();
        vista = new FitViewport(alto,ancho,camara);
        vistaHUD = new StretchViewport(alto,ancho,camaraHUD);

        Gdx.input.setInputProcessor(new ProcesadorEntrada());

        crearTexturas();
        cargarObjetos();
        estadosJuego = EstadosJuego.JUGANDO;

        pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);




    }

    @Override
    public void render(float delta) {
        if(estadosJuego != EstadosJuego.PERDIO){
            moverPersonaje();
        }
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        //batch.setProjectionMatrix(camaraHUD.combined);
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
            btnDisparar.render(batch);
            btnSalto.render(batch);
            btnIzq.render(batch);
            btnDer.render(batch);
        batch.end();



        batch.begin();
            pinguino.renderNivel3(batch);
        batch.end();




    }

   /* public void actualizar(){
        float nuevaX = sprite.getX();
        switch (pinguino.getEstadoMovimiento()){
            case DER:
                nuevaX +=
        }
    }*/

    public void moverPersonaje(){
        switch (pinguino.getEstadoMovimiento()){
            case DER:
                float x = 0;
                x = pinguino.getSprite().getX()+velocidadPinguino;
                pinguino.getSprite().setX(x);
                break;
            case IZQ:
                float x1 = 0;
                x1 = pinguino.getSprite().getX()-velocidadPinguino;
                pinguino.getSprite().setX(x1);
                break;
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

// hacer funcionar botones
public class ProcesadorEntrada extends InputAdapter{
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        transformarCoordenadas(screenX, screenY);
        if (btnSalto.contiene(x, y)) {
            pinguino.saltar();
        }

        if (btnDer.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
        }
        if (btnIzq.contiene(x,y)){
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.IZQ);
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
}
