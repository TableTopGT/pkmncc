package org.tabletop.pokemon;


public class Charmeleon extends Pokemon {

	public Charmeleon() {
		type = PokemonType.FIRE;
		evolvable = true;
		evolution = "Charizard";
	}
	
	public Charmeleon(Player target) {
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
