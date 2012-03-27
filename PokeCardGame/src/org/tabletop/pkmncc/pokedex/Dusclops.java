package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Dusclops extends Pokemon {

	public Dusclops() {
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.STAGE1, "Dusknoir");
		setDefense(80, 1, Element.DARKNESS, 20, Element.COLORLESS, 20);
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
		int multiplier = getOwner().coinFlip() ? 1 : 1;
		multiplier += getOwner().coinFlip() ? 1 : 0;
		action1.attack(target, 20*multiplier);
		/* call flipCoin
		 * if (i == "heads"){
		 * baseAttack = baseAttack + 20;
		 * }
		 */
	}
}