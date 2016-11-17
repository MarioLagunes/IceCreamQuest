package equipo3.itesm.mx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import equipo3.itesm.mx.Juego;
import equipo3.itesm.mx.MenuPrincipal;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 400;
		//config.height = 640;
		new LwjglApplication(new Juego(), config);
	}
}
