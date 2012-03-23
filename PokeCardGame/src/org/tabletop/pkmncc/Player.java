package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;

public class Player {

	public int card, health;
	public RFIDListener rfid;
	public Pokemon holder; //used for switching array positions (active <--> benched)
	public Player otherPlayer;
	public int i; //generic counter
	public Trainer thisTrainer = null; //tracks whether a trainer has been used already during a turn	
	public int playerNum;
	
	//this array contains all the players pokemon. index 0 is the active pokemon. all the rest are benched
	public Pokemon[] pokeArr = new Pokemon[6];
	
	//Player constructor--if you use this constructor, you should call setOpponent after
	public Player(){
		i = 0;
		//set all the players pokemon to null
		while (i<5){
			pokeArr[i]=null;
			i++;
		}
	}
	
	public Player(int playerPosition){
		i = 0;
		playerNum = playerPosition;
		//set all the players pokemon to null
		while (i<5){
			pokeArr[i]=null;
			i++;
		}
	}
	//Player constructor: input opponent
	public Player(Player opponent){
		i = 0;
		//set all the players pokemon to null
		while (i<5){
			pokeArr[i]=null;
			i++;
		}
		otherPlayer = opponent;		
	}
	
	/** True is Heads, Tails is False */
	public boolean coinFlip() {
		//TODO use Random number generator
		return true;
	}
	
	public Pokemon getActive() {
		return pokeArr[0];
	}
	
	//sets the players opponent
	public void setOpponent(Player opponent){
		otherPlayer=opponent;
	}

	//check to see what kind of card the player has scanned
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
	

	
	//execute when a player wants play a trainer card
	public void playTrainer(Trainer trainerCard){
		if (thisTrainer == null){
			thisTrainer = trainerCard;
			trainerCard.useTrainer(otherPlayer);
		}
		else {
			//open pop up that says you cannot play another trainer on this turn
		}
	}
	
	//execute when a player wants to add a pokemon to their bench
	
	// NOTICE : this function should just add a pokemon in a designated area in the pokeArray, filling
	//			up the array (when the game starts) will be a function in Game.java and will use this.
	//			also, it should auto-update the incrementer (i) to place the pokemon in the right place
	public void addPokemon(Pokemon pokemonCard){
		//assign the pokemon to the first spot available
		i = 0;
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
	
/*	public void endTurn(){
		i = 0;
		//if any of the pokemon are poisoned,subtract from their health 
		while (i<5){
			if (pokeArr[i].getStatus() == PokemonStatus.POISONED ){
				pokeArr[i].removeHP(10);
			}
		}
		thisTrainer = null;
	}
*/	
	public void switchActive(int newActiveIndex){
		holder = pokeArr[0];
		pokeArr[0]=pokeArr[newActiveIndex];
		pokeArr[newActiveIndex]=holder;		
	}

	//returns the index of the pokemon
	public int getIndex(Pokemon thisPoke){
		i=0;
		//go through the array testing for the pokemon
		while (pokeArr[i] != thisPoke && i<6){
			i++;
		}
		//returns the index of the pokemon, or '6' if pokemon isn't owned by player
		return i;
	}
	

}
