package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Dusclops extends Pokemon {

	public Dusclops() {
		setPokedexNumber(356);
		setElement(Element.PSYCHIC);
		setEvolution(PokemonStage.STAGE1, Dusknoir.class);
		setDefense(100, 2, Element.DARKNESS, 10, Element.COLORLESS, 10);
		//removes 2 damage counters for every energy Dusclops holds
		action1 = new ActionDesc("Shadow Beam", 20*getEnergySize(), Element.PSYCHIC);
	
		
	}



}
