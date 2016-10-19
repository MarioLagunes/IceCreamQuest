package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Mario Lagunes on 26/09/2016.
 */
public class Nivel1 implements Screen {
    public static final float ancho_mapa = 12000;
    private Juego juego;
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
    private Texture texturaSalto,texturaBoomeran,texturaGano,texturaDisparo,texturaPausa,texturaPausado,texturaEnemigo,texturaFondo1,texturaFondo2,texturaFondo3,texturaScore;
    private Boton btnSalto,btnDisparar,btnGanar,btnPausa,btnPausado,btnScore;
    private int heladosRecolectados = 0;
    private int vidas = 3;
    private static final int celda = 128;
    private Boomerang boomerang;
    private Personaje enemigo;//,enemigo1,enemigo2,enemigo3,enemigo4,enemigo5;
    private Fondo fondo,fondo2,fondo3,fondo4;

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

    }

    private void cargarTexturas() {
        AssetManager manager = juego.getManager();
        manager.load("Fondo.tmx",TiledMap.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("BtnBoom.png",Texture.class);
        manager.load("SpriteBoom.png",Texture.class);
        manager.load("BtnArriba.png",Texture.class);
        manager.load("btnGana.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("mole.png",Texture.class);
        manager.load("boomeran.png",Texture.class);
        manager.load("FondonivelLoop.png",Texture.class);
        manager.load("FondonivelLoop2.png",Texture.class);
        manager.load("Salida.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.finishLoading();
    }

    private void crearObjetos(){
        AssetManager manager = juego.getManager();
        mapa = manager.get("Fondo.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
        rendererMapa.setView(camara);
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        pinguino = new Personaje(texuturaPersonaje);
        pinguino.getSprite().setPosition(Juego.ancho/10,Juego.alto * 0.1f);
        texturaSalto = manager.get("BtnArriba.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(10,Juego.alto * 0.01f);
        texturaBoomeran = manager.get("SpriteBoom.png");
        boomerang = new Boomerang(texturaBoomeran);
        texturaDisparo = manager.get("BtnBoom.png");
        texturaGano = manager.get("btnGana.png");
        btnGanar = new Boton(texturaGano);
        btnDisparar = new Boton(texturaDisparo);
        btnDisparar.setPosicion(1100,Juego.alto * 0.01f);
        texturaPausa = manager.get("BtnPausa.png");
        btnPausa = new Boton((texturaPausa));
        btnPausa.setPosicion(1100,Juego.alto*0.8f);
        texturaPausado = manager.get("mole.png");
        btnPausado = new Boton(texturaPausado);
        texturaEnemigo = manager.get("boomeran.png");
        //enemigo.PersonajeEnemigo(texturaEnemigo,10);
        //enemigo1 = new Personaje(texturaEnemigo);
        //enemigo2 = new Personaje(texturaEnemigo);
        //enemigo3 = new Personaje(texturaEnemigo);
        //enemigo4 = new Personaje(texturaEnemigo);
        //enemigo5 = new Personaje(texturaEnemigo);
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
        fondo3 = new Fondo(texturaFondo1);
        fondo4 = new Fondo(texturaFondo3);
        texturaScore = manager.get("CuadroScore.png");
        btnScore = new Boton(texturaScore);
        btnScore.setPosicion(0,Juego.alto*0.7f);

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
            fondo.setPosicion(0,Juego.alto/165);
            fondo2.setPosicion(Juego.ancho+1720,Juego.alto/165);
            fondo3.setPosicion(Juego.ancho + 4720,Juego.alto/165 );
            fondo4.setPosicion(Juego.ancho + 7720,Juego.alto/165);
            fondo.draw(batch);
            fondo2.draw(batch);
            fondo3.draw(batch);
            fondo4.draw(batch);
        batch.end();
        rendererMapa.render();

        batch.begin();
            pinguino.render(batch);
            boomerang.render(batch);
            //enemigo.renderEnemigo(batch);
            //enemigo.setEstadoEnemigo(Personaje.EstadosEnemigo.DERECHA);
            //enemigo.moverEnemigosDer();
            /*enemigo1.render(batch);
            enemigo2.render(batch);
            enemigo3.render(batch);
            enemigo4.render(batch);
            enemigo5.render(batch);*/
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
            /*if(pinguino.getX() > 800){
                batch.begin();
                fondo.setPosicion(1280,800);
                fondo.draw(batch);
            }*/
        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();

            if(estadoJuego == EstadosJuego.GANO ){
                btnGanar.render(batch);
            }
            else if(estadoJuego == EstadosJuego.PAUSADO){
                btnPausado.setPosicion(Juego.ancho/2,Juego.alto/2);
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                btnPausado.render(batch);
            }
            else{
                btnSalto.render(batch);
                btnDisparar.render(batch);
                btnPausa.render(batch);
                btnScore.render(batch);
                texto.mostrarMensaje(batch," " + heladosRecolectados,150,Juego.alto * 0.93f);
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
            case INICIANDO:
                int celdaX = (int)(pinguino.getX()/celda);
                int celdaY = (int)((pinguino.getY() + pinguino.velocidadY)/celda);
                TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(1);
                TiledMapTileLayer.Cell celda1 = capa.getCell(celdaX,celdaY);
                if(celda1 == null){
                    pinguino.caer();
                }
                else if(!esHelado(celda1) || !esHeladoEspecial(celda1)){
                    pinguino.setPosicion(pinguino.getX(),(celdaY+1)* celda);
                    pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                }
                break;
            case DER:
                pinguino.actualizar();
                probarColisiones();
                break;
        }
        if(pinguino.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO &&
                (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)){
            int celdaX = (int) (pinguino.getX() / celda);
            int celdaY = (int) ((pinguino.getY() + pinguino.velocidadY) / celda);
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get(1);
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX,celdaY);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX+1,celdaY);
            if((celdaAbajo == null && celdaDerecha == null) || esHelado(celdaAbajo) || esHelado(celdaDerecha)){
                pinguino.caer();
                pinguino.setEstadoSalto(Personaje.EstadoSalto.CAIDALIBRE);
            }
            else{
                pinguino.setPosicion(pinguino.getX(),(celdaY +1)*celda);
                pinguino.setEstadoSalto(Personaje.EstadoSalto.ABAJO);

            }
            if(pinguino.getX() >= 11000){
                pinguino.setPosicion(-1000,0);
                estadoJuego = estadoJuego.GANO;
            }
            else if(pinguino.getY() <= 10){
                vidas --;
                pinguino.getSprite().setPosition(Juego.ancho/10,Juego.alto * 0.1f);
                camara.position.set(Juego.ancho/pinguino.getX(),camara.position.y,0);
                camaraHUD.position.set(Juego.ancho/2,camaraHUD.position.y,0);
                camara.update();
                camaraHUD.update();
                actualizarCamara();
                /*batch.setProjectionMatrix(camara.combined);
                rendererMapa.setView(camara);
                rendererMapa.render();
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.INICIANDO);*/
            }
            if(vidas == 0){
                pinguino.setPosicion(-200,-200);
                estadoJuego = estadoJuego.PERDIO;
            }
            if(estadoJuego == estadoJuego.PERDIO){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        juego.setScreen(new MenuPrincipal(juego));
                    }
                },3);
            }
        }
        switch (pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto();
                break;
        }
    }

    private void probarColisiones() {
        Personaje.EstadoMovimiento estado = pinguino.getEstadoMovimiento();
        if(estado != Personaje.EstadoMovimiento.DER){
            return;
        }
        float px = pinguino.getX();
        px = pinguino.getEstadoMovimiento() == Personaje.EstadoMovimiento.DER? px + Personaje.velocidadX:
            px-Personaje.velocidadX;
        int celdaX = (int)(px/celda);
        if(pinguino.getEstadoMovimiento() == Personaje.EstadoMovimiento.DER){
            celdaX++;
        }
        int celdaY = (int)(pinguino.getY()/celda); 
        TiledMapTileLayer capaPlataforma1 = (TiledMapTileLayer) mapa.getLayers().get(2);
        if(capaPlataforma1.getCell(celdaX,celdaY) != null || capaPlataforma1.getCell(celdaX,celdaY+1) != null){
            if(esHelado(capaPlataforma1.getCell(celdaX,celdaY))){
                heladosRecolectados +=500;
                capaPlataforma1.setCell(celdaX,celdaY,null);
            }
            else if(esHeladoEspecial(capaPlataforma1.getCell(celdaX,celdaY))){
                heladosRecolectados += 1000;
                capaPlataforma1.setCell(celdaX,celdaY,null);
            }
            else{
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
        }
        else{
            pinguino.actualizar();
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

    }

    @Override
    public void dispose() {
        AssetManager manager1 = juego.getManager();
        manager1.unload("pinguidoChido2.png");
        manager1.unload("boomeran.png");
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
            if(btnDisparar.contiene(x,y)){
                boomerang = new Boomerang(texturaBoomeran);
                boomerang.setPosicion(pinguino.getX(),(int)pinguino.getY());
                boomerang.salir();
                System.out.println("Velocidad = " + boomerang.getVelocidadX());
                System.out.println("Me clicliestas");
            }
            else if(btnPausa.contiene(x,y)){
                estadoJuego = estadoJuego.PAUSADO;

            }
            else if(btnPausado.contiene(x,y)){
                estadoJuego = estadoJuego.JUGANDO;
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
                btnPausado.setPosicion(-1000,-1000);
                moverPersonaje();

            }
            else if(estadoJuego == EstadosJuego.GANO){
                if(btnGanar.contiene(x,y)){
                    Gdx.app.exit();
                }
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
