package equipo3.itesm.mx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Mario Lagunes on 21/11/2016.
 */

public class Musica {
    public Musica(Music musica,Boolean nivel,Boolean ajuste){
        if(nivel == true && ajuste == false){
            musica.setLooping(true);
            musica.play();
            musica.setVolume(1.5f);
        }
        if(ajuste == true){
            musica.stop();
        }
    }

    /*public Musica(Sound sonido, Boolean nivel, Boolean ajuste){
        if(nivel == true && ajuste == false){
            sonido.play();
        }
        if(ajuste == true){
            sonido.stop();
        }
    }*/

    public void musicaAjustes(Music music){
        music.stop();
    }

    /*public void apagarMusica(Boolean cambiar){
        if(cambiar == true){
            musica1.stop();
        }
    }*/
}
