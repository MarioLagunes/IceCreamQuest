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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Mario Lagunes on 26/09/2016.
 */
public class Nivel1 implements Screen {
    public static final float ancho_mapa = 7000;
    private Juego juego;
    private OrthographicCamera camara;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;
    private Viewport vista;
    private SpriteBatch batch;
    private Texture texuturaPersonaje;
    private Personaje pinguino;
    private OrthographicCamera camaraHUD;
    private EstadosJuego estadoJuego;
    private Texto texto;
    private Texture texturaSalto,texturaBoomeran;
    private Boton btnSalto,btnDisparar;
    private int heladosRecolectados = 0;
    private static final int celda = 128;
    private Boomerang boomerang;

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
        camaraHUD = camaraHUD1.crearCamara(camaraHUD);
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
        manager.load("back.png",Texture.class);
        manager.load("boomeran.png",Texture.class);
        manager.load("BtnMusica.png",Texture.class);
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
        texturaSalto = manager.get("back.png");
        btnSalto = new Boton(texturaSalto);
        btnSalto.setPosicion(celda, 5 * celda);
        texturaBoomeran = manager.get("boomeran.png");
        boomerang = new Boomerang(texturaBoomeran);
        texturaSalto = manager.get("BtnMusica.png");
        btnDisparar = new Boton(texturaSalto);
        btnDisparar.setPosicion(1000, celda);

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
        rendererMapa.render();

        batch.begin();
            pinguino.render(batch);
            boomerang.render(batch);
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
            btnSalto.render(batch);
            btnDisparar.render(batch);
            texto.mostrarMensaje(batch,"Puntos: " + heladosRecolectados,Juego.ancho/2,Juego.alto * 0.95f);
        batch.end();
    }

    private void actualizarCamara(){
        float posX = pinguino.getX();
        if(posX >= Juego.ancho/2 && posX <= ancho_mapa - Juego.ancho/2){
            camara.position.set((int)posX,camara.position.y,0);
        }
        else if(posX > ancho_mapa - Juego.ancho/2){
            camara.position.set((int)posX, camara.position.y,0);
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
                break;
            case DER:
                pinguino.actualizar();
                probarColisiones();
                break;
        }
        if(pinguino.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)){
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
        }
        switch (pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto();
                break;
        }
    }

    /*private void moverBoomerang(){
        switch (boomerang.getBoom()){
            case LANZADO: case REGRESANDO:
                boomerang.actualizarBoom();
                break;
        }
    }*/

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
                /*if(boomerang.getVelocidadX() >= 10){
                    System.out.println("Velocidad = " + boomerang.getVelocidadX());
                    boomerang.regresar();
                }*/




                //boomerang.actualizarSalir();
               //boomerang.actualizarBoom();
                //boomerang.setBoom(Boomerang.boom.REGRESANDO);
                /*if(boomerang.getX() >= 300){

                    boomerang.actualizarBoom();
                    System.out.println("ya llegue a los 200");
                }*/
                //boomerang.draw(boomerang);
                //boomerang.actualizar();
                System.out.println("Me clicliestas");
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
