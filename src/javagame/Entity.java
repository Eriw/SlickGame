/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

//import static javagame.Game.*;

/**
 * 
 * @author Wirebrand
 */
public class Entity {

	public Rectangle entity;
	public Rectangle hitbox;
	public Vector2f velocity;
	public Graphics g;
	// private GameContainer gc;
	public Play world;
	public float health;

	public Entity(GameContainer gc, Play play, float x, float y, float width,
			float height) {

		this.g = gc.getGraphics();
		// this.gc = gc;
		this.world = play;
		entity = new Rectangle(x, y, width, height);
		health = 1000f;
	}

	public void update(int delta) {

	}

	public void render() {

		if (health <= 100f) {
			g.setColor(new Color(0.0f, 1.0f, 0.0f));
		} else {
			g.setColor(new Color(1.0f, 1.0f, 1.0f));
		}

		// g.draw(entity);
		float x0 = entity.getX() - world.camera.getPosition();
		g.drawRect(x0, entity.getY(), entity.getWidth(), entity.getHeight());
		if (health <= 100f) {
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawRect(x0 + entity.getWidth() / 10,
					entity.getY() + entity.getHeight() / 10, entity.getWidth()
							* 0.8f * health / 100f, entity.getHeight() / 10);
		}
	}

	public float getX() {
		return entity.getX();
	}

	public float getY() {
		return entity.getY();
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

}
