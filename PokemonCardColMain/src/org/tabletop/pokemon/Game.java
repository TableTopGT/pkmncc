package org.tabletop.pokemon;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import java.lang.String;


public class Game implements ApplicationListener {
	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 800;
	private static String buttonOne = "startButton";
	
	// State machine variables
	public enum Screen {START, BATTLE};
	boolean runOnce = true;

	Stage mainMenu;
	SpriteBatch menuBatch, batch;
	Actor startButton, exitButton;
	Music introMusic, battleMusic;
	Texture startScreen, battleScreen;
	Screen state;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		menuBatch = new SpriteBatch();
		mainMenu = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, false, menuBatch);
		batch = new SpriteBatch();
		
		// Initialize textures (seems to dislike pngs)
		startScreen =  new Texture(Gdx.files.internal("images/badlogic.jpg"));
		battleScreen =  new Texture(Gdx.files.internal("images/pikachu.jpg"));
		
		// Initialize music
		battleMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/25.mp3", FileType.Internal));
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));

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
				// Play music
				introMusic.play();
				
				// Draw start screen
				batch.begin();
				batch.draw(startScreen, SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
				batch.end();
				
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
