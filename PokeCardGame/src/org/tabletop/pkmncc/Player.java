package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;

public class Player {

	public int card, health;
	public Pokemon holder; //used for switching array positions (active <--> benched)
	public Player otherPlayer;
	public int i; //generic counter
	public Trainer thisTrainer = null; //tracks whether a trainer has been used already during a turn	
	
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
			trainerCard.useTrainer(this, otherPlayer);
		}
		else {
			//open pop up that says you cannot play another trainer on this turn
		}
	}
	
	//execute when a player wants to add a pokemon to their bench
	public void addPokemon(Pokemon pokemonCard){
		//assign the pokemon to the first spot available
		i = 0;
		while (pokeArr[i] != null){
			i++;
		}
		if (i<=5){
			pokeArr[i]=pokemonCard;
		}
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
