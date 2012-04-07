package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;


public class Draw {
	public static AssetManager pokedraw;
	private static InputStream instream;
	private static Bitmap activepokemon;
	
	
	/*Draws an active Pokemon, based on which player 
	(must find better way to input which player is active, probably quick fix in player class) */
/*	public void DrawMain(Pokemon Poke, Player player, Canvas canvas){
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
	} */
	
	public static void drawEnergy( Player player, Canvas canvas, AssetManager pokedraw){
		Matrix matrix = new Matrix();
		Bitmap energy = null;
		Bitmap flippedenergy = null;
		matrix.postRotate(180);
		for (int k = 0; k < player.getActive().getEnergy().size(); k++){
			try {
				instream = pokedraw.open(player.getActive().getEnergy().get(k).getImage());
				energy = BitmapFactory.decodeStream(instream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(player.playerNum == 1) canvas.drawBitmap(energy, 1000, 465-(50*(k-1)), null);
			if(player.playerNum == 2){
				flippedenergy = Bitmap.createBitmap(energy, 0, 0, energy.getWidth(), energy.getHeight(), matrix, true);
				canvas.drawBitmap(flippedenergy, 280, 300+(50*(k-1)), null);
			}
		}
	} 
	
	
	public static void drawPoke(Canvas board, Player player, AssetManager pokedraw){
		Matrix matrix = new Matrix();
		Bitmap flippedpoke = null;
		Bitmap flippedSmallpoke = null;
		for (int k = 0; k < player.numPokemon(); k++){
			try {
				instream = pokedraw.open(player.getPokemon(k).getImage());
				activepokemon = BitmapFactory.decodeStream(instream);
				flippedpoke = Bitmap.createBitmap(activepokemon, 0, 0, activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(k==0){
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 800, 300, null);
				if(player.playerNum == 2){
					matrix.postRotate(180);
					flippedpoke = Bitmap.createBitmap(activepokemon, 0, 0, activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
					board.drawBitmap(flippedpoke, 320, 300, null);
				}
			}
			else{
				flippedpoke = Bitmap.createScaledBitmap(activepokemon, 75, 75, true);
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 1150, 615 - (100 * (k-1)), null);
				else if(player.playerNum == 2){
					flippedSmallpoke = Bitmap.createBitmap(flippedpoke, 0, 0, flippedpoke.getWidth(), flippedpoke.getHeight(), matrix, true);
					board.drawBitmap(flippedSmallpoke, 50, 50 + (100 * (k-1)), null);
				}
			}
		}
	}
}
