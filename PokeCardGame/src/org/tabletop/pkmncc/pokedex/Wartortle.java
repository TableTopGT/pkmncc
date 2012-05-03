package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Wartortle extends Pokemon {

	public Wartortle() {
		setPokedexNumber(8);
		setElement(Element.WATER);
		setEvolution(PokemonStage.STAGE1, Blastoise.class);
		setDefense(70, 1, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Bubble", 20, Element.WATER, Element.COLORLESS);
		action2 = new ActionDesc("Tackle", 50, Element.COLORLESS,
				Element.COLORLESS, Element.WATER);
	}

	@Override
	/** Pokemon is paralyzed if coin flip -- attack still happens**/
	public boolean actionOne(Player target) {
		boolean able = super.actionOne(target);
		if (able)
			if (getOwner().coinFlip()) {
				target.getActive().addStatus(PokemonStatus.PARALYZED);
			}
		return able;
	}

}
