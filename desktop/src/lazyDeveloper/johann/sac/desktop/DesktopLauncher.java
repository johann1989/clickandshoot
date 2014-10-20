package lazyDeveloper.johann.sac.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lazyDeveloper.johann.sac.ShootAndClick;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= 770;
		config.height = 630;
		new LwjglApplication(new ShootAndClick(), config);
	}
}
