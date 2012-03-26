package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Dusclops extends Pokemon {

	public Dusclops(Player target) {
		super (target);
		HP = 80;
		retreatCost = 1;
		setElement(Element.PSYCHIC);
		setEvolution(true, true, "Dusknoir");
		setDefense(Element.DARKNESS, 20, Element.COLORLESS, 20);
		action1 = new ActionDesc("Dark One-Eye", 20, Element.PSYCHIC);
		action2 = new ActionDesc("Ambush", 40, Element.PSYCHIC, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}

	public void actionOne (Player target){
		/* if (owner discards card from hand){
		 * opponent also discards a card from hand;
		 * }
		 */
	}
	
	public void actionTwo (Player target){
		/* call flipCoin
		 * if (i == "heads"){
		 * baseAttack = baseAttack + 20;
		 * }
		 */
	}
}
