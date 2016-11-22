package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;


/**
 * Created by Mario Lagunes on 06/11/2016.
 */

public class Nivel2 implements Screen,InputProcessor{

    private Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private OrthographicCamera camaraHUD;
    private StretchViewport vistaHUD;
    private SpriteBatch batch;
    private Fondo fondo,fondo2,fondo3,fondo4,fondo5,fondoEx,fondoExI,fondoEx1,fondoEx2,fondoPiso1,fondoPiso2,fondoPiso3,fondoCarre1,
            fondoCarre2,fondoCarre3,fondoCarre4,fondoCarre5,fondoCarre6,fondoNubes,fondoNubes2;
    private Texture fondoNoche,texuturaPersonaje,texturaQui,texturaSal,textFondo2,edificiosIzq,edificiosDer,texturaCentro,fondoPiso,textCarreDer,textCarreIzq,textCono,textBtnSaltar,textBtnPausa,
                    textBtnReaunudar,textBtnSalir,texturaPausa,texturaGanaste,texturaPerdiste,texturaScore,textPosteDer,textPosteIzq,textMoco,textBtnDer,textBtnIzq,textPinIzq,textPinSalIzq,
                texturaBote1,texturaBote2,texturaBotes1,texturaBotes2,textConoDor,textSiguiente,textRegreso,textPingIzq,textPingDer,textCapaNubes;
    private int velocidadX = 5, velocidadY = -5,velocidadItemY = -3,velocidadItemX = 1,velocidadPosteX = 5,velocidadPosteY =-3,velocidadPinguino = 10,
            velocidadBotex = 1,velocidadBotey=-3,velocidadMocox = 3,velocidadMocoy = -3,velocidadNubes = 2;
    private Personaje pinguino;
    private Sprite cono,conoDorado,marcador,pausa,posteDer,posteIzq,moco,posteDer1,posteIzq1,posteEstaticoDer,posteEstaticoIzq,bote1,bote2,botes1,botes2,moco2,moco3;
    private EstadosJuego estadosJuego;
    private Personaje.EstadoMovimiento estadoMovimiento;
    private Personaje.EstadoSalto estadoSalto;
    private Boton btnSaltar,btnPausa,btnReanudar,btnSalir,ganar,perder,btnDer,btnIzq,btnRegreso,btnSiguiente;
    private Texto texto;
    private Animation animacionBote,animacionbote2;
    private float timerAnimar,timerAnimar2;
    private int vidas = 5,numero1,numeroDibujo=0;
    private float j = 0.004f,j2=0.015f,j3=0.009f,tiempo;
    private Vector3 coordenadas = new Vector3();
    private float x,y;
    private Music musica;
    private Musica musicaNivel2;
    public static Boolean ajuste = false;
    private Sound sonidoCono,sonidoConoDorado,muere,sonidoBote,sonidoMoco;;
    private int contador = 0;


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
            Gdx.input.setInputProcessor(this);
            Gdx.input.setCatchBackKey(true);
        //pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);

    }

    private void cargarTexturas(){
        AssetManager manager = juego.getManager();
        manager.load("ciudadnivel2.png", Texture.class);
        manager.load("Carretera.png",Texture.class);
        manager.load("edificiosIZQ.png",Texture.class);
        manager.load("edificiosDER.png",Texture.class);
        manager.load("banquetaIZQ.png",Texture.class);
        manager.load("banquetaDER.png",Texture.class);
        manager.load("cono.png",Texture.class);
        manager.load("cono_dor.png",Texture.class);
        manager.load("BtnPausa.png",Texture.class);
        manager.load("Btnsalto_Naranja.png",Texture.class);
        manager.load("CuadroScore.png",Texture.class);
        manager.load("Pausa.png",Texture.class);
        manager.load("Perdiste_1.png",Texture.class);
        manager.load("Ganaste_1.png",Texture.class);
        manager.load("BTN_Resumen.png",Texture.class);
        manager.load("BTN_Salir.png",Texture.class);
        manager.load("poste.png",Texture.class);
        manager.load("poste_der.png",Texture.class);
        manager.load("moco.png",Texture.class);
        manager.load("Btnder_Naranja.png",Texture.class);
        manager.load("Btnizq_Naranja.png",Texture.class);
        manager.load("Sprite_deslizar.png",Texture.class);
        manager.load("bote1.png",Texture.class);
        manager.load("bote2.png",Texture.class);
        manager.load("SpriteBote.png",Texture.class);
        manager.load("SpriteBote2.png",Texture.class);
        manager.load("cono_dorprueba.png",Texture.class);
        manager.load("conoprueba.png",Texture.class);
        manager.load("mocoprueba.png",Texture.class);
        manager.load("Helado especial.mp3",Sound.class);
        manager.load("Helado normal.mp3",Sound.class);
        manager.load("Meeehhpp!!.wav",Sound.class);
        manager.load("botonSiguiente.png",Texture.class);
        manager.load("botonRegresar.png",Texture.class);
        manager.load("deslizar_der.png",Texture.class);
        manager.load("deslizar_izq.png",Texture.class);
        manager.load("Mocos.wav",Sound.class);
        manager.load("Basura.wav",Sound.class);
        manager.load("Cono.wav",Sound.class);
        manager.load("Capa-Nubes.png",Texture.class);
        manager.load("Nivel2.mp3",Music.class);
        manager.finishLoading();
    }

    private void crearObjetos() {
        AssetManager manager = juego.getManager();
        textBtnSaltar = manager.get("Btnsalto_Naranja.png");
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
        textMoco = manager.get("mocoprueba.png");
        textPosteIzq = manager.get("poste.png");
        textPosteDer = manager.get("poste_der.png");
        textBtnDer = manager.get("Btnder_Naranja.png");
        textBtnIzq = manager.get("Btnizq_Naranja.png");
        texturaBote1 = manager.get("bote1.png");
        texturaBote2 = manager.get("bote2.png");
        texturaBotes1 = manager.get("SpriteBote.png");
        texturaBotes2 = manager.get("SpriteBote2.png");
        textConoDor = manager.get("cono_dorprueba.png");
        textSiguiente = manager.get("botonSiguiente.png");
        textRegreso = manager.get("botonRegresar.png");
        textPingDer = manager.get("deslizar_der.png");
        textPingIzq = manager.get("deslizar_izq.png");
        sonidoMoco = manager.get("Mocos.wav");
        sonidoCono = manager.get("Cono.wav");
        sonidoBote = manager.get("Basura.wav");
        textCapaNubes = manager.get("Capa-Nubes.png");
        muere = manager.get("Meeehhpp!!.wav");

        musica = manager.get("Nivel2.mp3");
        musicaNivel2 = new Musica(musica,true,ajuste);

        fondoNubes = new Fondo(textCapaNubes);
        fondoNubes2 = new Fondo(textCapaNubes);

        btnSiguiente = new Boton(textSiguiente);
        btnRegreso = new Boton(textRegreso);

        TextureRegion texturaBote1 = new TextureRegion(texturaBotes1);
        TextureRegion[][] textuAniBote1 = texturaBote1.split(124,238);
        animacionBote = new Animation(0.10f,textuAniBote1[0][1],textuAniBote1[0][2],textuAniBote1[0][3]);
        animacionBote.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimar = 0;

        TextureRegion texturaBote2 = new TextureRegion(texturaBotes2);
        TextureRegion[][] textuAniBote2 = texturaBote2.split(124,238);
        animacionbote2 = new Animation(0.10f,textuAniBote2[0][1],textuAniBote2[0][2],textuAniBote2[0][3]);
        animacionbote2.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimar2 = 0;

        bote1 = new Sprite(texturaBote1);
        bote2 = new Sprite(texturaBote2);
        botes1 = new Sprite(textuAniBote1[0][0]);
        botes2 = new Sprite(textuAniBote2[0][0]);
        moco = new Sprite(textMoco);
        moco2 = new Sprite(textMoco);
        moco3 = new Sprite(textMoco);

        btnSaltar = new Boton(textBtnSaltar);
        btnPausa = new Boton(textBtnPausa);
        btnReanudar = new Boton(textBtnReaunudar);
        btnSalir = new Boton(textBtnSalir);
        marcador = new Sprite(texturaScore);
        ganar = new Boton(texturaGanaste);
        perder = new Boton(texturaPerdiste);
        pausa = new Sprite(texturaPausa);
        btnDer = new Boton(textBtnDer);
        btnIzq = new Boton(textBtnIzq);
        texto = new Texto();

        marcador.setPosition(0,560);
        btnDer.setPosicion(180,-10);
        btnIzq.setPosicion(80,-10);
        btnSaltar.setPosicion(1100,-10);
        btnPausa.setPosicion(1100,640);

        fondoNoche = manager.get("ciudadnivel2.png");
        fondoPiso = manager.get("Carretera.png");
        textCarreDer = manager.get("banquetaDER.png");
        textCarreIzq = manager.get("banquetaIZQ.png");


        textCono = manager.get("conoprueba.png");
        cono = new Sprite(textCono);
        cono.setPosition(610,400);
        cono.setScale(0.2f,0.2f);
        conoDorado = new Sprite(textConoDor);
        conoDorado.setPosition(550,400);
        conoDorado.setScale(0.2f,0.2f);
        botes2.setScale(0.2f,0.2f);
        botes1.setScale(0.2f,0.4f);
        botes1.setPosition(550,400);
        botes2.setPosition(610,400);
        moco.setScale(0.2f,0.2f);
        moco2.setScale(0.3f,0.3f);
        moco3.setScale(0.3f,0.3f);
        moco.setPosition(580,400);
        moco2.setPosition(450,400);
        moco3.setPosition(670,400);

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
        texuturaPersonaje = manager.get("Sprite_deslizar.png");
        //texturaSal = manager.get("Saltar.png");
        //textPinSalIzq = manager.get("SaltarIZQ.png");
        pinguino = new Personaje(texuturaPersonaje,textPingDer,textPingIzq);
        pinguino.setPosicion2(1280/2,0);
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

        posteDer = new Sprite(textPosteDer);
        posteIzq = new Sprite(textPosteIzq);
        posteDer1 = new Sprite(textPosteDer);
        posteIzq1 = new Sprite(textPosteIzq);
        posteEstaticoDer = new Sprite(textPosteDer);
        posteEstaticoIzq = new Sprite(textPosteIzq);
        posteDer.setScale(0.5f,0.5f);
        posteIzq.setScale(0.3f,0.3f);
        posteDer.setPosition(770,200);
        posteIzq.setPosition(440,300);
        posteDer1.setScale(1f,1f);
        posteIzq1.setScale(0.8f,0.8f);
        posteDer1.setPosition(1050,-100);
        posteIzq1.setPosition(180,50);
        posteEstaticoIzq.setScale(0.15f,0.15f);
        posteEstaticoDer.setScale(0.15f,0.15f);
        posteEstaticoIzq.setPosition(445,270);
        posteEstaticoDer.setPosition(670,270);
        //sonidoCono = manager.get("Helado normal.mp3");
        sonidoConoDorado = manager.get("Helado especial.mp3");
        //muere = manager.get("Meeehhpp!!.wav");
        perder.setPosicion(Juego.ancho/4,Juego.alto/16);
        ganar.setPosicion(Juego.ancho/4,Juego.alto/16);
        fondoNubes.setPosicion(0,0);
        fondoNubes2.setPosicion(1280,0);

    }

    private void actualizarCamara(){
        float posX = pinguino.getX2();
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
        if(estadosJuego != EstadosJuego.PERDIO){
            actualizarCamara();
            moverPersonaje();
        }

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

            tiempo += Gdx.graphics.getDeltaTime();
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
            posteDer.draw(batch);
            posteIzq.draw(batch);
            posteDer1.draw(batch);
            posteIzq1.draw(batch);
            posteEstaticoDer.draw(batch);
            posteEstaticoIzq.draw(batch);
            fondoNubes.draw(batch);
            fondoNubes2.draw(batch);

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
            if(fondoNubes.getX() <= -1280){
                fondoNubes.setPosicion(1280,0);
            }
            if(fondoNubes2.getX() <= -1280){
                fondoNubes2.setPosicion(1280,0);
            }

            if(tiempo >= 3){
                cono.draw(batch);
                for(float i= 0.01f; i<1;i++) {
                    cono.setScale(cono.getScaleX() + j3, cono.getScaleY() + j3);
                    if(cono.getY() < -200){
                        cono.setPosition(610,400);
                        cono.setScale(0.2f,0.2f);
                    }
                }
                float xFondoCono = cono.getX();
                float yFondoCono = cono.getY();
                xFondoCono += velocidadItemX;
                yFondoCono += velocidadItemY;
                cono.setX(xFondoCono);
                cono.setY(yFondoCono);
            }
            if(tiempo >= 5){
                for(float i= 0.01f; i<1;i++){
                    conoDorado.setScale(conoDorado.getScaleX() + j3, conoDorado.getScaleY() + j3);
                    if(conoDorado.getY() < -200){
                        conoDorado.setPosition(550,400);
                        conoDorado.setScale(0.2f,0.2f);
                    }
                }
                conoDorado.draw(batch);
                float xFondoConoDor = conoDorado.getX();
                float yFondoConoDor = conoDorado.getY();
                xFondoConoDor -= velocidadItemX;
                yFondoConoDor += velocidadItemY;
                conoDorado.setX(xFondoConoDor);
                conoDorado.setY(yFondoConoDor);
                //moco.draw(batch);
                //moco2.draw(batch);
                //moco3.draw(batch);
            }

            timerAnimar +=Gdx.graphics.getDeltaTime();
            TextureRegion region = animacionBote.getKeyFrame(timerAnimar);
            botes1.setRegion(region);
            ///botes1.draw(batch);
            timerAnimar2 +=Gdx.graphics.getDeltaTime();
            TextureRegion region1 = animacionbote2.getKeyFrame(timerAnimar2);
            botes2.setRegion(region1);
            //botes2.draw(batch);
            if(tiempo >= 10){
                botes1.draw(batch);
                for(float i= 0.01f; i<1;i++){
                    botes1.setScale(botes1.getScaleX()+j3,botes1.getScaleY()+j3);
                    if(botes1.getY() <-200){
                        botes1.setPosition(550,300);
                        botes1.setScale(0.3f,0.3f);
                    }
                }
                float boteAnimado1x = botes1.getX();
                float boteAnimado1y = botes1.getY();
                boteAnimado1x -= velocidadBotex;
                boteAnimado1y += velocidadBotey;
                botes1.setX(boteAnimado1x);
                botes1.setY(boteAnimado1y);
            }
            if(tiempo >= 17){
                botes2.draw(batch);
                for(float i= 0.01f; i<1;i++){
                    botes2.setScale(botes2.getScaleX()+j3,botes2.getScaleY()+j3);
                    if(botes2.getY() < -200){
                        botes2.setScale(0.3f,0.3f);
                        botes2.setPosition(610,300);
                    }
                }
                float boteAnimado2x = botes2.getX();
                float boteAnimado2y = botes2.getY();
                boteAnimado2x += velocidadBotex;
                boteAnimado2y += velocidadBotey;
                botes2.setX(boteAnimado2x);
                botes2.setY(boteAnimado2y);
            }

            if(tiempo >= 21){
                moco2.draw(batch);
                moco3.draw(batch);
                for(float i= 0.01f; i<1;i++) {
                    moco2.setScale(moco2.getScaleX() + j3, moco2.getScaleY() + j3);
                    moco3.setScale(moco3.getScaleX() + j3, moco3.getScaleY() + j3);
                    if(moco2.getY() < -200){
                        moco2.setPosition(450,400);
                        moco2.setScale(0.3f,0.3f);
                    }
                    if(moco3.getY() < -200){
                        moco3.setPosition(670,400);
                        moco3.setScale(0.3f,0.3f);
                    }
                }
                float xmoco2 = moco2.getX();
                float ymoco2 = moco2.getY();
                xmoco2 -= velocidadMocox;
                ymoco2 += velocidadMocoy;
                moco2.setX(xmoco2);
                moco2.setY(ymoco2);

                float xmoco3 = moco3.getX();
                float ymoco3 = moco3.getY();
                xmoco3 += velocidadMocox;
                ymoco3 += velocidadMocoy;
                moco3.setX(xmoco3);
                moco3.setY(ymoco3);
            }
            for(float i= 0.01f; i<1;i++){
                posteIzq.setScale(posteIzq.getScaleX()+j2,posteIzq.getScaleY()+j2);
                posteDer.setScale(posteDer.getScaleX()+j2,posteDer.getScaleY()+j2);
                posteIzq1.setScale(posteIzq1.getScaleX()+j2,posteIzq1.getScaleY()+j2);
                posteDer1.setScale(posteDer1.getScaleX()+j2,posteDer1.getScaleY()+j2);
                if(posteIzq.getY() < -200){
                    posteIzq.setScale(0.3f,0.3f);
                    posteIzq.setPosition(450,300);
                }
                if(posteDer.getY() < -200){
                    posteDer.setScale(0.3f,0.3f);
                    posteDer.setPosition(670,300);
                }
                if(posteIzq1.getY() < -200){
                    posteIzq1.setScale(0.3f,0.3f);
                    posteIzq1.setPosition(450,300);
                }
                if(posteDer1.getY() < -200){
                    posteDer1.setScale(0.3f,0.3f);
                    posteDer1.setPosition(670,300);
                }
                if(moco.getY() < -200){
                    moco.setPosition(570,400);
                    moco.setScale(0.2f,0.2f);
                }

            }
        pinguino.renderNivel2(batch);



        float xNubes = fondoNubes.getX();
        xNubes -= velocidadNubes;
        fondoNubes.getSprite().setX(xNubes);
        float xNubes2 = fondoNubes2.getX();
        xNubes2 -= velocidadNubes;
        fondoNubes2.getSprite().setX(xNubes2);



        float ymoco = moco.getY();
        ymoco += velocidadMocoy;
        moco.setY(ymoco);


        float xFondo2 =  fondo2.getX();
        float yFondo2 = fondo2.getY();
        xFondo2 -= velocidadX;
        yFondo2 += velocidadY;
        fondo2.getSprite().setX(xFondo2);
        fondo2.getSprite().setY(yFondo2);

        float xFondoEx =  fondoEx.getX();
        xFondoEx -= velocidadX;
        fondoEx.getSprite().setX(xFondoEx);

        float xFondo4 =  fondo4.getX();
        float yFondo4 = fondo4.getY();
        xFondo4 += velocidadX;
        yFondo4 += velocidadY;
        fondo4.getSprite().setX(xFondo4);
        fondo4.getSprite().setY(yFondo4);

        float xFondoExI =  fondoExI.getX();
        xFondoExI += velocidadX;
        fondoExI.getSprite().setX(xFondoExI);

        float yFondoPiso1 = fondoPiso1.getY();
        yFondoPiso1 += velocidadY;
        fondoPiso1.getSprite().setY(yFondoPiso1);

        float xFondoCarre2 =  fondoCarre2.getX();
        float yFondoCarre2 = fondoCarre2.getY();
        xFondoCarre2 -= velocidadX;
        yFondoCarre2 += velocidadY;
        fondoCarre2.getSprite().setX(xFondoCarre2);
        fondoCarre2.getSprite().setY(yFondoCarre2);

        float xFondoCarre3 =  fondoCarre3.getX();
        xFondoCarre3 -= velocidadX;
        fondoCarre3.getSprite().setX(xFondoCarre3);

        float xFondoCarre5 =  fondoCarre5.getX();
        float yFondoCarre5 = fondoCarre5.getY();
        xFondoCarre5 -= velocidadX;
        yFondoCarre5 += velocidadY;
        fondoCarre5.getSprite().setX(xFondoCarre5);
        fondoCarre5.getSprite().setY(yFondoCarre5);

        float xFondoCarre6 =  fondoCarre6.getX();
        xFondoCarre6 -= velocidadX;
        fondoCarre6.getSprite().setX(xFondoCarre6);

        float xPosteIzq = posteIzq.getX();
        float yPosteIzq = posteIzq.getY();
        xPosteIzq -= velocidadPosteX;
        yPosteIzq += velocidadPosteY;
        posteIzq.setX(xPosteIzq);
        posteIzq.setY(yPosteIzq);

        float xPosteDer = posteDer.getX();
        float yPosteDer = posteDer.getY();
        xPosteDer += velocidadPosteX;
        yPosteDer += velocidadPosteY;
        posteDer.setX(xPosteDer);
        posteDer.setY(yPosteDer);

        float xPosteIzq1 = posteIzq1.getX();
        float yPosteIzq1 = posteIzq1.getY();
        xPosteIzq1 -= velocidadPosteX;
        yPosteIzq1 += velocidadPosteY;
        posteIzq1.setX(xPosteIzq1);
        posteIzq1.setY(yPosteIzq1);

        float xPosteDer1 = posteDer1.getX();
        float yPosteDer1 = posteDer1.getY();
        xPosteDer1 += velocidadPosteX;
        yPosteDer1 += velocidadPosteY;
        posteDer1.setX(xPosteDer1);
        posteDer1.setY(yPosteDer1);
        //PROBAR COLISIONES
        //la imagen actual menos la escala
        /*System.out.println("pinguino xinicial = " + pinguino.getX2());
        System.out.println("cono xinicial = " + (moco.getX()) +"\n");
        System.out.println("pinguino xfinal = " + pinguino.getY2());
        System.out.println("cono xfinal = " + (cono.getY()+cono.getHeight()) + "\n");*/
        //System.out.println("La escala en y es:" + cono.getY());*/
        if((pinguino.getX2() >= 660/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 710) && (pinguino.getX2() <= 870 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((cono.getY()-10) >=pinguino.getY2() &&(cono.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos += 500;
            sonidoCono.play();
            cono.setPosition(610,400);
            cono.setScale(0.2f,0.2f);
        }

        if((pinguino.getX2() >= 300/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 396) && (pinguino.getX2() <= 560 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((conoDorado.getY()-10) >=pinguino.getY2() &&(conoDorado.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos += 1000;
            sonidoConoDorado.play();
            conoDorado.setPosition(550,400);
            conoDorado.setScale(0.2f,0.2f);
        }

        if((pinguino.getX2() >= 300/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 396) && (pinguino.getX2() <= 560 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((botes1.getY()-10) >=pinguino.getY2() &&(botes1.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos -= 500;
            sonidoBote.play();
            botes1.setPosition(550,400);
            botes1.setScale(0.2f,0.2f);
        }

        if((pinguino.getX2() >= 660/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 710) && (pinguino.getX2() <= 870 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((botes2.getY()-10) >=pinguino.getY2() &&(botes2.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos -= 500;
            sonidoBote.play();
            botes2.setPosition(610,400);
            botes2.setScale(0.2f,0.2f);
        }

        if((pinguino.getX2() >= 0/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 124) && (pinguino.getX2() <= 130 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((moco2.getY()-10) >=pinguino.getY2() &&(moco2.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos -= 100;
            contador++;
            sonidoMoco.play();
            moco2.setPosition(450,400);
            moco2.setScale(0.3f,0.3f);
        }

        if((pinguino.getX2() >= 1156/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 1156) && (pinguino.getX2() <= 1280 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((moco3.getY()-10) >=pinguino.getY2() &&(moco3.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            pinguino.puntos -= 100;
            contador++;
            sonidoMoco.play();
            moco3.setPosition(670,400);
            moco3.setScale(0.3f,0.3f);
        }
        if((pinguino.getX2() >= 0/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 0) && (pinguino.getX2() <= 50 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((posteDer.getY()-10) >=pinguino.getY2() &&(posteDer.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            vidas --;
            muere.play();
            posteDer.setScale(0.3f,0.3f);
            posteDer.setPosition(670,300);
        }

        if((pinguino.getX2() >= 0/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 0) && (pinguino.getX2() <= 50 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((posteDer1.getY()-10) >=pinguino.getY2() &&(posteDer1.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            vidas --;
            muere.play();
            posteDer1.setScale(0.3f,0.3f);
            posteDer1.setPosition(670,300);
        }

        if((pinguino.getX2() >= 1200/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 1200) && (pinguino.getX2() <= 1280 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((posteIzq.getY()-10) >=pinguino.getY2() &&(posteIzq.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            vidas--;
            muere.play();
            posteIzq.setScale(0.3f,0.3f);
            posteIzq.setPosition(450,300);
        }

        if((pinguino.getX2() >= 1200/*cono.getX()*/ || pinguino.getX2()+pinguino.getSprite2().getWidth() > 1200) && (pinguino.getX2() <= 1280 /*(cono.getX()+cono.getWidth()) <= (pinguino.getX2()+pinguino.getSprite2().getWidth())*/)
                && ((posteIzq1.getY()-10) >=pinguino.getY2() &&(posteIzq1.getY()-10) < pinguino.getY2()+10  && pinguino.getY2() <= 10)
                && (pinguino.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO || pinguino.getEstadoSalto() != Personaje.EstadoSalto.BAJANDO ) ){
            vidas--;
            muere.play();
            posteIzq1.setScale(0.3f,0.3f);
            posteIzq1.setPosition(450,300);
        }

        if(contador == 3){
            vidas--;
            contador = 0;
        }



        batch.end();

        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
        if(estadosJuego == EstadosJuego.GANO ){
            ganar.render(batch);
            btnSiguiente.render(batch);
            btnSiguiente.setPosicion(1100,0);
        }
        else if(estadosJuego == EstadosJuego.PAUSADO){
            pausa.setPosition(Juego.ancho/4,Juego.alto/16);
            pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            j=0;
            j2=0;
            j3 = 0;
            cono.setScale(cono.getScaleX(),cono.getScaleY());
            conoDorado.setScale(conoDorado.getScaleX(),conoDorado.getScaleY());
            posteDer.setScale(posteDer.getScaleX(),posteDer.getScaleY());
            posteIzq.setScale(posteIzq.getScaleX(),posteIzq.getScaleY());
            posteDer1.setScale(posteDer1.getScaleX(),posteDer1.getScaleY());
            posteIzq1.setScale(posteIzq1.getScaleX(),posteIzq1.getScaleY());
            botes1.setScale(botes1.getScaleX(),botes1.getScaleY());
            botes2.setScale(botes2.getScaleX(),botes2.getScaleY());
            moco.setScale(moco.getScaleX(),moco.getScaleY());
            moco2.setScale(moco2.getScaleX(),moco2.getScaleY());
            moco3.setScale(moco3.getScaleX(),moco3.getScaleY());
            pausa.draw(batch);
            pinguino.tiempoAnimar = 0;
            pinguino.setPosicion2(pinguino.getX2(),pinguino.getY2());
            velocidadX = 0;
            velocidadY = 0;
            velocidadItemY = 0;
            velocidadPosteX = 0;
            velocidadPosteY = 0;
            velocidadItemX = 0;
            velocidadBotex = 0;
            velocidadBotey = 0;
            velocidadPinguino = 0;
            velocidadMocox = 0;
            velocidadMocoy = 0;
            timerAnimar = 0;
            timerAnimar2 = 0;
            btnReanudar.setPosicion(Juego.ancho/3,Juego.alto*0.45f);
            btnSalir.setPosicion(Juego.ancho/3,Juego.alto*0.15f);
            btnReanudar.render(batch);
            btnSalir.render(batch);
        }

        else if(estadosJuego == EstadosJuego.PERDIO){
            perder.render(batch);
            //perder.draw(batch,delta);
            btnRegreso.render(batch);
            btnRegreso.setPosicion(0,0);
        }
        else{
            btnSaltar.render(batch);
            //btnDisparar.render(batch);
            btnPausa.render(batch);
            btnDer.render(batch);
            btnIzq.render(batch);
            marcador.draw(batch);
            texto.mostrarMensaje(batch," " + pinguino.puntos,150,Juego.alto * 0.93f);
            texto.mostrarMensaje(batch," " + vidas,150,Juego.alto * 0.85f);
        }
        batch.end();
    }

    public void moverPersonaje(){
        switch (pinguino.getEstadoMovimiento()){
            case DER:
                float x = 0;
                if(pinguino.getX2() >= 1034){
                    x = pinguino.getSprite2().getX();
                    pinguino.getSprite2().setX(x);
                }
                else{
                    x = pinguino.getSprite2().getX()+velocidadPinguino;
                    pinguino.getSprite2().setX(x);
                }
                break;
            case IZQ:
                float x1 = 0;
                if(pinguino.getX2() <= 0){
                    x1 = pinguino.getSprite2().getX();
                    pinguino.getSprite2().setX(x1);
                }
                else{
                    x1 = pinguino.getSprite2().getX()-velocidadPinguino;
                    pinguino.getSprite2().setX(x1);
                }
                break;
        }
        if(vidas == 0 || pinguino.puntos < 0){
            estadosJuego = EstadosJuego.PERDIO;
            pinguino.setPosicion2(-1000,0);

        }
        if(tiempo >= 75 && pinguino.puntos > 0){
            estadosJuego = EstadosJuego.GANO;
        }

        switch(pinguino.getEstadoSalto()){
            case SUBIENDO: case BAJANDO:
                pinguino.actualizarSalto2();
                break;
        }

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
        dispose();
    }

    @Override
    public void dispose() {
        AssetManager manager = juego.getManager();
        manager.unload("ciudadnivel2.png");
        manager.unload("Carretera.png");
        manager.unload("edificiosIZQ.png");
        manager.unload("edificiosDER.png");
        manager.unload("banquetaIZQ.png");
        manager.unload("banquetaDER.png");
        manager.unload("cono.png");
        manager.unload("cono_dor.png");
        manager.unload("BtnPausa.png");
        manager.unload("Btnsalto_Naranja.png");
        manager.unload("CuadroScore.png");
        manager.unload("Pausa.png");
        manager.unload("Perdiste_1.png");
        manager.unload("Ganaste_1.png");
        manager.unload("BTN_Resumen.png");
        manager.unload("BTN_Salir.png");
        manager.unload("poste.png");
        manager.unload("poste_der.png");
        manager.unload("moco.png");
        manager.unload("Btnder_Naranja.png");
        manager.unload("Btnizq_Naranja.png");
        manager.unload("Sprite_deslizar.png");
        manager.unload("bote1.png");
        manager.unload("bote2.png");
        manager.unload("SpriteBote.png");
        manager.unload("SpriteBote2.png");
        manager.unload("cono_dorprueba.png");
        manager.unload("conoprueba.png");
        manager.unload("mocoprueba.png");
        manager.unload("Helado especial.mp3");
        manager.unload("Helado normal.mp3");
        manager.unload("Meeehhpp!!.wav");
        manager.unload("botonSiguiente.png");
        manager.unload("botonRegresar.png");
        manager.unload("deslizar_der.png");
        manager.unload("deslizar_izq.png");
        manager.unload("Mocos.wav");
        manager.unload("Basura.wav");
        manager.unload("Cono.wav");
        manager.unload("Capa-Nubes.png");
        manager.unload("Nivel2.mp3");
    }

    //public class ProcesadorEntrada extends InputAdapter{

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
            transformarCoordenadas(screenX,screenY);
            if(btnSaltar.contiene(x,y)){
                pinguino.saltar2();
            }
            if(btnDer.contiene(x,y)){
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.DER);
            }
            if(btnIzq.contiene(x,y)){
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.IZQ);
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
                pausa.setPosition(-13444,-12435);
                j=0.004f;
                j2 = 0.015f;
                j3=0.009f;
                velocidadX = 5;
                velocidadY = -5;
                velocidadItemY = -3;
                velocidadItemX = 1;
                velocidadPosteX = 5;
                velocidadPosteY = -3;
                velocidadPinguino = 10;
                velocidadBotex = 1;
                velocidadBotey = -3;
                velocidadMocox = 3;
                velocidadMocoy = -5;
            }
            else if(btnSalir.contiene(x,y)){
                juego.setScreen(new MenuPrincipal(juego));
                //musica.setVolume(0);
                //musica.stop();
            }
            if(estadosJuego == EstadosJuego.PERDIO && btnRegreso.contiene(x,y)){
                juego.setScreen(new Nivel2(juego));
            }
            if(estadosJuego == EstadosJuego.GANO && btnSiguiente.contiene(x,y)){
                juego.setScreen(new PantallaInstrucciones3(juego));
                //musica.setVolume(0);
                //musica.stop();
            }
                //musica.setVolume(0);
                //musica.stop();


            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX,screenY);
            if(btnDer.contiene(x,y)){
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
            if(btnIzq.contiene(x,y)){
                pinguino.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
            return true;
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

    private void transformarCoordenadas(int screenX, int screenY){
            coordenadas.set(screenX, screenY,0);
            camaraHUD.unproject(coordenadas);
            x = coordenadas.x;
            y = coordenadas.y;
        }
    //}
}
