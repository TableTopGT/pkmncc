package org.tabletop.pkmncc;

public class Energy extends Card {

	public static enum EnergyType {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};
	
	private EnergyType type;
	
	public Energy(EnergyType type) {
		this.type = type;
	}
	
	public EnergyType getType() {
		return type;
	}

	// setter returns this for chaining
	public Energy setType(EnergyType type) {	
		this.type = type;
		return this;
	}

}
