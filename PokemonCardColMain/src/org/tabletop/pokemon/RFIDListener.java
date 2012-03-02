package org.tabletop.pokemon;

public class RFIDListener {
	public static String RFIDTag;
	public String getTag() {
		
		// Reads of the output of the RFID reader
		RFIDTag = this.getTag();
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
