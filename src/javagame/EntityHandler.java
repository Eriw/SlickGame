package javagame;

import static javagame.Game.*;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class EntityHandler {

    private Graphics g;
    private GameContainer gc;
    public Play world;
    private Image map;
	
    public ArrayList<Entity> objects;
    public ArrayList<Entity> bullets;
    public ArrayList<Entity> enemies;

    public EntityHandler(GameContainer gc, Play play) throws SlickException {
        this.g = gc.getGraphics();
        this.gc = gc;
        this.world = play;
        
        map = new Image("res/map.png");

        objects = new ArrayList<Entity>();
        bullets = new ArrayList<Entity>();
        enemies = new ArrayList<Entity>();
    }

    public void init(){
        /*int i = 0;
        while(i<20){
                createRandomPlatform();
                ++i;
        }
        i = 0;
        while(i<40){
            i += createRandomBox();
        }*/
        /*float x0 = 0f;
        while(x0 < WORLD_WIDTH){

            if(x0 % 300 != 0){
                createBox(new Rectangle(x0, WINDOW_HEIGHT-50, 90, 45));
            }
            if(x0 % 600 == 0){
                createBox(new Rectangle(x0, WINDOW_HEIGHT-50*2, 90, 45));
                createBox(new Rectangle(x0, WINDOW_HEIGHT-50*3, 90, 45));
            }
            x0 += 100f ;
        }*/
        
        Color c = map.getColor(0, 9);
        Color c2 = Color.red;
        int w = map.getWidth();
        int h = map.getHeight();
        
        int test = 0;
        if(map.getColor(0, 9).r == 1f && map.getColor(0, 9).g == 0f && map.getColor(0, 9).b == 0f) test = 1;
        
        float blockWidth = 100;
        for(int x = 0; x < map.getWidth(); ++x){
            for(int y = 0; y < map.getHeight(); ++y){
                if(map.getColor(x, y).r == 1f && map.getColor(x, y).g == 0f && map.getColor(x, y).b == 0f){
                    createBox(new Rectangle(blockWidth*x + blockWidth/10, WINDOW_HEIGHT/map.getHeight()*y + 5f, blockWidth*0.8f, WINDOW_HEIGHT/map.getHeight() - 10f));
                }
                if(map.getColor(x, y).r == 0f && map.getColor(x, y).g == 1f && map.getColor(x, y).b == 0f){
                    createPlatform(new Rectangle(blockWidth*x + blockWidth/10, WINDOW_HEIGHT/map.getHeight()*y + 5f, blockWidth*0.8f, WINDOW_HEIGHT/map.getHeight() - 10f));
                }
                if(map.getColor(x, y).r == 0f && map.getColor(x, y).g == 0f && map.getColor(x, y).b == 1f){
                    createBox(new Rectangle(blockWidth*x + blockWidth/10, WINDOW_HEIGHT/map.getHeight()*y + 5f, blockWidth*0.8f, WINDOW_HEIGHT/map.getHeight() - 10f));
                    objects.get(objects.size()-1).health = 100f;
                }
            }
        }
        
        if(map.getWidth() * blockWidth < WORLD_WIDTH){
            createBox(new Rectangle(map.getWidth() * blockWidth + blockWidth/10, WINDOW_HEIGHT - 15, WORLD_WIDTH, 10));
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
    
    public void createPlatform(Rectangle rect){
            objects.add(new Platform(gc, world, rect.getX(), rect.getY(),  rect.getWidth()) );
    }

    public int createBox(Rectangle rect){

            Box b = new Box(gc, world, rect.getX(), rect.getY(),  rect.getWidth(), rect.getHeight());
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
                  return;
                }
                
                for(int j = 0; j < objects.size(); ++j){
                    
                    Entity destroyableBox = objects.get(j);
                    
                    if(destroyableBox.health <= 200f){
                        if(destroyableBox.getHitbox().intersects(e.getHitbox())){
                            destroyableBox.health -= 20f;
                            if(destroyableBox.health <= 0){
                                objects.remove(destroyableBox);
                            }
                            bullets.remove(i);
                        }
                    }
                    
                   
                    
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
