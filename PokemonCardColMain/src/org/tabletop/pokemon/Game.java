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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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

	SpriteBatch batch;
	TextureRegion buttonRegion;
	Music introMusic, battleMusic;
	Texture startScreen, startButton, exitButton;
	Screen state;
	Rectangle startBound, exitBound;
	Vector3 touchPoint;
	
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		
		// Init. touchPoint
		touchPoint = new Vector3();
		
		// Create the SpriteBatch
		batch = new SpriteBatch();
		
		// Initialize music
		battleMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/25.mp3", FileType.Internal));

		// Initialize Textures
		startScreen =  new Texture(Gdx.files.internal("images/background.jpg"));
		startButton =  new Texture(Gdx.files.internal("images/diabetesbluecircle.png"));
		exitButton = new Texture(Gdx.files.internal("images/pikachu.jpg"));
		buttonRegion = new TextureRegion(startScreen, startScreen.getWidth(), startScreen.getHeight());
		
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));
		
		// Intitialize Button Touch Boundaries
		startBound = new Rectangle((SCREEN_WIDTH/2)-(startButton.getWidth())/2, (SCREEN_HEIGHT/2)+100, startButton.getWidth(), startButton.getHeight());

		// Initialize display screen
		state = Screen.START;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		switch (state) {
		case START:
			if (runOnce) {
				// Play music
				introMusic.play();
				
				// Draw start screen
				batch.begin();
				batch.draw(startScreen, 1, 1);
				batch.draw(startButton, (SCREEN_WIDTH/2)-(startButton.getWidth())/2, (SCREEN_HEIGHT/2)+100);
				batch.draw(startButton, (SCREEN_WIDTH/2)-(startButton.getWidth())/2, (SCREEN_HEIGHT/2)-300);
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
				batch.draw(exitButton, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
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
	}

}
