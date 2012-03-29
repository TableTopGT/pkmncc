package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;


public class Machop extends Pokemon{

	public Machop () {
		
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.BASIC, "Machoke");
		setDefense(60, 1, Element.PSYCHIC, 10, null, 0);
		action1 = new ActionDesc("Kick", 10, Element.COLORLESS);
		action2 = new ActionDesc("Knock Back", 20, Element.FIGHTING, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionTwo (Player target){
		/* opponent switches the Defending Pok�mon with 1 of his or her Benched Pok�mon.
		 * -- Use DialogBox to get Pokemon Index
		 * -- Use switchActive to switch active with bench pokemon
		 */
	}
}
