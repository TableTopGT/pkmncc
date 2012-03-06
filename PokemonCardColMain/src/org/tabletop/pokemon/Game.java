package org.tabletop.pokemon;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.audio.Music;
import java.lang.String;

public class Game implements ApplicationListener {
	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 800;
    /** flag indicating whether we were initialized already **/
    private boolean isInitialized = false;

    /** the current screen **/
    private Screen screen;
    int lastTouchX, lastTouchY;
	
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
	Vector2 touchPoint;
	
	
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		
		// Init. touchPoint and Button Boundaries
		touchPoint = new Vector2();
		
		// Create the SpriteBatch
		batch = new SpriteBatch();
		
		// Initialize music
		battleMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/25.mp3", FileType.Internal));

		// Initialize Textures
		startScreen =  new Texture(Gdx.files.internal("images/background.jpg"));
		startButton =  new Texture(Gdx.files.internal("images/diabetesbluecircle256.jpg"));
		exitButton = new Texture(Gdx.files.internal("images/pikachu.jpg"));
		buttonRegion = new TextureRegion(startScreen, startScreen.getWidth(), startScreen.getHeight());
		
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));
		
		// Intitialize Button Touch Boundaries
		startBound = new Rectangle(640-(startButton.getWidth())/2, 40, startButton.getWidth(), startButton.getHeight());
		exitBound = new Rectangle(640-(startButton.getWidth())/2, 400-300, startButton.getWidth(), startButton.getHeight());
		
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
		switch (state) {
		case START:
			if (runOnce) {
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				runOnce = false;
				// Play music
				introMusic.play();
				
				// Draw start screen
				batch.begin();
//				batch.draw(startScreen, 0, 0);
				batch.draw(startButton, 640-(startButton.getWidth())/2, 400+100);
				batch.draw(startButton, 640-(startButton.getWidth())/2, 400-300);
				batch.end();
				//set winner to null
				winner = null;
			}
			
		    if (Gdx.input.justTouched()) {
		    	touchPoint.set(Gdx.input.getX(), Gdx.input.getY());
		        lastTouchX = Gdx.input.getX();
		        lastTouchY = Gdx.input.getY();
		        if(OverlapTester.pointInRectangle(640-(startButton.getWidth())/2, 400+100, startButton, touchPoint.x, touchPoint.y)){
					introMusic.stop();
					state = Screen.BATTLE;
					runOnce = true;
	            	return;
		        }
		    }
		    else if (Gdx.input.isTouched()) {
		    	touchPoint.set(Gdx.input.getX(), Gdx.input.getY());
		        lastTouchX = Gdx.input.getX();
		        lastTouchY = Gdx.input.getY();
		        if(OverlapTester.pointInRectangle(640-(startButton.getWidth())/2, 400+100, startButton, touchPoint.x, touchPoint.y)){
					introMusic.stop();
					state = Screen.BATTLE;
					runOnce = true;
	            	return;
		        }
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
