package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Mario Lagunes on 21/11/2016.
 */

public class PantallaCargando implements Screen {
    private Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;
    private Texture texturaCargando,texturFondo;
    private Fondo fondo;
    private Sprite spriteCargando;
    private Animation animacionCarga;
    private float timer;
    private AssetManager manager;

    public PantallaCargando(Juego juego){
        this.juego = juego;
        this.manager = juego.getManager();
    }


    @Override
    public void show() {
        PantallaDatos camara1 = new PantallaDatos(camara);
        PantallaDatos vista1 = new PantallaDatos(vista);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        batch = new SpriteBatch();

        manager.load("Spritecargando.png",Texture.class);
        manager.load("Fondo-Cargando.png",Texture.class);
        manager.finishLoading();
        texturaCargando = manager.get("Spritecargando.png");
        texturFondo = manager.get("Fondo-Cargando.png");
        fondo = new Fondo(texturFondo);
        fondo.setPosicion(0,0);
        TextureRegion texturaCompleta = new TextureRegion(texturaCargando);
        TextureRegion[][] animacioTextura = texturaCompleta.split(333,494);
        animacionCarga = new Animation(0.10f,animacioTextura[0][0],animacioTextura[0][1],animacioTextura[0][2],animacioTextura[0][3],animacioTextura[0][4],animacioTextura[0][5],
                animacioTextura[0][6],animacioTextura[0][7],animacioTextura[0][8]);
        animacionCarga.setPlayMode(Animation.PlayMode.LOOP);
        timer = 0;
        spriteCargando = new Sprite(animacioTextura[0][0]);
        spriteCargando.setPosition(500,100);
        cargarSiguienteNivel();
        //Gdx.input.setInputProcessor(this);
        //Gdx.input.setCatchBackKey(true);
    }

    private void cargarSiguienteNivel() {
        manager.load("Fondo64.tmx",TiledMap.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("BtnBoom.png",Texture.class);
        manager.load("SpriteBoom.png",Texture.class);
        manager.load("BtnArriba.png",Texture.class);
        manager.load("Ganaste_1.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("SpriteZookDerecha.png",Texture.class);
        manager.load("SpriteZookIzquierda.png",Texture.class);
        manager.load("FondonivelLoop.png",Texture.class);
        manager.load("FondonivelLoop2.png",Texture.class);
        manager.load("Salida.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("dardo.png",Texture.class);
        manager.load("Parado.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("Perdiste_1.png",Texture.class);
        manager.load("Nivel_1.mp3", Music.class);
        manager.load("Helado especial.mp3",Sound.class);
        manager.load("Helado normal.mp3",Sound.class);
        manager.load("Meeehhpp!!.wav",Sound.class);
        manager.load("Walkgud_IZQ.png",Texture.class);
        manager.load("SaltarIZQ.png",Texture.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
    }

    @Override
    public void render(float delta) {
        actualizar();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        timer += Gdx.graphics.getDeltaTime();
        TextureRegion region = animacionCarga.getKeyFrame(timer);
        spriteCargando.setRegion(region);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fondo.draw(batch);
        spriteCargando.draw(batch);
        batch.end();
    }

    private void actualizar() {
        if(manager.update()){
            juego.setScreen(new Nivel1(juego));
        }
        else{
            float avance = manager.getProgress();
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
        manager.unload("Spritecargando.png");
    }
}
