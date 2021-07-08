package game.Item.portableitem;

/**
 * Class that represents a Vegeterian Meal Kit
 * @see PortableItem
 */
public class VegMealKit extends PortableItem{

    /**
     * Constructor
     */
    public VegMealKit() {
        super("Vegetarian Meal Kit", ';');
    }

    /**
     * Gets the price of this Meal Kit
     * @return 100
     */
    @Override
    public int getPrice() {
        return 100;
    }

    /**
     * Checks whether this Meal Kit is Herbivore Food or not
     * @return true
     */
    @Override
    public Boolean isHerbivoreFood() {
        return true;
    }

    /**
     * Checks whether this Meal Kit is Carnivore Food or not
     * @return false
     */
    @Override
    public Boolean isCarnivoreFood() {
        return false;
    }

    /**
     * Checks whether this Meal Kit is Meal kit or not
     * @return true
     */
    @Override
    public Boolean isMealKit() {
        return true;
    }
}
