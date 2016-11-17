package equipo3.itesm.mx;

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
import com.sun.org.apache.xpath.internal.operations.String;


/**
 * Created by Mario Lagunes(3) on 26/09/2016.
 */
public class Personaje {
    private Sprite sprite,spriteEnemigo,spriteSalto,spriteQuieto;
    public static final float velocidadY = -4f;
    public float velX;
    public float velocidadX = 4;
    private Animation animacion,animar,animarSalto,animarQuieto,animarReg;
    public float timerAnimacion,tiempoAnimar,timerSalto;
    private EstadoMovimiento estadoMovimiento = EstadoMovimiento.INICIANDO;
    //private Texture texturaSalto;
    private EstadoSalto estadoSalto = EstadoSalto.ABAJO;
    private EstadosEnemigo estadoEnemigo;
    private static final float V0 = 60.0f;
    private static final float G = 9.81f;
    private static final float G_2 = G/2f;
    private float yInicial;
    private float tiempoVuelo;
    private  float tiempoSalto;
    //private TextureRegion region;
    private TextureRegion textfinalSalto,pinguQuieto;
    private Texture salto;
    private Dardos dardos;
    public int puntos;
    float x;

    public enum EstadoSalto {
        ABAJO,
        SUBIENDO,
        BAJANDO,
        CAIDALIBRE
    }

    public enum EstadosEnemigo{
        INICIO,
        DERECHA,
        IZQUIERDA,
        BORRADO
    }

    public  Personaje(Texture textura,Texture texturaSaltos,float numero){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion texturaSalto = new TextureRegion(texturaSaltos);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(64,64);
        TextureRegion[][] texturaSaltar = texturaSalto.split(64,64);
        animacion = new Animation(0.10f,texturaPersonaje[0][1], texturaPersonaje[0][2], texturaPersonaje[0][3],
                texturaPersonaje[0][4],texturaPersonaje[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        animarSalto = new Animation(0.10f,texturaSaltar[0][1]);
        animarSalto.setPlayMode(Animation.PlayMode.LOOP);
        timerSalto = 0;
        timerAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
    }

    public Personaje(Texture textura,Texture textura2){
        TextureRegion texturaEnemigoFull = new TextureRegion(textura);
        TextureRegion texturaEnemiegoReg = new TextureRegion(textura2);
        TextureRegion[][] texturaEnemigo = texturaEnemigoFull.split(64,64);
        TextureRegion[][] texturaEnemigoRegreso = texturaEnemiegoReg.split(64,64);
        animar = new Animation(0.10f,texturaEnemigo[0][0],texturaEnemigo[0][1],texturaEnemigo[0][2],texturaEnemigo[0][3],texturaEnemigo[0][4],texturaEnemigo[0][5]);
        animarReg = new Animation(0.10f,texturaEnemigoRegreso[0][6],texturaEnemigoRegreso[0][5],texturaEnemigoRegreso[0][4],texturaEnemigoRegreso[0][3],texturaEnemigoRegreso[0][2],texturaEnemigoRegreso[0][1]);
        animar.setPlayMode(Animation.PlayMode.LOOP);
        animarReg.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimar = 0;
        spriteEnemigo = new Sprite(texturaEnemigo[0][6]);
        estadoEnemigo = EstadosEnemigo.INICIO;
    }

    public void render(SpriteBatch batch){

        if(estadoMovimiento == EstadoMovimiento.DER && estadoSalto == EstadoSalto.ABAJO){
            timerAnimacion += Gdx.graphics.getDeltaTime();
            TextureRegion region = animacion.getKeyFrame(timerAnimacion);
            sprite.setRegion(region);
        }
        if(estadoSalto == EstadoSalto.SUBIENDO || estadoSalto == EstadoSalto.BAJANDO || estadoSalto == EstadoSalto.CAIDALIBRE){
            timerSalto += Gdx.graphics.getDeltaTime();
            TextureRegion regionSalto = animarSalto.getKeyFrame(timerSalto);
            sprite.setRegion(regionSalto);
        }
        sprite.draw(batch);

        //batch.draw(sprite,sprite.getX(),sprite.getY());
    }

    public void renderEnemigo(SpriteBatch batch){
        switch(estadoEnemigo){
            case DERECHA:
                velX += 2;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region4 = animar.getKeyFrame(tiempoAnimar);
                //x=spriteEnemigo.getX()+velX;
                //spriteEnemigo.setX(x);
                batch.draw(region4,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                if(velX > 300.0){
                    //dardos.render(batch);
                  //System.out.print(velX);
                   moverEnemigosIzq();
                }
                break;
            case IZQUIERDA:
                velX -= 2;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region5 = animarReg.getKeyFrame(tiempoAnimar);
                //if(region5.isFlipX()){
                  //  region5.flip(true,false);
                //}
                //x=spriteEnemigo.getX()+velX;
                //spriteEnemigo.setX(x);
                batch.draw(region5,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                if(velX <= 10){
                    moverEnemigosDer();
                }
                break;
            case INICIO:
                velX = 0;
                break;
            case BORRADO:
                velX =0;
                break;
        }
    }



    /*public void actualizar(TiledMap mapa){
        float nuevaX = sprite.getX();
        switch(estadoMovimiento){
            case DER:
                nuevaX += velocidadX;
                if(nuevaX <= Nivel1.ancho_mapa - sprite.getWidth() ){
                    sprite.setX(nuevaX);
                }
                break;
        }
        recolectarHelados(mapa);
    }*/

    /*public void actualizar(TiledMap mapa){
        switch (estadoMovimiento){
            case DER:
                moverHorizontal(mapa);
                break;
            case INICIANDO:
                caer(mapa,velocidadY);
                break;
        }
        switch (estadoSalto){
            case SUBIENDO: case BAJANDO:
                actualizarSalto(mapa);
                break;
        }
        recolectarHelados(mapa);
    }*/

    public void recolectarHelados(TiledMap mapa, Sound helado, Sound heladoEspecial){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(1);
        int x = (int)(sprite.getX()/64);
        int y = (int)(sprite.getY()/64);
        TiledMapTileLayer.Cell celda = capa.getCell(x,y);
        if(celda != null){
            Object tipo = celda.getTile().getProperties().get("tipo");
            if("helado".equals(tipo)){
                puntos += 500;
                helado.play();
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
            }
            else if("heladoespecial".equals(tipo)){
                puntos += 1000;
                heladoEspecial.play();
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
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
                estadoSalto = EstadoSalto.BAJANDO;
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

    /*public void actualizarSalto(){
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
    }*/

    public Sprite getSprite(){
        return sprite;
    }

    public Sprite getSpriteEnemigo(){
        return spriteEnemigo;
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

    public void setPosicionEnemiga(float x, float y){
        spriteEnemigo.setPosition(x,y);
    }

    public float getXEnemiga(){
        return spriteEnemigo.getX();
    }

    public float getYEnemiga(){
        return spriteEnemigo.getY();
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
            timerAnimacion = 0;
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
