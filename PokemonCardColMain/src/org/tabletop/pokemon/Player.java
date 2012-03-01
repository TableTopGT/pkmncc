package org.tabletop.pokemon;

public class Player {
	public int health;
	public int card;
	public Pokemon activePoke = null;
	public Pokemon bench1poke = null;
	public Pokemon bench2poke = null;
	public Pokemon bench3poke = null;
	public Pokemon bench4poke = null;
	public Pokemon bench5poke = null;
	public Trainer thisTrainer = null;
	public boolean myTurn = false;
	
	public void attack (Player defender){
		
	}
	
	//check to see what kin of card the player has scanned
	public void addCard(Card playedCard){

		if(playedCard instanceof Trainer){
			this.addTrainer( (Trainer) playedCard);
		}
		else if(playedCard instanceof Pokemon){
			this.addPokemon( (Pokemon) playedCard);
		}
		else if (playedCard instanceof Energy){
			this.addEnergy( (Energy) playedCard);
		}
	}
	
	//execute when a player wants play a trainer card
	public void addTrainer(Trainer trainerCard){
		if (thisTrainer == null){
			thisTrainer = trainerCard;
		}
		else {
			//open pop up that says you cannot play another trainer on this turn
		}
	}
	
	//execute when a player wants to add a pokemon to their bench
	public void addPokemon(Pokemon pokemonCard){
		//assign the pokemon to the first spot available
		if (activePoke == null){
			activePoke = pokemonCard;
		}
		else if (bench1poke == null){
			bench1poke = pokemonCard;
		}
		else if (bench2poke == null){
			bench2poke = pokemonCard;
		}
		else if (bench3poke == null){
			bench3poke = pokemonCard;
		}
		else if (bench4poke == null){
			bench4poke = pokemonCard;
		}
		else if (bench5poke == null){
			bench5poke = pokemonCard;
		}
		else {
			//make screen come up saying no more pokemon can be played 
		}
	}
	
	//execute if a player wants to add energy to a pokemon
	public void addEnergy(Energy energyCard){
		//display screen asking which pokemon player would like to add energy to
	}
	
	//execute at the end of every player turn to reset player's trainer card to null
	public void throwTrainer(){
		thisTrainer = null;
	}
	//Return the current trainer card associated with the player
	public Trainer getTrainer(){
		return thisTrainer;
	}
	
	public boolean isMyTurn(){
		return myTurn;
	}
	public void setMyTurn(boolean YorN){
		myTurn = YorN;
	}

}
