package org.tabletop.pkmncc.card;

import org.tabletop.pkmncc.Player;

//this class contains all the functions associated with trainer cards
public class Trainer extends Card {
	
	public static enum TrainerType {POTION, ENERGYREMOVAL, FULLHEAL};
	
	public TrainerType trainerName;
	
	public Trainer(TrainerType name){
		trainerName = name;
	}
	
	public void useTrainer(Player opponent){
		if (trainerName == TrainerType.POTION){
			getOwner().getActive().addHP(10);
		}
		else if (trainerName == TrainerType.ENERGYREMOVAL){
			opponent.getActive().removeEnergy();
		}
		else if (trainerName == TrainerType.FULLHEAL){
			getOwner().getActive().removeAllStatus();
		}
	}
}
