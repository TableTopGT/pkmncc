package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machoke extends Pokemon {

	public Machoke() {
		
		setPokedexNumber(67);
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.STAGE1, Machamp.class);
		setDefense(80, 1, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Low Kick", 30, Element.FIGHTING, Element.COLORLESS);

	}
	
}