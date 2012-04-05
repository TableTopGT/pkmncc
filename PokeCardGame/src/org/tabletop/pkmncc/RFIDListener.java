package org.tabletop.pkmncc;

import org.tabletop.pkmncc.card.*;
import org.tabletop.pkmncc.card.Card.Element;
import org.tabletop.pkmncc.card.Trainer.TrainerType;
import org.tabletop.pkmncc.pokedex.*;
import android.content.Context;

/** This class must be instantiated before any cards are created. */
public final class RFIDListener extends Thread {

	public static enum Mode {INIT, SILENT};
	
	private Mode currMode = Mode.SILENT;
	private String RFIDTag = null;
	private boolean dataAvailable = false;
	
	public RFIDListener(Context context) {
		Card.setContext(context);
	}

	public boolean cardSwiped() {
		return dataAvailable;
	}

	public void setMode(Mode m) {
		currMode = m;
		//interrupt();
		//run(); //TODO really inefficient, need to implement wait()
	}

	public Card getCard() {
		
		Card swipedCard = null;
		
		// Return the swiped card
		if (RFIDTag.equals("O11111110")){
			swipedCard =  new Charizard();
		}
		else if (RFIDTag.equals("0222222220")){
			swipedCard =  new Charmander();
		}
		else if (RFIDTag.equals("03333333330")){
			swipedCard =  new Charmeleon();
		}
		else if (RFIDTag.equals("0444444440")){
			swipedCard =  new Energy(Element.COLORLESS);
		}
		else if (RFIDTag.equals("0555555550")){
			swipedCard =  new Energy(Element.DARKNESS);
		}
		else if (RFIDTag.equals("0666666660")){
			swipedCard =  new Energy(Element.FIGHTING);
		}
		else if (RFIDTag.equals("0777777770")){
			swipedCard =  new Energy(Element.FIRE);
		}
		else if (RFIDTag.equals("0888888880")){
			swipedCard =  new Energy(Element.GRASS);
		}
		else if (RFIDTag.equals("0999999990")){
			swipedCard =  new Energy(Element.LIGHTNING);
		}
		else if (RFIDTag.equals("0121212120")){
			swipedCard =  new Energy(Element.METAL);
		}
		else if (RFIDTag.equals("0131313130")){
			swipedCard =  new Energy(Element.WATER);
		}
		else if (RFIDTag.equals("0141414140")){
			swipedCard =  new Trainer(TrainerType.ENERGYREMOVAL);
		}
		else if (RFIDTag.equals("0161616160")){
			swipedCard =  new Trainer(TrainerType.FULLHEAL);
		}
		else if (RFIDTag.equals("0171717170")){
			swipedCard =  new Trainer(TrainerType.POTION);
		}

		// Don't allow main thread to get a new card until next swipe
		dataAvailable = false;

		// Reset RFIDtag
		RFIDTag = null;

		// Give the swiped card
		return swipedCard;
	}

	/** Swipe a card w/ specified tag after x seconds */
	private void swipeCard(String s, int seconds) {
		try {
			Thread.sleep(seconds*1000);
			RFIDTag = s; 				// swipe a card
			dataAvailable = true;		// notify of card swipe
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // sleep x seconds
	}
	
	@Override
	public void run() {
			while (true)
				switch(currMode) {
				case INIT:
					// swipe a charmander every 3 seconds
					swipeCard("0222222220", 3);
					break;
				case SILENT:
					//wait()
					break;
				}

	}

}
