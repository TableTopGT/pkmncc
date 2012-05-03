package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Venusaur extends Pokemon {

	public Venusaur() {
		setPokedexNumber(3);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.STAGE2);
		setDefense(150, 3, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Pollen Hazard", 20, Element.COLORLESS,
				Element.COLORLESS, Element.GRASS);
		action2 = new ActionDesc("Solarbeam", 90, Element.GRASS, Element.GRASS,
				Element.GRASS, Element.COLORLESS, Element.COLORLESS);
	}

	@Override
	/** Pollen Hazard: Opponent is poisoned, burned, and confused **/
	public boolean actionOne(Player target) {
		boolean able = super.actionOne(target);
		if (able) {
			target.getActive().addStatus(PokemonStatus.POISONED);
			target.getActive().addStatus(PokemonStatus.BURNED);
			target.getActive().addStatus(PokemonStatus.CONFUSED);
		}
		return able;
	}

}
