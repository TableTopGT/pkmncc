package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.Pokemon;
import org.tabletop.pkmncc.Pokemon.PokemonType;

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
	}
	
	public void actionTwo(Player target) {
	}
	
}
