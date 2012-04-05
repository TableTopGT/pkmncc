package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Duskull extends Pokemon {

	public Duskull() {
		
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.BASIC, Dusclops.class);
		setDefense(50, 1, Element.DARKNESS, 10, Element.COLORLESS, 20);
		action1 = new ActionDesc("Tackle", 10, Element.COLORLESS);
		action2 = new ActionDesc("Surprise Attack", 30, Element.PSYCHIC, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionTwo (Player target) {
		
		 if (getOwner().coinFlip()){
		    action1.attack(target, 30);
		   }
		 
	}
}
