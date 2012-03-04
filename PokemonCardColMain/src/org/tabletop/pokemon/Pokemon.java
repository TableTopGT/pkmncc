package org.tabletop.pokemon;
import org.tabletop.pokemon.Player;

public abstract class Pokemon extends Card {
	
	// Pokemon Player Pointer
	public Player owner;
	public String element;
	public String evolution;
	public boolean evolvable;
	public Energy[] energy;
	
	// Default constructor
	public Pokemon() {
	}
	
	// Player constructor
	public Pokemon(Player thisPlayer){
		owner = thisPlayer;
	}
	
	// Pokemon virtual member methods
	public abstract void ActionOne(Player target);
	public abstract void ActionTwo(Player target);
	
	public void addEnergy(Energy addThisEnergy){
	}
}
