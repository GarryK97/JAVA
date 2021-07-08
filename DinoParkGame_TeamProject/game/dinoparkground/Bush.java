package game.dinoparkground;

import edu.monash.fit2099.engine.Location;

/**
 * Bush of Ground
 * @see game.dinoparkground.Plant
 * @see game.dinoparkground.PlantProperty
 * @see game.dinoparkground.DinoParkGround
 */
public class Bush extends Plant{

    /**
     * Constructor with probability of producing a fruit
     */
    public Bush() {
        super('*', 10);
    }

    /**
     * A method to deal with Bush when a turn passes.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        produceFruit();

        age++;
    }

    /**
     * Checks whether a plant can grow at Bush
     * @return false
     */
    @Override
    public Boolean isGrowable() {
        return false;
    }

    /**
     * Checks whether a Stegosaur can eat Bush
     * @return true
     */
    @Override
    public Boolean canStegoEat() {
        return true;
    }

    /**
     * Checks whether a Brachiosaur can eat Bush
     * @return false
     */
    @Override
    public Boolean canBrachioEat() {
        return false;
    }

    /**
     * Checks whether Bush can drop a fruit or not
     * @return false
     */
    @Override
    public Boolean canDropFruit() {
        return false;
    }

    /**
     * Checks whether Bush can be destroyed or not
     * @return true
     */
    @Override
    public Boolean isDestructible() {
        return true;
    }
}
