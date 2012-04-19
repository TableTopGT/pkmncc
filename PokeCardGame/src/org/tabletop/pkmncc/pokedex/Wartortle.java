package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;


public class Wartortle extends Pokemon {
	
	public Wartortle() {
		
		setElement(Element.WATER);
		setEvolution(PokemonStage.STAGE1, Blastoise.class);
		setDefense(70, 1, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Bubble", 20, Element.WATER, Element.COLORLESS);
		action2 = new ActionDesc("Tackle", 50, Element.COLORLESS, Element.COLORLESS, Element.WATER);
	}

	
	@Override
	/** Pokemon is paralyzed if coin flip -- attack still happens**/
	public void actionOne(Player target){
		if(getOwner().coinFlip()){
			target.getActive().addStatus(PokemonStatus.PARALYZED);
		}
		action1.attack(target);		
	}
	
}
