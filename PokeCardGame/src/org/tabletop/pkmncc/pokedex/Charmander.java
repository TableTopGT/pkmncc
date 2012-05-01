package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.card.Pokemon;

public class Charmander extends Pokemon {

	public Charmander() {
		setPokedexNumber(4);
		setElement(Element.FIRE);
		setEvolution(PokemonStage.BASIC, Charmeleon.class);
		setDefense(50, 1, Element.WATER, 0, null, 0);
		action1 = new ActionDesc("Headbutt", 10, Element.COLORLESS);
		action2 = new ActionDesc("Slash", 20, Element.COLORLESS,
				Element.COLORLESS);
	}

}
