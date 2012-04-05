/* 
 * Energy.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;


public class Energy extends Card {

	public Energy(Element element) {
		setElement(element);
		setImage(toString());
	}
	
	/** Returns the element of the Energy in lowercase */
	@Override
	public String toString() {
		return getElement().toString().toLowerCase();
	}
	
	protected static ArrayList<Energy> listFromArray(Element... input) {
		ArrayList<Energy> newList = new ArrayList<Energy>();
		for (Element E : input)
			newList.add(new Energy(E));
		return newList;
	}
}
