package game.dinoparkground;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents the floor inside a building.
 * @see game.dinoparkground.DinoParkGround
 */
public class Floor extends Ground implements DinoParkGround {

	public Floor() {
		super('_');
	}

	/**
	 * Checks whether a plant can grow at Floor
	 * @return false
	 */
	@Override
	public Boolean isGrowable() {
		return false;
	}

	/**
	 * Check whether Floor is a plant or not
	 * @return false
	 */
	@Override
	public Boolean isPlant() {
		return false;
	}

	/**
	 * Check whether Floor is water-based or not
	 * @return false
	 */
	@Override
	public Boolean isWater() {
		return false;
	}
}
