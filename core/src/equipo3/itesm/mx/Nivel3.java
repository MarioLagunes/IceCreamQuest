package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by andrescalvavalencia on 08/11/16.
 */

public class Nivel3 implements Screen {
    private Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private OrthographicCamera camaraHUD;
    private StretchViewport vistaHUD;
    private SpriteBatch batch;
    private Personaje pinguino;
    private Fondo fondo1, fondo2,fondo3, fondo4;
    private Texture fondo,fondoFin, fondoLopp, fondoUltimo;

    public Nivel3(Juego juego){
        this.juego = juego;
    }

    private void crearTexturas(){
        AssetManager manager = juego.getManager();
        manager.load("Fondo3-png",Texture.class);
        manager.load("Fondo3fin.png",Texture.class);
        manager.load("Fondo3loop.png",Texture.class);
        manager.load("Fondo3ultima.png",Texture.class);
        manager.finishLoading();
    }

    private  void cargarObjetos(){
        AssetManager manager = juego.getManager();
        fondo = manager.get("Fondo3.png");
        fondoFin = manager.get("Fondo3fin.png");
        fondoLopp = manager.get("Fondo3loop.png");
        fondoUltimo = manager.get("Fondo3ultima.png");

        fondo1 = new Fondo(fondo);
        fondo2 = new Fondo(fondoFin);
        fondo3 = new Fondo(fondoLopp);
        fondo4 = new Fondo(fondoUltimo);

        fondo1.setPosicion(0,0);
        fondo2.setPosicion(0,1280);
        fondo3.setPosicion(0,2560);
        fondo4.setPosicion(0,3840);

    }
    @Override
    public void show() {
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
        PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        camaraHUD = camaraHUD1.crearCamara(camaraHUD);
        vistaHUD = vistaHUD1.crearVistaHUD(camaraHUD,vistaHUD);
        batch = new SpriteBatch();

        crearTexturas();
        cargarObjetos();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         batch.begin();
            fondo1.draw(batch);
            fondo2.draw(batch);
            fondo3.draw(batch);
            fondo4.draw(batch);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
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
}
