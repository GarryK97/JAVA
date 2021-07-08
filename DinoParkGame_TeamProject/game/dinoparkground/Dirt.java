package game.dinoparkground;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 * @see game.dinoparkground.DinoParkGround
 */
public class Dirt extends Ground implements DinoParkGround {

	public Dirt() {
		super('.');
	}

	/**
	 * Checks whether a plant can grow at Dirt
	 * @return true
	 */
	@Override
	public Boolean isGrowable() {
		return true;
	}

	/**
	 * Check whether Dirt is a plant or not
	 * @return false
	 */
	@Override
	public Boolean isPlant() {
		return false;
	}

	/**
	 * Check whether Dirt is water-based or not
	 * @return false
	 */
	@Override
	public Boolean isWater() {
		return false;
	}

}
