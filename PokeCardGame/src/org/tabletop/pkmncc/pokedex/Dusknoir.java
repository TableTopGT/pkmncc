package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE


public class Dusknoir extends Pokemon {

	public Dusknoir() {
		
		setPokedexNumber(477);
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.STAGE2);
		setDefense(120, 2, Element.DARKNESS, 30, Element.COLORLESS, 20);
		action1 = new ActionDesc("Darkness Mist", 60, Element.PSYCHIC, Element.COLORLESS, Element.COLORLESS);
		
	}
	
	@Override
	/** If defending pokemon has two or more damage counters, baseAttack = baseAttack + 20 **/
	public void actionOne (Player target){
		
		if ((target.getActive().getDamage())>=20){
		    action1.attack(target, 80);
		   }
	}
}
