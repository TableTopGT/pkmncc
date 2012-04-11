package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;


public class Lumineon extends Pokemon{

	public Lumineon() {
		setElement(Element.WATER);
		setEvolution(PokemonStage.STAGE1);
		setDefense(80, 0, Element.LIGHTNING, 20, null, 0);
		action1 = new ActionDesc("Quick Swim", 0);
		action1 = new ActionDesc("Elegant Swim", 30, Element.WATER);
		// TODO Auto-generated constructor stub
	}

	public void actionOne (Player target){
		/* Use DialogBox to choose 1 of your opponent's Pok�mon. 
		 * -- This attack does 20 damage to that Pok�mon. 
		 * -- This attack's damage isn't affected by Weakness or Resistance.
		 */
	}
	
	public void actionTwo (Player target){
		/* Flip a coin. If heads, prevent all effects of an attack, 
		 * including damage, done to Lumineon during your opponent�s next turn.
		 */
	}
}