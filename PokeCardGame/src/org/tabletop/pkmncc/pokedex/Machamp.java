package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Machamp extends Pokemon {

	public Machamp() {
		
		setElement(Element.FIGHTING);
		setEvolution(PokemonStage.STAGE2);
		setDefense(130, 2, Element.PSYCHIC, 30, null, 0);
		action1 = new ActionDesc("Take Out", 40, Element.FIGHTING);
		action2 = new ActionDesc("Hurricane Punch", 30, Element.COLORLESS, Element.COLORLESS);
		// action3 = new ActionDesc("Rage", 60, Element.FIGHTING, Element.FIGHTING, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionOne (Player target){
		
		if(isBasic()){
			
		}
		
		/* If the Defending Pokémon isn't an Evolved Pokémon, 
		 * that Pokémon is Knocked Out instead of damaged by this attack.
	   	*/
	}
	
	@Override
	public void actionTwo (Player target){
		
		/* heads = 0;
		 * for (count <= 4){
		 * call flipCoin
		 * if (i == 'heads'){
		 * heads = heads + 1;
		 * }
		 * }
		 * baseAttack = baseAttack * heads
		 */
	}
	
	/*public void actionThree (Player target){
		 // baseAttack = baseAttack + (damageCounters * 10);
	}
	*/
}
