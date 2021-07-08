package game.Item.immovableitem;

import game.Item.portableitem.*;
import game.action.BuyAction;

/**
 * Class that represents a Vending Machine selling Items
 * @see ImmovableItem
 * @see BuyAction
 */
public class VendingMachine extends ImmovableItem{
    /***
     * Constructor.
     */
    public VendingMachine() {
        super("Vending Machine", '$');

        // Adds Selling Products of this Vending Machine
        this.allowableActions.add(new BuyAction(new Fruit()));
        this.allowableActions.add(new BuyAction(new VegMealKit()));
        this.allowableActions.add(new BuyAction(new CarniMealKit()));
        this.allowableActions.add(new BuyAction(new EggStego()));
        this.allowableActions.add(new BuyAction(new EggBrachio()));
        this.allowableActions.add(new BuyAction(new EggAllosaur()));
        this.allowableActions.add(new BuyAction(new EggPtero()));
        this.allowableActions.add(new BuyAction(new LaserGun()));
    }

    /**
     * Gets the price of this Vending machine
     * @return -1
     */
    @Override
    public int getPrice() {
        return -1; // = This Item has no price set
    }

    /**
     * Checks whether this Item is Herbivore Food or not
     * @return false
     */
    @Override
    public Boolean isHerbivoreFood() {
        return false;
    }

    /**
     * Checks whether this Item is Carnivore Food or not
     * @return false
     */
    @Override
    public Boolean isCarnivoreFood() {
        return false;
    }

    /**
     * Checks whether this Item is Meal kit or not
     * @return false
     */
    @Override
    public Boolean isMealKit() {
        return false;
    }
}
