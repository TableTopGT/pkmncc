package org.tabletop.pokemon.pokedex;

import org.tabletop.pokemon.Player;
import org.tabletop.pokemon.Pokemon;
import org.tabletop.pokemon.Pokemon.PokemonType;

public class Charizard extends Pokemon {

	public Charizard() {
		super();
		type = PokemonType.FIRE;
		basic = false;
		evolvable = false;
		evolution = null;
	}
	
	public Charizard(Player target) {
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
