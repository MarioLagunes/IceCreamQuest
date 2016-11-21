package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Mario Lagunes on 20/11/2016.
 */
public class PantallaInstrucciones2 implements Screen, InputProcessor {

    private Juego juego;
    private Texture texturaBack, texturaNext, texturaInstrucciones;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    //private final AssetManager manager = new AssetManager();

    public PantallaInstrucciones2(Juego juego) {
        this.juego = juego;
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

    @Override
    public void show() {
        //*** CREAR CAMARA Y VISTA ***\\
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        //*** FIN DE CREAR CAMARA Y VISTA ***\\

        //*** CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES ***\\
        cargarImagenes();
        cargarFondo();
        cargarBotones();
        //*** FIN DE CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES***\\
    }

    private void cargarBotones() {
        TextureRegionDrawable back = new TextureRegionDrawable(new TextureRegion(texturaBack));
        ImageButton btonBack = new ImageButton(back);
        btonBack.setPosition(0,0);
        escena.addActor(btonBack);

        TextureRegionDrawable next = new TextureRegionDrawable(new TextureRegion(texturaNext));
        ImageButton btonNext = new ImageButton(next);
        btonNext.setPosition(1100,0);
        escena.addActor(btonNext);

        btonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));
            }
        });

        btonNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //juego.setScreen(new Nivel1(juego));
                juego.setScreen(new Nivel2(juego));
            }
        });

    }

    private void cargarFondo() {
        escena = new Stage();
        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(true);
        Image fondo = new Image(texturaInstrucciones);
        escena.addActor(fondo);
    }

    private void cargarImagenes() {
        AssetManager manager = juego.getManager();
        manager.load("Instruccionesnivel2.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.finishLoading();
        texturaInstrucciones = manager.get("Instruccionesnivel2.png");
        texturaBack = manager.get("botonRegresar.png");
        texturaNext = manager.get("botonSiguiente.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
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
        manager.unload("Instruccionesnivel2.png");
        manager.unload("botonRegresar.png");
        manager.unload("botonSiguiente.png");
        texturaBack.dispose();
        texturaInstrucciones.dispose();
        texturaNext.dispose();
    }
}
