package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Charmeleon extends Pokemon {
	
	public Charmeleon(Player target) {
		super(target);
		HP = 60;
		retreatCost = 1;
		setElement(Element.FIRE);
		setEvolution(true, true, "Charizard");
		setDefense(Element.WATER, 0, Element.NONE, 0);	
		action1 = new ActionDesc("Scratch", 10, Element.COLORLESS);
		action2 = new ActionDesc("Ember", 30, Element.COLORLESS, Element.FIRE);
	}

	public void actionOne(Player target) {
	}
	
	public void actionTwo(Player target) {
	}
	
}
