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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Mario Lagunes on 21/11/2016.
 */

public class Comic implements Screen, InputProcessor {
    private Juego juego;
    private Texture texturaComic,texturaSiguiente,texturaSkip;
    private Fondo fondoComic;
    private Boton btnSiguiente,btnSkip;
    private SpriteBatch batch;
    private OrthographicCamera camara,camaraHUD;
    private Viewport vista;
    private StretchViewport vistaHUD;
    private float velY=1;
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    private Music musica;
    private Musica musicaComic;
    public static Boolean ajuste = false;


    public Comic(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
        PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);
        camaraHUD = camaraHUD1.crearCamara(camaraHUD);
        vistaHUD = vistaHUD1.crearVistaHUD(camaraHUD,vistaHUD);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        AssetManager manager = juego.getManager();
        manager.load("Comic.png",Texture.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.load("Comic.mp3",Music.class);
        manager.load("BtnSkip1.png",Texture.class);
        manager.finishLoading();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
        texturaComic = manager.get("Comic.png");
        texturaSiguiente = manager.get("botonSiguiente.png");
        texturaSkip = manager.get("BtnSkip1.png");
        btnSkip = new Boton(texturaSkip);
        fondoComic = new Fondo(texturaComic);
        fondoComic.setPosicion(0,-800);
        btnSiguiente = new Boton(texturaSiguiente);
        btnSiguiente.setPosicion(1100,0);
        batch = new SpriteBatch();
        musica = manager.get("Comic.mp3");
        musicaComic = new Musica(musica,true,ajuste);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fondoComic.draw(batch);
        btnSkip.render(batch);
        float yFon = fondoComic.getY();
        yFon += velY;
        fondoComic.getSprite().setY(yFon);
        if(fondoComic.getY() == 0){
            velY = 0;
            btnSiguiente.render(batch);
            btnSkip.setPosicion(-1000,0);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        if(btnSiguiente.contiene(x,y)){
            juego.setScreen(new PantallaCargando(juego));
            musica.stop();
        }
        if(btnSkip.contiene(x,y)){
            juego.setScreen(new PantallaCargando(juego));
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
}
