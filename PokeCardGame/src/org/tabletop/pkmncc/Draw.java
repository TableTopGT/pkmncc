package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import org.tabletop.pkmncc.card.Pokemon;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;


public class Draw extends Activity{
	public static AssetManager pokedraw;
	private static InputStream instream;
	private static Bitmap activepokemon;
	
	
	/*Draws an active Pokemon, based on which player 
	(must find better way to input which player is active, probably quick fix in player class) */
	public void DrawMain(Pokemon Poke, Player player, Canvas canvas){
		pokedraw = this.getAssets();
		try {
			instream = pokedraw.open(Poke.getImage());
			activepokemon = BitmapFactory.decodeStream(instream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		if (player.playerNum==1){
			canvas.drawBitmap(activepokemon, 800, 300, null);
		}else if (player.playerNum==2){
			Matrix matrix = new Matrix();
	        // rotate the Bitmap
	        matrix.postRotate(180); 
	        // recreate the new Bitmap
	        Bitmap flippedpoke = Bitmap.createBitmap(activepokemon, 0, 0,
	        		activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
	   
	        canvas.drawBitmap(flippedpoke, 320, 300, null);
			
		}
	}
	
	// Cameron I made this so I can continue Game.java and tests to see if it works
	// You can probably use your Draw function in this one instead of canvas.drawBitmap
	// It iterates through the Bench and draws the whole playing side
	public static void drawBenchPoke(Canvas board, Player player, AssetManager pokedraw){
//		pokedraw = this.getAssets();
		int k = 0;
		Matrix matrix = new Matrix();
		Bitmap flippedpoke = null;
		while(k < player.pokeArr.length){
			try {
				if(player.pokeArr[k] == null) break;
				instream = pokedraw.open(player.pokeArr[k].getImage());
				activepokemon = BitmapFactory.decodeStream(instream);
				flippedpoke = Bitmap.createBitmap(activepokemon, 0, 0, activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
			} catch (IOException e) {
			e.printStackTrace();
			} finally {
			}
			if(k==0){
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 320, 300, null);
				if(player.playerNum == 2){
					matrix.postRotate(180);
					board.drawBitmap(flippedpoke, 800, 300, null);
				}
			}
			else{
				flippedpoke = Bitmap.createBitmap(activepokemon, 75, 75, activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 50, 50 + (100 * k), null);
				else if(player.playerNum == 2) board.drawBitmap(flippedpoke, 1150, 200 + (100 * k), null);
			}
			k++;
		}
	}
}
