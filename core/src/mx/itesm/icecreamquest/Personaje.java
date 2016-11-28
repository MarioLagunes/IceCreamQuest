package mx.itesm.icecreamquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


/**
 * Created by Mario Lagunes(3) on 26/09/2016.
 */
public class Personaje {
    private Sprite sprite,spriteNivel2;
    public static final float velocidadY = -4f;
    public float velocidadX = 4;
    private Animation animacion,animar,animarSalto,animarIzq,animarSaltoIZQ,animarDer2,animarIzq2;
    public float timerAnimacion,tiempoAnimar,timerSalto,timerAnimacion3,timerSalto3,timerIzq,timerSaltoIzq,tiempoAnimarIzq,tiempoAnimarDer;
    private EstadoMovimiento estadoMovimiento = EstadoMovimiento.INICIANDO;
    private EstadoSalto estadoSalto = EstadoSalto.ABAJO;
    private static final float V0 = 70.0f;
    private static final float G = 9.81f;
    private static final float G_2 = G/2f;
    private float yInicial;
    private float tiempoVuelo;
    private  float tiempoSalto;
    public int puntos;
    float xavance,x;

    public enum EstadoSalto {
        ABAJO,
        SUBIENDO,
        BAJANDO,
        CAIDALIBRE
    }

    public  Personaje(Texture textura,Texture texturaSaltos,Texture texturaIzq,Texture texuraSaltoIzq){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion texturaSalto = new TextureRegion(texturaSaltos);
        TextureRegion texturaIzquierda = new TextureRegion(texturaIzq);
        TextureRegion texturaSaltoIZQ = new TextureRegion(texuraSaltoIzq);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(64,64);
        TextureRegion[][] texturaSaltar = texturaSalto.split(64,64);
        TextureRegion[][] texturaIzqui = texturaIzquierda.split(64,64);
        TextureRegion[][] texturaSaltoIzqui = texturaSaltoIZQ.split(64,64);
        animacion = new Animation(0.10f,texturaPersonaje[0][1], texturaPersonaje[0][2], texturaPersonaje[0][3],
                texturaPersonaje[0][4],texturaPersonaje[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        animarSalto = new Animation(0.10f,texturaSaltar[0][1]);
        animarSalto.setPlayMode(Animation.PlayMode.LOOP);
        animarIzq = new Animation(0.10f,texturaIzqui[0][5],texturaIzqui[0][4],texturaIzqui[0][3],texturaIzqui[0][2],texturaIzqui[0][1],texturaIzqui[0][0]);
        animarIzq.setPlayMode(Animation.PlayMode.LOOP);
        animarSaltoIZQ = new Animation(0.10f,texturaSaltoIzqui[0][1]);
        animarSaltoIZQ.setPlayMode(Animation.PlayMode.LOOP);
        timerSalto = 0;
        timerAnimacion = 0;
        timerAnimacion3 = 0;
        timerSalto3 = 0;
        timerIzq = 0;
        timerSaltoIzq = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
    }

    public Personaje(Texture textura2,Texture texturaDer,Texture texturaIzq){
        TextureRegion textPinguino2 = new TextureRegion(textura2);
        TextureRegion textPingDer = new TextureRegion(texturaDer);
        TextureRegion textPinIzq = new TextureRegion(texturaIzq);
        TextureRegion[][] texturaPingu2 = textPinguino2.split(246,190);
        TextureRegion[][] animarPinDer = textPingDer.split(246,190);
        TextureRegion[][] animarPinIzq = textPinIzq.split(246,190);
        animar = new Animation(0.10f,texturaPingu2[0][1],texturaPingu2[0][2],texturaPingu2[0][3],texturaPingu2[0][4],texturaPingu2[0][5]);
        animar.setPlayMode(Animation.PlayMode.LOOP);
        animarDer2 = new Animation(0.10f,animarPinDer[0][0],animarPinDer[0][1],animarPinDer[0][2],animarPinDer[0][3]);
        animarDer2.setPlayMode(Animation.PlayMode.LOOP);
        animarIzq2 = new Animation(0.10f,animarPinIzq[0][3],animarPinIzq[0][2],animarPinIzq[0][1],animarPinIzq[0][0]);
        animarIzq2.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimarIzq = 0;
        tiempoAnimarDer = 0;
        tiempoAnimar = 0;
        spriteNivel2 = new Sprite(texturaPingu2[0][0]);
    }

    public void render(SpriteBatch batch){
        if(estadoMovimiento == EstadoMovimiento.DER && estadoSalto == EstadoSalto.ABAJO){
            timerAnimacion += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacion.getKeyFrame(timerAnimacion);
            sprite.setRegion(region);
        }
        if(estadoMovimiento == EstadoMovimiento.IZQ && estadoSalto == EstadoSalto.ABAJO){
            timerIzq += Gdx.graphics.getDeltaTime();
            TextureRegion region = animarIzq.getKeyFrame(timerIzq);
            sprite.setRegion(region);
        }
        if(estadoMovimiento == EstadoMovimiento.DER && (estadoSalto == EstadoSalto.SUBIENDO || estadoSalto == EstadoSalto.BAJANDO || estadoSalto == EstadoSalto.CAIDALIBRE)){
            timerSalto += Gdx.graphics.getDeltaTime();
            TextureRegion regionSalto = animarSalto.getKeyFrame(timerSalto);
            sprite.setRegion(regionSalto);
        }
        if(estadoMovimiento == EstadoMovimiento.IZQ && (estadoSalto == EstadoSalto.SUBIENDO || estadoSalto == EstadoSalto.BAJANDO || estadoSalto == EstadoSalto.CAIDALIBRE)){
            timerSaltoIzq += Gdx.graphics.getDeltaTime();
            TextureRegion regionSalto = animarSaltoIZQ.getKeyFrame(timerSaltoIzq);
            sprite.setRegion(regionSalto);
        }

        sprite.draw(batch);
    }

    public void renderNivel2(SpriteBatch batch){

        if(estadoMovimiento == EstadoMovimiento.DER){
            tiempoAnimarDer += Gdx.graphics.getDeltaTime();
            TextureRegion region = animarDer2.getKeyFrame(tiempoAnimarDer);
            spriteNivel2.setRegion(region);
        }
        else if(estadoMovimiento == EstadoMovimiento.IZQ){
            tiempoAnimarIzq += Gdx.graphics.getDeltaTime();
            TextureRegion region = animarIzq2.getKeyFrame(tiempoAnimarIzq);
            spriteNivel2.setRegion(region);
        }
        else{
            tiempoAnimar += Gdx.graphics.getDeltaTime();
            TextureRegion region = animar.getKeyFrame(tiempoAnimar);
            spriteNivel2.setRegion(region);
        }
        spriteNivel2.draw(batch);
    }

    public void recolectarHelados(TiledMap mapa, Sound helado, Sound heladoEspecial){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(1);
        int x = (int)(sprite.getX()/64);
        int y = (int)(sprite.getY()/64);
        TiledMapTileLayer.Cell celda = capa.getCell(x,y);
        if(celda != null){
            Object tipo = celda.getTile().getProperties().get("tipo");
            if("helado".equals(tipo)){
                puntos += 500;
                if(Nivel1.ajusteSonido == false){
                    helado.play();
                }
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
            }
            else if("heladoespecial".equals(tipo)){
                puntos += 1000;
                if(Nivel1.ajusteSonido == false){
                    heladoEspecial.play();
                }
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
            }
        }
    }
    public void recolectarToppings(TiledMap mapa,Sound topping){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(1);
        int x = (int)(sprite.getX()/64);
        int y = (int)(sprite.getY()/64);
        TiledMapTileLayer.Cell celda = capa.getCell(x,y);
        if(celda != null){
            Object tipo = celda.getTile().getProperties().get("tipo");
            if("cereza".equals(tipo)){
                puntos += 500;
                if(Nivel3.ajusteSonido == false){
                    topping.play();
                }
                capa.setCell(x,y,null);
                capa.setCell(x,y,capa.getCell(0,4));
            }
            else if("chispas".equals(tipo)){
                puntos += 1000;
                if(Nivel3.ajusteSonido == false){
                    topping.play();
                }
                capa.setCell(x,y,null);
                capa.setCell(x,y,capa.getCell(0,4));
            }
        }
    }

    /*public void moverHorizontal(TiledMap mapa){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(0);
        float nuevaX = sprite.getX();
        if(estadoMovimiento == EstadoMovimiento.DER){
            int x = (int)((sprite.getX()+128)/128);
            int y = (int)(sprite.getY()/128);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(x,y);
            if(celdaDerecha != null){
                Object tipo = celdaDerecha.getTile().getProperties().get("tipo");
                if(!"esCuadroPiso".equals(tipo)){
                    celdaDerecha = null;
                }
            }
            if(celdaDerecha == null){
                nuevaX += velocidadX;
                if(nuevaX <= Juego.ancho-sprite.getWidth()){
                    sprite.setX(nuevaX);
                    probarCaida(mapa);
                }
            }
        }
    }*/


    public void probarCaida(TiledMap mapa){
        boolean hayCeldaAbajo = leerCeldaAbajo(mapa);
            if(!hayCeldaAbajo){
                caer();
                //estadoSalto = EstadoSalto.BAJANDO;
            }
    }

    public void caer(){
        timerAnimacion = 0;
        sprite.setY(sprite.getY() + velocidadY);
    }

    /*public void caer(TiledMap mapa,float desplazamiento){
        boolean hayCeldaAbajo = leerCeldaAbajo(mapa);
        if(!hayCeldaAbajo){
            sprite.setY(sprite.getY()+desplazamiento);
        }
        else{
            estadoMovimiento = EstadoMovimiento.QUIETO;
            estadoSalto = EstadoSalto.ABAJO;
        }
    }*/

    public void quieto(Batch batch){
        sprite.draw(batch);
    }

    private boolean leerCeldaAbajo(TiledMap mapa){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(0);
        int x = (int)((sprite.getX())/64);
        int y = (int)((sprite.getX()+velocidadY)/64);
        TiledMapTileLayer.Cell celdaAbajo = capa.getCell(x,y);
        if(celdaAbajo != null){
            Object tipo = celdaAbajo.getTile().getProperties().get("tipo");
            if(!"esCuadroPiso".equals(tipo)){
                celdaAbajo = null;
            }
        }
        TiledMapTileLayer.Cell celdaAbajoDerecha = capa.getCell(x+1,y);
        if(celdaAbajoDerecha != null){
            Object tipo = celdaAbajoDerecha.getTile().getProperties().get("tipo");
            if(!"esCuadroPiso".equals(tipo)){
                celdaAbajoDerecha = null;
            }
        }
        return celdaAbajo!=null || celdaAbajoDerecha != null;
    }

    public void actualizarSalto(TiledMap mapa){
        timerAnimacion = 0;
        tiempoSalto += 10*Gdx.graphics.getDeltaTime();
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if(tiempoSalto > tiempoVuelo/2){
            estadoSalto = EstadoSalto.BAJANDO;
            //probarCaida(mapa);
        }
        if(estadoSalto == EstadoSalto.SUBIENDO){
            sprite.setY(yInicial+y);
        }
        else if(estadoSalto == EstadoSalto.BAJANDO){
            boolean hayCeldaAbajo = leerCeldaAbajo(mapa);
            if(hayCeldaAbajo){
                estadoSalto = EstadoSalto.ABAJO;
            }
            else{
                sprite.setY(sprite.getY()+velocidadY);
                //probarCaida(mapa);
            }
        }
        if(y<0){
            sprite.setY(yInicial);
            estadoSalto = EstadoSalto.ABAJO;
        }
        /*tiempoSalto += 10*Gdx.graphics.getDeltaTime();
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if(estadoSalto == EstadoSalto.SUBIENDO && tiempoSalto > tiempoVuelo/2){
            estadoSalto = estadoSalto.BAJANDO;
        }*/
        //if(tiempoSalto > tiempoVuelo/2){
          //  estadoSalto = EstadoSalto.BAJANDO;
        //}
        /*if(estadoSalto == EstadoSalto.SUBIENDO){
            sprite.setY(sprite.getY()-velocidadY);
        }
        else if(estadoSalto == EstadoSalto.BAJANDO){
            boolean hayCeldaAbajo = leerCeldaAbajo(mapa);
            if(hayCeldaAbajo){
                estadoSalto = EstadoSalto.ABAJO;
            }
            else{
                sprite.setY(sprite.getY()+velocidadY);
            }
        }
        /*if(y<0){
            sprite.setY(yInicial);
            estadoSalto = EstadoSalto.ABAJO;
        }*/
    }

    public void actualizarSalto(){
        timerAnimacion = 0;
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

    public Sprite getSprite2(){
        return spriteNivel2;
    }

    public float getX2(){
        return spriteNivel2.getX();
    }

    public float getY2(){
        return spriteNivel2.getY();
    }

    public void setPosicion2(float x,float y){
        spriteNivel2.setPosition(x,y);
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
            timerAnimacion = 0;
            tiempoSalto = 0;
            yInicial = sprite.getY();
            estadoSalto = EstadoSalto.SUBIENDO;
            tiempoVuelo = 2 * V0 / G;
        }
    }

    public void saltar2(){
        if(estadoSalto == EstadoSalto.ABAJO){
            tiempoSalto = 0;
            yInicial = spriteNivel2.getY();
            estadoSalto = EstadoSalto.SUBIENDO;
            tiempoVuelo = 2*V0/G;
        }
    }

    public void actualizarSalto2(){
        //timerAnimacion = 0;
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if(tiempoSalto > tiempoVuelo/2){
            estadoSalto = EstadoSalto.BAJANDO;
        }
        tiempoSalto += 10 * Gdx.graphics.getDeltaTime();
        spriteNivel2.setY(yInicial + y);
        if(y < 0){
            spriteNivel2.setY(yInicial);
            estadoSalto = EstadoSalto.ABAJO;
        }
    }

    public EstadoSalto getEstadoSalto(){
        return estadoSalto;
    }

    public enum EstadoMovimiento {
        INICIANDO,
        QUIETO,
        DER,
        IZQ
    }
}
