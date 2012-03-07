package org.tabletop.pokemon;

public abstract class Pokemon extends Card {
	
	public static enum PokemonType {NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, 
		BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK};
	
	protected Player owner;
	protected PokemonType type;
	protected String evolution;
	protected boolean evolvable;
	protected Energy[] energy;
	
	
	// Constructors
	public Pokemon() {
		
	}
	
	public Pokemon(Player thisPlayer) {
		owner = thisPlayer;
	}
	
	// Virtual methods
	public abstract void actionOne(Player target);
	
	public abstract void actionTwo(Player target);
	
	// Shared methods
	public void addEnergy(Energy energyCard) {
		//energy.append(energyCard);
	}
}
