package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
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
 * Created by Mario Lagunes on 26/09/2016.
 */
public class PantallaInstrucciones implements Screen {
    private final Juego juego;
    private Texture texturaBack, texturaNext, texturaInstrucciones;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    private final AssetManager manager = new AssetManager();

    public PantallaInstrucciones(Juego juego) {
        this.juego = juego;
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
        Image fondo = new Image(texturaInstrucciones);
        escena.addActor(fondo);
    }

    private void cargarImagenes() {
        manager.load("Instrucciones-01.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.finishLoading();
        texturaInstrucciones = manager.get("Instrucciones-01.png");
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
        texturaBack.dispose();
        texturaInstrucciones.dispose();
        texturaNext.dispose();
    }

    @Override
    public void dispose() {

    }
}
