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
	public AssetManager pokedraw;
	private InputStream instream;
	private Bitmap activepokemon;
	
	
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
}
