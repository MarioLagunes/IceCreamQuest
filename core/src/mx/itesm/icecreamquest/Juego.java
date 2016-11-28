package mx.itesm.icecreamquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by Mario Lagunes on 23/09/2016.
 */
public class Juego extends Game{
    public static final float ancho = 1280;
    public static final float alto = 800;

    private final AssetManager manager = new AssetManager();

    @Override
    public void create() {
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        setScreen(new PantallaInicio(this));

    }

    public AssetManager getManager(){
        return manager;
    }

    @Override
    public void dispose(){
        super.dispose();
        manager.clear();
    }
}
