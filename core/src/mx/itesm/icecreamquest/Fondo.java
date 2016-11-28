package mx.itesm.icecreamquest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mario Lagunes on 26/09/2016.
 */
public class Fondo {
    private Sprite sprite;

    public Fondo(Texture texturaFondo){
        sprite = new Sprite(texturaFondo);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosicion(float x, float y){
        sprite.setPosition(x,y);
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Sprite getSprite(){
        return sprite;
    }

}
