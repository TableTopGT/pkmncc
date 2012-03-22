package org.tabletop.pkmncc.card;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon.PokemonStatus;

//this class contains all the functions associated with trainer cards
public class Trainer extends Card {
	
	public static enum TrainerType {POTION, ENERGYREMOVAL, FULLHEAL};
	
	public TrainerType trainerName;
	
	public Trainer(TrainerType name){ //TODO Should take in an owner
		super(CardType.TRAINER, (Player)null);
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
