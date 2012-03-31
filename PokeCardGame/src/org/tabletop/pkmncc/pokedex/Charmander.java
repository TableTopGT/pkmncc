package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Charmander extends Pokemon {

	public Charmander() {
		setElement(Element.FIRE);
		setEvolution(PokemonStage.BASIC, Charmeleon.class);
		setDefense(60, 1, Element.WATER, 0, null, 0);
		action1 = new ActionDesc("Scratch", 10, Element.COLORLESS);
		action2 = new ActionDesc("Ember", 30, Element.COLORLESS, Element.FIRE);
	}

	@Override
	public void actionTwo(Player target) {
		removeEnergy();
		action2.attack(target);
	}
	
}
