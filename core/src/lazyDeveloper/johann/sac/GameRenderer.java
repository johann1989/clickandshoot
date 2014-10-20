package lazyDeveloper.johann.sac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

public class GameRenderer {
private GameController gameController;
private Texture bgTexture;
private SpriteBatch batch;
public OrthographicCamera camera;

public GameRenderer(GameController gameController){
	this.gameController = gameController;
	init();
}

private void init() {
	bgTexture = new Texture(Gdx.files.internal("testmoorhun.png"));
	batch = new SpriteBatch();
	camera = new OrthographicCamera();
    camera.setToOrtho(false,770, 630);
    camera.position.set(770/2, 630/2, 0);
	
}
public void render(){
	        batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(bgTexture,0,0, 770, 630);
			batch.end();
			for(int i = 0 ;i< gameController.monsterArray.size;i++){
				gameController.monsterArray.get(i).render(batch);
				
			}
			
			camera.update();
}
}
