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

	public void actionOne (Player target){
	
		//new ArrayList<Pokemon.Energy> thisEnergy;
		
	}

	
}
