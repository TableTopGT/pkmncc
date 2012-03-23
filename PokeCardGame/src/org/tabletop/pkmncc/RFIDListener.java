package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;
import static org.tabletop.pkmncc.Battle.currentPlayer;

public final class RFIDListener {
	
	public String RFIDTag;
		
	private boolean dataAvailable = false;
	
	public boolean dataOnBus() {
		return dataAvailable;
	}
	
	public String getTag() { //TODO asynch must run in seperate thread
		RFIDTag = null;
		return RFIDTag;
	}
	
	public Pokemon getPokeCard() {
		if (RFIDTag.equals("O11111110")){
			return new Charizard(currentPlayer);
		}
		return new Charmander(currentPlayer);
	}
	
	public boolean listen(){
		//while(USB.getData) or whatever
		return false;
	}
	
	public Card getCard() {
		RFIDTag = getTag(); //TODO Asynch compatible?
		
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
			return new Trainer(currentPlayer, TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag.equals("0161616160")){
			return new Trainer(currentPlayer, TrainerType.FULLHEAL);
		}
		else if (RFIDTag.equals("0171717170")){
			return new Trainer(currentPlayer, TrainerType.POTION);
		}
		else {
			return null;
		}
	}

}
