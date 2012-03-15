package org.tabletop.pkmncc;
import org.tabletop.pkmncc.RFIDListener.CardType;
import org.tabletop.pkmncc.pokedex.Pokemon;


import android.app.Activity;
import android.view.View;
import android.widget.*;

public class Battle extends Activity {
	public Player player1;
	public Player player2;
	public Player currentPlayer;
	public Player winner = null;
	public RFIDListener cardListener;
	public Card newCard = null;
	public CardType newCardType;
	public boolean endTurn;

	
	/*************************
	//set up buttons
	final Button p1action1;
	final Button p1action2;
	final Button p1switch;
	final Button p1endTurn;
	final Button p2action1;
	final Button p2action2;
	final Button p2switch;
	final Button p2endTurn;
	**************************/
	
	
	public Battle(){
		//instantiate players
		player1 = new Player();
		player2 = new Player(player1);
		player1.setOpponent(player2);
		
		cardListener = new RFIDListener();
		
		endTurn = false;
		
		/*****************************************
		//draw end turn and switch buttons
		p1endTurn = (Button) findViewById(R.id.p1endTurn_button);
		p2endTurn = (Button) findViewById(R.id.p2endTurn_button);
		p1switch = (Button) findViewById(R.id.p1switch_button);
		p2switch = (Button) findViewById(R.id.p2switch_button);
		
		
		p1endTurn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// If its player 1's turn, allow them to end thier turn
				if (currentPlayer == player1){
					endTurn = true;
				}
			}
		});
		
		p2endTurn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// If its player 2's turn, allow them to end their turn
				if (currentPlayer == player2){
					endTurn=true;
				}
			}
		});
		
		p1switch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// if it's player 1's turn, allow them to switch pokemon
				// needs to be updated to query player which pokemon theyd like to switch
				if (currentPlayer == player1){
					if (player1.pokeArr[1] != null){
						player1.switchActive(1);
						updateP1buttons();
					}
				}
			}
		});
		
		p2switch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// if it's player 2's turn, allow them to switch pokemon
				// needs to be updated to query player which pokemon theyd like to switch
				if (currentPlayer == player2){
					if (player2.pokeArr[1] != null){
						player2.switchActive(1);
						updateP2buttons();
					}
				}
			}
		});
		************************************************/
		
	}
	
	public Player continueBattle(){
		player1turn1();
		while (winner == null){
			anyTurn(player1);
			//make sure player 1 didn't win before completing the loop
			if (winner == null){
			anyTurn(player2);
			}
		}
		return winner;
	}
	
	public void player1turn1(){
		//first move must be to add an active pokemon
		currentPlayer = player1;
		while (newCard==null){
		newCard = cardListener.getCard();
		newCardType = cardListener.getCardType();
		}
		if (newCardType == CardType.POKEMONCARD){
			player1.addCard(newCard);
			newCard = null;
			newCardType=null;
		}
		//now player can play either more pokemon or energy until turn is ended
		while (endTurn==false){
			newCard = cardListener.getCard();
			newCardType = cardListener.getCardType();
			if (newCardType == CardType.POKEMONCARD || newCardType == CardType.POKEMONCARD){
				player1.addCard(newCard);
				newCard = null;
				newCardType = null;
			}
		}
				
	}
	
	public void anyTurn(Player thisPlayer){
		currentPlayer = thisPlayer;
		endTurn = false;
		//draw buttons and setOnClickListener just for current player's buttons
		if (currentPlayer == player1){
			updateP1buttons();
		}
		else if (currentPlayer == player2){
			updateP2buttons();
		}
		
		while (endTurn = false){
			//Option 1: swipe a card
			newCard = cardListener.getCard();
			thisPlayer.addCard(newCard);
			//if new card is a pokemon, update action buttons
			if (newCard instanceof Pokemon){
				if (currentPlayer == player1){
					updateP1buttons();
				}
				else if (currentPlayer == player2){
					updateP2buttons();
				}
			}
			newCard = null;
			//update screen
			
			//Option 2: switch active and benched pokemon
			
						
			//Option 3: attack your opponent
			
			
			//Option 4: end your turn (without attack)
		}
	}
	
	public void updateP1buttons(){
		/*if (player1.pokeArr[0] != null){
			p1action1 = (Button) findViewById(player1.pokeArr[0].getId());
			p1action1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// update p1action1 button
					player1.pokeArr[0].actionOne(player2);
					endTurn = true;
				}
			});
			p1action2 = (Button) findViewById(player1.pokeArr[0].getId());
			p1action2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// update p1action2 button
					player1.pokeArr[0].actionTwo(player2);
					endTurn = true;
				}
			});
	}*/
	}
	
	public void updateP2buttons(){
		/*if (player2.pokeArr[0] != null){
			p2action1 = (Button) findViewById(player2.pokeArr[0].getId());
			p2action1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// update p2action1 button
				player2.pokeArr[0].actionOne(player1);
				}
			});
			p2action2 = (Button) findViewById(player2.pokeArr[0].getId());
			p2action2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// update p2action2 button
					player2.pokeArr[0].actionTwo(player1);
				}
			});
	}*/
	}
}
