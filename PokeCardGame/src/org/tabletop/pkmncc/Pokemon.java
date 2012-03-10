package org.tabletop.pkmncc;

import java.util.ArrayList;

public abstract class Pokemon extends Card {
	
	// General information about Pokemon		
	// Note: NONE and HEALTHY aren't standard types/statuses		
	public static enum PokemonType {NONE, GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};

	public static enum PokemonStatus {HEALTHY, ASLEEP, CONFUSED, PARALYZED, BURNED, POISONED};
	
	public static enum PokemonStage {BASIC, STAGE1, STAGE2};
	
	// Constant Pokemon characteristics
	protected class ActionDesc {
		public ArrayList<Energy> energyCost;
		public String actionName;
		public int baseAttack;
		public ActionDesc(String actionName, int baseAttack) {
			this.energyCost = null;
			this.actionName = actionName;
			this.baseAttack = baseAttack;
		}
	}
	protected class DefenseDesc {
		public PokemonType weakness;
		public int multAdder;

		public PokemonType resistance;
		public int subtracter;
		
		// Give default values for weakness and resistance
		public DefenseDesc(PokemonType weakness, int multAdder, PokemonType resistance, int subtracter) {
			this.weakness = weakness;
			this.multAdder = (multAdder > 0) ? multAdder : 2;
			this.resistance = resistance; 
			this.subtracter = (subtracter > 0) ? subtracter : 30;
		}

	}
	protected ActionDesc action1, action2; // Provide default attacks
	protected DefenseDesc defense;
	protected PokemonType type;
	protected int retreatCost;
	
	/* Dynamic Pokemon characteristics */
	protected Player owner;
	protected int HP;
	protected ArrayList<Energy> energy;
	// status[3] holds three fields 
	// [ HEALTHY/ASLEEP/CONFUSED/PARALYZED, HEALTHY/BURN, HEALTHY/POISON]
	protected PokemonStatus[] status;
	protected PokemonStatus oldstatus;
	
	// These fields help determine if a Pokemon can be played
	protected boolean basic;
	protected boolean evolvable;
	protected String evolution;
	
	
	
	// Constructors
	public Pokemon() {
		energy = new ArrayList<Energy>();
		status = new PokemonStatus[3];
		this.healAllStatus();
	}
	
	public Pokemon(Player thisPlayer) {
		this();
		owner = thisPlayer;
	}
	
	
	
	// Abstract attack/ability methods
	public abstract void actionOne(Player target);
	
	public abstract void actionTwo(Player target);
	
	
	
	
	
	/* Health-centered methods */
	public int getHP() {
		return HP;
	}
	
	public int addHP(int hitPoints) {
		return HP += hitPoints;
	}
	
	public int removeHP(int hitPoints) {
		return HP -= hitPoints;
	}
	
	
	
	
	
	/* Battle centered methods */
	
	// Determine if a pokemon can attack or retreat
	public boolean canMove() {
		return (status[0] != PokemonStatus.ASLEEP) && (status[0] != PokemonStatus.PARALYZED);
	}
	
	public boolean canRetreat() {
		return this.canMove() && (energy.size() >= retreatCost);
	}

	public int attack(Player opponent, ActionDesc action) {
		// if we can move and not get hurt by our confusion
		if (this.canMove() && !this.confusedEffect()) {
			Pokemon enemy = opponent.pokeArr[0];
			if (enemy.defense.weakness == this.type) {
				if (defense.multAdder > 10)
					return enemy.removeHP(action.baseAttack+defense.multAdder);
				else
					return enemy.removeHP(action.baseAttack*defense.multAdder);
			} 
			else if (enemy.defense.resistance == this.type)
				return enemy.removeHP(action.baseAttack - defense.subtracter);
			else
				return enemy.removeHP(action.baseAttack);
		} 
		else
			return 0;
	}
	
	
	
	
	
	
	/* Energy-centered methods */
	// Use when switching a pokemon from bench to active for Energy display
	public ArrayList<Energy> getEnergy() {
		return energy;
	}
	
	public void addEnergy(Energy energyCard) {
		energy.add(energyCard);
	}
	
	public void removeEnergy() { //Prototype only function
		energy.remove(1);
	}
	
	public void removeEnergy(Energy energyCard) {
		energy.remove(energyCard);
	}
	
	public void removeAllEnergy() {
		energy.clear();
	}
	
	
	
	/* Status-centered methods */
	
	public PokemonStatus[] getStatus() {
		return status;
	}
	
	public void setStatus(PokemonStatus stat) {
		// Reserve proper locations
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
	
	public void healStatus(PokemonStatus stat) {
		// Reserve proper locations
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
	
	public void healAllStatus() {
		status[2] = PokemonStatus.HEALTHY;
		status[1] = PokemonStatus.HEALTHY;
		status[0] = PokemonStatus.HEALTHY;
	}
	
	// all statuses except for confused are handled here
	// Run this before the beginning of a user's turn
	public void statusEffect() {
		for (int i = 2; i < 0; i--)
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
				if (true) // if flip is tails
					removeHP(20);
				break;
			case ASLEEP:
				/*Turn the Pokémon counterclockwise to show that it is Asleep.*/
				if (true) // if flip is heads
					status[0] = PokemonStatus.HEALTHY;
				break;
			case PARALYZED:
				/*Turn the Paralyzed Pokémon clockwise.
				If a Pokémon is Paralyzed, it cannot attack or retreat. Remove the Special
				Condition Paralyzed during the in-between turns phase if your Pokémon
				was Paralyzed since the beginning of your last turn.*/
				if (oldstatus == status[0])
					status[0] = PokemonStatus.HEALTHY;
				else 
					oldstatus = status[0];
				break;
			}
	}	 
	
	@SuppressWarnings("unused")
	public boolean confusedEffect() {
		if (true) 			// coinflip is heads
			return false;
		else {				// coinflip is tails
			removeHP(30);
			return true;
		}
	}
	
}
