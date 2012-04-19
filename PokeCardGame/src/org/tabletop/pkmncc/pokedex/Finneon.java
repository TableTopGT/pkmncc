package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE

public class Finneon extends Pokemon{

	public Finneon() {
		setElement(Element.WATER);
		setEvolution(PokemonStage.BASIC, Lumineon.class);
		setDefense(50, 1, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Aqua Linear", 0);
		action2 = new ActionDesc("Mouth Pump", 10, Element.WATER);
		
		
	}
	
	public void actionOne (Player target){
		
		/*Choose 1 of your opponent's Benched Pokémon. 
		 * This attack does 10 damage to that Pokémon. 
		 * (Don't apply Weakness and Resistance for Benched Pokémon.)
		 */
	}
	
	public void actionTwo (Player target){
		//Flip a coin. If heads, this attack does 10 damage plus 10 more damage.
		if (getOwner().coinFlip()){
			action2.attack(target, 20);
		}
		
	}
}
