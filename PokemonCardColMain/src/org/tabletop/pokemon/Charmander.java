package org.tabletop.pokemon;
import org.tabletop.pokemon.Pokemon;

public class Charmander extends Pokemon {
	public void ActionOne(Player target){
		
	}
	public void ActionTwo(Player target){
		
	}
	public Charmander(){
		element = "red";
		evolvable = true;
		evolvePokemon = "Charizard";
	}
	public Charmander(Player target){
		owner = target;
		element = "red";
		evolvable = true;
		evolvePokemon = "Charizard";
	}
}
