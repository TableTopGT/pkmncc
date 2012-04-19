package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machop extends Pokemon{

	public Machop () {
		
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.BASIC, Machoke.class);
		setDefense(60, 2, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Low Kick", 20, Element.FIGHTING);

	}
	

}
