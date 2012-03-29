package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;
import org.tabletop.pkmncc.card.Pokemon;

public class Combee extends Pokemon {

	public Combee() {
		
		setElement(Element.GRASS);
		setEvolution(PokemonStage.BASIC, "Vespiquen");
		setDefense(50, 1, Element.FIRE, 10, Element.FIGHTING, 20);
		action1 = new ActionDesc("Alert", 0, Element.COLORLESS);
		// TODO Auto-generated constructor stub
	}
	
	public void actionOne (Player tagrget) {
		/* Player draws a card
		 * Call DialogBox "Do you want to switch your active pokemon?"
		 * if yes
		 * call switchActive
		 */
	}
}
