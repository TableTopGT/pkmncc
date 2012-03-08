package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmcc.Player;
import org.tabletop.pkmcc.Pokemon;
import org.tabletop.pkmcc.Pokemon.PokemonType;

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
