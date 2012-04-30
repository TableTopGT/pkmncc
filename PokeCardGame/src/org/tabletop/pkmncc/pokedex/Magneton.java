package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Magneton extends Pokemon {

	public Magneton() {
		setPokedexNumber(82);
		setElement(Element.METAL);
		setEvolution(PokemonStage.STAGE1, Magnezone.class);
		setDefense(80, 1, Element.FIGHTING, 10, Element.METAL, 10);
		action1 = new ActionDesc ("Supersonic", 20, Element.LIGHTNING, Element.COLORLESS);
		action2 = new ActionDesc ("Speed Ball", 50, Element.LIGHTNING, Element.COLORLESS, Element.COLORLESS);
		
		
	}
	
	/** Flip coins. If heads, opponenet is confused**/
	@Override
	public void actionOne (Player target){
		if(getOwner().coinFlip()){
			target.getActive().addStatus(PokemonStatus.CONFUSED);
		}
		
		action1.attack(target);
	}

}
