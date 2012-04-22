package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Energy;
import org.tabletop.pkmncc.pokedex.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class Game extends Activity{
    /** Called when the activity is first created. */
	private MediaPlayer battleMusic;
	private enum State {START, BATTLE, TURN, END};
	private State gameState = State.START;
	private boolean gameStartingTwo;
	private RFIDListener rfid = new RFIDListener(this); //XXX onReCreate behavior?
	private enum Turn {ONE, TWO};
	private Turn playerTurn = Turn.ONE;
	static Player playerOne;
	static Player playerTwo;
	static FrameLayout mat;
	Draw surf;
	Context c = this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display mat background
        setContentView(R.layout.mat);
        mat = (FrameLayout) findViewById(R.id.frame1);
        surf = (Draw) findViewById(R.id.surfaceView1);
        
        // Create endturn views
        Button et = new Button(this);
        et.setLayoutParams(new FrameLayout.LayoutParams(70, 210));
        et.setX(1105);
        et.setY(20);
        Game.mat.addView(et);
        et.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (playerTurn == Turn.ONE) {
					if (event.getAction() == MotionEvent.ACTION_UP)
						new AlertDialog.Builder(c)
						.setMessage("Turn is over ").show();
					playerTurn = (Player.currentPlayer.playerNum == 1) ? Turn.TWO : Turn.ONE;
				}
				return false;
			}
		});
        
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.title);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        gameStartingTwo = true;
        playerOne = new Player(null);
        playerTwo = new Player(playerOne);
        
        ImageButton endTurn2 = (ImageButton) findViewById(R.id.endturn);
        ImageButton forfeit = (ImageButton) findViewById(R.id.quit);
        
        endTurn2.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (playerTurn == Turn.TWO) {
					if (event.getAction() == MotionEvent.ACTION_UP)
						new AlertDialog.Builder(c)
						.setMessage("Turn is over ").show();
					playerTurn = (Player.currentPlayer.playerNum == 1) ? Turn.TWO : Turn.ONE;
				}
				return false;
			}
		});
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
        		if(gameStartingTwo){
        			switch(playerTurn){
        			case ONE :
        				initialPokemon(canvas, playerOne);

        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE/////////////////////
        				playerOne.addCard(new Energy(Element.FIRE));
        				playerOne.addCard(new Energy(Element.WATER));
        				playerOne.addCard(new Energy(Element.GRASS));
        				
        				// Example of running retreat function
        				playerOne.getActive().setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View v, MotionEvent event) {
								new AlertDialog.Builder(getContext())
								.setMessage("retreating " + v.toString() + " w/ no 1").show();
								playerOne.switchActive(1);
		        				return false;
							}
						});
        				
        				playerTurn = Turn.TWO;
        				playerOne.addCard(new Charmeleon());
        				playerOne.addCard(new Pichu());
        				playerOne.addCard(new Machop());
        				playerOne.addCard(new Combee());

//        				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        				builder.setMessage("Player Two choose active pokemon followed by bench pokemon").show();
        				break;
        			case TWO :
        				initialPokemon(canvas, playerTwo);
        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE////////////////////////
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.LIGHTNING));
        				playerTwo.addCard(new Energy(Element.FIGHTING));
        				playerTwo.addCard(new Energy(Element.PSYCHIC));
        				//playerTwo.getActive().removeEnergy();
        				playerTwo.addCard(new Charmeleon());
        				playerTwo.addCard(new Charizard());
        				playerTwo.addCard(new Magnemite());
        				playerTwo.addCard(new Finneon());
        				playerTwo.addCard(new Duskull());

        				// Example of running custom function
        				playerTwo.getActive().setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View v, MotionEvent event) {
								new AlertDialog.Builder(getContext())
								.setMessage("Hi I'm " + v.toString()).show();	
		        				return false;
							}
						});
        				AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        				builder2.setMessage("Players draw 6 prize cards").show();
        				gameStartingTwo = false;
        				playerTurn = Turn.ONE;
        				gameState = State.BATTLE;
        				break;
        			}
        		}
    			break;
    		case BATTLE:
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
    			break;
    		case TURN:
    			break;
    		case END:
    			break;
    		}
    	}
    	
    	
    	private void initialPokemon(Canvas board, Player activePlayer){
    		activePlayer.startTurn();
    		rfid.setMode(Mode.INIT);
    		for (int k = 0; k < 3; ) {
    			if (rfid.cardSwiped()) {
    				activePlayer.addCard(rfid.getCard());
    				++k;
    			}
    		}
    	}
    }
 
    
    // Will fix these later
	@Override
	public void onPause(){
		super.onPause();
		surf.pause();
		rfid.pause();
		battleMusic.stop();
	}

	
	@Override
	public void onStop(){
		super.onStop();
		battleMusic.stop();
		battleMusic.release();
	}

	@Override
	public void onResume(){
		super.onResume();
		surf.resume();
		rfid.start();
		final Context c = this;
		new AlertDialog.Builder(this)
		.setMessage("Player One choose active pokemon followed by bench pokemon")
		.setNeutralButton("OK", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				mat.addView(new RenderView(c));				
			}
			
		}).show();		
//		mat.addView(new RenderView(c));
		battleMusic.start();
	}


}
