package lazyDeveloper.johann.sac;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen implements Screen {
Game game;
Stage stage;
public Texture bgTexture;
private OrthographicCamera camera;
private Batch batch;
TextureAtlas atlas;
Skin skin ;
	public OptionsScreen(Game game){
		this.game = game;
		init();
	}
	private void init() {
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"),atlas);
		bgTexture = new Texture(Gdx.files.internal("testmoorhun.png"));
		batch = new SpriteBatch();
	    stage = new Stage(new ScreenViewport(), batch);
	   
	    camera = new OrthographicCamera();
        camera.setToOrtho(false,770, 630);
        camera.position.set(770/2, 630/2, 0);
        
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		Label label = new Label("Hier Benutznamen eintippen: ", skin);
		label.setColor(Color.BLUE);
		final TextField text = new TextField("", skin);
		TextButton but = new TextButton("Start MultiPlayerGame", skin);
	    but.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new MultiPlayerScreen(game,text.getText()));
			
			
			}
		});
		
		stage.addActor(table);
		table.add(label);
		table.add(text);
		table.add(but);
		Gdx.input.setInputProcessor(stage);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    batch.setProjectionMatrix(camera.combined);
	    camera.update();
		batch.begin();
		batch.draw(bgTexture,0,0, 770, 630);
		batch.end();
		stage.draw();
		stage.act();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
