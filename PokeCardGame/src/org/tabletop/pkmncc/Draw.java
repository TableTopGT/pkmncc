package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import org.tabletop.pkmncc.card.Pokemon;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Draw extends SurfaceView implements Runnable {
	private final SurfaceHolder holder;
	private Thread drawThread;
	private volatile boolean running;
	private static Bitmap flippedpokeball;
	private static Bitmap flippedretreat;
	private static Bitmap retreat;
	private static Bitmap flippedgameover;
	private static Bitmap gameover;
	private static Bitmap flippedGObutton;
	private static Bitmap GObutton;
	private static Bitmap flippedendturn;
	private static Bitmap endturn;
	private static Bitmap flippedETbutton;
	private static Bitmap ETbutton;
	private static Bitmap battleGround;
	private static Bitmap pokeball;
	private static Bitmap healthRed;
	private static Bitmap health;
	private static Bitmap HP;
	private static Bitmap flippedHP;
	public static AssetManager assetmanager;
	private static InputStream instream;
	private static Bitmap alphaSprites;
	private static Bitmap scratch;

	public Draw (Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = getHolder();
		assetmanager = context.getAssets();
	}
	
	public boolean isReady() {
		return holder.getSurface().isValid();
	}
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
	private void loadAssets() {
		try{
			Matrix matrix = getMatrix();
			matrix.setRotate(180);
			instream = assetmanager.open("images/HPred.png");
			healthRed = BitmapFactory.decodeStream(instream);
			instream = assetmanager.open("images/pokeball.png");
			pokeball = BitmapFactory.decodeStream(instream);
			instream = assetmanager.open("images/HPgreen.png");
			health = BitmapFactory.decodeStream(instream);
			instream = assetmanager.open("images/battlebackground.png");
			battleGround = BitmapFactory.decodeStream(instream);
	        battleGround = Bitmap.createScaledBitmap(battleGround, 1280, 750, false);
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
			flippedpokeball = Bitmap.createBitmap(pokeball, 0, 0, pokeball.getWidth(), pokeball.getHeight(), matrix, true);
			instream = assetmanager.open("images/HP.png");
			HP = BitmapFactory.decodeStream(instream);
			flippedHP = Bitmap.createBitmap(HP, 0, 0, HP.getWidth(), HP.getHeight(), matrix, true);
			instream = assetmanager.open("images/alphasprites2.png");
			alphaSprites = BitmapFactory.decodeStream(instream);
			alphaSprites = Bitmap.createBitmap(alphaSprites);
			instream = assetmanager.open("images/stratch.png");
			scratch = BitmapFactory.decodeStream(instream);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void drawPokestuff( Player player, Canvas canvas, AssetManager pokedraw){
		Matrix matrix = new Matrix();
		Bitmap energy = null;
		Bitmap flippedenergy = null;
		Bitmap health = null;
		matrix.postRotate(180);
		if (player.numPokemon() == 0) return; 	
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
		if (((10*player.getActive().getHP())/player.getActive().getFullHP())< .4){
			health = Draw.healthRed;
		}else{
			health = Draw.health;
		}
		for (int k=0; (k*10) < player.getActive().getHP(); k++){
			if((player.playerNum == 1)&&(k<7)) canvas.drawBitmap(health, 768, 180-(30*(k-1)), null);
			if((player.playerNum == 1)&&(k>=7)) canvas.drawBitmap(health, 797, 180-(30*(k-8)), null);
			if((player.playerNum == 2)&&(k<7)) canvas.drawBitmap(health, 507, 550+(30*(k-1)), null);
			if((player.playerNum == 2)&&(k>=7)) canvas.drawBitmap(health, 483, 550+(30*(k-8)), null);
		}		
		if (((10*player.getActive().getHP())/player.getActive().getFullHP())< .4){
			health = Draw.healthRed;
		}else{
			health = Draw.health;
		}
		for (int k=0; (k*10) < player.getActive().getHP(); k++){
			if((player.playerNum == 1)&&(k<7)) canvas.drawBitmap(health, 768, 180-(30*(k-1)), null);
			if((player.playerNum == 1)&&(k>=7)) canvas.drawBitmap(health, 797, 180-(30*(k-8)), null);
			if((player.playerNum == 2)&&(k<7)) canvas.drawBitmap(health, 507, 550+(30*(k-1)), null);
			if((player.playerNum == 2)&&(k>=7)) canvas.drawBitmap(health, 483, 550+(30*(k-8)), null);
		}		
	} 
	
	
	public static void drawPoke(Canvas board, Player player, AssetManager pokedraw){
		for (int k = 0; k < player.numPokemon(); k++){
			
			Pokemon poke = player.getPokemon(k);
			
			// wait until added to mat
			if (poke.isShown()) {

				// get pokemon's image
				Bitmap pkb = getSprite(poke.getPokedexNumber());
	
				// rotate scale and draw pokemon
				Bitmap flippedpoke = rotate(pkb, poke.getRotation());
				int side = poke.getLayoutParams().height;
				Bitmap scaledPoke = Bitmap.createScaledBitmap(flippedpoke, side+10, side+10, true);
				board.drawBitmap(scaledPoke, poke.getX(), poke.getY(), null);
			}
		}
	}
	
	public static int[] getPokemonLayout(Player p, int index) {
		int degrees = (p.playerNum == 1) ? -90 : 90;
		int x = 0, y = 0, scale = 0;
		int k = index;
		if (k==0) {
			scale = 200;
			y = 295;
			x = (p.playerNum == 1) ? 800 : 320;
		} else {
			scale = 100;
			y = (p.playerNum == 1) ? 629 - (100 * (k-1)) : 21 + (100 * (k-1));
			x = (p.playerNum == 1) ? 1159 : 21;
		}
		return new int[] {x,y,scale,degrees};
	}
	
	private static Rect getSpriteRect(int pokedex) {
		int leftOffset = 17;
		int topOffset = 3;
		int dex = pokedex-1;
		int w = 80;
		int h = 80;
		int l = leftOffset + dex % 25 *w;
		int r = l+w;
		int t = topOffset + dex / 25 * h;
		int b = t+h;
		return new Rect(l,t,r,b);
	}
	
	private static Rect getAttackRect(int pos) {
		int leftOffset = 0;
		int topOffset = 0;
		int dex = pos-1;
		int w = 52;
		int h = 52;
		int l = leftOffset + dex % 25 *w;
		int r = l+w;
		int t = topOffset + dex / 25 * h;
		int b = t+h;
		return new Rect(l,t,r,b);
	}
	
	private static Bitmap getAttackSprite(int pokedex) {
		Rect w = getAttackRect(pokedex);
		return Bitmap.createBitmap(alphaSprites, w.left, w.top, w.width(), w.height());
	}
	
	private static Bitmap getSprite(int pokedex) {
		Rect w = getSpriteRect(pokedex);
		return Bitmap.createBitmap(alphaSprites, w.left, w.top, w.width(), w.height());
	}
	
	private static Bitmap rotate(Bitmap src, float f) {
		Matrix m = new Matrix();
		m.postRotate(f);
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
	}
	
	public static void drawBoard(Canvas board, AssetManager assetmanager){
        board.drawBitmap(battleGround, 0, 0, null);
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


	@Override
	public void run() {
		while (running) {
			if (!isReady())
				continue;
			Canvas canvas = holder.lockCanvas();
			Draw.drawBoard(canvas, assetmanager);		
			Draw.drawPoke(canvas, Game.playerOne, assetmanager);
			Draw.drawPoke(canvas, Game.playerTwo, assetmanager);
			Draw.drawPokestuff(Game.playerOne, canvas, assetmanager);
			Draw.drawPokestuff(Game.playerTwo, canvas, assetmanager);
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void resume() {
		running = true;
		drawThread = new Thread(this);
		loadAssets();
		drawThread.start();
		
	}
	
	public void pause() {
		running = false;
		while (true)
			try {
				drawThread.join();
				break;
			} catch (Exception e) {
				// try again
			}
	}
}
