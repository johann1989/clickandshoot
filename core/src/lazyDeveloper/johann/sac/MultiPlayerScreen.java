package lazyDeveloper.johann.sac;

import lazyDeveloper.johann.client.MyClient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MultiPlayerScreen implements Screen{
	public Game game;
	public String playerName;
	public MyClient client;
	public GameRenderer gameRenderer;	
	public GameController gameController;
	public Stage fakeStage;
 public MultiPlayerScreen(Game game){
	 this.game = game;
 }
	public MultiPlayerScreen(Game game, String playerName) {
		 this.game = game;
		 this.playerName = playerName;
		 System.out.println(playerName);
}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameController.update(delta);
		gameRenderer.render();
		handleInput();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	public void handleInput(){
		 if (Gdx.input.isTouched()) {
	            Vector3 touchPos = new Vector3();
	            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	            gameRenderer.camera.unproject(touchPos);
	            gameController.checkHit(touchPos.x, touchPos.y);
	            client.sendShoot(touchPos.x, touchPos.y);
		 }
	}

	@Override
	public void show() {
		fakeStage = new Stage();
		Gdx.input.setInputProcessor(fakeStage);
		gameController = new GameController(game,client);
		client = new MyClient(gameController);
	    gameRenderer = new GameRenderer(gameController);
	
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
