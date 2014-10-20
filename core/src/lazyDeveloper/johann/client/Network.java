package lazyDeveloper.johann.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;



public class Network {
public static final int port = 54555;
//public static final String ipAdress =  "31.220.48.52";
public static final String ipAdress =  "172.0.0.1";
public static final int timeOut = 10000;

//This registers objects that are going to be sent over the network.
static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Shoot.class);
        kryo.register(CreateNewMonster.class);
        kryo.register(PlayerHit.class);
        kryo.register(Name.class);
}
static public class Name{
	public String playerName;
}
static public class Shoot{
	
	public float x;
	public float y;
	public String playerName;
}
static public class CreateNewMonster{
	public static final int fromLEFT = 0;
	public static final int fromRIGHT = 1;
	public float y;
	public int state;
	
}
static public class PlayerHit{
	public String playerName;
}
static public class UpdateScore{
	
}
}
