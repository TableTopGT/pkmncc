package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Pichu extends Pokemon {

	public Pichu() {
		setPokedexNumber(172);
		setElement(Element.LIGHTNING);
		setEvolution(PokemonStage.BASIC, Pikachu.class);
		setDefense(40, 1, Element.FIGHTING, 10, Element.METAL, 20);
		action1 = new ActionDesc("Chupi", 20);
	}

	@Override
	/** Only attack if heads.  If tails, do nothing. **/
	public boolean actionOne(Player target) {
		if (getOwner().coinFlip())
			return super.actionOne(target);
		else
			return action1.attack(target,0);
	}

}
