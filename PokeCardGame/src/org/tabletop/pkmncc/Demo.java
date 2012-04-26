package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Energy;
import org.tabletop.pkmncc.card.Pokemon;
import org.tabletop.pkmncc.pokedex.*;

import android.animation.ObjectAnimator;
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

public class Demo extends Activity{
    /** Called when the activity is first created. */
	private MediaPlayer battleMusic;
	private enum State {START, BATTLE, TURN, END};
	private State gameState = State.START;
	private boolean gameStartingTwo;
	private RFIDListener rfid = new RFIDListener(this); //XXX onReCreate behavior?
	private enum Turn {ONE, TWO, NONE};
	private Turn playerTurn = Turn.ONE;
	static Player playerOne;
	static Player playerTwo;
	static FrameLayout mat;
	Draw surf;
	Context c = this;
//	Game Game = new Game();
	
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
        Demo.mat.addView(et);
        et.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (playerTurn != Turn.TWO) {
					playerTurn = Turn.TWO;
					new AlertDialog.Builder(c)
					.setMessage("Turn is over ").show();
					playerTwo.startTurn();
				}
			}
		});
        
		// Example of running retreat function
        Button retB = new Button(this);
        retB.setLayoutParams(new FrameLayout.LayoutParams(70, 210));
        retB.setX(905);
        retB.setY(498);
        Demo.mat.addView(retB);
		retB.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				new AlertDialog.Builder(c)
//				.setMessage("retreating " + playerOne.getActive().toString() + " w/ no 1").show();
				if (playerOne.getActive().getEnergySize() > 0)
					playerOne.getActive().removeEnergy();

				playerOne.switchActive(1);
			}
		});
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.title);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        gameStartingTwo = true;
        playerOne = new Player(null);
        playerTwo = new Player(playerOne);
        Game.playerOne = playerOne;
        Game.playerTwo = playerTwo;
        Game.mat = mat;
        Button endTurn2 = (Button) findViewById(R.id.endturn);
        Button forfeit = (Button) findViewById(R.id.quit);
        
        endTurn2.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (playerTurn != Turn.ONE) {
					playerTurn = Turn.ONE;
					new AlertDialog.Builder(c)
					.setMessage("Turn is over ").show();
					playerOne.startTurn();
				}
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
        				playerOne.addCard(new Energy(Element.FIRE));
        				playerOne.addCard(new Energy(Element.WATER));
        				playerOne.addCard(new Energy(Element.GRASS));        				
        				playerOne.addCard(new Charmeleon());
        				playerOne.addCard(new Pichu());
        				playerOne.addCard(new Machop());
        				playerOne.addCard(new Combee());
        				playerTurn = Turn.NONE;

        	
        				
//        				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        				builder.setMessage("Player Two choose active pokemon followed by bench pokemon").show();
        				break;
        			case TWO :
        				initialPokemon(canvas, playerTwo);
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.FIRE));
        				playerTwo.addCard(new Energy(Element.LIGHTNING));
        				playerTwo.addCard(new Energy(Element.FIGHTING));
        				playerTwo.addCard(new Energy(Element.PSYCHIC));
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
        				
        				playerOne.getActive().setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View v, MotionEvent event) {
								
								// Tackle
								float start = v.getTranslationX();
								int jump = (start < 640) ? 200 : -200;
		        		        ObjectAnimator o = ObjectAnimator.ofFloat(v, "translationX", start, start+jump, start);
		        		        //ObjectAnimator.setFrameDelay(1);
		        		        o.setDuration(1000);
		        		        o.start();
		        		   
		        		        
		        		        // Rotate Opposite pokemon
		        		        Pokemon p2Poke = playerTwo.getActive();
		        		        float rotStart = p2Poke.getRotation();
		        		        ObjectAnimator a = ObjectAnimator.ofFloat(p2Poke, "rotation", rotStart, rotStart+30, rotStart-30, rotStart);
		        		        a.setStartDelay(1000);
		        		        a.setDuration(500);
		        		        a.start();
		        		        return true;
							}
						});
        				AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        				builder2.setMessage("Players draw 6 prize cards").show();
        				gameStartingTwo = false;
//        				playerTurn = Turn.ONE;
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
    		invalidate();
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
