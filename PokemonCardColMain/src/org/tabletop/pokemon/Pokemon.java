package org.tabletop.pokemon;

import java.util.ArrayList;

public abstract class Pokemon extends Card {
	
	// General information about Pokemon
	// Note: NONE and HEALTHY aren't standard types/statuses
	public static enum PokemonType {NONE, NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, 
		BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK};
		
	public static enum PokemonStatus {HEALTHY, ASLEEP, BURNED, CONFUSED, PARALYZED, POISONED};
	

	
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
		public PokemonType weakRes;
		public int multDiv;
		public int addSub;
		public DefenseDesc(PokemonType weakRes, int multDiv, int addSub) {
			this.weakRes = weakRes;
			this.multDiv = multDiv;
			this.addSub = addSub;
		}
	}
	protected ActionDesc action1, action2;
	protected DefenseDesc defense;
	protected PokemonType type;
	protected int retreatCost;
	
	// Dynamic Pokemon characteristics
	protected Player owner;
	protected int HP;
	protected PokemonStatus status;
	protected ArrayList<Energy> energy;
	
	// These fields help determine if a Pokemon can be played
	protected boolean basic;
	protected boolean evolvable;
	protected String evolution;
	
	
	
	// Constructors
	public Pokemon() {
		energy = new ArrayList<Energy>();
		status = PokemonStatus.HEALTHY;
	}
	
	public Pokemon(Player thisPlayer) {
		this();
		owner = thisPlayer;
	}
	
	
	
	// Abstract attack/ability methods
	public abstract void actionOne(Player target);
	
	public abstract void actionTwo(Player target);
	
	// Health-centered methods
	public int getHP() {
		return HP;
	}
	
	public int addHP(int hitPoints) {
		return HP += hitPoints;
	}
	
	public int removeHP(int hitPoints) {
		return HP -= hitPoints;
	}
	
	// Used to take into account weaknesses and resistances for damage calc
	public int defend(int attack) {
		if (defense.weakRes == PokemonType.WATER)
			return removeHP(attack*defense.multDiv + defense.addSub);
		else
			return removeHP(attack);
	}
	
	
	// Energy-centered methods
	public void addEnergy(Energy energyCard) {
		energy.add(energyCard);
	}
	
	public void removeEnergy(Energy energyCard) {
		energy.remove(energyCard);
	}
	
	public boolean canRetreat() {
		return energy.size() >= retreatCost;
	}
	
	
	
	// Status-centered methods
	public PokemonStatus getStatus() {
		return status;
	}
	
	public void setStatus(PokemonStatus stat) {
		status = stat;
	}
	
	public void statusEffect() {
		switch(status) {
		}
	}	
	
}
