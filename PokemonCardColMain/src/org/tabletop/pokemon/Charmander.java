package org.tabletop.pokemon;


public class Charmander extends Pokemon {

	public Charmander() {
		type = PokemonType.FIRE;
		evolvable = true;
		evolution = "Charmeleon";
	}
	
	public Charmander(Player target) {
		this();
		owner = target;
	}

	public void actionOne(Player target) {
		target.health = target.health - 5;
	}
	
	public void actionTwo(Player target) {
		target.health = target.health - 5;
	}
	
}
