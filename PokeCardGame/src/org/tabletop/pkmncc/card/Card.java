package org.tabletop.pkmncc.card;

import org.tabletop.pkmncc.Player;

public abstract class Card {

	/** Used by Pokemon and Energy Cards */
	public static enum Element {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};

	private Element element;
	private Player owner;
	private String image;

	protected Card(Player owner) {
		this.owner = owner;
	}
	
	protected Card(Element element) {
		this.element = element;
	}
	
	public final Player getOwner() {
		return owner;
	} 
	
	public final Element getElement() {
		return element;
	}
	
	public final void setElement(Element element) {	
		this.element = element;
	}

	public final String getImage() {
		return image;
	}

	protected final void setImage(String image) {
		this.image = "images/" + image + ".png";
	}
	
	//TODO draw()
}
