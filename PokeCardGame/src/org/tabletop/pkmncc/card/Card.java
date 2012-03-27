package org.tabletop.pkmncc.card;

import org.tabletop.pkmncc.Player;
import static org.tabletop.pkmncc.Battle.currentPlayer;

public abstract class Card {

	/** Used by Pokemon and Energy Cards */
	public static enum Element {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};

	private Element element;
	private Player owner;
	private String image;

	protected Card() {
		this.owner = currentPlayer;
	}
	
	public final Player getOwner() {
		return owner;
	} 
	
	public final void setOwner(Player owner) {
		this.owner = owner;
	}

	public final Element getElement() {
		return element;
	}
	
	public final void setElement(Element element) {	
		this.element = element;
		this.setImage(toString());
	}

	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = "images/" + image + ".png";
	}
	
	//TODO public void draw();
}