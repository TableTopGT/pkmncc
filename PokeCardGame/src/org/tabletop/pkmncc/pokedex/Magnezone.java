package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

//DO NOT USE

public class Magnezone extends Pokemon {

	public Magnezone() {
		setElement(Element.METAL);
		setEvolution(PokemonStage.STAGE2);
		setDefense(120, 4, Element.FIRE, 30, Element.PSYCHIC, 20);
		action1 = new ActionDesc ("Metal Blast", 50, Element.METAL, Element.COLORLESS, Element.COLORLESS);
				
	}

	@Override
	/**Metal Blast: Does 50 damage plus 10 more damage for each metal energy attached to Magnezone **/
	public void actionOne (Player target){
		int metalCount = 0;
		for (int i = 0; i<target.getActive().getEnergySize(); i++){

			if (target.getActive().getEnergy().get(i).getElement() == Element.METAL){
				metalCount++;
			}		
			
		}
		
		action1.attack(target, (50+metalCount*10) );
	}
	

	
}
