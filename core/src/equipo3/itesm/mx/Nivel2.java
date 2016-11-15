package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Mario Lagunes on 06/11/2016.
 */

public class Nivel2 implements Screen{

    private Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private OrthographicCamera camaraHUD;
    private StretchViewport vistaHUD;
    private SpriteBatch batch;
    private Fondo fondo,fondo2,fondo3,fondo4,fondo5,fondoEx,fondoExI,fondoEx1,fondoEx2;
    private Texture fondoNoche,texuturaPersonaje,texturaQui,texturaSal,textFondo2,edificiosIzq,edificiosDer,texturaCentro;
    private int velocidadX = 10, velocidadY = -10,contador;
    private Personaje pinguino;

    public Nivel2(Juego juego){
        this.juego = juego;
    }


    @Override
    public void show() {
        //Crear Camará
            PantallaDatos camara1 = new PantallaDatos(camara);
            PantallaDatos vista1 = new PantallaDatos(vista);
            PantallaDatos camaraHUD1 = new PantallaDatos(camaraHUD);
            PantallaDatos vistaHUD1 = new PantallaDatos(vistaHUD);
            camara = camara1.crearCamara(camara);
            vista = vista1.crearVista(camara,vista);
            camaraHUD = camaraHUD1.crearCamara(camaraHUD);
            vistaHUD = vistaHUD1.crearVistaHUD(camaraHUD,vistaHUD);
            batch = new SpriteBatch();
            cargarTexturas();
            crearObjetos();
            Gdx.input.setInputProcessor(new ProcesadorEntrada());
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);

    }

    private void cargarTexturas(){
        AssetManager manager = juego.getManager();
        manager.load("Fondo2.png", Texture.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("edificiosIZQ.png",Texture.class);
        manager.load("edificiosDER.png",Texture.class);
        manager.finishLoading();
    }

    private void crearObjetos() {
        AssetManager manager = juego.getManager();
        fondoNoche = manager.get("Fondo2.png");
        textFondo2 = manager.get("Fondo2.png");


        edificiosDer = manager.get("edificiosDER.png");
        edificiosIzq = manager.get("edificiosIZQ.png");
        fondo = new Fondo(edificiosIzq);
        fondo2 = new Fondo(edificiosIzq);
        fondoEx = new Fondo(edificiosIzq);
        fondoEx1 = new Fondo(edificiosIzq);
        fondoEx2 = new Fondo(edificiosIzq);

        fondo3 = new Fondo(edificiosDer);
        fondo4 = new Fondo(edificiosDer);
        fondoExI = new Fondo(edificiosDer);
        fondo5 = new Fondo(fondoNoche);
        texuturaPersonaje = manager.get("PinguinoChido2.png");
        texturaSal = manager.get("Saltar.png");
        pinguino = new Personaje(texuturaPersonaje,texturaSal,0);
        pinguino.getSprite().setPosition(1280/2,0);
        fondo.setPosicion(0,0);
        fondo2.setPosicion(0,0);
        fondoEx1.setPosicion(0,400);
        fondoEx2.setPosicion(0,400);
        //fondo2.setPosicion(200,400);
        fondoEx.setPosicion(0,0);
        fondo3.setPosicion(0,0);
        fondo4.setPosicion(0,0);
        fondoExI.setPosicion(0,0);

    }

    private void actualizarCamara(){
        float posX = pinguino.getX();
        if(posX >= Juego.ancho/2 && posX <= 1280 - Juego.ancho/2){
            camara.position.set((int)posX,camara.position.y,0);
        }
        else if(posX > 1280 - Juego.ancho/2){
            camara.position.set(1280-Juego.ancho/2, camara.position.y,0);
        }
        else if(posX < Juego.ancho/2){
            camara.position.set(Juego.ancho/2,Juego.alto/2,0);
        }
        camara.update();
    }

    @Override
    public void render(float delta) {
        //velocidadX += 1;
        //velocidadY -= 5;
        actualizarCamara();
        Gdx.gl.glClearColor(0.42f,0.55f,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //fondoNoche.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        //textFondo2.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        //edificiosDer.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        //edificiosIzq.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
            //batch.draw(edificiosIzq,0,0,velocidadX,velocidadY,1280,800);
            //batch.draw(edificiosIzq,0,0);
            //batch.draw(textFondo2,0,0,(-1)*velocidad,(-1)*velocidad,1280,800);
            fondo5.draw(batch);
            fondo.draw(batch);
            fondo2.draw(batch);
            fondoEx.draw(batch);
            fondo3.draw(batch);
            fondo4.draw(batch);
            fondoExI.draw(batch);
            /*if(fondo.getSprite().getX() == -500){
                fondo.setPosicion(0,0);
            }*/


            if(fondo2.getSprite().getX() < -166){
                fondo2.setPosicion(0,0);
            }
            if(fondoEx.getSprite().getX() < -166){
                fondoEx.setPosicion(0,0);
            }

            if(fondo4.getSprite().getX() > 166){
                fondo4.setPosicion(0,0);
            }
            if(fondoExI.getSprite().getX() > 166){
                fondoExI.setPosicion(0,0);
            }
            pinguino.render(batch);
        //float nuevaX = pinguino.getSprite().getX();
        //nuevaX += pinguino.velocidadX;
        //pinguino.getSprite().setX(nuevaX);*/
        /*float xFondo =  fondo.getX();
        float yFondo = fondo.getY();
        xFondo -= velocidadX;
        yFondo += velocidadY;
        fondo.getSprite().setX(xFondo);
        fondo.getSprite().setY(yFondo);
        contador++;
        //System.out.println(contador);*/

        float xFondo2 =  fondo2.getX();
        float yFondo2 = fondo2.getY();
        xFondo2 -= velocidadX;
        yFondo2 += velocidadY;
        fondo2.getSprite().setX(xFondo2);
        fondo2.getSprite().setY(yFondo2);

        float xFondoEx =  fondoEx.getX();
        //float yFondoEx = fondoEx.getY();
        xFondoEx -= velocidadX;
        //yFondoEx += velocidadY;
        fondoEx.getSprite().setX(xFondoEx);
        //fondoEx.getSprite().setY(yFondoEx);

        /*if(fondo2.getSprite().getX() == 166) {
            float xFondoEx1 = fondoEx1.getX();
            float yFondoEx1 = fondoEx1.getY();
            xFondoEx1 -= velocidadX;
            yFondoEx1 += velocidadY;
            fondoEx1.getSprite().setX(xFondoEx1);
            fondoEx1.getSprite().setY(yFondoEx1);

            float xFondoEx2 = fondoEx2.getX();
            //float yFondoEx = fondoEx.getY();
            xFondoEx2 -= velocidadX;
            //yFondoEx += velocidadY;
            fondoEx2.getSprite().setX(xFondoEx2);
            //fondoEx.getSprite().setY(yFondoEx);
        }*/

        /*float xFondo3 =  fondo3.getX();
        float yFondo3 = fondo3.getY();
        xFondo3 += velocidadX;
        yFondo3 += velocidadY;
        fondo3.getSprite().setX(xFondo3);
        fondo3.getSprite().setY(yFondo3);*/

        float xFondo4 =  fondo4.getX();
        float yFondo4 = fondo4.getY();
        xFondo4 += velocidadX;
        yFondo4 += velocidadY;
        fondo4.getSprite().setX(xFondo4);
        fondo4.getSprite().setY(yFondo4);

        float xFondoExI =  fondoExI.getX();
        //float yFondoEx = fondoEx.getY();
        xFondoExI += velocidadX;
        //yFondoEx += velocidadY;
        fondoExI.getSprite().setX(xFondoExI);
        /*float xFondo2 =  fondo2.getX();
        xFondo2 += velocidad;
        fondo2.getSprite().setX(xFondo2);*/
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

    public class ProcesadorEntrada extends InputAdapter{

    }
}
