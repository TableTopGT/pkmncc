package org.tabletop.pkmncc.card;


import android.content.Context;
import android.widget.ImageView;

import org.tabletop.pkmncc.Player;
import static org.tabletop.pkmncc.Player.currentPlayer;


public abstract class Card extends ImageView {

	/** Used by Pokemon and Energy Cards */
	public static enum Element {GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};

	private Element element;
	private Player owner;
	private String image;
	private static Context context;

	protected Card() {
		super(context);
		owner = currentPlayer;
		setWillNotDraw(true);
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
	}

	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = "images/" + image + ".png";
	}

	public final static void setContext(Context context) {
		Card.context = context;
	}
}
