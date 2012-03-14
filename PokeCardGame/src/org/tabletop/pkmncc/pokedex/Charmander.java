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
		evolved = false;
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
	
	public void actionTwo(Player target) {
		removeEnergy(); //XXX Player should be able to choose which Energy Card
		attack(target, action2);
	}
	
}
