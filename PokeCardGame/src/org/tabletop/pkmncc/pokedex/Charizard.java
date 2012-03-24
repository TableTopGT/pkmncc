package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Energy;
import org.tabletop.pkmncc.card.Pokemon;

public class Charizard extends Pokemon {
	
	public Charizard() {
		setElement(Element.FIRE);
		setEvolution(PokemonStage.STAGE2, "");
		setDefense(120, 3, Element.WATER, 0, Element.FIGHTING, 30);
		action1 = new ActionDesc("PP: Energy Burn", 0, (Element)null);
		action2 = new ActionDesc("Fire Spin", 100, Element.FIRE, Element.FIRE,
												   Element.FIRE, Element.FIRE);
	}

	public void actionOne(Player target) {
		if (getStatus()[0] == null)
			for (Energy E : getEnergy())
				E.setElement(Element.FIRE);
		// Can't undo this yet, might need an endPokePower()
	}
	
	public void actionTwo(Player target) {
		removeEnergy();
		removeEnergy();
		action2.attack(target);
	}
	
}
