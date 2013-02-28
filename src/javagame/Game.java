package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String GAME_NAME = "Game!!";
	public static final int GAME_STATE_MENU = 0;
	public static final int GAME_STATE_PLAY = 1;
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final int WORLD_WIDTH = 1280 * 8;
	public static final int GAME_FPS = 240;

	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(GAME_STATE_MENU));
		this.addState(new Play(GAME_STATE_PLAY));
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(GAME_STATE_MENU).init(gc, this);
		this.getState(GAME_STATE_PLAY).init(gc, this);
		this.enterState(GAME_STATE_MENU);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
