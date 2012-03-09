package org.tabletop.pkmncc;

import org.tabletop.pkmncc.Energy.EnergyType;
import org.tabletop.pkmncc.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;

public class RFIDListener {
	
	public String RFIDTag;
	public Card newCard;
	public static enum CardType {POKEMONCARD, ENERGYCARD, TRAINERCARD};
	public CardType thisCardType;
		
	/*public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		return RFIDTag;
		}*/
	
	public Card getCard (){
		//insert code about getting card tag from RFID reader and setting it
		//equal to RFIDTag				
		
		// Takes in the string, compares it with known values and returns the card
		if (RFIDTag == "O11111110"){
			newCard = new Charizard();
			thisCardType = CardType.POKEMONCARD;
		}
		else if (RFIDTag == "0222222220"){
			newCard = new Charmander();
			thisCardType = CardType.POKEMONCARD;
		}
		else if (RFIDTag == "03333333330"){
			newCard = new Charmeleon();
			thisCardType = CardType.POKEMONCARD;
		}
		else if (RFIDTag == "0444444440"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.COLORLESS);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0555555550"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.DARKNESS);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0666666660"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.FIGHTING);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0777777770"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.FIRE);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0888888880"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.GRASS);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0999999990"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.LIGHTNING);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0121212120"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.METAL);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0131313130"){
			newCard = new Energy();
			((Energy) newCard).setType(EnergyType.WATER);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0141414140"){
			newCard = new Trainer(TrainerType.ENERGYREMOVAL);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0161616160"){
			newCard = new Trainer (TrainerType.FULLHEAL);
			thisCardType = CardType.ENERGYCARD;
		}
		else if (RFIDTag == "0171717170"){
			newCard = new Trainer (TrainerType.POTION);
			thisCardType = CardType.ENERGYCARD;
		}
		else {
			newCard = null;
			thisCardType = null;
		}
	
		return (Card) newCard;
	}
	
	public CardType getCardType(){
		return thisCardType;
	}
}
