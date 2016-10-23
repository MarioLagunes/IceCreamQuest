package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
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
    public static  float velX;
    public static  float velocidadX = 4;
    private Animation animacion,animar,animarSalto,animarQuieto,animarReg;
    public float timerAnimacion,tiempoAnimar;
    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;
    private EstadosEnemigo estadoEnemigo;
    private static final float V0 = 60.0f;
    private static final float G = 9.81f;
    private static final float G_2 = G/2f;
    private float yInicial;
    private float tiempoVuelo;
    private  float tiempoSalto;
    private TextureRegion textfinalSalto,pinguQuieto;
    private Dardos dardos;
    public int puntos;

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

    public  Personaje(Texture textura,Texture texturaSaltos,Texture texturaQuieto){
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion texturaSalto = new TextureRegion(texturaSaltos);
        TextureRegion texturaQuietos = new TextureRegion(texturaQuieto);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(128,128);
        TextureRegion[][] texturaSaltar = texturaSalto.split(128,128);
        TextureRegion[][] texturaQuies = texturaQuietos.split(128,128);
        animacion = new Animation(0.10f,texturaPersonaje[0][1], texturaPersonaje[0][2], texturaPersonaje[0][3],
                texturaPersonaje[0][4],texturaPersonaje[0][5]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        textfinalSalto = new TextureRegion(texturaSaltar[0][1]);
        pinguQuieto = new TextureRegion(texturaQuies[0][1]);
        timerAnimacion = 0;

        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.ABAJO;
    }

    public Personaje(Texture textura,Texture textura2){
        TextureRegion texturaEnemigoFull = new TextureRegion(textura);
        TextureRegion texturaEnemiegoReg = new TextureRegion(textura2);
        TextureRegion[][] texturaEnemigo = texturaEnemigoFull.split(128,128);
        TextureRegion[][] texturaEnemigoRegreso = texturaEnemiegoReg.split(128,128);
        animar = new Animation(0.10f,texturaEnemigo[0][0],texturaEnemigo[0][1],texturaEnemigo[0][2],texturaEnemigo[0][3],texturaEnemigo[0][4],texturaEnemigo[0][5],texturaEnemigo[0][6]);
        animarReg = new Animation(0.10f,texturaEnemigoRegreso[0][6],texturaEnemigoRegreso[0][5],texturaEnemigoRegreso[0][4],texturaEnemigoRegreso[0][3],texturaEnemigoRegreso[0][2],texturaEnemigoRegreso[0][1]);
        animar.setPlayMode(Animation.PlayMode.LOOP);
        animarReg.setPlayMode(Animation.PlayMode.LOOP);
        tiempoAnimar = 0;
        spriteEnemigo = new Sprite(texturaEnemigo[0][0]);
        estadoEnemigo = EstadosEnemigo.INICIO;
    }

    public void render(SpriteBatch batch){
        switch (estadoMovimiento){
            case DER:
                //velocidadX = 4;
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacion.getKeyFrame(timerAnimacion);
                //if(region.isFlipX()){
                  //  region.flip(true,false);
                //}
                batch.draw(region,sprite.getX(),sprite.getY());
                if(timerAnimacion == 0){
                    sprite.setRegion(pinguQuieto);
                }
                if(estadoMovimiento.equals(EstadoSalto.SUBIENDO)){
                    timerAnimacion=0;
                    sprite.setRegion(textfinalSalto);
                }
                break;
            /*case IZQ:
                velocidadX = 2;
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region2 = animacion.getKeyFrame(timerAnimacion);
                if(region2.isFlipX()){
                    region2.flip(true,false);
                }
                batch.draw(region2,sprite.getX(),sprite.getY());
                break;*/
            case INICIANDO:case QUIETO:
                //timerAnimacion = 0;
                //estadoMovimiento = EstadoMovimiento.QUIETO;
                sprite.setRegion(pinguQuieto);
                sprite.draw(batch);
                break;
        }
        switch (estadoSalto){
            case SUBIENDO: case BAJANDO: case CAIDALIBRE:
                timerAnimacion = 0;
                //timerAnimacion += Gdx.graphics.getDeltaTime();
                //TextureRegion regionSalto = animarSalto.getKeyFrame(timerAnimacion);
                sprite.setRegion(textfinalSalto);
                //if(estadoMovimiento){

                //}
                sprite.draw(batch);


                break;
        }
    }

    public void renderEnemigo(SpriteBatch batch){
        switch(estadoEnemigo){
            case DERECHA:
                velX += 2;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region4 = animar.getKeyFrame(tiempoAnimar);
                if(region4.isFlipX()){
                    region4.flip(true,false);
                }
                batch.draw(region4,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                //System.out.println("vel =" + velX);
                if(velX > 300.0){
                    //dardos.render(batch);
                  //  System.out.print("estouy aqui");
                    moverEnemigosIzq();
                }
                break;
            case IZQUIERDA:
                velX -= 2;
                tiempoAnimar += Gdx.graphics.getDeltaTime();
                TextureRegion region5 = animarReg.getKeyFrame(tiempoAnimar);
                if(region5.isFlipX()){
                    region5.flip(true,false);
                }
                batch.draw(region5,spriteEnemigo.getX()+velX,spriteEnemigo.getY());
                if(velX == 10){
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

    public void recolectarHelados(TiledMap mapa){
        TiledMapTileLayer capa = (TiledMapTileLayer)mapa.getLayers().get(1);
        int x = (int)(sprite.getX()/128);
        int y = (int)(sprite.getY()/128);
        TiledMapTileLayer.Cell celda = capa.getCell(x,y);
        if(celda != null){
            Object tipo = celda.getTile().getProperties().get("tipo");
            if("helado".equals(tipo)){
                puntos += 500;
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
            }
            else if("heladoespecial".equals(tipo)){
                puntos += 1000;
                capa.setCell(x,y,null);
                //capa.setCell(x,y,capa.getCell(0,4));
            }
        }
    }

    /*private void moverHorizontal(TiledMap mapa){
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
        int x = (int)((sprite.getX())/128);
        int y = (int)(sprite.getX()+velocidadY)/128;
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
            }
        }
        if(y<0){
            sprite.setY(yInicial);
            estadoSalto = EstadoSalto.ABAJO;
        }
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
