package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;

public class Charizard extends Pokemon {

	public Charizard() {
		super();
		element = Element.FIRE;
		evolved = true;
		evolvable = false;
		evolution = null;
	}
	
	public Charizard(Player target) {
		this();
		owner = target;
	}

	public void actionOne(Player target) {
	}
	
	public void actionTwo(Player target) {
	}
	
}
