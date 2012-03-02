package org.tabletop.pokemon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.audio.Music;
import java.lang.String;

public class Game implements ApplicationListener {
	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 800;
	private static String buttonOne = "startButton";
    /** flag indicating whether we were initialized already **/
    private boolean isInitialized = false;

    /** the current screen **/
    private Screen screen;
	
	// State machine variables
	public enum Screen {START, BATTLE};
	boolean runOnce = true;
	Player winner = null; 
	
	Battle thisBattle;

	Stage mainMenu;
	SpriteBatch menuBatch, batch;
	Button startButton, exitButton;
	TextureRegion unpressedRegion, pressedRegion;
	Texture prototype;
	Texture startscreen;
	Music introMusic, battleMusic;
	Texture startScreen, battleScreen;
	Screen state;
	
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		prototype = new Texture(Gdx.files.internal("images/badlogic.jpg"));
		menuBatch = new SpriteBatch();
//		exitButton = new Button(unpressedRegion, pressedRegion);
		mainMenu = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, true, menuBatch);
		unpressedRegion = new TextureRegion(prototype, 20, 20, prototype.getWidth(), prototype.getHeight());
		pressedRegion = new TextureRegion(prototype, 20, 20, prototype.getWidth(), prototype.getHeight());
		startButton = new Button(unpressedRegion, pressedRegion);
		mainMenu.addActor(startButton);
		batch = new SpriteBatch();
		
		// Initialize music
		battleMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/25.mp3", FileType.Internal));

		// Initialize textures (seems to dislike pngs)
		startScreen =  new Texture(Gdx.files.internal("images/badlogic.jpg"));
		battleScreen =  new Texture(Gdx.files.internal("images/pikachu.jpg"));
		
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));

		// Initialize display screen
		state = Screen.START;
		// Above stage and button code is useless for now, ignore, below this line is new approach
		//------------------------------------------------------------------------
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mainMenu.draw();
		switch (state) {
		case START:
			if (runOnce) {
				// Play music
				introMusic.play();
				
				// Draw start screen
				batch.begin();
				batch.draw(startScreen, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
				batch.end();
				
				//set winner to null
				winner = null;
								
				runOnce = false;
			}
			
			// Switch to next screen after 5 seconds
			if (introMusic.getPosition() > 5) {
				introMusic.stop();
				state = Screen.BATTLE;
				runOnce = true;
			}
			break;
		case BATTLE:
			if (runOnce) {
				// Play music
				battleMusic.setVolume(0.4f);
				battleMusic.play();
				
				// Draw battle screen
				batch.begin();
				batch.draw(battleScreen, SCREEN_WIDTH/2-15, SCREEN_HEIGHT/2-15);
				batch.end();
				
				//set winner to null
				winner = null;
				
				/*create a new battle				
				Battle thisBattle= new Battle();
				
				//do nothing until the battle is over
				do {} while (thisBattle.getBattleOver() == false);
				
				//set winner to winner of battle
				winner = thisBattle.getWinner();*/
				
				
				
				//exit battle case
				runOnce = false;
			}
			break;
		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		prototype.dispose();
		mainMenu.dispose();
	}

}
