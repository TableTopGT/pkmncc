package org.tabletop.pkmncc.pokedex;

import org.tabletop.pkmncc.Player;

public class Charmeleon extends Pokemon {

	public Charmeleon() {
		super();
		type = Element.FIRE;
		evolved = true;
		evolvable = true;
		evolution = "Charizard";
	}
	
	public Charmeleon(Player target) {
		this();
		owner = target;
	}

	public void actionOne(Player target) {
	}
	
	public void actionTwo(Player target) {
	}
	
}
