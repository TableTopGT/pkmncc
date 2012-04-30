package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card;
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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Demo extends Activity{
    /** Called when the activity is first created. */
	private MediaPlayer battleMusic;
	private enum State {START, BATTLE, TURN, END};
	private State gameState = State.START;
	private boolean gameStartingTwo;
	private RFIDListener rfid = new RFIDListener(this); //XXX onReCreate behavior?
	public enum Turn {ONE, TWO, ONET, TWOT};
	public static Turn playerTurn = Turn.ONE;
	static Player playerOne;
	static Player playerTwo;
	public static FrameLayout mat;
	static Button[] atk = new Button[4];


	public static boolean retreatUsed = false;
	public static boolean activeDead = false;

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
        
        
        // Hide menu bar
        mat.setSystemUiVisibility(View.INVISIBLE);
        
        // Create endturn views
        Button et = new Button(this);
        et.setLayoutParams(new FrameLayout.LayoutParams(45, 208));
        et.setX(1121);
        et.setY(18);
        Demo.mat.addView(et);
        et.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(activeDead == true){
					new AlertDialog.Builder(c)
					.setMessage("Your pokemon has fainted, please retreat").show();
					return;
				}
				if ((playerTurn ==Turn.ONE)||(playerTurn == Turn.ONET)) {
					retreatUsed = false;
					playerTurn = Turn.TWO;
					new AlertDialog.Builder(c)
					.setMessage("Player 2 Turn").show();
					playerTwo.startTurn();
				}
			}
		});
        
		// Example of running retreat function
        Button retB = new Button(this);
        retB.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
        retB.setRotation(90);
        retB.setX(808);
        retB.setY(587);
        Demo.mat.addView(retB);
		retB.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(activeDead == true && playerTurn == Turn.TWO){
					new AlertDialog.Builder(c)
					.setMessage("Your pokemon has fainted, please retreat").show();
					return;
				}
				if(playerOne.getActive().canRetreat()){
					switch(playerTurn){
					case ONE:
						for(int h = 1; h < playerOne.numPokemon(); ++h){
							if(playerOne.getPokemon(h).selected == true){
								retreatUsed = true;
								for(int u = 0; u < playerOne.getActive().getRetreat(); ++u)
									playerOne.getActive().removeEnergy();
								playerOne.switchActive(h);
								break;
							}
						}
						break;
					}
				}
			}
		});
		

        
        Button retBTwo = new Button(this);
        retBTwo.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
        retBTwo.setRotation(90);
        retBTwo.setX(224);
        retBTwo.setY(106);
        Demo.mat.addView(retBTwo);
		retBTwo.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(activeDead == true && playerTurn == Turn.ONE){
					new AlertDialog.Builder(c)
					.setMessage("Your pokemon has fainted, please retreat").show();
					return;
				}
				if(playerTwo.getActive().canRetreat()){
					switch(playerTurn){
					case TWO:
						for(int h = 1; h < playerTwo.numPokemon(); ++h){
							if(playerTwo.getPokemon(h).selected == true){
								retreatUsed = true;
								for(int u = 0; u < playerTwo.getActive().getRetreat(); ++u)
									playerTwo.getActive().removeEnergy();
								playerTwo.switchActive(h);
								
								
								break;
							}
						}
						break;
					}
				}

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
        
        Button et2 = new Button(this);
        et2.setLayoutParams(new FrameLayout.LayoutParams(45, 208));
        et2.setX(96);
        et2.setY(528);
        Demo.mat.addView(et2);
        et2.setOnClickListener(new android.view.View.OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				if(activeDead == true){
					new AlertDialog.Builder(c)
					.setMessage("Your pokemon has fainted, please retreat").show();
					return;
				}
				if ((playerTurn == Turn.TWO)||(playerTurn == Turn.TWOT)){ 
					retreatUsed = false;
					playerTurn = Turn.ONE;
					if (gameStartingTwo){
						AlertDialog.Builder builder2 = new AlertDialog.Builder(c);
        				builder2.setMessage("Players draw 6 prize cards").show();
        				gameStartingTwo = false;
        				gameState = State.BATTLE;
					}
					new AlertDialog.Builder(c)
					.setMessage("Player One Turn").show();
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
        	    		playerOne.startTurn();
        	    		if (rfid.cardSwiped())
        	    			playerOne.addCard(rfid.getCard(Pokemon.class));
        				updateAttacks();

    	    			

//        				playerOne.addCard(new Energy(Element.FIRE));
//        				playerOne.addCard(new Energy(Element.WATER));
//        				playerOne.addCard(new Energy(Element.GRASS));        				
//        				playerOne.addCard(new Charmeleon());
//        				playerOne.addCard(new Pichu());
//        				playerOne.addCard(new Machop());
//        				playerOne.addCard(new Combee());
//        				playerTurn = Turn.ONET;

//        				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        				builder.setMessage("Player Two choose active pokemon followed by bench pokemon").show();
        				break;
        			case TWO :
        	    		playerTwo.startTurn();
        	    		if (rfid.cardSwiped())
        	    			playerTwo.addCard(rfid.getCard(Pokemon.class));
    	    			

//        				playerTwo.addCard(new Energy(Element.FIRE));
//        				playerTwo.addCard(new Energy(Element.FIRE));
//        				playerTwo.addCard(new Energy(Element.LIGHTNING));
//        				playerTwo.addCard(new Energy(Element.FIGHTING));
//        				playerTwo.addCard(new Energy(Element.PSYCHIC));
//        				playerTwo.addCard(new Charmeleon());
//        				playerTwo.addCard(new Charizard());
//        				playerTwo.addCard(new Magnemite());
//        				playerTwo.addCard(new Finneon());
//        				playerTwo.addCard(new Duskull());
//        				playerTurn = Turn.TWOT;
        				
    	    			// End turn if player presses end turn button
    	    			if (playerTurn == Turn.ONE)
    	    				gameState = State.BATTLE;
        				break;
        			}
        		}
    			break;
    		case BATTLE:
    			switch(playerTurn){
    				case ONE :
    					if(playerTwo.getActive().isFainted() && activeDead == false){
    						new AlertDialog.Builder(c)
							.setMessage("Your pokemon has fainted, please retreat").show();
    						activeDead = true;
    						playerTurn = Turn.TWO;
    						playerTwo.startTurn();
    						//playerOne.getActive().Kill();
    					}
    					//playerOne.getActive().statusEffect();
    					break;
    				case TWO :
    					if(playerOne.getActive().isFainted() && activeDead == false){
    						new AlertDialog.Builder(c)
							.setMessage("Your pokemon has fainted, please retreat").show();
    						activeDead = true;
    						playerTurn = Turn.ONE;
    						playerOne.startTurn();
    						//playerTwo.getActive().Kill();
    					}
    					//playerOne.getActive().statusEffect();
    					break;
    			}
    			break; 
    		case TURN:
    			break;
    		case END:
    			break;
    		}
    		invalidate();
			updateAttacks();
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
		battleMusic.start();
	}
	
	public void updateAttacks() {
		Player p = Player.currentPlayer;
		
		// If there is no pokemon return
		if (p.numPokemon() == 0) return;
		
		// If there is an active pokemon
		Pokemon active = p.getActive();
		if (active != null) {
			
			// Get names and indices
			String[]  names = active.getActionNames();
			int dex = p.playerNum*2;
			
			// If actionOne doesn't exist create it else update it
			if (atk[dex-1] == null) {
				
				// Create first button
				atk[dex-1] = createAttackButton(p, names[0], 0);
		        Demo.mat.addView(atk[dex-1]);
			} else {
				atk[dex-1].setText(names[0]);
			}
			
			// If pokemon has an actionTwo do stuff else hide button
			if (names[1] != null) {
				
		        // Create button if it isn't made yet ese update it
		        if (atk[dex-2] == null) {
		        	atk[dex-2] = createAttackButton(p, names[1], 1);
			        Demo.mat.addView(atk[dex-2]);
		        } else {
		        	atk[dex-2].setEnabled(true);
		        	atk[dex-2].setVisibility(View.VISIBLE);
		        	atk[dex-2].setText(names[1]);
		        }
			} else if (atk[dex-2] != null) {
		        if (atk[dex-2].isShown()) {
		        	atk[dex-2].setVisibility(View.INVISIBLE);
		        	atk[dex-2].setEnabled(false);
		        }
			}
		}
	}
	
	private Button createAttackButton(final Player p, final String name, final int num) {
		
		// Create a button
		Button buttonMake = new Button(c);
		
		// Change text appearance
		buttonMake.setText(name);
		buttonMake.setTextSize(20);
		buttonMake.setTextColor(Color.BLACK);
//		buttonMake.setShadowLayer((float) 1, 0, 0, Color.RED);
		
		// Color the background
//		int lightGreen = Color.rgb(165, 248, 78);
//		buttonMake.getBackground().setColorFilter(lightGreen, PorterDuff.Mode.DST_IN);
//		int alightGreen = Color.argb(200, 165, 248, 78);
//		buttonMake.setBackgroundColor(alightGreen);
		
		// Position the button
		int degrees = (p.playerNum == 1) ? -90 : 90;
		int x = 0, y = 0;
		// First attack or Second attack
		if (num == 0) {
			y = (p.playerNum == 1) ? 106 : 587;
			x = (p.playerNum == 1) ? 742 : 293;
		} else {
			y = (p.playerNum == 1) ? 106 : 587;
			x = (p.playerNum == 1) ? 812 : 223;
		}
		buttonMake.setRotation(degrees);
        buttonMake.setY(y);   
        buttonMake.setX(x);
        buttonMake.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
        
        // Add a pokeball
//        buttonMake.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pokeball, 0, 0, 0);

        
        // Add a listener
        buttonMake.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!gameStartingTwo){
					if (num == 0)
						p.getActive().actionOne(p.opponent);
					else 
						p.getActive().actionTwo(p.opponent);
					attackAnim(p, p.opponent);
				}else{
					new AlertDialog.Builder(c)
					.setMessage("Hey bud, calm down, the game hasn't started yet").show();
				}								}
		});
        return buttonMake;
	}
	
	private void attackAnim(Player attacker, Player defender) {
		// Tackle
        Pokemon atk = attacker.getActive();
		float start = atk.getTranslationX();
		int jump = (start < 640) ? 200 : -200;
        ObjectAnimator o = ObjectAnimator.ofFloat(atk, "translationX", start, start+jump, start);
        o.setDuration(1000);
        o.start();
   
        
        // Rotate Opposite pokemon
        Pokemon def = defender.getActive();
        float rotStart = def.getRotation();
        ObjectAnimator a = ObjectAnimator.ofFloat(def, "rotation", rotStart, rotStart+30, rotStart-30, rotStart);
        a.setStartDelay(1000);
        a.setDuration(500);
        a.start();
	}

}
