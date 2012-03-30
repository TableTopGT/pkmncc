package org.tabletop.pkmncc;

import java.util.Arrays;
import java.util.Random;

import org.tabletop.pkmncc.card.*;

public class Player {

	public static Player currentPlayer;
	private static int playerCount = 0;
	public final int playerNum = ++playerCount;
	public int card, health;
	public RFIDListener rfid;
	public Player opponent;
	public Trainer thisTrainer = null; //tracks whether a trainer has been used already during a turn	
	
	//this array contains all the players pokemon. index 0 is the active pokemon. all the rest are benched
	public Pokemon[] pokeArr = new Pokemon[6];
	
	/**Player constructor--if you use this constructor, you should call setOpponent after**/
	public Player(){
		//set all the players pokemon to null
		Arrays.fill(pokeArr, null);
		assert (playerCount < 3) : "Too many players!";
	}
	
	/** True is Heads, False is Tails */
	public boolean coinFlip() {
		return (new Random()).nextBoolean();
	}
	
	/** Returns the player's active Pokemon **/
	public Pokemon getActive() {
		return pokeArr[0];
	}
	
	/** Set the players opponent **/
	public void setOpponent(Player opponent){
		this.opponent = opponent;
		opponent.opponent = this;
	}

	/**Check to see what kind of card the player has scanned**/
	public void addCard(Card playedCard){

		if(playedCard instanceof Trainer){
			this.playTrainer( (Trainer) playedCard);
		}
		else if(playedCard instanceof Pokemon){
			this.addPokemon( (Pokemon) playedCard);
		}
		else if (playedCard instanceof Energy){
			this.pokeArr[0].addEnergy( (Energy) playedCard);
		}
	}
	
	
	/**Execute when a player wants play a trainer card**/
	public void playTrainer(Trainer trainerCard){
		if (thisTrainer == null){
			thisTrainer = trainerCard;
			trainerCard.useTrainer(opponent);
		}
		else {
			//open pop up that says you cannot play another trainer on this turn
		}
	}
	
	/**Add a Pokemon to the player's bench**/
	
	// NOTICE : this function should just add a pokemon in a designated area in the pokeArray, filling
	//			up the array (when the game starts) will be a function in Game.java and will use this.
	//			also, it should auto-update the incrementer (i) to place the pokemon in the right place
	public void addPokemon(Pokemon pokemonCard){
		//assign the pokemon to the first spot available
		int i = 0;
		while (pokeArr[i] != null){
			if (i<=5){
//				while(rfid.waiter){
				while(rfid.dataOnBus()){
//				pokeArr[i]=rfid.getCard();  // NEEDS 3 DIFFERENT TYPES OF getCard, one that returns each type of card
				}
			}
			i++;
		}
	}
	
	//TODO use canPlay() dialogBox() getIndex() switchActive() to evolve
	public final boolean canPlay(Pokemon pokemonCard) {
		boolean playable = false;
		for (int i = 0; i < 7; i++) {
			if (pokeArr[i] == null) 
				playable = pokemonCard.isBasic();
			else
				playable = pokemonCard.isEvolutionOf(pokeArr[i]);
		}
		return playable;
	}
	
	public void startTurn() {
		currentPlayer = this;
	}
	
	/**Execute at end of player's turn to reset variables, clean up, etc.**/
	public void endTurn(){
		thisTrainer = null;
	}

	/**Switch player's active Pokemon**/
	public void switchActive(int newActiveIndex){
		Pokemon holder = pokeArr[0];
		pokeArr[0] = pokeArr[newActiveIndex];
		pokeArr[newActiveIndex] = holder;
	}

	/**Returns the index of the pokemon**/
	public int getIndex(Pokemon thisPoke){
		int i = 0;
		//go through the array testing for the pokemon
		while (pokeArr[i] != thisPoke && i<6){
			i++;
		}
		//returns the index of the pokemon, or '6' if pokemon isn't owned by player
		return i;
	}
	

}
