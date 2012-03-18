package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;

public class Charmander extends Pokemon {

	public Charmander() {
		super();
		HP = 60;
		retreatCost = 1;
		element = Element.FIRE;
		setEvolution(false, true, "Charmeleon");
		setDefense(Element.WATER, 0, Element.NONE, 0);
		action1 = new ActionDesc("Scratch", 10, Element.COLORLESS);
		action2 = new ActionDesc("Ember", 30, Element.COLORLESS, Element.FIRE);
	}
	
	public Charmander(Player owner) {
		this();
		this.owner = owner;
	}
	
	public void actionTwo(Player target) {
		removeEnergy(); //XXX Player should be able to choose which Energy Card
		action2.attack(target);
	}
	
}
