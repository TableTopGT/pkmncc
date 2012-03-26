package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Stunky extends Pokemon {

	public Stunky(Player target) {
		super(target);
		HP = 60;
		retreatCost = 1;
		setElement(Element.DARKNESS);
		setEvolution(false, true, "Skuntank");
		setDefense(Element.FIGHTING, 10, Element.PSYCHIC, 20);
		action1 = new ActionDesc("Gnaw and Run", 10, Element.DARKNESS);
		action2 = new ActionDesc("Double Scratch", 20, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionOne (Player target){
		/* switch Stunky with one of your bench pokemon
		 * -- Use DialogBox to get Pokemon Index
		 * -- Use switchActive to switch active with bench pokemon
		 */
	}
	
	public void actionTwo (Player target){
		/* heads = 0;
		 * for (ii <= 2){
		 * call flipCoin
		 * if (i == "heads"){
		 * heads = heads + 1;
		 * }
		 * }
		 * baseAttack = baseAttack * heads
		 */
	}

}
