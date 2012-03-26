package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;


public class Machop extends Pokemon{

	public Machop (Player target) {
		super (target);
		HP = 60;
		retreatCost = 1;
		setElement(Element.FIGHTING);
		setEvolution(false, true, "Machoke");
		setDefense(Element.PSYCHIC, 10, Element.NONE, 0);
		action1 = new ActionDesc("Kick", 10, Element.COLORLESS);
		action2 = new ActionDesc("Knock Back", 20, Element.FIGHTING, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionTwo (Player target){
		/* opponent switches the Defending Pokémon with 1 of his or her Benched Pokémon.
		 * -- Use DialogBox to get Pokemon Index
		 * -- Use switchActive to switch active with bench pokemon
		 */
	}
}
