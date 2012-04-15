package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Magnemite extends Pokemon {

	public Magnemite() {
		setElement(Element.METAL);
		setEvolution(PokemonStage.BASIC, Magneton.class);
		setDefense(50, 1, Element.FIRE, 10, Element.PSYCHIC, 20);
		action1 = new ActionDesc("Magnetic Bomb", 30, Element.LIGHTNING, Element.COLORLESS);
		
	}
	
	public void actionOne (Player target){
		boolean heads = getOwner().coinFlip();
		
		if (heads){
			action2.attack(target, 40);
		}
		else if (! heads) {
			
			getOwner().health += getOwner().health - 10;
			
		}
		/*Flip a coin. If heads, this attack does 30 damage plus 10 more damage. 
		 * If tails, Magnemite does 10 damage to itself.
		 */
	}

}
