/* 
 * Energy.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;


public class Energy extends Card {

	public Energy(Element element) {
		super(CardType.ENERGY, element);
	}
	
	protected static ArrayList<Energy> listFromArray(Element... input) {
		ArrayList<Energy> newList = new ArrayList<Energy>();
		for (Element E : input)
			newList.add(new Energy(E));
		return newList;
	}
}
