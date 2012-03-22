package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;
import static org.tabletop.pkmncc.Battle.currentPlayer;

public class RFIDListener {
	
	public String RFIDTag;
	boolean waiter;
		
	/*public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		return RFIDTag;
		}*/
	
	public RFIDListener(){
		RFIDTag = "";
		waiter = false;
	}
	
	public Card getCard() {
		//insert code about getting card tag from RFID reader and setting it
		//equal to RFIDTag
		
		// Takes in the string, compares it with known values and returns the card
		if (RFIDTag.equals("O11111110")){
			return new Charizard(currentPlayer);
		}
		else if (RFIDTag.equals("0222222220")){
			return new Charmander(currentPlayer);
		}
		else if (RFIDTag.equals("03333333330")){
			return new Charmeleon(currentPlayer);
		}
		else if (RFIDTag.equals("0444444440")){
			return new Energy(Element.COLORLESS);
		}
		else if (RFIDTag.equals("0555555550")){
			return new Energy(Element.DARKNESS);
		}
		else if (RFIDTag.equals("0666666660")){
			return new Energy(Element.FIGHTING);
		}
		else if (RFIDTag.equals("0777777770")){
			return new Energy(Element.FIRE);
		}
		else if (RFIDTag.equals("0888888880")){
			return new Energy(Element.GRASS);
		}
		else if (RFIDTag.equals("0999999990")){
			return new Energy(Element.LIGHTNING);
		}
		else if (RFIDTag.equals("0121212120")){
			return new Energy(Element.METAL);
		}
		else if (RFIDTag.equals("0131313130")){
			return new Energy(Element.WATER);
		}
		else if (RFIDTag.equals("0141414140")){
			return new Trainer(TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag.equals("0161616160")){
			return new Trainer(TrainerType.FULLHEAL);
		}
		else if (RFIDTag.equals("0171717170")){
			return new Trainer(TrainerType.POTION);
		}
		else {
			return null;
		}
	}

}
