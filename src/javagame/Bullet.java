package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import static javagame.Game.*;

public class Bullet extends Entity {
	
	private final float HORISONTAL_SPEED = 1.5f;
	private float timeToLive = 400;
	

	public Bullet(GameContainer gc, Play play, float x, float y, float width, float height, Vector2f velocity) {
		super(gc, play, x, y, width, height);
		this.hitbox = new Rectangle(x, y, width, height);
		this.velocity = velocity;
	}
	
	 public void update(int delta){
	        move(delta);
	        timeToLive -= 1f*delta;
	        if(timeToLive <= 0){
	        	entity.setX(WORLD_WIDTH*2);
	        	hitbox.setX(WORLD_WIDTH*2);
	        }
	 }

	private void move(int delta) {

		entity.setX(entity.getX() + delta*velocity.x*HORISONTAL_SPEED);
		hitbox.setX(hitbox.getX() + delta*velocity.x*HORISONTAL_SPEED);
		
	}

}
