package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Bulbasaur extends Pokemon {
	
	public Bulbasaur () {
		
		setPokedexNumber(1);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.BASIC, Ivysaur.class);
		setDefense(50, 1, Element.FIRE, 10, null, 0);
		action1 = new ActionDesc("Sleep Poison", 0, Element.COLORLESS);
		action2 = new ActionDesc("Razor Leaf", 20, Element.COLORLESS, Element.COLORLESS);
	
	}

	@Override
	/** Sleep Poison: Flip coin. If heads, opponent is asleep and poisoned **/
	public void actionOne (Player target) {		
		if (getOwner().coinFlip()){
			target.getActive().addStatus(PokemonStatus.POISONED);
			target.getActive().addStatus(PokemonStatus.ASLEEP);
		}
	}
	
}
