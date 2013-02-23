package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
import static javagame.Game.*;

public class Menu extends BasicGameState{

   private Rectangle playButton;
   private Rectangle exitButton;
   
   public Menu(int state){
   }
   
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
       gc.setTargetFrameRate(240);
       playButton = new Rectangle(WINDOW_WIDTH/2 - WINDOW_WIDTH/6, 200, WINDOW_WIDTH/3, 20);
       exitButton = new Rectangle(playButton.getX(), playButton.getY()+playButton.getHeight()*2, playButton.getWidth(), playButton.getHeight());
   }
   
   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
      
       g.drawString("Menu", WINDOW_WIDTH/2-20, 100);
       
        g.draw(playButton);
        g.drawString("Play!!", playButton.getX(), playButton.getY());
        g.draw(exitButton);
        g.drawString("Exit!!", exitButton.getX(), exitButton.getY());
   }
   
   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        int xpos = Mouse.getX();
        int ypos = WINDOW_HEIGHT - Mouse.getY();

        if(playButton.intersects(new Rectangle(xpos, ypos, 1, 1))){
            if(Mouse.isButtonDown(0)){
              sbg.enterState(GAME_STATE_PLAY);
            }
        }
        if(exitButton.intersects(new Rectangle(xpos, ypos, 1, 1))){
            if(Mouse.isButtonDown(0)){
              System.exit(0);
            }
        }

        if(gc.getInput().isKeyDown(Input.KEY_ESCAPE)){
           System.exit(0);
        } 
        if(gc.getInput().isKeyDown(Input.KEY_RETURN)){
           sbg.enterState(GAME_STATE_PLAY);
        } 
   }
   
   public int getID(){
        return GAME_STATE_MENU;
   }
}