package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Mario Lagunes on 24/09/2016.
 */
public class PantallaDatos {
    protected OrthographicCamera camara,camara1;
    protected Viewport vista;
    protected final int ancho = 1280;
    protected final int alto = 800;

    private static final float ANCHO=896;
    private static final float ALTO=1280;

    public PantallaDatos() {

    }



    public PantallaDatos(OrthographicCamera camara1) {
        this.camara = camara;
    }

    public PantallaDatos(Viewport vista){
        this.vista = vista;
    }


    public OrthographicCamera crearCamara(OrthographicCamera camara){
        camara = new OrthographicCamera(ancho,alto);
        camara.position.set(ancho/2,alto/2,0);
        camara.update();
        return camara;
    }

    public OrthographicCamera crearCamaraNivel3(){
        OrthographicCamera camara = new OrthographicCamera(ANCHO,ALTO);
        camara.position.set(ANCHO/2,ALTO/2,0);
        camara.rotate(90);
        camara.update();
        return camara;
    }

    public Viewport crearVista(OrthographicCamera camara, Viewport vista){
        return vista = new StretchViewport(ancho,alto,camara);
    }


    public Viewport crearVistaNivel3(OrthographicCamera camara, Viewport vista){
        return vista = new StretchViewport(alto,ancho,camara);
    }


    public StretchViewport crearVistaHUD(OrthographicCamera camara,StretchViewport vista){
        return vista = new StretchViewport(ancho,alto,camara);
    }

    public StretchViewport crearVistaHUDNivel33(OrthographicCamera camara,StretchViewport vista){
        return vista = new StretchViewport(alto,ancho,camara);
    }

    public Viewport crearVistaHUDNivel3(OrthographicCamera camara, Viewport vista){
        return vista = new StretchViewport(alto,ancho,camara);
    }

}
