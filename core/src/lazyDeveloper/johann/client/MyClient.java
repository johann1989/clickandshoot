package lazyDeveloper.johann.client;

import java.io.IOException;

import lazyDeveloper.johann.client.Network.CreateNewMonster;
import lazyDeveloper.johann.client.Network.Name;
import lazyDeveloper.johann.client.Network.Shoot;
import lazyDeveloper.johann.sac.GameController;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;







public class MyClient {
Client client ;
public GameController gameController;
public MyClient(GameController gameController){
	this.gameController = gameController;
	
	init();
}
private void init() {
	client = new Client();
	Network.register(client);
	client.start();
	client.addListener(new Listener() {
	
	 public void received (Connection connection, Object object) {
         if (object instanceof Name) {
       	  Name first = (Name)object;
       	  gameController.playerName = first.playerName;
       	  System.out.println(first.playerName);
		}
         if (object instanceof CreateNewMonster) {
        	 final CreateNewMonster newMonster = (CreateNewMonster) object;
        	 
          	 Gdx.app.postRunnable(new Runnable() {
				
				@Override
				public void run() {
					gameController.createNewMonster(newMonster);
					
				}
			});
          	
   		}
         if (object instanceof Shoot) {
        	 Shoot shoot = (Shoot) object;
        	 
        	 gameController.checkHit(shoot.x, shoot.y);
        	 gameController.checkHit(shoot.x-70, shoot.y);
        	 gameController.checkHit(shoot.x+70, shoot.y);
        	
         }
         
             
         }
      
   });
	
	try {
		client.connect(Network.timeOut, Network.ipAdress, Network.port);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}
public void sendShoot(float x,float y){
	Shoot shoot = new Shoot();
	shoot.x = x;
	shoot.y = y;
	client.sendTCP(shoot);
}

}
