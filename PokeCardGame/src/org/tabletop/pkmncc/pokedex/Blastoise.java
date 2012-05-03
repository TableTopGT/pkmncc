package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Blastoise extends Pokemon {

	public Blastoise() {
		setPokedexNumber(9);
		setElement(Element.WATER);
		setEvolution(PokemonStage.STAGE2);
		setDefense(150, 3, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Hyper Whirlpool", 80, Element.WATER,
				Element.WATER, Element.WATER, Element.COLORLESS);
	}

	@Override
	/** Flip until tails and remove energy from defending pokemon for every heads -- attack still happens**/
	public boolean actionOne(Player target) {
		boolean able = super.actionOne(target);
		if (able) {
			int i = 0;
			boolean coin = getOwner().coinFlip();
	
			while (coin == true) {
				i++;
				coin = getOwner().coinFlip();
			}
	
			for (int ii = 1; ii < i; ii++) {
				target.getActive().removeEnergy();
			}
		}
		return able;

	}

}
