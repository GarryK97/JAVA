package game.Item.immovableitem;

import edu.monash.fit2099.engine.Action;

/**
 * A class that represents a bridge
 */
public class Bridge extends ImmovableItem{

    /***
     * Constructor.
     */
    public Bridge() {
        super("Bridge", '^');
    }

    /**
     * Gets the price of this Bridge
     * @return -1
     */
    @Override
    public int getPrice() {
        return -1;
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

    /**
     * Adds action to cross the bridge.
     * @param action to cross bridge
     */
    public void addAction(Action action){
        this.allowableActions.add(action);
    }
}
