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
 * Created by Mario Lagunes on 22/11/2016.
 */

public class PantallaCargando3 implements Screen {
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

    public PantallaCargando3(Juego juego){
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
        TextureRegion[][] animacioTextura = texturaCompleta.split(255,273);
        animacionCarga = new Animation(0.10f,animacioTextura[0][0],animacioTextura[0][1],animacioTextura[0][2],animacioTextura[0][3],animacioTextura[0][4],animacioTextura[0][5],
                animacioTextura[0][6],animacioTextura[0][7],animacioTextura[0][8]);
        animacionCarga.setPlayMode(Animation.PlayMode.LOOP);
        timer = 0;
        spriteCargando = new Sprite(animacioTextura[0][0]);
        spriteCargando.setPosition(550,250);
        cargarSiguienteNivel();
        //Gdx.input.setInputProcessor(this);
        //Gdx.input.setCatchBackKey(true);
    }

    private void cargarSiguienteNivel() {
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
        manager.load("Walkgud_IZQ.png",Texture.class);
        manager.load("SpriteBoom_ver.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("Helado especial.mp3",Sound.class);
        manager.load("Helado normal.mp3",Sound.class);
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
            juego.setScreen(new Nivel3(juego));
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
