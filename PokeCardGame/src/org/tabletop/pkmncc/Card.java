package org.tabletop.pkmncc;

public abstract class Card {

	public static enum CardType {POKEMON, ENERGY, TRAINER};

	/** 
	 * Contains all PokemonTCG Types. 
	 * 
	 * Used by Energy and Pokemon Cards. Type NONE isn't standard 
	 * and should be used when to handle exceptional cases.
	 */		
	public static enum Element {NONE, GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};
	
}
