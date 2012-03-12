/* 
 * Energy.java
 */


package org.tabletop.pkmncc;

import java.util.ArrayList;

public class Energy extends Card {

	public static enum EnergyType {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};
	
	private EnergyType type;
	
	public Energy(EnergyType type) {
		this.type = type;
	}
	
	public EnergyType getType() {
		return type;
	}

	public void setType(EnergyType type) {	
		this.type = type;
	}
	
	public static ArrayList<Energy> listFromArray(Energy.EnergyType... input) {
		ArrayList<Energy> newList = new ArrayList<Energy>();
		for (EnergyType E : input) {
			newList.add(new Energy(E));
		}
		return newList;
	}
}
