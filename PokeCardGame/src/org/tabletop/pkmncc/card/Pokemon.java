/*
 * Pokemon.java
 */


package org.tabletop.pkmncc.card;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import org.tabletop.pkmncc.Game;
import org.tabletop.pkmncc.Player;


public abstract class Pokemon extends Card {
	
	public static enum PokemonStatus {ASLEEP, CONFUSED, PARALYZED, BURNED, POISONED};
	
	protected static enum PokemonStage {BASIC, STAGE1, STAGE2};
	
	protected final class ActionDesc {
		public final String actionName;
		private final int baseAttack;
		private ArrayList<Energy> energyCost;

		public ActionDesc(String actionName, int baseAttack, Element... energyCost) {
			this.actionName = actionName;
			this.baseAttack = baseAttack;
			this.energyCost = Energy.listFromArray(energyCost); 
			//FIXME how does this handle empty or colorless cost?
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
			if (!energy.containsAll(energyCost)) { //FIXME might compare references, need to do deepequals (override eauls & toHash inside enrgy class?
				new AlertDialog.Builder(getContext())
				.setMessage("Energies missing for this action.").show(); //TODO toast
				return 0;
			}

			if (!canMove()) {
				new AlertDialog.Builder(getContext())
				.setMessage("Pokemon can't move...").show();
				return 0;
			}

			if (confusedEffect()) {
				new AlertDialog.Builder(getContext())
				.setMessage("Pokemon was hurt by its confusion!").show(); 
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
			//XXX Implement prize cards here?
		}
	}
	
	
	private int HP;
	private int currentHP;
	private Element weakness;
	private Element resistance;
	private int weakMod;
	private int resMod;
	private int retreatCost;
	protected ActionDesc action1;
	protected ActionDesc action2;

	private int pokedexNumber;
	private boolean isEvolved;
	private boolean isEvolvable;
	private Class<? extends Pokemon> evolution;

	private MediaPlayer cry = MediaPlayer.create(getContext(), 
			getContext().getResources()
			.getIdentifier(toString(), "raw", "org.tabletop.pkmncc"));
	
	private ArrayList<Energy> energy = new ArrayList<Energy>();
	private PokemonStatus[] status = new PokemonStatus[3];
	private PokemonStatus oldStatus;
	
	protected final int imageid = getResources().getIdentifier("viewover", "drawable", "org.tabletop.pkmncc");
	private Bitmap bc = BitmapFactory.decodeResource(getResources(), imageid);

	protected Pokemon() {
		setImage(toString());
		setImageResource(imageid);
		cry.start();
	}
	
	/**
	 * Attack's a target with the defined attack1. Override
	 * to provide custom behavior.
	 * @param target
	 */
	public void actionOne(Player target) {
		assert(action1 != null) : "Action 1 not set";
		action1.attack(target);
	}
	
	/**
	 * Attack's a target with the defined attack2. Override
	 * to provide custom behavior.
	 * @param target
	 */
	public void actionTwo(Player target) {
		assert(action2 != null) : "Action 2 not set";
		action2.attack(target);
	}

	/** Returns the lowercase class name of the Pokemon */
	@Override
	public final String toString() {
		return getClass().getSimpleName().toLowerCase();
	}
	
	/* Health-centered methods */
	public final int getHP() {
		return currentHP;
	}

	public final int getDamage() { //TODO protected doesn't work w/ Dusknoir?
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
		return !isEvolved;
	}

	public boolean isEvolutionOf(Pokemon pokemon) { //XXX will fail if pokemon evolutions not changed
		return isEvolved && pokemon.isEvolvable
				&& pokemon.evolution.equals(getClass());
	}
	
	/**
	 * Transfers damage and energies to another pokemon. Use when performing
	 * an evolution.
	 */
	public final void transferStatsTo(Pokemon nextForm) {
		nextForm.removeHP(getDamage());
		nextForm.energy = energy;
		energy = null; // Dispose of local reference to the energy object
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
	
	public final void removeEnergy() {
		final CharSequence[] items = {"Red", "Green", "Blue"}; //FIXME use energy names
		boolean[] checkedItems = {true, false, false};
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				removeEnergy(energy.get(which));				
			}
		}).create();
		builder.show();
		energy.remove(1);
	}
	
	public final void removeEnergy(Energy energyCard) {
		energy.remove(energyCard);
	}

	/**
	 * Discards all of the energy of a Pokemon.
	 */
	public final void removeAllEnergy() {
		energy.clear();
	}
	
	
	/* Status-centered methods */
	/**
	 * @return array holding [ASLEEP/CONFUSED/PARALYZED, BURN, POISON] or nulls
	 */
	public final PokemonStatus[] getStatus() {
		return status;
	}
	
	/**
	 * Gives Pokemon a specified status ailment.
	 */
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

	/**
	 * Relieves Pokemon of specified status ailment.
	 */
	public final void removeStatus(PokemonStatus stat) {
		
		/* Reserve proper locations */
		switch (stat) { //TODO implement sortable in Status enums
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

	/**
	 * Relieves Pokemon of all status ailments.
	 */
	public final void removeAllStatus() {
		status[2] = null;
		status[1] = null;
		status[0] = null;
		oldStatus = null;
	}
	
	/** 
	 * Run this before the beginning of a user's turn. All statuses except
	 * confusion are handled here. Confusion is handled automatically before attacks.
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
	 * Sets the Pokemon's entry number in the Pokedex.
	 */
	protected void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	/**
	 * Sets properties related to evolution state and capabilities.
	 * @param stage - the Pokemon's stage
	 */
	protected void setEvolution(PokemonStage stage) {
		setEvolution(stage, (Class<? extends Pokemon>) null);
	}

	/**
	 * Sets properties related to evolution state and capabilities.
	 * @param stage - the Pokemon's stage
	 * @param evolution - class of next the evolution
	 */
	protected void setEvolution(PokemonStage stage, Class<? extends Pokemon> evolution) {
		this.isEvolved = !PokemonStage.BASIC.equals(stage);
		this.isEvolvable = evolution != null;
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
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		cry.start();
		return super.onTouchEvent(event);
	}
}
