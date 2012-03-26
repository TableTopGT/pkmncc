package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Pikachu extends Pokemon {

	public Pikachu(Player target) {
		super (target);
		HP = 70;
		retreatCost = 2;
		setElement(Element.LIGHTNING);
		setEvolution(true, true, "Raichu");
		setDefense(Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Pika Punch", 20, Element.COLORLESS, Element.COLORLESS);
		action2 = new ActionDesc("Speed Bolt", 40, Element.FIGHTING, Element.COLORLESS, Element.COLORLESS);
	}
		
	
		
	/* action2 - If Pikachu evolved from Pichu during this turn, 
	   prevent all effects of an attack, including damage, 
	   done to Pikachu during your opponent's next turn.
	   
	   public void actionTwo(Player target) {
		}
	*/
		
}


