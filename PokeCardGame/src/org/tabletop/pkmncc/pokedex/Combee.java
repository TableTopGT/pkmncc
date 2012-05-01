package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Combee extends Pokemon {

	public Combee() {
		setPokedexNumber(415);
		setElement(Element.GRASS);
		setEvolution(PokemonStage.BASIC, Vespiquen.class);
		setDefense(50, 1, Element.FIRE, 10, Element.FIGHTING, 20);
		action1 = new ActionDesc("Nap", 0, Element.GRASS);
		action2 = new ActionDesc("Zzzt", 20, Element.COLORLESS,
				Element.COLORLESS);
	}

	@Override
	/** Nap: Removes two damage counters **/
	public void actionOne(Player target) {
		addHP(20);
	}
}
