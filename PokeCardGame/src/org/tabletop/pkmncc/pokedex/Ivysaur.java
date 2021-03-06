package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Ivysaur extends Pokemon {

	public Ivysaur() {
		setPokedexNumber(2);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.STAGE1, Venusaur.class);
		setDefense(80, 1, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Poison Seed", 0, Element.COLORLESS);
		action2 = new ActionDesc("Razor Leaf", 50, Element.GRASS,
				Element.GRASS, Element.COLORLESS);

	}

	@Override
	/** Poison Seed: Opponent is poisoned **/
	public boolean actionOne(Player target) {
		boolean able = super.actionOne(target);
		if (able)
			target.getActive().addStatus(PokemonStatus.POISONED);
		return able;
	}

}
