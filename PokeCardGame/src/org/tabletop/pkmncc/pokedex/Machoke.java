package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machoke extends Pokemon {

	public Machoke() {
		
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.STAGE1, Machamp.class);
		setDefense(80, 2, Element.PSYCHIC, 20, null, 0);
		action1 = new ActionDesc("Steady Punch", 20, Element.FIGHTING); // How is 20 different from 20+
		action2 = new ActionDesc("Brick Break", 30, Element.FIGHTING, Element.COLORLESS);
	}
	
	@Override
	public void actionOne (Player target){
		
		if (getOwner().coinFlip()){
			action1.attack(target, 40);
		}
		else {
			action1.attack(target, 20);
		}
	
		/* (Flip a coin. If heads, this attack does 20 damage plus 20 more damage.)
		call flipCoin
		if (i == 'heads'){
		baseAttack = baseAttack + 20;
		*/
		}
	
	@Override
	public void actionTwo (Player target){
		
		
		/* This attack's damage isn't affected by Resistance, 
		 * Poké-Powers, Poké-Bodies, or any other effects on the Defending Pokémon.
		 */
	}

}
