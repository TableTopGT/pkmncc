package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;
import org.tabletop.pkmncc.Battle;

public class RFIDListener {
	
	public String RFIDTag;
		
	/*public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		return RFIDTag;
		}*/
	
	public Card getCard() {
		//insert code about getting card tag from RFID reader and setting it
		//equal to RFIDTag				
		Card newCard;
		Player currPlayer = Battle.currentPlayer; //XXX should be pulled from Game
		// Takes in the string, compares it with known values and returns the card
		if (RFIDTag.equals("O11111110")){
			newCard = new Charizard(currPlayer);
		}
		else if (RFIDTag.equals("0222222220")){
			newCard = new Charmander(currPlayer);
		}
		else if (RFIDTag.equals("03333333330")){
			newCard = new Charmeleon(currPlayer);
		}
		else if (RFIDTag.equals("0444444440")){
			newCard = new Energy(Element.COLORLESS);
		}
		else if (RFIDTag.equals("0555555550")){
			newCard = new Energy(Element.DARKNESS);
		}
		else if (RFIDTag.equals("0666666660")){
			newCard = new Energy(Element.FIGHTING);
		}
		else if (RFIDTag.equals("0777777770")){
			newCard = new Energy(Element.FIRE);
		}
		else if (RFIDTag.equals("0888888880")){
			newCard = new Energy(Element.GRASS);
		}
		else if (RFIDTag.equals("0999999990")){
			newCard = new Energy(Element.LIGHTNING);
		}
		else if (RFIDTag.equals("0121212120")){
			newCard = new Energy(Element.METAL);
		}
		else if (RFIDTag.equals("0131313130")){
			newCard = new Energy(Element.WATER);
		}
		else if (RFIDTag.equals("0141414140")){
			newCard = new Trainer(TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag.equals("0161616160")){
			newCard = new Trainer(TrainerType.FULLHEAL);
		}
		else if (RFIDTag.equals("0171717170")){
			newCard = new Trainer(TrainerType.POTION);
		}
		else {
			newCard = null;
		}
	
		return newCard;
	}

}
