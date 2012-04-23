package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;


public class Squirtle extends Pokemon {
	
	public Squirtle (){
		setPokedexNumber(7);
		setElement(Element.WATER);
		setEvolution(PokemonStage.BASIC, Wartortle.class);
		setDefense(50, 1, Element.LIGHTNING, 10, null, 0);
		action1 = new ActionDesc("Tackle", 10, Element.COLORLESS);
		
	}

}
