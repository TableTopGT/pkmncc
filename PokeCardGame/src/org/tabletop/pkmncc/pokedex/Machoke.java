package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machoke extends Pokemon {

	public Machoke(Player target) {
		super (target);
		HP = 80;
		retreatCost = 2;
		setElement(Element.FIGHTING);
		setEvolution(true, true, "Machamp");
		setDefense(Element.PSYCHIC, 20, Element.NONE, 0);
		action1 = new ActionDesc("Steady Punch", 20, Element.FIGHTING); // How is 20 different from 20+
		action2 = new ActionDesc("Brick Break", 30, Element.FIGHTING, Element.COLORLESS);
	}
	public void actionOne (Player target){
		/* (Flip a coin. If heads, this attack does 20 damage plus 20 more damage.)
		call flipCoin
		if (i == 'heads'){
		baseAttack = baseAttack + 20;
		*/	
		}
	
	public void actionTwo (Player target){
		/* This attack's damage isn't affected by Resistance, 
		 * Poké-Powers, Poké-Bodies, or any other effects on the Defending Pokémon.
		 */
	}

}
