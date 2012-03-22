/*
 * Pokemon.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;

import org.tabletop.pkmncc.Player;

public abstract class Pokemon extends Card {
	
	/** HEALTHY isn't an official status, but is implied */
	public static enum PokemonStatus {HEALTHY, ASLEEP, CONFUSED, PARALYZED, BURNED, POISONED};
	
	protected static enum PokemonStage {BASIC, STAGE1, STAGE2};
	
	
	protected final class ActionDesc {
		public String actionName;
		private int baseAttack;
		private ArrayList<Energy> energyCost;
		
		public ActionDesc(String actionName, int baseAttack, 
				Element... energyCost) {
			this.actionName = actionName;
			this.baseAttack = baseAttack;
			this.energyCost = Energy.listFromArray(energyCost);
		}
		
		/**
		 * Attempt an attack on the opponent's active Pokemon.
		 * @param opponent
		 * @param action
		 * @return the damage done by the attack
		 */
		public int attack(Player opponent) { //TODO check if fainted
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
			int damage = baseAttack;
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
	
	
	// Battle attributes
	private Element weakness;
	private Element resistance;
	private int weakMod;
	private int resMod;
	protected ActionDesc action1;
	protected ActionDesc action2;
	protected int retreatCost;
		
	
	// These fields help determine if a Pokemon can be played
	private boolean evolved;
	private boolean evolvable;
	private String evolution;
	
	
	/* Dynamic Pokemon characteristics */
	protected int HP;
	private int damage = 0;
	private ArrayList<Energy> energy = new ArrayList<Energy>();
	
	/* status[3] holds three fields,
	 * [HEALTHY/ASLEEP/CONFUSED/PARALYZED, HEALTHY/BURN, HEALTHY/POISON]
	 */
	private PokemonStatus[] status = new PokemonStatus[3];
	private PokemonStatus oldstatus;

	
	/* Constructor */
	public Pokemon(Player owner) {
		super(CardType.POKEMON, owner);
		this.healAllStatus();
	}
	
	
	/* Overridable attack/ability methods */
	public void actionOne(Player target) {
		assert(action1 != null) : "Action 1 not set";
		action1.attack(target);
	}
	
	public void actionTwo(Player target) {
		assert(action2 != null) : "Action 2 not set";
		action2.attack(target);
	}
	
	public final String toString() {
		return getClass().getSimpleName();
	}
	
	/* Health-centered methods */
	public final int getHP() {
		return HP - damage;
	}
	
	public final int addHP(int hitPoints) {
		damage -= hitPoints;
		if (damage < 0) damage = 0;
		return HP - damage;
	}
	
	public final int removeHP(int hitPoints) {
		damage += hitPoints;
		if (damage > HP) damage = HP;
		return HP - damage;
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
	
	//XXX Prototype-only function
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
	
	public final void setStatus(PokemonStatus stat) {
		
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
	
	public final void healStatus(PokemonStatus stat) {
		
		/* Reserve proper locations */
		switch (stat) {
		case POISONED:
			status[2] = PokemonStatus.HEALTHY;
			break;
		case BURNED:
			status[1] = PokemonStatus.HEALTHY;
			break;
		default:
			status[0] = PokemonStatus.HEALTHY;
		}	
	}

	public final void healAllStatus() {
		status[2] = PokemonStatus.HEALTHY;
		status[1] = PokemonStatus.HEALTHY;
		status[0] = PokemonStatus.HEALTHY;
		oldstatus = PokemonStatus.HEALTHY;
	}
	
	/** 
	 * Run this before the beginning of a user's turn. All statuses except
	 * confusion are handled here. Confusion is handled before attacks.
	 */
	public final void statusEffect() {
		for (int i = 2; i < 0; i--) {
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
				if (true) //FIXME if flip is tails
					removeHP(20);
				break;
			case ASLEEP:
				
				/*Turn the Pokémon counterclockwise to show that it is Asleep.*/
				if (true) // if coinflip is heads
					status[0] = PokemonStatus.HEALTHY;
				break;
			case PARALYZED:
				
				/*Turn the Paralyzed Pokémon clockwise.
				If a Pokémon is Paralyzed, it cannot attack or retreat. Remove the Special
				Condition Paralyzed during the in-between turns phase if your Pokémon
				was Paralyzed since the beginning of your last turn.*/
				if (oldstatus.equals(PokemonStatus.PARALYZED))
					status[0] = PokemonStatus.HEALTHY;
				break;
			}
		}
		oldstatus = status[0];
	}	 
	
	@SuppressWarnings("unused")
	private boolean confusedEffect() {
		if (true) {			//FIXME if coinflip is heads
			return false;
		} else {				// if coinflip is tails
			removeHP(30);
			return true;
		}
	}
	
	/**
	 * Sets properties related to evolution capabilities.
	 * @param evolved - false if stage is basic
	 * @param evolveable
	 * @param evolution - name of evolution or null string
	 */
	protected final void setEvolution(boolean evolved, boolean evolveable, 
			String evolution) {
		this.evolved = evolved;
		this.evolvable = evolveable;
		this.evolution = evolution;
	}
	
	/** Enter 0 for default modifiers */
	protected final void setDefense(Element weakness, int weakMod, 
			Element resistance, int resMod) {
		this.weakness = weakness;
		this.weakMod = (weakMod > 2) ? weakMod : 2; // Default unlisted value
		this.resistance = resistance; 
		this.resMod = (resMod >= 10) ? resMod : 30; // Default unlisted value
	}
}
