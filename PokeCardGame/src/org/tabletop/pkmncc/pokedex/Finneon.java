package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE

public class Finneon extends Pokemon {

	public Finneon() {
		setPokedexNumber(456);
		setElement(Element.WATER);
		setEvolution(PokemonStage.BASIC, Lumineon.class);
		setDefense(50, 1, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Aqua Linear", 0);
		action2 = new ActionDesc("Mouth Pump", 10, Element.WATER);

	}

	@Override
	public boolean actionOne(Player target) {

		/*
		 * Choose 1 of your opponent's Benched Pok�mon. This attack does 10
		 * damage to that Pok�mon. (Don't apply Weakness and Resistance for
		 * Benched Pok�mon.)
		 */
		return true;
	}

	@Override
	public boolean actionTwo(Player target) {
		// Flip a coin. If heads, this attack does 10 damage plus 10 more
		// damage.
		if (getOwner().coinFlip()) {
			return action2.attack(target, 20);
		}
		return super.actionTwo(target);
	}
}
