package mx.itesm.icecreamquest;

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
    private mx.itesm.icecreamquest.Fondo fondo;
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
        mx.itesm.icecreamquest.PantallaDatos camara1 = new mx.itesm.icecreamquest.PantallaDatos(camara);
        mx.itesm.icecreamquest.PantallaDatos vista1 = new mx.itesm.icecreamquest.PantallaDatos(vista);
        camara = camara1.crearCamara(camara);
        vista = vista1.crearVista(camara,vista);
        batch = new SpriteBatch();

        manager.load("Spritecargando.png",Texture.class);
        manager.load("Fondo-Cargando.png",Texture.class);
        manager.finishLoading();
        texturaCargando = manager.get("Spritecargando.png");
        texturFondo = manager.get("Fondo-Cargando.png");
        fondo = new mx.itesm.icecreamquest.Fondo(texturFondo);
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
        manager.load("Btnsalto3.png",Texture.class);
        manager.load("BtnBoom3.png",Texture.class);
        manager.load("BtnDer3.png",Texture.class);
        manager.load("Btnizq3.png",Texture.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("SaltarIZQ.png",Texture.class);
        manager.load("Walkgud_IZQ.png",Texture.class);
        manager.load("SpriteBoom_ver.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("BtnPausa_azul.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("Toppings.wav",Sound.class);
        manager.load("Nivel3.mp3",Music.class);
        manager.load("SpriteKicha.png",Texture.class);
        manager.load("BtnSig_azul.png",Texture.class);
        manager.load("BtnRegresar_azul.png",Texture.class);
        manager.load("Ganaste_1.png",Texture.class);
        manager.load("Perdiste_1.png",Texture.class);
        manager.load("Meeehhpp!!.wav",Sound.class);
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
            juego.setScreen(new mx.itesm.icecreamquest.Nivel3(juego));
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
