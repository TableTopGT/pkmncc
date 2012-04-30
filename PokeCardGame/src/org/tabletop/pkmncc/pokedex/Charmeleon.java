package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Charmeleon extends Pokemon {
	
	public Charmeleon() {
		
		setPokedexNumber(5);
		setElement(Element.FIRE);
		setEvolution(PokemonStage.STAGE1, Charizard.class);
		setDefense(80, 1, Element.WATER, 20, null, 0);
		action1 = new ActionDesc("Slam", 30, Element.COLORLESS, Element.COLORLESS);
		action2 = new ActionDesc("Fire Punch", 50, Element.COLORLESS, Element.COLORLESS, Element.FIRE);
	}

	@Override
	public void actionOne(Player target) {
		int multiplier = getOwner().coinFlip() ? 1 : 1;
		multiplier += getOwner().coinFlip() ? 1 : 0;
		action1.attack(target, 30*multiplier);
	}
	
}
