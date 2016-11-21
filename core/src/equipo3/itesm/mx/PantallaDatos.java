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
    protected final int ancho2 = 800;
    protected final int alto2 = 1280;

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

    public OrthographicCamera crearCamaraNivel3(OrthographicCamera camara1){
        camara1 = new OrthographicCamera(alto,ancho);
        camara1.position.set(alto /2,ancho/2,0);
        camara1.update();
        return camara1;
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
