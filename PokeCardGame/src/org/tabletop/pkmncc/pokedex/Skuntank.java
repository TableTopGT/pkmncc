package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Skuntank extends Pokemon {

	public Skuntank(Player target) {
		super (target);
		HP = 100;
		retreatCost = 2;
		setElement(Element.DARKNESS);
		setEvolution(true, false, "None");
		setDefense(Element.FIGHTING, 20, Element.PSYCHIC, 20);
		action1 = new ActionDesc("Poison Claws", 20, Element.COLORLESS, Element.COLORLESS);
		action2 = new ActionDesc("Plunder", 60, Element.DARKNESS, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionOne (Player target) {
		// Defending Pokemon is poisoned
	}
	
	public void actionTwo (Player target) {
		/* Discard all trainer cards
		 * removeTrainer();
		 * do damage
		 */
	}

}
