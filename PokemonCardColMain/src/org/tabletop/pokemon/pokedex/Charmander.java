package org.tabletop.pokemon.pokedex;

import org.tabletop.pokemon.Player;
import org.tabletop.pokemon.Pokemon;
import org.tabletop.pokemon.Energy.*;

public class Charmander extends Pokemon {

	public Charmander() {
		super();
		type = PokemonType.FIRE;
		basic = true;
		evolvable = true;
		evolution = "Charmeleon";
		action1 = new ActionDesc("Scratch", 10);
		action2 = new ActionDesc("Ember", 30);
	}
	
	public Charmander(Player target) {
		this();
		owner = target;
	}

	public void actionOne(Player target) {
		if (energy.contains(EnergyType.COLORLESS))
			target.health -= action1.baseAttack;
	}
	
	public void actionTwo(Player target) {
		if (energy.contains(EnergyType.COLORLESS) && energy.contains(EnergyType.FIRE))
			target.health -= action2.baseAttack;
	}
	
}
