package game.Item.portableitem;

import edu.monash.fit2099.engine.WeaponItem;
import game.Item.DinoParkItem;

/**
 * Class that represents a Laser Gun
 * @see WeaponItem
 * @see DinoParkItem
 */
public class LaserGun extends WeaponItem implements DinoParkItem {

    /**
     * Constructor
     */
    public LaserGun() {
        super("Laser Gun",'/', 60, "shoots");
    }

    /**
     * Gets the price of LaserGun
     * @return 500
     */
    @Override
    public int getPrice() {
        return 500;
    }

    /**
     * Checks whether LaserGun is Meal kit or not
     * @return false
     */
    @Override
    public Boolean isMealKit() {
        return false;
    }

    /**
     * Checks whether LaserGun is Herbivore Food or not
     * @return false
     */
    @Override
    public Boolean isHerbivoreFood() {
        return false;
    }

    /**
     * Checks whether LaserGun is Carnivore Food or not
     * @return false
     */
    @Override
    public Boolean isCarnivoreFood() {
        return false;
    }
}
