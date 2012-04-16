package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Magneton extends Pokemon {

	public Magneton() {
		setElement(Element.METAL);
		setEvolution(PokemonStage.STAGE1, Magnezone.class);
		setDefense(80, 1, Element.FIRE, 20, Element.PSYCHIC, 20);
		action1 = new ActionDesc ("Magnetic Resonance", 20, Element.COLORLESS, Element.COLORLESS);
		action2 = new ActionDesc ("Magnetic Release", 40, Element.LIGHTNING, Element.COLORLESS, Element.COLORLESS);
		
		
	}
	
	public void actionOne (Player target){
		/*If you have a Stadium card in play, 
		 * this attack does 20 damage to 2 of your opponent's Benched Pokémon. 
		 * (Don't apply Weakness and Resistance for Benched Pokémon.)
		 */
	}
	
	public void actionTwo (Player target){
		
		int multiplier = target.getActive().getEnergy().size();
		action2.attack(target, 40 + (10*multiplier));
		
		/*Does 40 damage plus 10 more damage for each 
		 * Energy attached to the Defending Pokémon.
		 */
	}

}
