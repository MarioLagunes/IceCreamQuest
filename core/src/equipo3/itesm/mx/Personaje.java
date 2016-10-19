package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Mario Lagunes(3) on 26/09/2016.
 */
public class Personaje {
    private Sprite sprite,spriteEnemigo;
    public static final float velocidadY = -4f;
    public static  float velocidadX,velX;
    private Animation animacion,animar;
    private float timerAnimacion,tiempoAnimar;
    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;
    private EstadosEnemigo estadoEnemigo;
    private static final float V0 = 60;
    private static final float G = 9.81f;
    private static final float G_2 = G/2;
    private float yInicial;
    private float tiempoVuelo;
    private  float tiempoSalto;

    public enum EstadoSalto {
        ABAJO,
        SUBIENDO,
        BAJANDO,
        CAIDALIBRE
    }

    public enum EstadosEnemigo{
        INICIO,
        DERECHA,
        IZQUIERDA
    }

    public  Personaje(Texture textura){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(128,128);
        animacion = new Animation(0.10f,texturaPersonaje[0][1], texturaPersonaje[0][2], texturaPersonaje[0][3],
                texturaPersonaje[0][4],texturaPersonaje[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;

        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.ABAJO;
    }

    public void PersonajeEnemigo(Texture textur,float vel){
        TextureRegion texturaEnemigoFull = new TextureRegion(textur);
        TextureRegion[][] texturaEnemigo = texturaEnemigoFull.split(128,128);
        animar = new Animation(0.10f,texturaEnemigo[0][1],texturaEnemigo[0][2],texturaEnemigo[0][3],
                texturaEnemigo[0][4],texturaEnemigo[0][5]);
        animar.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimar = 0;
        spriteEnemigo = new Sprite(texturaEnemigo[0][0]);
        estadoEnemigo = EstadosEnemigo.INICIO;
    }

    public void render(SpriteBatch batch){
        switch (estadoMovimiento){
            case DER:
                velocidadX = 2;
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(timerAnimacion);
                if(region.isFlipX()){
                    region.flip(true,false);
                }
                batch.draw(region,sprite.getX(),sprite.getY());
                break;
            case IZQ:
                velocidadX = 2;
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region2 = animacion.getKeyFrame(timerAnimacion);
                if(region2.isFlipX()){
                    region2.flip(true,false);
                }
                batch.draw(region2,sprite.getX(),sprite.getY());
                break;
            case INICIANDO:
                velocidadX = 0;
                sprite.draw(batch);
                break;
            case QUIETO:
                velocidadX = 0;
                quieto(batch);
        }
    }

    public void renderEnemigo(SpriteBatch batch){
        switch(estadoEnemigo){
            case DERECHA:
                velX += 5;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region = animar.getKeyFrame(tiempoAnimar);
                if(region.isFlipX()){
                    region.flip(true,false);
                }
                batch.draw(region,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                if(velX >= 500){
                    moverEnemigosIzq();
                }
                break;
            case IZQUIERDA:
                velX -= 5;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region1 = animar.getKeyFrame(tiempoAnimar);
                if(region1.isFlipX()){
                    region1.flip(true,false);
                }
                batch.draw(region1,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                if(velX <= -500){
                    moverEnemigosDer();
                }
                break;
            case INICIO:
                velX = 0;
                break;
        }
    }


    public void actualizar(){
        float nuevaX = sprite.getX();
        switch(estadoMovimiento){
            case DER:
                nuevaX += velocidadX;
                if(nuevaX <= Nivel1.ancho_mapa - sprite.getWidth() ){
                    sprite.setX(nuevaX);
                }
                break;
        }
    }

    public void caer(){
        sprite.setY(sprite.getY() + velocidadY);
    }

    public void quieto(Batch batch){
        sprite.draw(batch);
    }

    public void actualizarSalto(){
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if(tiempoSalto > tiempoVuelo/2){
            estadoSalto = EstadoSalto.BAJANDO;
        }
        tiempoSalto += 10 * Gdx.graphics.getDeltaTime();
        sprite.setY(yInicial + y);
        if(y < 0){
            sprite.setY(yInicial);
            estadoSalto = EstadoSalto.ABAJO;
        }
    }

    public Sprite getSprite(){
        return sprite;
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public void setPosicion(float x, int y){
        sprite.setPosition(x,y);
    }

    public void setEstadoEnemigo(EstadosEnemigo estadoEnemigo){
        this.estadoEnemigo = estadoEnemigo;
    }

    public EstadoMovimiento getEstadoMovimiento(){
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento){
        this.estadoMovimiento = estadoMovimiento;
    }

    public void setEstadoSalto(EstadoSalto estadoSalto){
        this.estadoSalto = estadoSalto;
    }

    public void saltar(){
        if(estadoSalto == EstadoSalto.ABAJO){
            tiempoSalto = 0;
            yInicial = sprite.getY();
            estadoSalto = EstadoSalto.SUBIENDO;
            tiempoVuelo = 2 * V0 / G;
        }
    }

    public EstadoSalto getEstadoSalto(){
        return estadoSalto;
    }

    public void moverEnemigosDer(){
        estadoEnemigo = EstadosEnemigo.DERECHA;
    }

    public void moverEnemigosIzq(){
        estadoEnemigo = EstadosEnemigo.IZQUIERDA;
    }

    public enum EstadoMovimiento {
        INICIANDO,
        QUIETO,
        DER,
        IZQ
    }
}
