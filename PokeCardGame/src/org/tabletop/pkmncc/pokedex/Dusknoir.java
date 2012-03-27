package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Dusknoir extends Pokemon {

	public Dusknoir() {
		
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.STAGE2,"");
		setDefense(120, 2, Element.DARKNESS, 30, Element.COLORLESS, 20);
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
