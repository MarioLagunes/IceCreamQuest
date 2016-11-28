package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mario Lagunes on 10/10/2016.
 */

public class Boomerang {

    private Boomerang boomerang;
    private Animation animacion,animacionRegreso,animacionNivel3,animacionRegresoNivel3;
    private float tiempoAnimar,tiempoAnimarReg,tiempoAnimarNivel3,tiempoAnimarRegNivel3;
    private Sprite sprite;
    private boom estadosBoomerang;
    private float VelocidadX;
    private static final float resistencia = 9.8f;
    private static final float mediaResis = resistencia/2;
    private float x,y;
    public float velocidad = 20;
    public float tiempo = 0;
    private float xInicial;
    private float tiempoDistancia;
    private float tiempoTotal;
    private Batch batch;


    public Boomerang(Texture textura){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaBoomeran = texturaCompleta.split(64,64);
        animacion = new Animation(0.10f,texturaBoomeran[0][1], texturaBoomeran[0][2], texturaBoomeran[0][3],
                texturaBoomeran[0][4]);
        animacionRegreso = new Animation(0.10f,texturaBoomeran[0][5],texturaBoomeran[0][6],texturaBoomeran[0][7]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        animacionRegreso.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimar = 0;
        tiempoAnimarReg = 0;
        sprite = new Sprite(texturaBoomeran[0][0]);
        estadosBoomerang = boom.GUARDADO;

    }

    public Boomerang(Texture textura3,int z){

        TextureRegion texturaCompleta = new TextureRegion(textura3);
        TextureRegion[][] texturaBoomeran = texturaCompleta.split(64,64);
        animacionNivel3 = new Animation(0.10f,texturaBoomeran[1][0], texturaBoomeran[2][0], texturaBoomeran[3][0],
                texturaBoomeran[4][0]);
        animacionRegresoNivel3 = new Animation(0.10f,texturaBoomeran[5][0],texturaBoomeran[6][0],texturaBoomeran[7][0]);
        animacionNivel3.setPlayMode(Animation.PlayMode.LOOP);
        animacionRegresoNivel3.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimarNivel3 = 0;
        tiempoAnimarRegNivel3 = 0;
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

    public void renderNivel3 (SpriteBatch batch){
        if(estadosBoomerang == boom.LANZADO){
            tiempoAnimarNivel3 += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacionNivel3.getKeyFrame(tiempoAnimarNivel3);
            y = sprite.getY()+velocidad;
            sprite.setY(y);
            sprite.setRegion(region);
        }
        if (estadosBoomerang == boom.REGRESANDO){
            tiempoAnimarReg += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacionRegresoNivel3.getKeyFrame(tiempoAnimarReg);
            y = sprite.getY()-velocidad;
            sprite.setY(y);
            sprite.setRegion(region);
        }
        if(estadosBoomerang == boom.GUARDADO){
            velocidad = 0;
        }
        batch.draw(sprite,sprite.getX(),sprite.getY());

    }

    public void render(SpriteBatch batch){

        if(estadosBoomerang == boom.LANZADO){
            tiempoAnimar += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacion.getKeyFrame(tiempoAnimar);
            x = sprite.getX()+velocidad;
            sprite.setX(x);
            sprite.setRegion(region);
        }
        if(estadosBoomerang == boom.REGRESANDO){
            tiempoAnimarReg += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacionRegreso.getKeyFrame(tiempoAnimarReg);
            x = sprite.getX()-velocidad;
            sprite.setX(x);
            sprite.setRegion(region);
        }
        if(estadosBoomerang == boom.GUARDADO){
            velocidad = 0;
        }
        batch.draw(sprite,sprite.getX(),sprite.getY());
        /*switch (estadosBoomerang){
            case LANZADO:
                VelocidadX += 20;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(tiempoAnimar);
                if(region.isFlipX()){
                    region.flip(true,false);
                }
                batch.draw(region,sprite.getX()+VelocidadX,sprite.getY());
                if(VelocidadX >= 1000.0){
                    regresar();
                }
                break;
            case REGRESANDO:
                VelocidadX -= 20;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region1 = animacionRegreso.getKeyFrame(tiempoAnimar);
                if(region1.isFlipX()){
                    region1.flip(true,false);
                }
                batch.draw(region1,sprite.getX()+VelocidadX,sprite.getY());
                if(VelocidadX <= 400){
                    destruirBoomerang();
                }
                break;
            case GUARDADO:
                VelocidadX = 0;
                break;
        }*/


    }

    private void destruirBoomerang() {
        estadosBoomerang = estadosBoomerang.GUARDADO;
    }

    /*public void actualizarBoom(){
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
                if(nuevaX >= Nivel1.ancho_mapa - sprite.getWidth() ){
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
    }*/

    public void salir(){
        //if(estadosBoomerang == estadosBoomerang.GUARDADO){
            //tiempoTotal = 0;
            //xInicial = sprite.getX();
            estadosBoomerang = estadosBoomerang.LANZADO;
            //tiempoDistancia = 2 * VelocidadX / resistencia;
        //}
    }

    public float getVelocidadX(){
        return VelocidadX;
    }

    public void regresar(){
        //if(estadosBoomerang == estadosBoomerang.LANZADO){
            //tiempoTotal = 0;
            //xInicial = sprite.getX();
            estadosBoomerang = estadosBoomerang.REGRESANDO;
            //tiempoDistancia = 2 * VelocidadX / resistencia;
        //}
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
