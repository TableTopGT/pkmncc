package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Magnezone extends Pokemon {

	public Magnezone() {
		setElement(Element.METAL);
		setEvolution(PokemonStage.STAGE2);
		setDefense(120, 2, Element.FIRE, 30, Element.PSYCHIC, 20);
		action1 = new ActionDesc ("Speed Shot", 0, Element.LIGHTNING, Element.COLORLESS);
		action2 = new ActionDesc ("Crush Volt", 80, Element.LIGHTNING, Element.COLORLESS, Element.COLORLESS);
		
	}

	public void actionOne (Player target){
		/*Choose 1 of your opponent's Pokémon. This attack does 30 damage 
		 * to that Pokémon. This attack's damage isn't affected by Weakness, 
		 * Resistance, Poké-Powers, Poké-Bodies, or any other effects on 
		 * that Pokémon.
		 */
	}
	
	public void actionTwo (Player target){
		
		removeEnergy();
		action2.attack(target);
		//Discard an Energy attached to Magnezone.
	}
	
}
