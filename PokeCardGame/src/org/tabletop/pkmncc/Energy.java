/* 
 * Energy.java
 */


package org.tabletop.pkmncc;

import java.util.ArrayList;

public class Energy extends Card {

	private Element type;
	
	public Energy(Element type) {
		this.type = type;
	}
	
	// Needed for display
	public Element getType() {
		return type;
	}

	// Some attack effects temporarily change attached energy types
	public void setType(Element type) {	
		this.type = type;
	}
	
	public static ArrayList<Energy> listFromArray(Energy.Element... input) {
		ArrayList<Energy> newList = new ArrayList<Energy>();
		for (Element E : input) {
			newList.add(new Energy(E));
		}
		return newList;
	}
}
