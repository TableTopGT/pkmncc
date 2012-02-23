package org.tabletop.pokemon;
import org.tabletop.pokemon.Pokemon;

public class Charizard extends Pokemon {
	//element = "red";
	public void ActionOne(Player target){
		target.health = target.health - 5;
	}
	public void ActionTwo(Player target){
		
	}
	public Charizard(){
		element = "red";
		evolvable = false;
		evolvePokemon = null;
	}
	public Charizard(Player target){
		owner = target;
		element = "red";
		evolvable = false;
		evolvePokemon = null;
	}
}
