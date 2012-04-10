package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Stunky extends Pokemon {

	public Stunky() {
		
		setElement(Element.DARKNESS);
		setEvolution(PokemonStage.BASIC, Skuntank.class);
		setDefense(60, 1, Element.FIGHTING, 10, Element.PSYCHIC, 20);
		action1 = new ActionDesc("Gnaw and Run", 10, Element.DARKNESS);
		action2 = new ActionDesc("Double Scratch", 20, Element.COLORLESS, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionOne (Player target){
		/* switch Stunky with one of your bench pokemon
		 * -- Use DialogBox to get Pokemon Index
		 * -- Use switchActive to switch active with bench pokemon
		 */
	}
	
	@Override
	public void actionTwo (Player target){
		
		int multiplier = getOwner().coinFlip() ? 1 : 1;
		multiplier += getOwner().coinFlip() ? 1 : 0;
		action2.attack(target, 20*multiplier);
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
