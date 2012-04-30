package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Raichu extends Pokemon{

	public Raichu() {
		
		setPokedexNumber(26);
		setElement(Element.LIGHTNING);
		setEvolution(PokemonStage.STAGE2);
		setDefense(80, 1, Element.FIGHTING, 10, null, 0);
		action1 = new ActionDesc("Thundershock", 20, Element.COLORLESS, Element.COLORLESS);
		action2 = new ActionDesc("Pika Bolt", 50, Element.LIGHTNING, Element.COLORLESS, Element.COLORLESS);
		
	}

	@Override
	/** Flip coin, If heads defending pokemon is paralyzed **/
	public void actionOne (Player target){
		if(getOwner().coinFlip()){
			target.getActive().addStatus(PokemonStatus.PARALYZED);
		}
		action1.attack(target);
	}


}


