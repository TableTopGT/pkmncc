package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Pichu extends Pokemon {

	public Pichu (Player target) {
		super (target);
		HP = 50;
		retreatCost = 1;
		setElement(Element.LIGHTNING);
		setEvolution(false, true, "Pikachu");
		setDefense(Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Electric Circuit", 0, Element.NONE);
		action2 = new ActionDesc("Baby Evolution", 0, Element.NONE); // Poke Power
		}
	
	public void actionOne (Player target){
		/* Use DialogBox
		 * -- Search your discard pile for up to 4 Lightning Energy cards
		 * -- Show them to your opponent
		 * addCard (from player class)
		 * -- Put them into your hand.
		 */
	}
	
	public void actionTwo (Player target){
		/*Once during your turn (before your attack), 
		 * you may put Pikachu from your hand onto Pichu (this counts as evolving Pichu) 
		 * -- addCard
		 * and remove all damage counters from Pichu.
		 */


	}
}
