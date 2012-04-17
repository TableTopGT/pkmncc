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
	
	public static void drawPokestuff( Player player, Canvas canvas, AssetManager pokedraw){
		Matrix matrix = new Matrix();
		Bitmap energy = null;
		Bitmap flippedenergy = null;
		Bitmap health = null;
		Bitmap pokeball= null;
		matrix.postRotate(180);
		for (int k = 0; k < player.getActive().getEnergy().size(); k++){
			try {
				instream = pokedraw.open(player.getActive().getEnergy().get(k).getImage());
				energy = BitmapFactory.decodeStream(instream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(player.playerNum == 1) canvas.drawBitmap(energy, 1000, 435-(50*(k-1)), null);
			if(player.playerNum == 2){
				flippedenergy = Bitmap.createBitmap(energy, 0, 0, energy.getWidth(), energy.getHeight(), matrix, true);
				canvas.drawBitmap(flippedenergy, 280, 330+(50*(k-1)), null);
			}
		}
		if (((10*player.getActive().getHP())/player.getActive().getfullHP())< .4){
			try{
				instream = pokedraw.open("images/HPred.png");
				health = BitmapFactory.decodeStream(instream);
				instream = pokedraw.open("images/pokeball.png");
				pokeball = BitmapFactory.decodeStream(instream);
			}catch (IOException e){
				e.printStackTrace();
			}
		}else{
			try{
				instream = pokedraw.open("images/HPgreen.png");
				health = BitmapFactory.decodeStream(instream);
				instream = pokedraw.open("images/pokeball.png");
				pokeball = BitmapFactory.decodeStream(instream);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		for (int k=0; (k*10) < player.getActive().getHP(); k++){
			if((player.playerNum == 1)&&(k<7)) canvas.drawBitmap(health, 768, 180-(30*(k-1)), null);
			if((player.playerNum == 1)&&(k>=7)) canvas.drawBitmap(health, 797, 180-(30*(k-8)), null);
			if((player.playerNum == 2)&&(k<7)) canvas.drawBitmap(health, 507, 550+(30*(k-1)), null);
			if((player.playerNum == 2)&&(k>=7)) canvas.drawBitmap(health, 483, 550+(30*(k-8)), null);
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
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 800, 295, null);
				if(player.playerNum == 2){
					matrix.postRotate(180);
					flippedpoke = Bitmap.createBitmap(activepokemon, 0, 0, activepokemon.getWidth(), activepokemon.getHeight(), matrix, true);
					board.drawBitmap(flippedpoke, 320, 295, null);
				}
			}
			else{
				flippedpoke = Bitmap.createScaledBitmap(activepokemon, 75, 75, true);
				if(player.playerNum == 1) board.drawBitmap(flippedpoke, 1150, 635 - (100 * (k-1)), null);
				else if(player.playerNum == 2){
					flippedSmallpoke = Bitmap.createBitmap(flippedpoke, 0, 0, flippedpoke.getWidth(), flippedpoke.getHeight(), matrix, true);
					board.drawBitmap(flippedSmallpoke, 50, 40 + (100 * (k-1)), null);
				}
			}
		}
	}
	
	
	public static void drawBoard(Canvas board, AssetManager assetmanager){
		Matrix matrix = new Matrix();
		Bitmap ETbutton = null;
		Bitmap flippedETbutton = null;
		Bitmap endturn = null;
		Bitmap flippedendturn = null;
		Bitmap GObutton = null;
		Bitmap flippedGObutton = null;
		Bitmap gameover = null;
		Bitmap flippedgameover = null;
		Bitmap retreat = null;
		Bitmap flippedretreat = null;
		Bitmap pokeball = null;
		Bitmap flippedpokeball = null;
		Bitmap HP= null;
		Bitmap flippedHP= null;
		matrix.postRotate(180);
		try {
			instream = assetmanager.open("images/ETbutton.png");
			ETbutton = BitmapFactory.decodeStream(instream);
			flippedETbutton = Bitmap.createBitmap(ETbutton, 0, 0, ETbutton.getWidth(), ETbutton.getHeight(), matrix, true);
			instream = assetmanager.open("images/endturn.png");
			endturn = BitmapFactory.decodeStream(instream);
			flippedendturn = Bitmap.createBitmap(endturn, 0, 0, endturn.getWidth(), endturn.getHeight(), matrix, true);
			instream = assetmanager.open("images/GObutton.png");
			GObutton = BitmapFactory.decodeStream(instream);
			flippedGObutton = Bitmap.createBitmap(GObutton, 0, 0, GObutton.getWidth(), GObutton.getHeight(), matrix, true);
			instream = assetmanager.open("images/gameover.png");
			gameover = BitmapFactory.decodeStream(instream);
			flippedgameover = Bitmap.createBitmap(gameover, 0, 0, gameover.getWidth(), gameover.getHeight(), matrix, true);
			instream = assetmanager.open("images/retreat.png");
			retreat = BitmapFactory.decodeStream(instream);
			flippedretreat = Bitmap.createBitmap(retreat, 0, 0, retreat.getWidth(), retreat.getHeight(), matrix, true);
			instream = assetmanager.open("images/pokeball.png");
			pokeball = BitmapFactory.decodeStream(instream);
			flippedpokeball = Bitmap.createBitmap(pokeball, 0, 0, pokeball.getWidth(), pokeball.getHeight(), matrix, true);
			instream = assetmanager.open("images/HP.png");
			HP = BitmapFactory.decodeStream(instream);
			flippedHP = Bitmap.createBitmap(HP, 0, 0, HP.getWidth(), HP.getHeight(), matrix, true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		board.drawBitmap(ETbutton, 1125, 180, null);
		board.drawBitmap(flippedETbutton, 100, 530, null);
		board.drawBitmap(endturn, 1125, 28, null);
		board.drawBitmap(flippedendturn, 95, 573, null);
		board.drawBitmap(GObutton, 1182, 180, null);
		board.drawBitmap(flippedGObutton, 40, 530, null);
		board.drawBitmap(gameover, 1190, 55, null);
		board.drawBitmap(flippedgameover, 45, 580, null);
		board.drawBitmap(retreat, 925, 575, null);
		board.drawBitmap(flippedretreat, 335, 50, null);
		board.drawBitmap(pokeball, 905, 500, null);
		board.drawBitmap(flippedpokeball, 320, 175, null);
		board.drawBitmap(pokeball, 835, 200, null);
		board.drawBitmap(flippedpokeball, 390, 500, null);
		board.drawBitmap(pokeball, 905, 200, null);
		board.drawBitmap(flippedpokeball, 320, 500, null);
		board.drawBitmap(HP, 765, 240, null);
		board.drawBitmap(flippedHP, 500, 457, null);
	}
}
