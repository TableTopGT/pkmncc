package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;
import org.tabletop.pkmncc.RFIDListener.Mode;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Energy;
import org.tabletop.pkmncc.card.Pokemon;
import org.tabletop.pkmncc.pokedex.*;
import org.w3c.dom.Text;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.DragEvent;

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
	static public FrameLayout mat;
	static Button tv11, tv12, tv21, tv22;

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
        				initialPokemon(canvas, playerOne);
        				playerOne.addCard(new Energy(Element.FIRE));
        				playerOne.addCard(new Energy(Element.WATER));
        				playerOne.addCard(new Energy(Element.GRASS));        				
        				playerOne.addCard(new Charmeleon());
        				playerOne.addCard(new Pichu());
        				playerOne.addCard(new Machop());
        				playerOne.addCard(new Combee());
        				playerTurn = Turn.ONET;
        				String[]  namesone = playerOne.getActive().getActionNames();
        				
        				tv11 = new Button(c);
        				tv11.setText(namesone[0]);
        				tv11.setTextSize(20);
        				tv11.setTextColor(Color.BLACK);
        				//tv11.setBackgroundColor(Color.GREEN);
        				tv11.setRotation(270);
        		        tv11.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
        		        tv11.setX(742);
        		        tv11.setY(106);   
        		        tv11.setPadding(30,0,0,0);
        		        tv11.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								if(!gameStartingTwo){
									playerOne.getActive().actionOne(playerTwo);
									attackAnim(playerOne, playerTwo);
								}else{
									new AlertDialog.Builder(c)
									.setMessage("Hey bud, calm down, the game hasn't started yet").show();
								}
							}
						});
        		        Demo.mat.addView(tv11);
        		        if (namesone[1]!= null){
	        		        tv12 = new Button(c);
	        				tv12.setText(namesone[1]);
	        				tv12.setTextSize(20);
	        				tv12.setTextColor(Color.BLACK);
	        				//tv12.setBackgroundColor(Color.GREEN);
	        				tv12.setRotation(270);
	        		        tv12.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
	        		        tv12.setX(812);
	        		        tv12.setY(106);   
	        		        tv12.setPadding(30,0,0,0);
	        		        tv12.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									if(!gameStartingTwo){
										playerOne.getActive().actionTwo(playerTwo);
										attackAnim(playerOne, playerTwo);
									}else{
										new AlertDialog.Builder(c)
										.setMessage("Hey bud, calm down, the game hasn't started yet").show();
									}	
								}
							});
        		        }
        		        Demo.mat.addView(tv12);

        	
        				
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
        				String[] names = playerTwo.getActive().getActionNames();
        				playerTurn = Turn.TWOT;
        				tv21 = new Button(c);
        				tv21.setText(names[0]);
        				tv21.setTextSize(20);
        				tv21.setTextColor(Color.BLACK);
        				//tv21.setBackgroundColor(Color.GREEN);
        				tv21.setRotation(90);
        		        tv21.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
        		        tv21.setX(293);
        		        tv21.setY(587);   
        		        tv21.setPadding(30,0,0,0);
        		        tv21.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								if(!gameStartingTwo){
									playerTwo.getActive().actionOne(playerOne);
									attackAnim(playerTwo, playerOne);
								}else{
									new AlertDialog.Builder(c)
									.setMessage("Hey bud, calm down, the game hasn't started yet").show();
								}
							}
						});
        		        Demo.mat.addView(tv21);
        		        if (names[1]!= null){
	        		        tv22 = new Button(c);
	        				tv22.setText(names[1]);
	        				tv22.setTextSize(20);
	        				tv22.setTextColor(Color.BLACK);
	        				//tv22.setBackgroundColor(Color.GREEN);
	        				tv22.setRotation(90);
	        		        tv22.setLayoutParams(new FrameLayout.LayoutParams(239, 57));
	        		        tv22.setX(223);
	        		        tv22.setY(587);   
	        		        tv22.setPadding(30,0,0,0);
	        		        tv22.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									if(!gameStartingTwo){
										playerTwo.getActive().actionTwo(playerOne);
										attackAnim(playerTwo, playerOne);
									}else{
										new AlertDialog.Builder(c)
										.setMessage("Hey bud, calm down, the game hasn't started yet").show();
									}								}
							});
        		        }
        		        Demo.mat.addView(tv22);

   
        				
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
