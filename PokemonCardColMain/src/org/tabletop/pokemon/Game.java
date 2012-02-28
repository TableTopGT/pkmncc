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
	
	Stage mainMenu;
	SpriteBatch menuBatch, batch;
	Actor startButton, exitButton;
	Music introMusic;
	Texture startscreen;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		menuBatch = new SpriteBatch();
		mainMenu = new Stage(SCREEN_WIDTH, SCREEN_HEIGHT, false, menuBatch);
		
		batch = new SpriteBatch();
		startscreen =  new Texture(Gdx.files.internal("images/badlogic.jpg"));

		
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
		batch.begin();
		batch.draw(startscreen, 10, 10);
		batch.end();
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
