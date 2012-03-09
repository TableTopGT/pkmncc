package org.tabletop.pkmncc;
import org.tabletop.pkmncc.RFIDListener.CardType;

import android.app.Activity;
import android.view.View;

public class Battle extends Activity {
	public Player player1;
	public Player player2;
	public Player winner = null;
	public RFIDListener cardListener;
	public Card newCard = null;
	public CardType newCardType;
	public boolean endTurn;
	
	//set up buttons
	public View p1action1;
	public View p1action2;
	public View p1switch;
	public View p1endTurn;
	public View p2action1;
	public View p2action2;
	public View p2switch;
	public View p2endTurn;
	
	
	public Battle(){
		//instantiate players
		player1 = new Player();
		player2 = new Player(player1);
		player1.setOpponent(player2);
		
		cardListener = new RFIDListener();
		
		endTurn = false;
		
		//draw end turn and switch buttons
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
		endTurn = false;
		//draw buttons and setOnClickListener just for current player's buttons
		if (thisPlayer == player1){
			
		}
		else if (thisPlayer == player2){
			
		}
		
		while (endTurn = false){
			//Option 1: swipe a card
			newCard = cardListener.getCard();
			thisPlayer.addCard(newCard);
			newCard = null;
			//update screed
			
			//Option 2: switch active and benched pokemon
			
						
			//Option 3: attack your opponent
			
			
			//Option 4: end your turn (without attack)
		}
	}
	
	
}
