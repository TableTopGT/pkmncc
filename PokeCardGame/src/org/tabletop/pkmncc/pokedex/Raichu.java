package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Raichu extends Pokemon{

	public Raichu() {
		
		setElement(Element.LIGHTNING);
		setEvolution(PokemonStage.STAGE2);
		setDefense(90, 0, Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Slice", 30);
		action2 = new ActionDesc("Split Ball", 50, Element.COLORLESS, Element.COLORLESS, Element.COLORLESS);
		//action3 = new ActionDesc("Burst Ball", 100, Element.LIGHTNING, Element.LIGHTNING, Element.COLORLESS);
	}

	@Override
	public void actionOne (Player target){
		// Raichu can't use Slice during your next turn.
	}
	
	@Override
	public void actionTwo (Player target){
		// Move an Energy card attached to Raichu to 1 of your Benched Pokémon.
	}
	
	/*@Override
	 * public void actionThree (Player target{
		removeEnergy(); // Discard 3 Energy attached to any of your Pokémon in any way you like.
		action2.attack(target);
	} */
}


