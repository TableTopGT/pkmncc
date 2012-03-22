package org.tabletop.pkmncc.card;

import org.tabletop.pkmncc.Player;

public abstract class Card {

	public static enum CardType {POKEMON, ENERGY, TRAINER};

	/** Used by Pokemon and Energy Cards */
	public static enum Element {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};

	private CardType cardType;
	private Element element;
	private Player owner;

	protected Card(CardType cardType, Player owner, Element element) {
		this.cardType = cardType;
		this.owner = owner;
		this.element = element;
	}

	protected Card(CardType cardType, Player owner) {
		this(cardType, owner, null);
	}
	
	protected Card(CardType cardType, Element element) {
		this(cardType, null, element);
	}
	
	public final CardType getCardType() {
		return cardType;
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
	
	//TODO draw()
}
