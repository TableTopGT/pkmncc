package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Dusknoir extends Pokemon {

	public Dusknoir(Player target) {
		super (target);
		HP = 120;
		retreatCost = 2;
		setElement(Element.PSYCHIC);
		setEvolution(true, false, "None");
		setDefense(Element.DARKNESS, 30, Element.COLORLESS, 20);
		action1 = new ActionDesc("Darkness Mist", 60, Element.PSYCHIC, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionOne (Player target){
		/* if (Defending Pokemon has two or more damage counters){
		 * baseAttack = baseAttack + 20;
		 * }
		 */
	}
}
