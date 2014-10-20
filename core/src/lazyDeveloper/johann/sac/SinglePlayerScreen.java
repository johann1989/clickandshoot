package lazyDeveloper.johann.sac;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SinglePlayerScreen implements Screen {
	public Game game;
	public Texture bgTexture;
	public Batch batch;
	public Stage gameStage;
	public GameObject test;
	public float lastObjectTime;
	public Array<GameObject> monsterArray = new Array<GameObject>();
	private OrthographicCamera camera;
	ShapeRenderer shapeRenderer = new ShapeRenderer();

	public SinglePlayerScreen(Game game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bgTexture,0,0, 770, 630);
		batch.end();
		for (GameObject monster : monsterArray) {
			monster.update(delta);
			monster.render(batch);
		}
		gameStage.draw();
		
		 if (TimeUtils.nanoTime() - lastObjectTime > 1000000000)
	            spawnObject();
		 
		 Iterator<GameObject> iter = monsterArray.iterator();
		 while(iter.hasNext()){
			 GameObject object = iter.next();
			 if(object.position.x > 770 ||object.position.x < 0 || object.position.y < 0 )
				 iter.remove();
		 }
		 if (Gdx.input.isTouched()) {
	            Vector3 touchPos = new Vector3();
	            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	            camera.unproject(touchPos);
	            
	            for (GameObject monster : monsterArray) {
	            	if(monster.bounds.contains(new Vector2(touchPos.x, touchPos.y))){
	            	
	            		monster.hit();
	            	}
	            }
	        }
		 
		 camera.update();
		 shapeRenderer.setProjectionMatrix(camera.combined);
		 shapeRenderer.begin(ShapeType.Line);
		 shapeRenderer.setColor(Color.RED);
		 for (GameObject monster : monsterArray) {
			 shapeRenderer.rect(monster.bounds.x, monster.bounds.y, monster.bounds.width, monster.bounds.height);
         }
		 shapeRenderer.end();
		 
	}

	private void spawnObject() {
	monsterArray.add(new GameObject());
	lastObjectTime = TimeUtils.nanoTime();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		bgTexture = new Texture(Gdx.files.internal("testmoorhun.png"));
		batch = new SpriteBatch();
		gameStage = new Stage();
		Gdx.input.setInputProcessor(gameStage);
		test = new GameObject();
		lastObjectTime = TimeUtils.nanoTime();
		camera = new OrthographicCamera();
        camera.setToOrtho(false,770, 630);
        camera.position.set(770/2, 630/2, 0);
		
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
