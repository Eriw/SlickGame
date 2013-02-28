package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class Box extends Entity {

	public Box(GameContainer gc, Play play, float x, float y, float width, float height) {
		super(gc, play, x, y, width, height);
		hitbox = new Rectangle(x, y, width, height);
	}

}
