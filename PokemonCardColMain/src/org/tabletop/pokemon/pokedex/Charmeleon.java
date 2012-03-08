package org.tabletop.pokemon.pokedex;

import org.tabletop.pokemon.Player;
import org.tabletop.pokemon.Pokemon;
import org.tabletop.pokemon.Pokemon.PokemonType;


public class Charmeleon extends Pokemon {

	public Charmeleon() {
		super();
		type = PokemonType.FIRE;
		basic = false;
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
