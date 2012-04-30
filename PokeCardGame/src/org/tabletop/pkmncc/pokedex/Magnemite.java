package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.card.Pokemon;

public class Magnemite extends Pokemon {

	public Magnemite() {
		setPokedexNumber(81);
		setElement(Element.METAL);
		setEvolution(PokemonStage.BASIC, Magneton.class);
		setDefense(40, 1, Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Rollout", 10, Element.COLORLESS);
		action2 = new ActionDesc("Hook", 20, Element.COLORLESS, Element.COLORLESS);
	}
	
}
