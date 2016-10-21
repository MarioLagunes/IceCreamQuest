package equipo3.itesm.mx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//hola

public class MenuPrincipal extends PantallaDatos implements Screen {
	private final Juego juego;
	private Stage escena;
	private Texture texturaFondoMenu,texturaBtonAjustes,texturaBtonJugar,texturaBtonPuntaje,texturaAcercaDe,texturaAjustes,texturaInstrucciones,texturaBtonAcercaDe;
	private OrthographicCamera camara;
	private Viewport vista;
	public Music musica;
	private ImageButton[] botones1;
	private final AssetManager manager = new AssetManager();
	private ImageButton btonPuntaje,btonJugar,btonAcercaDe,btonAjustes;


	public MenuPrincipal(Juego juego) {
		super();
		this.juego = juego;
	}

	@Override
	public void show() {
		// *** CREAR CAMARA Y VISTA ***\\
			PantallaDatos camara1 = new PantallaDatos(camara);
			PantallaDatos vista1 = new PantallaDatos(vista);
			camara = camara1.crearCamara(camara);
			vista = vista1.crearVista(camara,vista);
		// *** FIN DE CREAR CAMARA Y VISTA ***\\

		// *** CARGAR FONDO Y BOTONES Y FUNCIONALIDAD DE LOS MISMOS ***\\
			imagenesFondo();
			cargarFondo();
			cargarBotones();
		// *** FIN DE CARGAR FONDO Y BOTONES Y FUNCIONALIDAD DE LOS MISMOS ***\\

		// *** CARGAR MÚSICA ***\\
			cargarMusica();
		// *** FIN DE CARGAR MUSICA ***\\
	}

	private void cargarMusica() {
		musica = Gdx.audio.newMusic(Gdx.files.internal("Menu-VidJu.mp3"));
		musica.setVolume(0.75f);
		musica.play();
	}

	private void cargarFuncionesBontones(ImageButton[] botones1) {
		btonJugar = botones1[0];
		btonAcercaDe = botones1[1];
		btonAjustes = botones1[2];
		btonPuntaje = botones1[3];

		btonJugar.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				juego.setScreen(new PantallaInstrucciones(juego));
			}
		});

		btonAcercaDe.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				juego.setScreen(new PantallaAcercaDe(juego));
			}
		});

		btonAjustes.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				juego.setScreen(new PantallaAjustes(juego));



			}
		});

		btonPuntaje.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				juego.setScreen(new PantallaPuntaje(juego));
			}
		});



	}

	private void cargarBotones() {
		TextureRegionDrawable texturaJugar = new TextureRegionDrawable(new TextureRegion(texturaBtonJugar));
		ImageButton btonJugar = new ImageButton(texturaJugar);
		btonJugar.setPosition(ancho/2 + 130, 0.30f * alto);

		TextureRegionDrawable texturaAjustes = new TextureRegionDrawable(new TextureRegion(texturaBtonAjustes));
		ImageButton btonAjustes = new ImageButton(texturaAjustes);
		btonAjustes.setPosition(ancho/2 + 75, 0.05f * alto);

		TextureRegionDrawable texturaPuntaje = new TextureRegionDrawable(new TextureRegion(texturaBtonPuntaje));
		ImageButton btonPuntaje = new ImageButton(texturaPuntaje);
		btonPuntaje.setPosition(ancho/2 + 270, 0.05f * alto);

		TextureRegionDrawable texturaAcercaDe = new TextureRegionDrawable(new TextureRegion(texturaBtonAcercaDe));
		ImageButton btonAcercaDe = new ImageButton(texturaAcercaDe);
		btonAcercaDe.setPosition(ancho/2 + 450, 0.05f * alto);

		escena.addActor(btonJugar);
		escena.addActor(btonAjustes);
		escena.addActor(btonPuntaje);
		escena.addActor(btonAcercaDe);

		botones1 = new ImageButton[]{btonJugar, btonAcercaDe, btonAjustes, btonPuntaje};
		cargarFuncionesBontones(botones1);
	}

	private void cargarFondo() {
		escena = new Stage();
		Gdx.input.setInputProcessor(escena);
		Image fondo = new Image(texturaFondoMenu);
		escena.addActor(fondo);
	}

	private void imagenesFondo() {
		//imagenes fondo
		manager.load("menuPrincipal.png",Texture.class);
		manager.load("BtnConfig.png",Texture.class);
		manager.load("BtnJugar.png",Texture.class);
		manager.load("BtnCopa.png",Texture.class);
		manager.load("BtnInf.png",Texture.class);
		manager.finishLoading();

		texturaFondoMenu = manager.get("menuPrincipal.png");
		texturaBtonAjustes = manager.get("BtnConfig.png");
		texturaBtonJugar = manager.get("BtnJugar.png");
		texturaBtonPuntaje = manager.get("BtnCopa.png");
		texturaBtonAcercaDe = manager.get("BtnInf.png");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		escena.setViewport(vista);
		escena.draw();
	}

	@Override
	public void resize(int width, int height) {
		vista.update(width,height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		texturaFondoMenu.dispose();
		//texturaAcercaDe.dispose();
		//texturaAjustes.dispose();
		texturaBtonAjustes.dispose();
		texturaBtonJugar.dispose();
		texturaBtonPuntaje.dispose();
		//texturaInstrucciones.dispose();
		musica.dispose();
	}

	@Override
	public void dispose () {

	}
}
