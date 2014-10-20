package lazyDeveloper.johann.sac;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
public Game game;
public Texture bgTexture;
public Batch batch;
public Stage mainStage;
private OrthographicCamera camera;
public MenuScreen(Game game){
	
	this.game = game;
	
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
		mainStage.act();
		mainStage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		mainStage.getViewport().update(width, height,false);
		
	}

	@Override
	public void show() {
		bgTexture = new Texture(Gdx.files.internal("testmoorhun.png"));
		batch = new SpriteBatch();
	    mainStage = new Stage(new ScreenViewport(), batch);
	    createButtons();
	    Gdx.input.setInputProcessor(mainStage);
	    camera = new OrthographicCamera();
        camera.setToOrtho(false,770, 630);
        camera.position.set(770/2, 630/2, 0);
	}

	private void createButtons() {
		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		Skin skin = new Skin();
		
		Texture buttonTex = new Texture(Gdx.files.internal("button.png"));
		
		skin.add("button", buttonTex);
		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());
		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("button");
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		mainStage.addActor(table);
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton singlePlayerButton = new TextButton("Single Player", skin);
		final TextButton multiPlayerButton = new TextButton("Multi Player", skin);
		
		table.add(singlePlayerButton).spaceBottom(20);
		table.row();
				table.add(multiPlayerButton);
		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		singlePlayerButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				System.out.println("Single PLayer");
				game.setScreen(new SinglePlayerScreen(game));
			
			}
		});
		multiPlayerButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new OptionsScreen(game));
				System.out.println("Multiplayer");
			}
		});
		
		
		
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
		bgTexture.dispose();
		mainStage.dispose();
		
	}

}
