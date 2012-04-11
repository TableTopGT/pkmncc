package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Energy;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Game extends Activity{
    /** Called when the activity is first created. */
	private MediaPlayer battleMusic;
	private Bitmap charmander, squirtle, fire, water, pokeball1, pokeball2, retreat1, retreat2;
	private AssetManager assetManager;
	private InputStream inputStream;
	private enum State {START, BATTLE, TURN, END};
	private State gameState = State.START;
	private boolean gameStarting, gameStartingTwo, gameStartingThree;
	private RFIDListener rfid = new RFIDListener(this); //XXX onReCreate behavior?
	private enum Turn {ONE, TWO};
	private Turn playerTurn = Turn.ONE;
	private Player playerOne, playerTwo;
	RelativeLayout mat;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display mat background
        setContentView(R.layout.mat);

        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.title);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        gameStartingTwo = true;
        gameStartingThree = false;
        playerOne = new Player(null);
        playerTwo = new Player(playerOne);
        
        // Begin rfid listener
        rfid.start();
        
        // Setup Asset stream
        assetManager = this.getAssets();
    	try {
			inputStream = assetManager.open("images/Charmander.png");
			charmander = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/Squirtle.png");
			squirtle = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/fire.png");
			fire = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/water.png");
			water = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/pokeball1.png");
			pokeball1 = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/pokeball2.png");
			pokeball2 = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/retreat1.png");
			retreat1 = BitmapFactory.decodeStream(inputStream);
			inputStream = assetManager.open("images/retreat2.png");
			retreat2 = BitmapFactory.decodeStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Player One choose active pokemon followed by bench pokemon").setNeutralButton("OK", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
		        mat = (RelativeLayout) findViewById(R.id.mat);
				mat.addView(new RenderView(builder.getContext()));				
			}
		}).show();		
    }
    
    class RenderView extends View {
    	// The Constructor here calls a super function for View
    	public RenderView(Context context) {
    		super(context);
    	}
    	
    	// Called to draw things
    	protected void onDraw(Canvas canvas) {
    		switch(gameState){
    		case START:
        
        		//Lets draw some Prototype shi; will keep a lot of these placements
 /*       		canvas.drawBitmap(charmander, 800, 300, null);
        		canvas.drawBitmap(squirtle, 320, 300, null);
        		canvas.drawBitmap(fire, 1000, 465, null);
        		canvas.drawBitmap(fire, 1000, 415, null);
        		canvas.drawBitmap(water, 280, 350, null);
        		canvas.drawBitmap(water, 280, 300, null);
        		canvas.drawBitmap(pokeball1, 925, 500, null);
        		//canvas.drawBitmap(pokeball1, 1100, 100, null);
        		//canvas.drawBitmap(pokeball1, 1200, 100, null);
        		canvas.drawBitmap(pokeball2, 320, 175, null);
        		//canvas.drawBitmap(pokeball2, 25, 575, null);
        		//canvas.drawBitmap(pokeball2, 100, 575, null);
        		canvas.drawBitmap(retreat1, 945, 575, null);
        		canvas.drawBitmap(retreat2, 335, 50, null);
        		canvas.drawBitmap(benchchar, 1150, 600, null);
        		canvas.drawBitmap(benchchar, 1150, 500, null);
        		canvas.drawBitmap(benchchar, 1150, 400, null);
        		canvas.drawBitmap(benchchar, 1150, 300, null);
        		canvas.drawBitmap(benchchar, 1150, 200, null);
        		canvas.drawBitmap(benchsquir, 50, 50, null);
        		canvas.drawBitmap(benchsquir, 50, 150, null);
        		canvas.drawBitmap(benchsquir, 50, 250, null);
        		canvas.drawBitmap(benchsquir, 50, 350, null);
        		canvas.drawBitmap(benchsquir, 50, 450, null);        		
 */       		
        		if(gameStartingTwo){
        			switch(playerTurn){
        			case ONE :
        				initialPokemon(canvas, playerOne);

        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE/////////////////////
        				playerOne.addCard(new Energy(Element.FIRE));
        				playerOne.addCard(new Energy(Element.WATER));
        				playerOne.addCard(new Energy(Element.GRASS));
        				
        				playerTurn = Turn.TWO;
        				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        				builder.setMessage("Player Two choose active pokemon followed by bench pokemon").show();
        				break;
        			case TWO :
        				initialPokemon(canvas, playerTwo);
        				
        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE////////////////////////
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.LIGHTNING));
        				playerTwo.addCard(new Energy(Element.FIGHTING));
        				playerTwo.addCard(new Energy(Element.PSYCHIC));
        				playerTwo.getActive().removeEnergy();

        				AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        				builder2.setMessage("Players draw 6 prize cards").show();
        				gameStartingTwo = false;
        				gameStartingThree = true;
        				playerTurn = Turn.ONE;
        				gameState = State.BATTLE;
        				break;
        			}
        		}
        		invalidate();  // <----------THIS REDRAWS EVERYTHING OVER AND OVER
    			break;
    		case BATTLE:
    			Draw.drawPoke(canvas, playerOne, assetManager);
    			Draw.drawPoke(canvas, playerTwo, assetManager);
    			Draw.drawEnergy(playerOne, canvas, assetManager);
    			Draw.drawEnergy(playerTwo, canvas, assetManager);
    			switch(playerTurn){
    				case ONE :
    					// New class for the players Turns since there are so many options
    					playerOne.getActive().statusEffect();
    					playerTurn = Turn.TWO;
    					break;
    				case TWO :
    					// New class for the players Turns since there are so many options
    					playerOne.getActive().statusEffect();
    					playerTurn = Turn.ONE;
    					break;
    			}
    			invalidate();
    			break;
    		case TURN:
    			break;
    		case END:
    			break;
    		}
    	}
    	
    }
 
    
    // Will fix these later
	@Override
	public void onPause(){
		super.onPause();
		battleMusic.stop();
	}
	
//	// This is called every time a touch occurs on screen, gets coords
//	@Override
//	public boolean onTouchEvent (MotionEvent event){
//		xCoord = event.getRawX();
//		yCoord = event.getRawY();
//		HandleTouch(event);
//		return true;
//	}
//	
//	// Handles a touch on the screen
//	public void HandleTouch(MotionEvent e){
//		if(!mainDialog.done && checktouch){
//			if(OverlapTester.pointInRectangle(mainDialog.button, xCoord, yCoord)){
//				mainDialog.done = true;
//			}
//		}
//	}
	
	public void initialPokemon(Canvas board, Player activePlayer){
		activePlayer.startTurn();
		rfid.setMode(Mode.INIT);
		for (int k = 0; k < 3; ) {
			if (rfid.cardSwiped()) {
				activePlayer.addCard(rfid.getCard());
				++k;
			}
		}
		Draw.drawPoke(board, activePlayer, assetManager);
	}
	
/*	@Override
	public void onStop(){
		super.onStop();
		battleMusic.stop();
		battleMusic.release();
	}*/
	
	@Override
	public void onResume(){
		super.onResume();
		battleMusic.start();
	}


}
