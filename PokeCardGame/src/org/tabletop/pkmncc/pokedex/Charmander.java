package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Energy.EnergyType;
import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.Pokemon;

public class Charmander extends Pokemon {

	public Charmander() {
		super();
		HP = 60;
		retreatCost = 1;
		type = PokemonType.FIRE;
		basic = true;
		evolvable = true;
		evolution = "Charmeleon";
		action1 = new ActionDesc("Scratch", 10, EnergyType.COLORLESS);
		action2 = new ActionDesc("Ember", 30, EnergyType.COLORLESS, EnergyType.FIRE);
		defense = new DefenseDesc(PokemonType.WATER, 0, PokemonType.NONE, 0);
	}
	
	public Charmander(Player owner) {
		this();
		this.owner = owner;
	}

	public void actionOne(Player target) {
		attack(target, action1);
	}
	
	public void actionTwo(Player target) {
		attack(target, action2);
	}
	
}
