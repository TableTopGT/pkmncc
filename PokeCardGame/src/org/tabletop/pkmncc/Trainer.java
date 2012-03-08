package org.tabletop.pkmncc;

import org.tabletop.pkmncc.Pokemon.PokemonStatus;

//this class contains all the functions associated with trainer cards
public class Trainer extends Card {

	public String trainerName;
	
	public Trainer(String name){
		trainerName = name;
	}
	
	public void useTrainer(Player owner, Player opponent){
		if (trainerName == "potion"){
			owner.pokeArr[0].addHP(10);
		}
		else if (trainerName == "energyRemoval"){
			opponent.pokeArr[0].removeEnergy();
		}
		else if (trainerName == "fullHeal"){
			owner.pokeArr[0].setStatus(PokemonStatus.HEALTHY);
		}
	}
	
}
