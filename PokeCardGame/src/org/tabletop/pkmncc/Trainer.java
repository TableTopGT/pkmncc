package org.tabletop.pkmncc;

import org.tabletop.pkmncc.Pokemon.PokemonStatus;

//this class contains all the functions associated with trainer cards
public class Trainer extends Card {

	public static enum TrainerType {POTION, ENERGYREMOVAL, FULLHEAL};
	
	public TrainerType trainerName;
	
	public Trainer(TrainerType name){
		trainerName = name;
	}
	
	public void useTrainer(Player owner, Player opponent){
		if (trainerName == TrainerType.POTION){
			owner.pokeArr[0].addHP(10);
		}
		else if (trainerName == TrainerType.ENERGYREMOVAL){
			opponent.pokeArr[0].removeEnergy();
		}
		else if (trainerName == TrainerType.FULLHEAL){
			owner.pokeArr[0].setStatus(PokemonStatus.HEALTHY);
		}
	}
	
}
