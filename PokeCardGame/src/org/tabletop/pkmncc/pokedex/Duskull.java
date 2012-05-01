package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE

public class Duskull extends Pokemon {

	public Duskull() {
		setPokedexNumber(355);
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.BASIC, Dusclops.class);
		setDefense(50, 1, Element.DARKNESS, 10, Element.COLORLESS, 20);
		action1 = new ActionDesc("Tackle", 10, Element.COLORLESS);
		action2 = new ActionDesc("Surprise Attack", 30, Element.PSYCHIC,
				Element.COLORLESS);

	}

	@Override
	public void actionTwo(Player target) {

		if (getOwner().coinFlip()) {
			action2.attack(target, 30);
		}

	}
}
