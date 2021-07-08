package game.Item.portableitem;

import game.Item.CarnivoreFood;

/**
 * Class that represents a Carnivore Meal Kit
 * @see PortableItem
 * @see CarnivoreFood
 */
public class CarniMealKit extends PortableItem implements CarnivoreFood {

    /**
     * Constructor
     */
    public CarniMealKit() {
        super("Carnivore Meal Kit", '_');
    }

    /**
     * Gets the price of this Meal Kit
     * @return 500
     */
    @Override
    public int getPrice() {
        return 500;
    }

    /**
     * Checks whether this Meal Kit is Herbivore Food or not
     * @return false
     */
    @Override
    public Boolean isHerbivoreFood() {
        return false;
    }

    /**
     * Checks whether this Meal Kit is Carnivore Food or not
     * @return true
     */
    @Override
    public Boolean isCarnivoreFood() {
        return true;
    }

    /**
     * Checks whether this Meal Kit is Meal kit or not
     * @return true
     */
    @Override
    public Boolean isMealKit() {
        return true;
    }

    /**
     * Gets the Food level increase amount of this Meal Kit
     * @return 999 (= Big enough to make all dinosaurs full)
     */
    @Override
    public int getFoodIncrease() {
        return 999;
    }
}
