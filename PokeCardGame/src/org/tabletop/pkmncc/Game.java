package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Energy;
import org.tabletop.pkmncc.card.Pokemon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class Game extends Activity{
    /** Called when the activity is first created. */
	public MediaPlayer battleMusic;
	public Bitmap battleGround, charmander, squirtle, fire, water, pokeball1, pokeball2, retreat1, retreat2;
	public Bitmap benchchar, benchsquir;
	public AssetManager assetManager, pokedraw;
	public InputStream inputStream;
	public boolean checktouch =true;
	public Paint textPaint, dialogBoxPaint, dialogButtonPaint;
	public enum State {START, BATTLE, TURN, END};
	public State gameState = State.START;
	public Rect dialogBoxRect;
	public float xCoord;
	public float yCoord;
	public boolean gameStarting, gameStartingTwo, gameStartingThree, initiateVars, initialSwipes;
	public DialogBox mainDialog;
	public RFIDListener rfid = new RFIDListener(this); //XXX onReCreate behavior?
	public enum Turn {ONE, TWO};
	public Turn playerTurn = Turn.ONE;
	public Player playerOne, playerTwo;
	RelativeLayout mat;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets the current view to the RenderView of "this"
        setContentView(R.layout.mat);
        mat = (RelativeLayout) findViewById(R.id.mat);
		mat.addView(new RenderView(this));

        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.title);
        battleMusic.setLooping(true);
        
        // Setup Paint types
        textPaint = new Paint();
        dialogBoxPaint = new Paint();
        dialogButtonPaint = new Paint();
//        dialogBoxPaint.setARGB(5, 100, 50, 0);
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeWidth(30);
        textPaint.setTextSize(30);
        dialogBoxPaint.setColor(Color.RED);
        dialogButtonPaint.setColor(Color.YELLOW);
        dialogBoxPaint.setStyle(Paint.Style.FILL); 
        dialogButtonPaint.setStyle(Paint.Style.FILL);
        
        gameStarting = true;
        gameStartingTwo = false;
        gameStartingThree = false;
        initiateVars = true;
        initialSwipes = false;
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
		} finally {
		}
    }
    
    class RenderView extends View {
    	
    	public RenderView(Context context) {
    		super(context);
    	}
    	
    	// Called to draw things
    	protected void onDraw(Canvas canvas) {
    	// to be implemented
    		int width = canvas.getWidth();  // Width of canvas
    		int height = canvas.getHeight(); // Height of canvas
    		
    		switch(gameState){
    		case START:
        		if(initiateVars){
            		// scale bench Pokemon
//            		benchchar = Bitmap.createScaledBitmap(charmander, 75, 75, false);
//            		benchsquir = Bitmap.createScaledBitmap(squirtle, 75, 75, false);
        			dialogBoxRect = new Rect((width/2)-300, (height/2)+200, (width/2)+300, (height/2)-200);
        			mainDialog = new DialogBox("Both players draw 7 cards", textPaint, dialogBoxRect, dialogBoxPaint, dialogButtonPaint);
        			initiateVars = false;
        		}

        		
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
        		if(gameStartingThree){
        			if(!mainDialog.done){
            			mainDialog.draw(canvas);
        			}
        			else{
        				checktouch=false;
        				gameStartingThree = false;
        				mainDialog.done = false;
        				gameState = State.BATTLE;
        				checktouch=true;
        			}
        		}
        		if(gameStartingTwo){
        			switch(playerTurn){
        			case ONE :
        				initialPokemon(canvas, playerOne);
        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE/////////////////////
        				playerOne.addCard(new Energy(Element.FIRE));
        				playerOne.addCard(new Energy(Element.WATER));
        				playerOne.addCard(new Energy(Element.GRASS));
        				/////////////////////////////////////////////////////////////
        				mainDialog.done = false;
        				playerTurn = Turn.TWO;
        				mainDialog.setText("Player Two choose active pokemon followed by bench pokemon");
        				//mainDialog.draw(canvas);
        				break;
        			case TWO :
        				initialPokemon(canvas, playerTwo);
        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE////////////////////////
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.LIGHTNING));
        				playerTwo.addCard(new Energy(Element.FIGHTING));
        				playerTwo.addCard(new Energy(Element.PSYCHIC));
        				//////////////////////////////////////////////////////////////
        				mainDialog.done = false;
        				mainDialog.setText("Players draw 6 prize cards");
        				mainDialog.draw(canvas);
        				gameStartingTwo = false;
        				gameStartingThree = true;
        				playerTurn = Turn.ONE;
        				checktouch=true;
        				break;
        			}
        		}
        		if(gameStarting){
        			if(!mainDialog.done){
            			mainDialog.draw(canvas);
        			}
        			else{
        				checktouch=false;
        				gameStarting = false;
        				gameStartingTwo = true;
        				mainDialog.done = false;
        				mainDialog.setText("Player One choose active pokemon followed by bench pokemon");
        				//mainDialog.draw(canvas);
        			}
        		}
        		

        		invalidate();  // <----------THIS REDRAWS EVERYTHING OVER AND OVER
    			break;
    		case BATTLE:
    			Draw.drawPoke(canvas, playerOne, assetManager);
    			Draw.drawPoke(canvas, playerTwo, assetManager);
    			Draw.drawPokestuff(playerOne, canvas, assetManager);
    			Draw.drawPokestuff(playerTwo, canvas, assetManager);
    			Draw.drawBoard(canvas, assetManager);
    			switch(playerTurn){
    				case ONE :
    					// New class for the players Turns since there are so many options
    					//rfid.swipeCard(playerOne)
    					playerOne.getActive().statusEffect();
    					playerTurn = Turn.TWO;
    					break;
    				case TWO :
    					// New class for the players Turns since there are so many options
    					//rfid.swipeCard(playerTwo);
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
		if (battleMusic != null) {
			battleMusic.pause();
		}
	}

	@Override
	public void onResume(){
		super.onResume();
		if (battleMusic != null)
			battleMusic.start();
	}

	// This is called every time a touch occurs on screen, gets coords
	@Override
	public boolean onTouchEvent (MotionEvent event){
		xCoord = event.getRawX();
		yCoord = event.getRawY();
		HandleTouch(event);
		return true;
	}

	// Handles a touch on the screen
	public void HandleTouch(MotionEvent e){
		if(!mainDialog.done && checktouch){
			if(OverlapTester.pointInRectangle(mainDialog.button, xCoord, yCoord)){
				mainDialog.done = true;
			}
		}
	}
	
	public void initialPokemon(Canvas board, Player activePlayer){
		activePlayer.startTurn();
		rfid.setMode(Mode.INIT);
		for (int k = 0; k < Player.fieldSpots; ) {
			//if(mainDialog.done) {
				if (rfid.cardSwiped()) {
					//if(!mainDialog.done) 
						activePlayer.addCard(rfid.getCard());
					//else break;
						k++;						
				}
			//}
			//else break;
		}
		Draw.drawPoke(board, activePlayer, assetManager);
	}
}
