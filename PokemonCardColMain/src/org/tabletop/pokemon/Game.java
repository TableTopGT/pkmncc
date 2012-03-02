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
	
	Stage mainMenu;
	SpriteBatch menuBatch, batch;
	Actor startButton, exitButton;
	Music introMusic;
	TextureRegion unpressedRegion, pressedRegion;
	Texture prototype;
	Texture startscreen;
	
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
		
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));
		introMusic.play();
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
