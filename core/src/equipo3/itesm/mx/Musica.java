package equipo3.itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by andrescalvavalencia on 21/10/16.
 */

public class Musica {
    public Music musica;

    public void cargarMusica(boolean flag){
        musica = Gdx.audio.newMusic(Gdx.files.internal("Score.mp3"));
        musica.setVolume(0.75f);

        if(flag == true){
            musica.play();
        }
        if(flag == false){
            musica.stop();
        }

    }
}
