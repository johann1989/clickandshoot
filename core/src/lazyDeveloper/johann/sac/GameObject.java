package lazyDeveloper.johann.sac;

import lazyDeveloper.johann.client.Network.CreateNewMonster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameObject {
	public enum State {
		LEFT,RIGHT,DEAD
	};
    public Rectangle bounds = new Rectangle();
	public Vector2 position = new Vector2();
	public Vector2 dimension;
	public Vector2 origin;
    
	public Vector2 scale;
	public float rotation;
	public State state;
	float stateTime;
	TextureRegion fly1;
	TextureRegion fly2;
	TextureRegion flyDead;
	Array<TextureRegion> animationArray;
	TextureRegion drawtexture;
	Animation flyAnimation;
	
	public GameObject(){
	if(MathUtils.random() > 0.5){
			position.x = 770 -50;
			state = State.LEFT;
		}else{
			state = State.RIGHT;
			position.x = 0 +50;
		}
		
		position.y = MathUtils.random(200, 500);
		
		init();
		
	}
public GameObject(CreateNewMonster newMonster){
		if(newMonster.state == CreateNewMonster.fromLEFT){
			position.x = 0 +50;
			state = State.RIGHT;
		}
		if(newMonster.state == CreateNewMonster.fromRIGHT){
			position.x = 770 -50;
			state = State.LEFT;
		}
		position.y = newMonster.y;
		init();
		
	}
	
	private void init() {
		fly1 = new TextureRegion(new Texture(Gdx.files.internal("flyFly1.png")));
		fly2 = new TextureRegion(new Texture(Gdx.files.internal("flyFly2.png")));
		animationArray = new Array<TextureRegion>();
		
		animationArray.add(fly1);
		animationArray.add(fly2);
		
		flyDead = new TextureRegion(new Texture(Gdx.files.internal("flyDead.png")));
		flyAnimation = new Animation(0.1f, animationArray,PlayMode.LOOP);
		bounds.height = 33;
		bounds.width = 59;
	}

	
	public void update(float delta){
		bounds.x = position.x;
		bounds.y =  position.y;
		stateTime +=delta;
		switch(state){
		case LEFT:
			drawtexture = flyAnimation.getKeyFrame(stateTime);
			position.x -= 200 * delta; 
			break;
		case RIGHT:
			drawtexture = flyAnimation.getKeyFrame(stateTime);
			position.x += 200 * delta; 
			bounds.x = position.x -bounds.width;
			break;
		case DEAD:
			drawtexture = flyDead;
			position.y -= 200 * delta;
	     default:
			break;
		}
		
		
	}
	
	public void render(Batch batch){
		batch.begin();
		
		switch(state){
		case LEFT:
			batch.draw(drawtexture, position.x, position.y, bounds.width, bounds.height);
			break;
		case RIGHT:
			batch.draw(drawtexture, position.x, position.y, -bounds.width, bounds.height);
			break;
	     default:
	    	 batch.draw(drawtexture, position.x, position.y, bounds.width, bounds.height);
			break;
		}
		
		batch.end();
	}
	public void hit(){
		state = State.DEAD;
	}
	
}
