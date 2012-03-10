package org.tabletop.pkmncc.pokedex;

import java.util.ArrayList;

import org.tabletop.pkmncc.Energy;
import org.tabletop.pkmncc.Energy.EnergyType;
import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.Pokemon;
import org.tabletop.pkmncc.Energy.*;

public class Charmander extends Pokemon {

	public Charmander() {
		super();
		type = PokemonType.FIRE;
		basic = true;
		evolvable = true;
		evolution = "Charmeleon";
		action1 = new ActionDesc("Scratch", 10);
		action2 = new ActionDesc("Ember", 30);
		defense = new DefenseDesc(PokemonType.WATER, 0, PokemonType.NONE, 0);
	}
	
	public Charmander(Player target) {
		this();
		owner = target;
	}

	public void actionOne(Player target) {
		if (energy.contains(EnergyType.COLORLESS))
			attack(target, action1);
	}
	
	public void actionTwo(Player target) {
		if (energy.contains(EnergyType.COLORLESS) && energy.contains(EnergyType.FIRE))
			attack(target, action2);
	}
	
}
