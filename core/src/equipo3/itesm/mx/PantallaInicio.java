package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Mario Lagunes on 20/11/2016.
 */

public class PantallaInicio implements Screen,InputProcessor{
    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private Texture textLogo,textTitulo;
    private Sprite logo,titulo;
    private SpriteBatch batch;
    private float timer = 0;

    public PantallaInicio(Juego juego){
        this.juego = juego;
    }

    @Override
    public void show() {
        // *** CREAR CAMARA Y VISTA ***\\
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        // *** FIN DE CREAR CAMARA Y VISTA ***\\

        // *** CARGAR IMAGENES ***//
        batch = new SpriteBatch();
        imagenesFondo();
        cargarFondo();

        Gdx.input.setInputProcessor(this);
        // *** FIN DE CARGAR IMAGENES***\\

        // *** CARGAR MÚSICA ***\\
        // *** FIN DE CARGAR MUSICA ***\\
    }

    private void imagenesFondo(){
        AssetManager manager = juego.getManager();
        manager.load("LogoTEC.png",Texture.class);
        manager.load("TituloInicio.png",Texture.class);
        manager.finishLoading();
    }

    private void cargarFondo(){
        AssetManager manager = juego.getManager();
        textLogo = manager.get("LogoTEC.png");
        textTitulo = manager.get("TituloInicio.png");
        logo = new Sprite(textLogo);
        titulo = new Sprite(textTitulo);
        logo.setPosition(0,0);
        titulo.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        timer += Gdx.graphics.getDeltaTime();
        batch.begin();
        logo.draw(batch);
        if(timer >= 2 && timer <4){
            titulo.draw(batch);
        }
        if(timer >= 4){
            juego.setScreen(new MenuPrincipal(juego));
        }
        batch.end();
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
        dispose();
    }

    @Override
    public void dispose() {
        AssetManager manager = juego.getManager();
        manager.unload("LogoTEC.png");
        manager.unload("TituloInicio.png");
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        return false;
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
}
