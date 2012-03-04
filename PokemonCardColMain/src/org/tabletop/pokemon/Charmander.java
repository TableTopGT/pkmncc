package org.tabletop.pokemon;
import org.tabletop.pokemon.Pokemon;

public class Charmander extends Pokemon {
	public void ActionOne(Player target) {
		
	}
	public void ActionTwo(Player target) {
		
	}
	public Charmander() {
		element = "red";
		evolvable = true;
		evolution = "Charizard";
	}
	public Charmander(Player target) {
		this();
		owner = target;
	}
}
