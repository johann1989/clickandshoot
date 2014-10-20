package lazyDeveloper.johann.sac;

import java.util.Iterator;

import lazyDeveloper.johann.client.MyClient;
import lazyDeveloper.johann.client.Network.CreateNewMonster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameController {
Game game;
public String playerName;
public Array<GameObject> monsterArray;
public MyClient client;
public GameController(Game game,MyClient client){
	this.game = game;
	this.client = client;
	init();
}
private void init() {
monsterArray = new Array<GameObject>();
	
}
public void update(float delta){
	for(int i = 0 ;i< monsterArray.size;i++){
		monsterArray.get(i).update(delta);
		
	}
	
}

public void checkHit(float x,float y){
	for (GameObject monster : monsterArray) {
    	if(monster.bounds.contains(new Vector2(x, y))){
    	   monster.hit();
    	}
}
}
public void checkBounds(){
     Iterator<GameObject> iter = monsterArray.iterator();
	 while(iter.hasNext()){
		 GameObject object = iter.next();
		 if(object.position.x > 770 ||object.position.x < 0 || object.position.y < 0 )
			 iter.remove();
	 }
}
public void createNewMonster(CreateNewMonster newMonster){
	monsterArray.add(new GameObject(newMonster));
	
}

}


