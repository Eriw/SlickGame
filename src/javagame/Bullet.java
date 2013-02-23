package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import static javagame.Game.*;

public class Bullet extends Entity {
	
	private final float PLAYER_HORISONTAL_SPEED = 0.5f;
	

	public Bullet(GameContainer gc, Play play, float x, float y, float width, float height, Vector2f velocity) {
		super(gc, play, x, y, width, height);
		this.hitbox = new Rectangle(x, y, width, height);
		this.velocity = velocity;
	}
	
	 public void update(int delta){
	        move(delta);
	 }

	private void move(int delta) {

		entity.setX(entity.getX() + delta*velocity.x);
		hitbox.setX(hitbox.getX() + delta*velocity.x);
		
	}

}
