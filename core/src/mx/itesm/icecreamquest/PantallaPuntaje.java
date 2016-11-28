package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Andres Calva on 25/09/2016.
 */
public class PantallaPuntaje extends mx.itesm.icecreamquest.PantallaDatos implements Screen{
    private final Juego juego;
    private Stage escena;
    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaBack,texturaInstrucciones,texturaReset;
    private Music musica;
    private mx.itesm.icecreamquest.Musica muscaPuntaje;
    public static Boolean ajuste = false;
    private mx.itesm.icecreamquest.Texto texto;
    private SpriteBatch batch;
    public Preferences score = Gdx.app.getPreferences("ScoreNivel1");
    public Preferences score2 = Gdx.app.getPreferences("ScoreNivel2");
    public Preferences score3 = Gdx.app.getPreferences("ScoreNivel3");
    int puntaje,puntaje2,puntaje3;

    public PantallaPuntaje(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        //*** CREAR CAMARA Y VISTA ***\\
            mx.itesm.icecreamquest.PantallaDatos camara1 = new mx.itesm.icecreamquest.PantallaDatos(camara);
            mx.itesm.icecreamquest.PantallaDatos vista1 = new mx.itesm.icecreamquest.PantallaDatos(vista);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);
        //*** FIN DE CREAR CAMARA Y VISTA ***\\

        //*** CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES ***\\
            cargarImagenes();
            cargarFondo();
            cargarBotones();
            batch = new SpriteBatch();
            puntaje = score.getInteger("Nivel1",0);
            puntaje2 = score2.getInteger("Nivel2",0);
            puntaje3 = score3.getInteger("Nivel3",0);
        //*** FIN DE CARGAR IMAGENES, FONDO, BOTONES Y FUNCIONALIDADES***\\
    }


    private void cargarBotones() {
        TextureRegionDrawable regresar = new TextureRegionDrawable(new TextureRegion(texturaBack));
        ImageButton btonBack = new ImageButton(regresar);
        btonBack.setPosition(0,0);
        escena.addActor(btonBack);
        btonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new MenuPrincipal(juego));

            }
        });
        TextureRegionDrawable textReset = new TextureRegionDrawable(new TextureRegion(texturaReset));
        ImageButton btnReset = new ImageButton(textReset);
        btnReset.setPosition(1030,80);
        escena.addActor(btnReset);
        btnReset.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                score.clear();
                score2.clear();
                score3.clear();
                score.flush();
                score2.flush();
                score3.flush();
                juego.setScreen(new PantallaPuntaje(juego));
            }
        });
    }

    private void cargarImagenes() {
        AssetManager manager = juego.getManager();
        manager.load("ScorePantalla.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.load("BtnReset.png",Texture.class);
        manager.load("Score.mp3",Music.class);
        manager.finishLoading();
        texturaInstrucciones = manager.get("ScorePantalla.png");
        texturaBack = manager.get("botonRegresar.png");
        texturaReset = manager.get("BtnReset.png");
        musica = manager.get("Score.mp3");
        muscaPuntaje = new mx.itesm.icecreamquest.Musica(musica,true,ajuste);
        texto = new mx.itesm.icecreamquest.Texto();
    }

    private void cargarFondo() {
        escena = new Stage();
        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(true);
        Image fondo = new Image(texturaInstrucciones);
        escena.addActor(fondo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.setViewport(vista);
        escena.draw();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        texto.mostrarMensaje(batch,"Nivel 1 ",240,510);
        texto.mostrarMensaje(batch," " + puntaje,410,490);
        texto.mostrarMensaje(batch,"Nivel 2 ",240,400);
        texto.mostrarMensaje(batch," " + puntaje2,410,380);
        texto.mostrarMensaje(batch,"Nivel 3 ",230,290);
        texto.mostrarMensaje(batch," " + puntaje3,410,270);
        batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            juego.setScreen(new MenuPrincipal(juego));
        }
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
        manager.unload("ScorePantalla.png");
        manager.unload("botonRegresar.png");
        manager.unload("Score.mp3");
    }
}
