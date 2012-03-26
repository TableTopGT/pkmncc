package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Raichu extends Pokemon{

	public Raichu(Player target) {
		super (target);
		HP = 90;
		retreatCost = 0;
		setElement(Element.LIGHTNING);
		setEvolution(true, false, "None");
		setDefense(Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Slice", 30, Element.NONE);
		action2 = new ActionDesc("Split Ball", 50, Element.COLORLESS, Element.COLORLESS, Element.COLORLESS);
		//action3 = new ActionDesc("Burst Ball", 100, Element.LIGHTNING, Element.LIGHTNING, Element.COLORLESS);
	}

	public void actionOne (Player target){
		// Raichu can't use Slice during your next turn.
	}
	
	public void actionTwo (Player target){
		// Move an Energy card attached to Raichu to 1 of your Benched Pokémon.
	}
	
	/*public void actionThree (Player target{
		removeEnergy(); // Discard 3 Energy attached to any of your Pokémon in any way you like.
		action2.attack(target);
	} */
}


