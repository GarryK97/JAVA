package game.dinoparkground;

/**
 * Interface class that identifies properties of the grounds in the game
 * @see game.dinoparkground
 */
public interface DinoParkGround {

    /**
     * Checks whether a plant can grow at the ground
     * @return true if a plant can grow, else false
     */
    public Boolean isGrowable();

    /**
     * Check whether this ground is a plant or not
     * @return true if this ground is plant, else false
     */
    public Boolean isPlant();

    /**
     * Check whether this ground should be deal with raining situation.
     * @return true if this ground is water-based, else false.
     */
    public Boolean isWater();
}
