/*
 * Pokemon.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;
import android.content.Context;
import android.media.MediaPlayer;
import org.tabletop.pkmncc.Player;


public abstract class Pokemon extends Card {
	
	public static enum PokemonStatus {ASLEEP, CONFUSED, PARALYZED, BURNED, POISONED};
	
	protected static enum PokemonStage {BASIC, STAGE1, STAGE2};
	
	protected final class ActionDesc {
		public String actionName;
		private int baseAttack;
		private ArrayList<Energy> energyCost;

		public ActionDesc(String actionName, int baseAttack, Element... energyCost) {
			this.actionName = actionName;
			this.baseAttack = baseAttack;
			this.energyCost = Energy.listFromArray(energyCost); //XXX how does this handle empty cost?
		}

		/**
		 * Attempt an attack on the opponent's active Pokemon.
		 * @param opponent
		 * @return the damage done by the attack
		 */
		public int attack(Player opponent) {
			return attack(opponent, baseAttack);
		}
		
		/**
		 * Attempt an attack with an adjusted attack strength.
		 * @param opponent
		 * @param tempAttack - modified attack strength
		 * @return the damage done by the attack
		 */
		public int attack(Player opponent, int tempAttack) {
			if (!energy.containsAll(energyCost)) {
				// Not enough energy!
				return 0;
			}
			
			if (!canMove()) {
				// Paralyzed/Asleep!
				return 0;
			}
					
			if (confusedEffect()) {
				// Hurt by it's confusion
				return 0;
			}
				
			Pokemon enemy = opponent.getActive();
			int damage = tempAttack;
			if (enemy.weakness == getElement()) {
				damage = (weakMod > 10) 
						? damage + enemy.weakMod
						: damage * enemy.weakMod;
			} else if (enemy.resistance == getElement()) {
				damage -= enemy.resMod;
			} 
			return enemy.removeHP(damage);
		}
	}
	
	
	// Static attributes
	private int HP;
	private Element weakness;
	private Element resistance;
	private int weakMod;
	private int resMod;
	private int retreatCost;
	protected ActionDesc action1;
	protected ActionDesc action2;
	private boolean evolved;
	private boolean evolvable;
	private String evolution;
	private MediaPlayer cry;
	
	// Dynamic attributes
	private int currentHP;
	private ArrayList<Energy> energy = new ArrayList<Energy>();
	// [ASLEEP/CONFUSED/PARALYZED, BURN, POISON]
	private PokemonStatus[] status = new PokemonStatus[3];
	private PokemonStatus oldStatus;

	protected Pokemon() { //XXX Make constructor that takes in context?
		setImage(toString());
	}
	
	public void play() { //TODO play the pokemon's actual sound, not bulbasaur
		Context c = getContext();
		cry = MediaPlayer.create(c, 
				c.getResources()
				.getIdentifier("bulbasaur", "raw", "org.tabletop.pkmncc"));
		cry.start();
	}
	
	// Overridable attack/ability methods
	public void actionOne(Player target) {
		assert(action1 != null) : "Action 1 not set";
		action1.attack(target);
	}
	
	public void actionTwo(Player target) {
		assert(action2 != null) : "Action 2 not set";
		action2.attack(target);
	}
	
	@Override // Charmander.toString() == "Charmander"
	public final String toString() {
		return getClass().getSimpleName();
	}
	
	/* Health-centered methods */
	public final int getHP() {
		return currentHP;
	}

	protected final int getDamage() {
		return HP - currentHP;
	}
	
	public final int addHP(int hitPoints) {
		currentHP += hitPoints;
		if (currentHP > HP) currentHP = HP;
		return getHP();
	}
	
	/** Pokemon will disappear from screen and cry on faint */
	public final int removeHP(int hitPoints) {
		currentHP -= hitPoints;
		if (currentHP <= 0) faint();
		return getHP();
	}
	
	private void faint() {
		cry.start();
		cry.release();
		cry = null;
	}
	
	public final boolean isFainted() {
		return getHP() <= 0;
	}
	
	
	/* Battle centered methods */
	private boolean canMove() {
		return (status[0] != PokemonStatus.ASLEEP)
				&& (status[0] != PokemonStatus.PARALYZED);
	}
	
	public final boolean canRetreat() {
		return canMove() && (energy.size() >= retreatCost);
	}
	
	public final boolean isBasic() {
		return !evolved;
	}

	public boolean isEvolutionOf(Pokemon pokemon) {
		return evolved && pokemon.evolvable 
				&& pokemon.evolution.equals(toString());
	}
	
	/* Energy-centered methods */
	/** 
	 * Useful for displaying energies after making a Pokemon active.
	 * @return a list of energies being held
	 */
	public final ArrayList<Energy> getEnergy() {
		return energy;
	}
	
	public final void addEnergy(Energy energyCard) {
		energy.add(energyCard);
	}
	
	//XXX Prototype-only function, dialogBox so player can chose which
	public final void removeEnergy() {
		energy.remove(1);
	}
	
	public final void removeEnergy(Energy energyCard) {
		energy.remove(energyCard);
	}
	
	public final void removeAllEnergy() {
		energy.clear();
	}
	
	
	/* Status-centered methods */
	public final PokemonStatus[] getStatus() {
		return status;
	}
	
	public final void addStatus(PokemonStatus stat) {
		
		/* Reserve proper locations */
		switch (stat) {
		case POISONED:
			status[2] = stat;
			break;
		case BURNED:
			status[1] = stat;
			break;
		default:
			status[0] = stat;
		}
	}
	
	public final void removeStatus(PokemonStatus stat) {
		
		/* Reserve proper locations */
		switch (stat) {
		case POISONED:
			status[2] = null;
			break;
		case BURNED:
			status[1] = null;
			break;
		default:
			status[0] = null;
		}	
	}

	public final void removeAllStatus() {
		status[2] = null;
		status[1] = null;
		status[0] = null;
		oldStatus = null;
	}
	
	/** 
	 * Run this before the beginning of a user's turn. All statuses except
	 * confusion are handled here. Confusion is handled before attacks.
	 */
	public final void statusEffect() {
		for (int i = 2; i < 0; i--) {
			if (status[i] == null) continue;
			switch(status[i]) {
			case POISONED:
				
				/*A Poisoned Pokémon takes damage in-between turns. When a Pokémon is
				Poisoned, put a Poison marker on it. Put a damage counter on each Poisoned
				Pokémon during each in-between turns step. */
				removeHP(10);
				break;
			case BURNED:
				
				/*If a Pokémon is Burned, it may take damage in-between turns. When a
				Pokémon is Burned, put a Burn marker on it. In-between turns, the owner
				of the Burned Pokémon flips a coin. If he or she flips tails, put 2 damage
				counters on the Burned Pokémon. */
				if (!getOwner().coinFlip()) // if Tails
					removeHP(20);
				break;
			case ASLEEP:
				
				/*Turn the Pokémon counterclockwise to show that it is Asleep.*/
				if (getOwner().coinFlip()) // if Heads
					status[0] = null;
				break;
			case PARALYZED:
				
				/*Turn the Paralyzed Pokémon clockwise.
				If a Pokémon is Paralyzed, it cannot attack or retreat. Remove the Special
				Condition Paralyzed during the in-between turns phase if your Pokémon
				was Paralyzed since the beginning of your last turn.*/
				if (oldStatus == PokemonStatus.PARALYZED)
					status[0] = null;
				break;
			}
		}
		oldStatus = status[0];
	}	 
	
	private boolean confusedEffect() {
		if (getOwner().coinFlip()) { 	// if Heads
			return false;
		} else {						// if Tails
			removeHP(30);
			return true;
		}
	}
	
	/**
	 * Sets properties related to evolution capabilities.
	 * @param stage - the Pokemon's stage
	 * @param evolution - name of next evolution or null string
	 */
	protected final void setEvolution(PokemonStage stage, String evolution) {
		this.evolved = PokemonStage.BASIC.equals(stage);
		this.evolvable = evolution != "";
		this.evolution = evolution;
	}
	
	/** Enter 0 for default modifiers, null if no weakness/resistance */
	protected final void setDefense(int HP, int retreatCost, 
			Element weakness, int weakMod, Element resistance, int resMod) {
		this.HP = HP;
		this.currentHP = HP;
		this.retreatCost = retreatCost;
		this.weakness = weakness;
		this.weakMod = (weakMod > 2) ? weakMod : 2; // Default unlisted value
		this.resistance = resistance; 
		this.resMod = (resMod >= 10) ? resMod : 30; // Default unlisted value
	}
}
