package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Charmander extends Pokemon {

	public Charmander(Player owner) {
		super(owner);
		setElement(Element.FIRE);
		setEvolution(false, true, "Charmeleon");
		setDefense(60, 1, Element.WATER, 0, null, 0);
		action1 = new ActionDesc("Scratch", 10, Element.COLORLESS);
		action2 = new ActionDesc("Ember", 30, Element.COLORLESS, Element.FIRE);
	}
	
	public void actionTwo(Player target) {
		removeEnergy(); //XXX Player should be able to choose which Energy Card
		action2.attack(target);
	}
	
}
