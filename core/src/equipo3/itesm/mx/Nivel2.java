package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xerces.internal.util.URI;


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
    private Fondo fondo,fondo2,fondo3,fondo4,fondo5,fondoEx,fondoExI,fondoEx1,fondoEx2,fondoPiso1,fondoPiso2,fondoPiso3,fondoCarre1,fondoCarre2,fondoCarre3,fondoCarre4,fondoCarre5,fondoCarre6;
    private Texture fondoNoche,texuturaPersonaje,texturaQui,texturaSal,textFondo2,edificiosIzq,edificiosDer,texturaCentro,fondoPiso,textCarreDer,textCarreIzq,textCono,textBtnSaltar,textBtnPausa,
                    textBtnReaunudar,textBtnSalir,texturaPausa,texturaGanaste,texturaPerdiste,texturaScore,textPosteDer,textPosteIzq,textMoco;
    private int velocidadX = 10, velocidadY = -10,velocidadItemY = -5;
    private Personaje pinguino;
    private Sprite cono,marcador,pausa,posteDer,posteIzq,moco;
    private EstadosJuego estadosJuego;
    private Boton btnSaltar,btnPausa,btnReanudar,btnSalir,ganar,perder;
    private Texto texto;
    private int vidas = 5;
    private float j = 0.01f,j2=0.03f;


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
        manager.load("ciudadnivel2.png", Texture.class);
        manager.load("Carretera.png",Texture.class);
        manager.load("PinguinoChido2.png",Texture.class);
        manager.load("Saltar.png",Texture.class);
        manager.load("edificiosIZQ.png",Texture.class);
        manager.load("edificiosDER.png",Texture.class);
        manager.load("banquetaIZQ.png",Texture.class);
        manager.load("banquetaDER.png",Texture.class);
        manager.load("cono.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("BtnArriba.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("Perdiste_1.png",Texture.class);
        manager.load("Ganaste_1.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("poste.png",Texture.class);
        manager.load("poste_der.png",Texture.class);
        manager.load("moco.png",Texture.class);
        manager.finishLoading();
    }

    private void crearObjetos() {
        AssetManager manager = juego.getManager();
        textBtnSaltar = manager.get("BtnArriba.png");
        textBtnPausa = manager.get("BtnPausa.png");
        textBtnReaunudar = manager.get("BTN_Resumen.png");
        textBtnSalir = manager.get("BTN_Salir.png");
        texturaPausa = manager.get("Pausa.png");
        texturaGanaste = manager.get("Ganaste_1.png");
        texturaPerdiste = manager.get("Perdiste_1.png");
        texturaGanaste = manager.get("Ganaste_1.png");
        texturaPerdiste = manager.get("Perdiste_1.png");
        texturaPausa = manager.get("Pausa.png");
        texturaScore = manager.get("CuadroScore.png");
        textMoco = manager.get("moco.png");
        textPosteIzq = manager.get("poste.png");
        textPosteDer = manager.get("poste_der.png");


        btnSaltar = new Boton(textBtnSaltar);
        btnPausa = new Boton(textBtnPausa);
        btnReanudar = new Boton(textBtnReaunudar);
        btnSalir = new Boton(textBtnSalir);
        marcador = new Sprite(texturaScore);
        ganar = new Boton(texturaGanaste);
        perder = new Boton(texturaPerdiste);
        pausa = new Sprite(texturaPausa);
        texto = new Texto();

        marcador.setPosition(0,560);
        btnSaltar.setPosicion(90,-10);
        btnPausa.setPosicion(1100,640);

        fondoNoche = manager.get("ciudadnivel2.png");
        fondoPiso = manager.get("Carretera.png");
        textCarreDer = manager.get("banquetaDER.png");
        textCarreIzq = manager.get("banquetaIZQ.png");


        textCono = manager.get("cono.png");
        cono = new Sprite(textCono);
        cono.setPosition(566,400);
        cono.setScale(0.3f,0.3f);


        edificiosDer = manager.get("edificiosDER.png");
        edificiosIzq = manager.get("edificiosIZQ.png");
        fondo = new Fondo(edificiosIzq);
        fondo2 = new Fondo(edificiosIzq);
        fondoEx = new Fondo(edificiosIzq);
        fondoEx1 = new Fondo(edificiosIzq);
        fondoEx2 = new Fondo(edificiosIzq);
        fondoPiso1 = new Fondo(fondoPiso);
        fondoPiso2 = new Fondo(fondoPiso);
        fondoPiso3 = new Fondo(fondoPiso);

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
        fondoPiso1.setPosicion(0,0);
        fondoPiso2.setPosicion(0,0);

        fondoCarre1 = new Fondo(textCarreIzq);
        fondoCarre2 = new Fondo(textCarreIzq);
        fondoCarre3 = new Fondo(textCarreIzq);
        fondoCarre4 = new Fondo(textCarreDer);
        fondoCarre5 = new Fondo(textCarreDer);
        fondoCarre6 = new Fondo(textCarreDer);
        fondoCarre1.setPosicion(0,0);
        fondoCarre2.setPosicion(10,0);
        fondoCarre3.setPosicion(10,0);
        fondoCarre4.setPosicion(0,0);
        fondoCarre5.setPosicion(0,0);
        fondoCarre6.setPosicion(0,0);

        moco = new Sprite(textMoco);
        posteDer = new Sprite(textPosteDer);
        posteIzq = new Sprite(textPosteIzq);
        posteDer.setScale(0.3f,0.3f);
        posteIzq.setScale(0.3f,0.3f);
        posteDer.setPosition(670,300);
        posteIzq.setPosition(450,300);

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
            fondoCarre1.draw(batch);
            fondoCarre2.draw(batch);
            fondoCarre3.draw(batch);
            fondoCarre4.draw(batch);
            fondoCarre5.draw(batch);
            fondoCarre6.draw(batch);
            fondo.draw(batch);
            fondo2.draw(batch);
            fondoEx.draw(batch);
            fondo3.draw(batch);
            fondo4.draw(batch);
            fondoExI.draw(batch);

            fondoPiso2.draw(batch);
            fondoPiso1.draw(batch);
            cono.draw(batch);
            posteDer.draw(batch);
            posteIzq.draw(batch);


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
            if(fondoPiso1.getSprite().getY() < -100){
                fondoPiso1.setPosicion(0,0);
            }
            if(fondoCarre2.getSprite().getX() < -166){
                fondoCarre2.setPosicion(10,0);
            }
            if(fondoCarre3.getSprite().getX() < -166){
                fondoCarre3.setPosicion(10,0);
            }
            if(fondoCarre5.getSprite().getX() > 166){
                fondoCarre5.setPosicion(0,0);
            }
            if(fondoCarre6.getSprite().getX() < 166){
                fondoCarre6.setPosicion(0,0);
            }
            for(float i= 0.01f; i<1;i++){
                cono.setScale(cono.getScaleX()+j,cono.getScaleY()+j);
                posteIzq.setScale(posteIzq.getScaleX()+j,posteIzq.getScaleY()+j);
                if(cono.getY() < -155){
                    cono.setPosition(566,400);
                    cono.setScale(0.3f,0.3f);
                }
                if(posteIzq.getY() < -200){
                    posteIzq.setScale(0.3f,0.3f);
                    posteIzq.setPosition(450,300);
                }
            }
            /*if(cono.getY() == 200){
                cono.setScale(0.6f,0.6f);
            }
            /*if(fondoPiso2.getSprite().getY() < -100){
                fondoPiso2.setPosicion(0,0);
            }*/
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

        float yFondoPiso1 = fondoPiso1.getY();
        yFondoPiso1 += velocidadY;
        fondoPiso1.getSprite().setY(yFondoPiso1);

        /*float yFondoPiso2 = fondoPiso2.getY();
        yFondoPiso2 += velocidadY;
        fondoPiso2.getSprite().setY(yFondoPiso2);*/

        float xFondoCarre2 =  fondoCarre2.getX();
        float yFondoCarre2 = fondoCarre2.getY();
        xFondoCarre2 -= velocidadX;
        yFondoCarre2 += velocidadY;
        fondoCarre2.getSprite().setX(xFondoCarre2);
        fondoCarre2.getSprite().setY(yFondoCarre2);

        float xFondoCarre3 =  fondoCarre3.getX();
        //float yFondoEx = fondoEx.getY();
        xFondoCarre3 -= velocidadX;
        //yFondoEx += velocidadY;
        fondoCarre3.getSprite().setX(xFondoCarre3);
        //fondoEx.getSprite().setY(yFondoEx);*/

        float xFondoCarre5 =  fondoCarre5.getX();
        float yFondoCarre5 = fondoCarre5.getY();
        xFondoCarre5 -= velocidadX;
        yFondoCarre5 += velocidadY;
        fondoCarre5.getSprite().setX(xFondoCarre5);
        fondoCarre5.getSprite().setY(yFondoCarre5);

        float xFondoCarre6 =  fondoCarre6.getX();
        //float yFondoEx = fondoEx.getY();
        xFondoCarre6 -= velocidadX;
        //yFondoEx += velocidadY;
        fondoCarre6.getSprite().setX(xFondoCarre6);
        //fondoEx.getSprite().setY(yFondoEx);*/

        float yFondoCono = cono.getY();
        yFondoCono += velocidadItemY;
        cono.setY(yFondoCono);

        float xPosteIzq = posteIzq.getX();
        float yPosteIzq = posteIzq.getY();
        xPosteIzq -= velocidadX;
        yPosteIzq += velocidadY;
        posteIzq.setX(xPosteIzq);
        posteIzq.setY(yPosteIzq);

        if(estadosJuego == EstadosJuego.GANO ){
            ganar.render(batch);
        }
        else if(estadosJuego == EstadosJuego.PAUSADO){
            pausa.setPosition(Juego.ancho/4,Juego.alto/16);
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            j=0;
            cono.setScale(cono.getScaleX(),cono.getScaleY());
            pausa.draw(batch);
            velocidadX = 0;
            velocidadY = 0;
            velocidadItemY = 0;
            btnReanudar.setPosicion(Juego.ancho/3,Juego.alto*0.45f);
            btnSalir.setPosicion(Juego.ancho/3,Juego.alto*0.15f);
            btnReanudar.render(batch);
            btnSalir.render(batch);
        }
        else if(estadosJuego == EstadosJuego.PERDIO){
            perder.render(batch);
        }
        else{
            btnSaltar.render(batch);
            //btnDisparar.render(batch);
            btnPausa.render(batch);
            marcador.draw(batch);
            texto.mostrarMensaje(batch," " + pinguino.puntos,150,Juego.alto * 0.93f);
            texto.mostrarMensaje(batch," " + vidas,150,Juego.alto * 0.85f);
        }

        batch.end();

        /*batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();

        batch.end();*/
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
        private Vector3 coordenadas = new Vector3();
        private float x,y;
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX,screenY);
            if(btnSaltar.contiene(x,y)){
                pinguino.saltar();
            }
            /*else if(btnDisparar.contiene(x,y)){
                boomerang = new Boomerang(texturaBoomeran);
                boomerang.setPosicion(pinguino.getX(),(int)pinguino.getY());
                boomerang.salir();
                btnDisparar.setPosicion(0,-100);

            }*/

            else if(btnPausa.contiene(x,y)){
                estadosJuego = estadosJuego.PAUSADO;

            }
            else if(btnReanudar.contiene(x,y)){
                estadosJuego = estadosJuego.JUGANDO;
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
                pausa.setPosition(-13444,-12435);
                j=0.01f;
                velocidadX = 10;
                velocidadY = -10;
                velocidadItemY = -5;
            }
            else if(btnSalir.contiene(x,y)){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        juego.setScreen(new MenuPrincipal(juego));
                    }
                },1);
                //musica.setVolume(0);
                //musica.stop();
            }
            else if(perder.contiene(x,y)){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        juego.setScreen(new MenuPrincipal(juego));
                    }
                },1);
                //musica.setVolume(0);
                //musica.stop();
            }
            else if(estadosJuego == EstadosJuego.GANO){
                if(ganar.contiene(x,y)){
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            juego.setScreen(new Nivel2(juego));
                        }
                    },1);
                }
                //musica.setVolume(0);
                //musica.stop();
            }

            return true;
        }

        private void transformarCoordenadas(int screenX, int screenY){
            coordenadas.set(screenX, screenY,0);
            camaraHUD.unproject(coordenadas);
            x = coordenadas.x;
            y = coordenadas.y;
        }
    }
}
