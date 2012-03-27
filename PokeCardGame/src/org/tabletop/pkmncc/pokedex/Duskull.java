package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Duskull extends Pokemon {

	public Duskull(Player target) {
		
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.BASIC, "Dusclops");
		setDefense(50, 1, Element.DARKNESS, 10, Element.COLORLESS, 20);
		action1 = new ActionDesc("Tackle", 10, Element.COLORLESS);
		action2 = new ActionDesc("Surprise Attack", 30, Element.PSYCHIC, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}

	public void actionTwo (Player target) {
		/* Call flipCoin
		 * if (i == "tails") {
		 * do nothing
		 * }
		 */
	}
}
