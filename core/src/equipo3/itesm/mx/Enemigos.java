package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mario Lagunes on 10/11/2016.
 */

public class Enemigos {
    private Sprite sprite;
    private Animation animarDer,animarIzq;
    private float timerAnimarDer,timerAnimarIzq,x;
    public float velocidad = 2;
    private EstadoEnemigo estadoEnemigo;

    public enum EstadoEnemigo{
        INICIO,
        DERECHA,
        IZQUIERDA,
        MUERTO
    }

    public Enemigos(Texture texturaDer, Texture texturaIzq){
        TextureRegion texturaFullDer = new TextureRegion(texturaDer);
        TextureRegion texturaFullIzq = new TextureRegion(texturaIzq);
        TextureRegion[][] texturaSepDer = texturaFullDer.split(64,64);
        TextureRegion[][] texturaSepIzq = texturaFullIzq.split(64,64);
        animarDer = new Animation(0.10f,texturaSepDer[0][0],texturaSepDer[0][1],texturaSepDer[0][2],texturaSepDer[0][3],texturaSepDer[0][4],texturaSepDer[0][5]);
        animarIzq = new Animation(0.10f,texturaSepIzq[0][6],texturaSepIzq[0][5],texturaSepIzq[0][4],texturaSepIzq[0][3],texturaSepIzq[0][2],texturaSepIzq[0][1]);
        animarDer.setPlayMode(Animation.PlayMode.LOOP);
        animarIzq.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimarDer = 0;
        timerAnimarIzq = 0;
        sprite = new Sprite(texturaSepDer[0][0]);
    }

    public void render(SpriteBatch batch){
        if(estadoEnemigo == EstadoEnemigo.DERECHA){
            timerAnimarDer += Gdx.graphics.getDeltaTime();
            TextureRegion region = animarDer.getKeyFrame(timerAnimarDer);
            x = sprite.getX()+velocidad;
            sprite.setX(x);
            sprite.setRegion(region);
        }
        if(estadoEnemigo == EstadoEnemigo.IZQUIERDA){
            timerAnimarIzq += Gdx.graphics.getDeltaTime();
            TextureRegion region = animarIzq.getKeyFrame(timerAnimarIzq);
            x = sprite.getX()-velocidad;
            sprite.setX(x);
            sprite.setRegion(region);
        }
        batch.draw(sprite,sprite.getX(),sprite.getY());
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

    public void setPosicion(float x, float y){
        sprite.setPosition(x,y);
    }

    public void setEstadoEnemigo(EstadoEnemigo estadoEnemigo){
        this.estadoEnemigo = estadoEnemigo;
    }
}
