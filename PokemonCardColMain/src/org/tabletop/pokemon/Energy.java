package org.tabletop.pokemon;
//this is a class to create energy cards
public class Energy extends Card {

	public enum EnergyType {FIRE, WATER, FIGHTING, PSYCHIC, LIGHTING, GRASS};
	
	public EnergyType thisType;

	
	//returns the type of energy
	public EnergyType getType(){
		return thisType;
	}

	
	//sets the energy to a specified type and return the Energy object
	public Energy setType (EnergyType aType){	
		thisType = aType;
		return this;
	}

}
