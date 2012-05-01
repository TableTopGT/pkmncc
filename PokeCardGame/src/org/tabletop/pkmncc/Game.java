package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;
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
import android.widget.FrameLayout;

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
		rfid.start();

		
        // Display mat background
        setContentView(R.layout.mat);
        mat = (FrameLayout) findViewById(R.id.frame1);
        surf = (Draw) findViewById(R.id.surfaceView1);
        
        // Hide menu bar
        mat.setSystemUiVisibility(View.INVISIBLE);
        
        
        
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.title);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        gameStartingTwo = true;
        playerOne = new Player(null);
        playerTwo = new Player(playerOne);
        
        
        // Create endturn views
     /*   Button et = new Button(this);
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
        

        
    /*    Button endTurn2 = (Button) findViewById(R.id.endturn);
        Button forfeit = (Button) findViewById(R.id.quit);
        
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
		});  */
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

        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE/////////////////////
//        				playerOne.addCard(new Energy(Element.FIRE));
//        				playerOne.addCard(new Energy(Element.WATER));
//        				playerOne.addCard(new Energy(Element.GRASS));
//        				
        				
        				playerTurn = Turn.TWO;

//        				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        				builder.setMessage("Player Two choose active pokemon followed by bench pokemon").show();
        				break;
        			case TWO :
//        				//DEBUG CODE TO ADD ENERGY TO ACTIVE POKE////////////////////////
//        				playerTwo.addCard(new Energy(Element.FIRE));
//        				playerTwo.addCard(new Energy(Element.FIRE));
//        				playerTwo.addCard(new Energy(Element.LIGHTNING));
//        				playerTwo.addCard(new Energy(Element.FIGHTING));
//        				playerTwo.addCard(new Energy(Element.PSYCHIC));
//        				//playerTwo.getActive().removeEnergy();

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
