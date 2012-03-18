package org.tabletop.pkmncc;

import org.tabletop.pkmncc.Card.*;
import org.tabletop.pkmncc.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;

public class RFIDListener {
	
	public String RFIDTag;
	public Card newCard;
		
	/*public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		return RFIDTag;
		}*/
	
	public Card getCard () {
		//insert code about getting card tag from RFID reader and setting it
		//equal to RFIDTag				
		
		// Takes in the string, compares it with known values and returns the card
		if (RFIDTag == "O11111110"){
			newCard = new Charizard();
		}
		else if (RFIDTag == "0222222220"){
			newCard = new Charmander();
		}
		else if (RFIDTag == "03333333330"){
			newCard = new Charmeleon();
		}
		else if (RFIDTag == "0444444440"){
			newCard = new Energy(Element.COLORLESS);
		}
		else if (RFIDTag == "0555555550"){
			newCard = new Energy(Element.DARKNESS);
		}
		else if (RFIDTag == "0666666660"){
			newCard = new Energy(Element.FIGHTING);
		}
		else if (RFIDTag == "0777777770"){
			newCard = new Energy(Element.FIRE);
		}
		else if (RFIDTag == "0888888880"){
			newCard = new Energy(Element.GRASS);
		}
		else if (RFIDTag == "0999999990"){
			newCard = new Energy(Element.LIGHTNING);
		}
		else if (RFIDTag == "0121212120"){
			newCard = new Energy(Element.METAL);
		}
		else if (RFIDTag == "0131313130"){
			newCard = new Energy(Element.WATER);
		}
		else if (RFIDTag == "0141414140"){
			newCard = new Trainer(TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag == "0161616160"){
			newCard = new Trainer (TrainerType.FULLHEAL);
		}
		else if (RFIDTag == "0171717170"){
			newCard = new Trainer (TrainerType.POTION);
		}
		else {
			newCard = null;
		}
	
		return (Card) newCard;
	}

}
