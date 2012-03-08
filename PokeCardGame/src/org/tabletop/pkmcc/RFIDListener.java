package org.tabletop.pkmcc;

import org.tabletop.pkmncc.pokedex.*;


public class RFIDListener {
	public static String RFIDTag;
	public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		return RFIDTag;
		}
	
	public Pokemon getCard (String RFIDTag){
		// Takes in the string, compares it with known values and returns the card
		if (RFIDTag == "O11111111")
			return new Charizard();
		else if (RFIDTag == "2222222222")
			return new Charmander();
		else 
			return null;
		}

}
