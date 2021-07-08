package game.Item.portableitem;

import edu.monash.fit2099.engine.Location;

/**
 * Class that represents a Fruit
 * @see PortableItem
 */
public class Fruit extends PortableItem {

    /**
     * The left turn until the fruit is rot
     */
    int rot_TurnLeft = 15;

    /**
     * Constructor
     */
    public Fruit() {
        super("Fruit", 'f');
    }

    /**
     * A method to deal with Fruit when a turn passes when the Fruit is on the ground.
     * @param location The location of this Fruit
     */
    @Override
    public void tick(Location location){
        // If fruit is rotten, remove it
        if (rot_TurnLeft <= 0){
            location.removeItem(this);
        }
        rot_TurnLeft--;
    }

    /**
     * Gets the price of Fruit
     * @return 30
     */
    @Override
    public int getPrice() {
        return 30;
    }

    /**
     * Checks whether Fruit is Herbivore Food or not
     * @return true
     */
    @Override
    public Boolean isHerbivoreFood() {
        return true;
    }

    /**
     * Checks whether Fruit is Carnivore Food or not
     * @return false
     */
    @Override
    public Boolean isCarnivoreFood() {
        return false;
    }

    /**
     * Checks whether Fruit is Meal kit or not
     * @return false
     */
    @Override
    public Boolean isMealKit() {
        return false;
    }

}
