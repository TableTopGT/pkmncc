package org.tabletop.pokemon;
import org.tabletop.pokemon.Pokemon;

public class Charizard extends Pokemon {
	public void ActionOne(Player target) {
		target.health = target.health - 5;
	}
	public void ActionTwo(Player target) {
		
	}
	public Charizard() {
		element = "red";
		evolvable = false;
		evolution = null;
	}
	public Charizard(Player target) {
		this();
		owner = target;
	}
}
