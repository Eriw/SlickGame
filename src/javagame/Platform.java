package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Platform extends Entity {
	

	public Platform(GameContainer gc, Play play, float x, float y, float width) {
		super(gc, play, x, y, width, 5);
		
		hitbox = new Rectangle(x, y, width, 1);
	}

}
