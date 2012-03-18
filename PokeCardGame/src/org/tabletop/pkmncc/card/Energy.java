/* 
 * Energy.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;


public class Energy extends Card {
	
	@SuppressWarnings("unused")
	private static final CardType cardType = CardType.ENERGY;
	
	public Energy(Element element) {
		this.element = element;
	}
	
	public static ArrayList<Energy> listFromArray(Energy.Element... input) {
		ArrayList<Energy> newList = new ArrayList<Energy>();
		for (Element E : input)
			newList.add(new Energy(E));
		return newList;
	}
}
