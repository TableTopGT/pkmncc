package org.tabletop.pokemon;
//this is a class to create energy cards
public class Energy extends Card {

	public String energyType;

	
	//returns the type of energy
	public String getType(){
		return energyType;
	}

	
	//sets the energy to a specified type and return the Energy object
	public Energy setType (String thisType){	
		energyType = thisType;
		return this;
	}

}
