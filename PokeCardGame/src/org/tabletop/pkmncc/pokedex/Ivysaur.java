package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;
import org.tabletop.pkmncc.card.Pokemon.PokemonStatus;

public class Ivysaur extends Pokemon {
	
	public Ivysaur(){
		setPokedexNumber(2);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.STAGE1, Venusaur.class);
		setDefense(80, 1, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Poison Seed", 0, Element.COLORLESS);
		action2 = new ActionDesc("Razor Leaf", 50, Element.GRASS, Element.GRASS, Element.COLORLESS);
		
	}
	
	@Override
	/** Poison Seed: Opponent is poisoned **/
	public void actionOne (Player target) {		
		target.getActive().addStatus(PokemonStatus.POISONED);
	}
	

}
