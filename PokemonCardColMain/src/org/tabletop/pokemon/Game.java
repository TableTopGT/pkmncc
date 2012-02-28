package org.tabletop.pokemon;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
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
	
	Stage mainMenu;
	SpriteBatch menuBatch;
	Actor startButton, exitButton;
	Music introMusic;
	TextureRegion unpressedRegion, pressedRegion;
	Texture prototype;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		menuBatch = new SpriteBatch();
		unpressedRegion = new TextureRegion(prototype, SCREEN_WIDTH/2, SCREEN_HEIGHT/3, SCREEN_WIDTH/2, SCREEN_HEIGHT/4);
		pressedRegion = new TextureRegion(prototype, SCREEN_WIDTH/2, SCREEN_HEIGHT/3, SCREEN_WIDTH/2, SCREEN_HEIGHT/4);
		startButton = new Button(unpressedRegion, pressedRegion);
//		exitButton = new Button(unpressedRegion, pressedRegion);
		mainMenu = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, false, menuBatch);
		
		introMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("music/title.mp3", FileType.Internal));
		introMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

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
