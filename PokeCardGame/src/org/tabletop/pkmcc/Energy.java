package org.tabletop.pkmcc;

public class Energy extends Card {

	public static enum EnergyType {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};
	
	private EnergyType type;

	public EnergyType getType() {
		return type;
	}

	// setter returns this for chaining
	public Energy setType(EnergyType aType) {	
		type = aType;
		return this;
	}

}
