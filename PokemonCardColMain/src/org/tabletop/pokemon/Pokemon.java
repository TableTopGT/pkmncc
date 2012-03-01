package org.tabletop.pokemon;
import org.tabletop.pokemon.Player;

public class Pokemon extends Card {
	
	public enum EnergyType {FIRE, WATER, FIGHTING, PSYCHIC, LIGHTING, GRASS};
	public EnergyType[] myEnergy;
	
	// Pokemon virtual member methods
	public void ActionOne(Player target){
		
	}
	public void ActionTwo(Player target){
		
	}
	// Pokemon Player Pointer
	public Player owner;
	protected String element;
	protected String evolvePokemon;
	protected boolean evolvable;
	public Energy[] energy;
	
	public void main(Player thisPlayer){
		owner = thisPlayer;
	}
	
	public void addEnergy(EnergyType addThisEnergy){
		
	}
}
