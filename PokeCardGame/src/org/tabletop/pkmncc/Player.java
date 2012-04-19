package org.tabletop.pkmncc;

import java.util.ArrayList;
import java.util.Random;
import org.tabletop.pkmncc.card.*;

public class Player {

	public static final int fieldSpots = 6;
	public static Player currentPlayer;
	private static final Random RNG = new Random();
	private static int playerCount;

	public final int playerNum = ++playerCount;
	public int card, health;
	public Player opponent;
	public Trainer thisTrainer; //tracks whether a trainer has been used already during a turn	
	
	//this array contains all the players pokemon. index 0 is the active pokemon. all the rest are benched
	private ArrayList<Pokemon> pokeArr = new ArrayList<Pokemon>(fieldSpots);
	
	/** Player constructor: input opponent */
	public Player(Player opponent) {
		if (opponent != null) {
			this.opponent = opponent;
			opponent.opponent = this;
		}
	}

	/** True is Heads, False is Tails */
	public boolean coinFlip() {
		return RNG.nextBoolean();
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
			this.getActive().addEnergy( (Energy) playedCard);
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

	public Pokemon getPokemon(int index) {
		return pokeArr.get(index);
	}

	/** Returns the player's active Pokemon **/
	public Pokemon getActive() {
		return getPokemon(0);
	}

	public int numPokemon() {
		return pokeArr.size();
	}

	/**
	 * Returns the index of the first occurrence
	 * of Pokemon or -1 if not found.
	 */
	public int getIndex(Pokemon thisPoke) {
		return pokeArr.indexOf(thisPoke);
	}

	/** Switch player's active Pokemon */
	public void switchActive(int newActiveIndex){
		Pokemon holder = pokeArr.get(0);
		pokeArr.set(0, pokeArr.get(newActiveIndex));
		pokeArr.set(newActiveIndex, holder);
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
		if ((pokeArr.size() < fieldSpots) && pokemonCard.isBasic()) {
			pokeArr.add(pokemonCard);
			pokemonCard.cry();
			return true;
		}
		for (int i = 0; i < pokeArr.size(); ++i) {
			if (pokemonCard.isEvolutionOf(pokeArr.get(i))) {
				pokeArr.get(i).transferStatsTo(pokemonCard);
				pokeArr.set(i, pokemonCard);
				pokemonCard.cry();
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
}
