/*
 * Pokemon.java
 */


package org.tabletop.pkmncc;

import java.util.ArrayList;

public abstract class Pokemon extends Card {
	
	/** 
	 * Contains all PokemonTCG Types. Type NONE isn't standard 
	 * and should be used when to handle exceptional cases.
	 */		
	public static enum PokemonType {NONE, GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, COLORLESS};
	
	/** 
	 * Contains all PokemonTCG Statuses. Type HEALTHY isn't officially 
	 * standard but is implied.
	 */		
	public static enum PokemonStatus {HEALTHY, ASLEEP, CONFUSED, PARALYZED, BURNED, POISONED};
	
	public static enum PokemonStage {BASIC, STAGE1, STAGE2};
	
	
	/* Constant Pokemon characteristics */
	protected class ActionDesc {
		public String actionName;
		public int baseAttack;
		public ArrayList<Energy> energyCost;
		public ActionDesc(String actionName, int baseAttack, Energy.EnergyType... energyCost) {
			this.actionName = actionName;
			this.baseAttack = baseAttack;
			this.energyCost = Energy.listFromArray(energyCost);
		}
	}
	
	
	protected class DefenseDesc { //XXX Make static, split?	
		public PokemonType weakness;
		public int multAdder;

		public PokemonType resistance;
		public int subtracter;
		
		/* Give default values for weakness and resistance */
		public DefenseDesc(PokemonType weakness, int multAdder, PokemonType resistance, int subtracter) {
			this.weakness = weakness;
			this.multAdder = (multAdder > 2) ? multAdder : 2;
			this.resistance = resistance; 
			this.subtracter = (subtracter >= 10) ? subtracter : 30;
		}
		
		public boolean isWeakness(PokemonType type) {
			return weakness == type;
		}
		
		public boolean isResistance(PokemonType type) {
			return resistance == type;
		}
	}
	
	
	protected ActionDesc action1, action2;
	protected DefenseDesc defense;
	protected PokemonType type;
	protected int retreatCost;
	
	/* These fields help determine if a Pokemon can be played */
	protected boolean evolved;
	protected boolean evolvable;
	protected String evolution;
	
	
	/* Dynamic Pokemon characteristics */
	protected Player owner;
	protected int HP;
	protected ArrayList<Energy> energy;
	
	/* status[3] holds three fields,
	 * [HEALTHY/ASLEEP/CONFUSED/PARALYZED, HEALTHY/BURN, HEALTHY/POISON]
	 */
	protected PokemonStatus[] status;
	protected PokemonStatus oldstatus;

	
	/* Constructors */
	public Pokemon() {
		this.energy = new ArrayList<Energy>();
		this.status = new PokemonStatus[3];
		this.healAllStatus();
	}
	
	public Pokemon(Player owner) {
		this();
		this.owner = owner;
	}
	
	
	/* Overridable attack/ability methods */
	public void actionOne(Player target) {
		attack(target, action1);
	}
	
	public void actionTwo(Player target) {
		attack(target, action2);
	}
	
	
	/* Health-centered methods */
	public int getHP() {
		return HP;
	}
	
	public int addHP(int hitPoints) {
		return (HP += hitPoints);
	}
	
	public int removeHP(int hitPoints) {
		return (HP -= hitPoints);
	}
	
	
	/* Battle centered methods */
	private boolean canMove() {
		return (status[0] != PokemonStatus.ASLEEP)
				&& (status[0] != PokemonStatus.PARALYZED);
	}
	
	public boolean canRetreat() {
		return canMove() && (energy.size() >= retreatCost);
	}

	/**
	 * Attempt an attack on the opponent's active Pokemon.
	 * @param opponent
	 * @param action
	 * @return the damage done by the attack
	 */
	public int attack(Player opponent, ActionDesc action) {
		if (!energy.containsAll(action.energyCost)) {
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
			
		Pokemon enemy = opponent.pokeArr[0];
		int damage = action.baseAttack;
		if (enemy.defense.isWeakness(type)) {
			damage = (defense.multAdder > 10) 
					? damage + enemy.defense.multAdder
					: damage * enemy.defense.multAdder;
		} else if (enemy.defense.isResistance(type)) {
			damage -= enemy.defense.subtracter;
		} 
		return enemy.removeHP(damage);
	}
	
	
	/* Energy-centered methods */
	/** 
	 * Useful for displaying energies after making a Pokemon active.
	 * @return a list of energies being held
	 */
	public ArrayList<Energy> getEnergy() {
		return energy;
	}
	
	public void addEnergy(Energy energyCard) {
		energy.add(energyCard);
	}
	
	//XXX Prototype-only function
	public void removeEnergy() {
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
	
	public void healStatus(PokemonStatus stat) {
		
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

	public void healAllStatus() {
		status[2] = PokemonStatus.HEALTHY;
		status[1] = PokemonStatus.HEALTHY;
		status[0] = PokemonStatus.HEALTHY;
		oldstatus = PokemonStatus.HEALTHY;
	}
	
	/** 
	 * Run this before the beginning of a user's turn. All statuses except
	 * confusion are handled here. Confusion is handled before attacks.
	 */
	public void statusEffect() {
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
				if (oldstatus == PokemonStatus.PARALYZED)
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
	
}
