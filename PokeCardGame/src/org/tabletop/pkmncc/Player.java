package org.tabletop.pkmncc;

import java.util.ArrayList;
import java.util.Random;
import org.tabletop.pkmncc.card.*;

import android.widget.FrameLayout;

public class Player {

	public static final int fieldSpots = 6;
	public static Player currentPlayer;
	private static final Random RNG = new Random();
	private static int playerCount;

	public final int playerNum = ++playerCount;
	public int card, health;
	public Player opponent;
	public Trainer thisTrainer; //tracks whether a trainer has been used already during a turn	
	public int Prizeleft= 6;
	
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
			if(addPokemon( (Pokemon) playedCard)) {
				
				// get image layout
				int k = getIndex((Pokemon) playedCard);
				int[] p = Draw.getPokemonLayout(this, k);

				// Set coordinates, rotation, dimensions
				playedCard.setX(p[0]);
				playedCard.setY(p[1]);
				playedCard.setRotation(p[3]);
				playedCard.setLayoutParams(new FrameLayout.LayoutParams(p[2], p[2]));

				// Add pokemon to board
				Game.mat.addView(playedCard);
			}
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
		Pokemon newActive = pokeArr.get(newActiveIndex);
		swapLayouts(newActive, holder);
		pokeArr.set(0, newActive);
		pokeArr.set(newActiveIndex, holder);
	}

	private void swapLayouts(Pokemon a, Pokemon b) {
		int h[] = Draw.getPokemonLayout(this, getIndex(a));
		int n[] = Draw.getPokemonLayout(this, getIndex(b));
		Game.mat.removeView(a);
		Game.mat.removeView(b);
		a.setX(n[0]);
		a.setY(n[1]);
		a.setLayoutParams(new FrameLayout.LayoutParams(n[2], n[2]));
		b.setX(h[0]);
		b.setY(h[1]);
		b.setLayoutParams(new FrameLayout.LayoutParams(h[2], h[2]));
		Game.mat.addView(a);
		Game.mat.addView(b);
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
			Pokemon checkPoke = pokeArr.get(i);
			if (pokemonCard.isEvolutionOf(checkPoke)) {
				checkPoke.transferStatsTo(pokemonCard);
				Game.mat.removeView(checkPoke);
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
