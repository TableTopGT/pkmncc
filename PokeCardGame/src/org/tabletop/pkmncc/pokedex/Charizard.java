package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Charizard extends Pokemon {
	
	public Charizard() {
		setPokedexNumber(6);
		setElement(Element.FIRE);
		setEvolution(PokemonStage.STAGE2);
		setDefense(120, 1, Element.WATER, 0, null, 0);
		action1 = new ActionDesc("Combustion", 30, Element.FIRE);
		action2 = new ActionDesc("Burning Inferno", 50, Element.FIRE, Element.FIRE,
												   Element.COLORLESS, Element.COLORLESS);
	}

	
	@Override
	/** Bursting Inferno: Each Defending Pokemon is now burned **/
	public void actionTwo(Player target) {
		for(int i=0; i< target.numPokemon(); i++){
			target.getPokemon(i).addStatus(PokemonStatus.BURNED);
		}
		action2.attack(target);
	}
	
}
