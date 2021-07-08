package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.dinosaurs.Dinosaur;

/**
 * Special Action for attacking other Actors.
 * @see edu.monash.fit2099.engine
 * @see game.Item.immovableitem.Corpse
 * @see game.dinosaurs.Dinosaur
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The amount of hp increase for Carnivore attacking another.
	 */
	protected int hp_increase;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor with HP increase
	 * @param target the victim of attack
	 * @param hp_increase Amount of HP increase of the attacker
	 */
	public AttackAction(Actor target, int hp_increase) {
		this.target = target;
		this.hp_increase = hp_increase;
	}

	/**
	 * Constructor without HP increase, used to deal with non-eating Attacks.
	 * @param target the victim of attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
		this.hp_increase = 0;
	}

	/**
	 * Performs this action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return the descriptive String of what happened.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Dinosaur victim = (Dinosaur) target;

		// If victim cannot survive from attack
		if (!victim.canSurviveAttack()){
			map.removeActor(victim);	// Remove actor directly without any corpse
			actor.heal(999);	// heals the actor to maximum
			return target + " is eaten by " + actor;
		}

		Weapon weapon = actor.getWeapon();

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			// When target dies, adds a corpse and remove the actor.
			map.locationOf(target).addItem(((Dinosaur)target).getNewCorpse());
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		// Heals the Attacker (Eating)
		actor.heal(hp_increase);
		return result;
	}

	/**
	 * The menu for Performing this action
	 * @param actor The actor performing the action.
	 * @return a descriptive String of this action
	 */
	@Override
	public String menuDescription(Actor actor) {
		Weapon weapon = actor.getWeapon();

		return actor + " " +weapon.verb() + " "+ target;
	}
}
