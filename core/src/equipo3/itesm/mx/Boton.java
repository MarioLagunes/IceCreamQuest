package equipo3.itesm.mx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;



/**
 * Created by Mario Lagunes on 03/10/2016.
 */
public class Boton extends Button {
    private Sprite sprite;
    private Rectangle colision;
    private Boolean isDisable;
    private Button boton;
    private int tiempo;
    private boolean enabled;

    public Boton(Texture textura){
        sprite = new Sprite(textura);
        colision = new Rectangle(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
    }
    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosicion(float x, float y){
        sprite.setPosition(x,y);
        colision.setPosition(x,y);
    }

    public boolean contiene(float x, float y){
        return colision.contains(x,y);
    }

}
