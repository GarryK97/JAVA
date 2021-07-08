package game.Item;

/**
 * Interface class to set Property of Items
 */
public interface DinoParkItem {

    /**
     * Gets the price of this Item
     * @return the price
     */
    public int getPrice();

    /**
     * Checks whether this Item is Herbivore Food or not
     * @return true if item is herbivore food, else false
     */
    public Boolean isHerbivoreFood();

    /**
     * Checks whether this Item is Carnivore Food or not
     * @return true if item is carnivore food, else false
     */
    public Boolean isCarnivoreFood();

    /**
     * Checks whether this Item is Meal kit or not
     * @return true if item is Meal kit, else false
     */
    public Boolean isMealKit();

}
