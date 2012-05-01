package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.card.Pokemon;

public class Pikachu extends Pokemon {

	public Pikachu() {
		setPokedexNumber(25);
		setElement(Element.LIGHTNING);
		setEvolution(PokemonStage.STAGE1, Raichu.class);
		setDefense(50, 1, Element.FIGHTING, 10, null, 0);
		action1 = new ActionDesc("Scratch", 10, Element.COLORLESS);
		action2 = new ActionDesc("Pika Bolt", 40, Element.LIGHTNING,
				Element.COLORLESS, Element.COLORLESS);
	}

}
