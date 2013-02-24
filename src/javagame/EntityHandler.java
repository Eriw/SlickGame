package javagame;

import static javagame.Game.*;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class EntityHandler {

	private Graphics g;
    private GameContainer gc;
    public Play world;
	
	public ArrayList<Entity> objects;
	public ArrayList<Entity> bullets;
	public ArrayList<Entity> enemies;
	
	public EntityHandler(GameContainer gc, Play play) {
		this.g = gc.getGraphics();
        this.gc = gc;
        this.world = play;
        
		objects = new ArrayList<Entity>();
		bullets = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
	}
	
	public void init(){
		int i = 0;
		while(i<20){
			createRandomPlatform();
			++i;
		}
		i = 0;
	    while(i<20){
	    	i += createRandomBox();
	    }
	}
	
	public int sumOfEntites(){
		return objects.size() + bullets.size() + enemies.size();
	}
	
	public void createRandomPlatform(){
		Random r = new Random();
		objects.add(new Platform(gc, world, r.nextInt(WORLD_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50), WINDOW_WIDTH/12));
	}
	
	public int createRandomBox(){
		Random r = new Random();
		Box b = new Box(gc, world, r.nextInt(WORLD_WIDTH-50) , r.nextInt(WINDOW_HEIGHT-50),  WINDOW_WIDTH/12,  WINDOW_WIDTH/12);
		boolean colides = false;
		for(Entity e : objects){
			colides = colides || e.entity.intersects(b.entity);
		}
		if(!colides || objects.size() == 0){
			objects.add(b);
			return 1;
		}
		
		return 0;
	}
	
	public void createBullet(Vector2f position, Vector2f velocity){
		bullets.add(new Bullet(gc, world, position.x , position.y, 10, 10, velocity));
		
	}
	
	public void update(int delta){
		 for(int i = 0; i < bullets.size(); ++i){
			   Entity e = bullets.get(i);
			   e.update(delta);
		       if(e.getHitbox().getMaxX() < 0 || e.getHitbox().getX() > WORLD_WIDTH || e.getHitbox().getMaxY() < 0 || e.getHitbox().getY() > WINDOW_HEIGHT){
		    	 bullets.remove(i);
		       }
		   }
	}
	
	public void render(){
		for(Entity e : objects){
			e.render();
		}
		for(Entity e : bullets){
			e.render();
		}
		for(Entity e : enemies){
			e.render();
		}
	}

}
