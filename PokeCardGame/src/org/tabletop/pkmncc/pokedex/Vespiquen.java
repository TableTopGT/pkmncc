package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Vespiquen extends Pokemon {

	public Vespiquen() {
		setElement(Element.GRASS);
		setEvolution(PokemonStage.STAGE1, "");
		setDefense(100, 1, Element.FIRE, 20, Element.FIGHTING, 20);
		action1 = new ActionDesc("Bee Drain", 20, Element.GRASS);
		action2 = new ActionDesc("Bee Powder", 50, Element.GRASS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionOne (Player target){
		/* After your attack, remove from Vespiquen the number of 
		 * damage counters equal to the damage you did to the Defending Pokémon.
		 */
	}

	public void actionTwo (Player target){
		/* Flip 2 coins. If both of them are heads, the Defending Pokémon 
		 * is now Burned, Paralyzed, and Poisoned.
		 */
	}
}
