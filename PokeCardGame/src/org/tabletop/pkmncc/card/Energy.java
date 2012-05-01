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

	/** Energies having the same element are equal */
	@Override
	public boolean equals(Object o) {
		Energy e = (Energy) o;
		return getElement().equals(e.getElement());
	}

	@Override
	public int hashCode() {
		return getElement().hashCode();
	}

	protected static int createCost(ArrayList<Energy> energyCost,
			Element... input) {
		int costSize = 0;
		energyCost = new ArrayList<Energy>();
		for (Element E : input) {
			if (E.equals(Element.COLORLESS))
				++costSize;
			else
				energyCost.add(new Energy(E));
		}
		return costSize;
	}
}
