package game.dinoparkground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents a Wall
 * @see game.dinoparkground.DinoParkGround
 */
public class Wall extends Ground implements DinoParkGround {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Checks whether an actor can enter and pass through Wall
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Check whether Wall can block thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Checks whether a plant can grow at Wall
	 * @return false
	 */
	@Override
	public Boolean isGrowable() {
		return false;
	}

	/**
	 * Check whether Wall is a plant or not
	 * @return false
	 */
	@Override
	public Boolean isPlant() {
		return false;
	}

	/**
	 * Check whether Wall is water-based or not
	 * @return false
	 */
	@Override
	public Boolean isWater() {
		return false;
	}
}
