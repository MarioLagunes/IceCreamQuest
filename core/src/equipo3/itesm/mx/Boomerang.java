package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

/**
 * Created by Mario Lagunes on 10/10/2016.
 */

public class Boomerang {

    private Boomerang boomerang;
    private Animation animacion;
    private float tiempoAnimar;
    private Sprite sprite;
    private boom estadosBoomerang;
    private float VelocidadX;
    private static final float resistencia = 9.8f;
    private static final float mediaResis = resistencia/2;
    private float xInicial;
    private float tiempoDistancia;
    private float tiempoTotal;
    private Batch batch;


    public Boomerang(Texture textura){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaBoomeran = texturaCompleta.split(128,128);
        animacion = new Animation(0.10f,texturaBoomeran[0][1], texturaBoomeran[0][2], texturaBoomeran[0][3],
                texturaBoomeran[0][4],texturaBoomeran[0][5],texturaBoomeran[0][6],texturaBoomeran[0][7]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimar = 0;
        sprite = new Sprite(texturaBoomeran[0][0]);
        estadosBoomerang = boom.GUARDADO;

    }

    /*public void draw(Boomerang boomerang){
        sprite.draw(batch);
    }*/

    /*public void actualizar(){
        VelocidadX = 10;
        tiempoAnimar += Gdx.graphics.getDeltaTime();
        TextureRegion region = animacion.getKeyFrame(tiempoAnimar);
        if(region.isFlipX()){
            region.flip(true,false);
        }
        batch.draw(region,sprite.getX(),sprite.getY());
    }*/

    public void render(SpriteBatch batch){
        switch (estadosBoomerang){
            case LANZADO:
                VelocidadX += 10;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(tiempoAnimar);
                if(region.isFlipX()){
                    region.flip(true,false);
                }
                batch.draw(region,sprite.getX()+VelocidadX,sprite.getY());
                break;
            case REGRESANDO:
                VelocidadX -= 10;
                tiempoAnimar -= Gdx.graphics.getDeltaTime();
                TextureRegion region1 = animacion.getKeyFrame(tiempoAnimar);
                if(region1.isFlipX()){
                    region1.flip(true,false);
                }
                batch.draw(region1,sprite.getX()+VelocidadX,sprite.getY());
                break;
            case GUARDADO:
                VelocidadX = 0;
                break;
        }


    }

    public void actualizarBoom(){
        float nuevaX = sprite.getX();
        switch(estadosBoomerang){
            case LANZADO:
                nuevaX += VelocidadX;
                if(nuevaX <= Nivel1.ancho_mapa - sprite.getWidth() ){
                    sprite.setX(nuevaX);
                }
                break;
            case REGRESANDO:
                nuevaX -= VelocidadX;
                if(nuevaX <= Nivel1.ancho_mapa - sprite.getWidth() ){
                    sprite.setX(nuevaX);
                }
                break;
        }
    }

    public void actualizarSalir(){
        float x = VelocidadX * tiempoTotal - mediaResis * tiempoTotal * tiempoTotal;
        if(tiempoTotal > tiempoDistancia/2){
            estadosBoomerang = estadosBoomerang.GUARDADO;
        }
        tiempoTotal += 10 * Gdx.graphics.getDeltaTime();
        sprite.setX(xInicial + 100);
        if(x > 10){
            sprite.setX(xInicial);
            estadosBoomerang = estadosBoomerang.REGRESANDO;
        }
    }

    public void salir(){
        if(estadosBoomerang == estadosBoomerang.GUARDADO){
            tiempoTotal = 0;
            xInicial = sprite.getX();
            estadosBoomerang = estadosBoomerang.LANZADO;
            tiempoDistancia = 2 * VelocidadX / resistencia;
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

    public boom getBoom(){
        return estadosBoomerang;
    }

    public void setBoom(boom estadosBoomerang){
        this.estadosBoomerang = estadosBoomerang;
    }

    public enum boom{
        GUARDADO,
        LANZADO,
        EN_CAMINO,
        REGRESANDO
    }
}
