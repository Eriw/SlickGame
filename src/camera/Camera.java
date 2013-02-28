package camera;

//import org.newdawn.slick.GameContainer;
import static javagame.Game.*;
import javagame.Play;

public class Camera {

	private Play world;
	private float cameraPos;

	public Camera(Play play) {
		world = play;
		cameraPos = WINDOW_WIDTH / 2;
	}

	public void update() {

		if (world.player.getCenterX() < WINDOW_WIDTH / 2) {
			cameraPos = WINDOW_WIDTH / 2;
		} else if (world.player.getCenterX() > WORLD_WIDTH - WINDOW_WIDTH / 2) {
			cameraPos = WORLD_WIDTH - WINDOW_WIDTH / 2;
		} else {
			cameraPos = world.player.getCenterX();
		}

		// Add offset
		cameraPos -= WINDOW_WIDTH / 2;

	}

	public float getPosition() {
		return cameraPos;
	}

}
