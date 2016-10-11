package equipo3.itesm.mx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mario Lagunes on 10/10/2016.
 */

public class Boomerang {

    private Boomerang boomerang;

    public Boomerang(Boomerang boomerang){
        this.boomerang = boomerang;
    }

    public Boomerang(Texture textura){
        TextureRegion texturaCompleta = new TextureRegion(textura);
    }
}
