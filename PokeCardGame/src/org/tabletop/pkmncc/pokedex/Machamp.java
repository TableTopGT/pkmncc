package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machamp extends Pokemon {

	public Machamp() {
		setPokedexNumber(68);
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.STAGE2);
		setDefense(130, 2, Element.PSYCHIC, 30, null, 0);
		action1 = new ActionDesc("Take Out", 40, Element.FIGHTING);
		action2 = new ActionDesc("Hurricane Punch", 30, Element.COLORLESS,
				Element.COLORLESS);
	}

	@Override
	/** If the defending pokemon is not evolved, KO instead of damage **/
	public boolean actionOne(Player target) {
		if (target.getActive().isBasic())
			return action1.attack(target, target.getActive().getHP());
		else
			return super.actionOne(target);
	}

	@Override
	/** Flip a coin 4 times -- do 30 damage times number of heads flipped **/
	public boolean actionTwo(Player target) {
		int count = 0;
		
		for (int i = 0; i < 4; i++)
			if (getOwner().coinFlip())
				count++;

		return action2.attack(target, 30 * count);
	}

}
