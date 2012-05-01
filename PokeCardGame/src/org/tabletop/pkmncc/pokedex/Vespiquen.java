package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE

public class Vespiquen extends Pokemon {

	public Vespiquen() {
		setPokedexNumber(416);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.STAGE1);
		setDefense(100, 1, Element.FIRE, 20, Element.FIGHTING, 20);
		action1 = new ActionDesc("Bee Drain", 20, Element.GRASS);
		action2 = new ActionDesc("Bee Powder", 50, Element.GRASS,
				Element.COLORLESS);
	}

	@Override
	/** Bee Drain: Removes HP from Vespiquen as well as opponent **/
	public void actionOne(Player target) {

		removeHP(20);
		action1.attack(target);
	}

	@Override
	/** Bee Powder: Flip 2 coins. If both heads, opponent is now burned, paralyze, and poisoned **/
	public void actionTwo(Player target) {
		if (getOwner().coinFlip() && getOwner().coinFlip()) {
			target.getActive().addStatus(PokemonStatus.POISONED);
			target.getActive().addStatus(PokemonStatus.BURNED);
			target.getActive().addStatus(PokemonStatus.PARALYZED);
			action2.attack(target);
		}
	}
}
