package org.tabletop.pkmncc;

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
	
	/** Player constructor: input opponent */
	public Player(Player opponent) {
		if (opponent != null) {
			this.opponent = opponent;
			opponent.opponent = this;
		}
	}

	/** True is Heads, False is Tails */
	public boolean coinFlip() {
		return (new Random()).nextBoolean();
	}
	
	/** Returns the player's active Pokemon **/
	public Pokemon getActive() {
		return pokeArr[0];
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
	
	/**
	 * Plays a pokemon card one of in two cases:<br>
	 * (1) Find an empty bench spot and the card is BASIC<br>
	 * (2) Find an occupied spot and the card is an Evolution of the pokemon
	 * occupying that spot
	 * @param pokemonCard - a card to play
	 * @return whether the attempt was successful or not
	 */
	public final boolean addPokemon(Pokemon pokemonCard) {
		for (int i = 0; i < pokeArr.length; ++i) {
			if (pokeArr[i] == null) {
				if (pokemonCard.isBasic()) {
					pokeArr[i] = pokemonCard;
					return true;
				}
			} else if (pokemonCard.isEvolutionOf(pokeArr[i])) {
				pokeArr[i].transferStatsTo(pokemonCard);
				pokeArr[i] = pokemonCard;
				return true;
			}
		}
		return false;
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
