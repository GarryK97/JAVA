package game.Item.immovableitem;

import edu.monash.fit2099.engine.Location;
import game.Item.CarnivoreFood;

/**
 * Abstract class that is used to create Corpse objects for Dinosaurs
 * @see CarnivoreFood
 * @see ImmovableItem
 */
public abstract class Corpse extends ImmovableItem implements CarnivoreFood {

    /**
     * The Turn Left until this corpse disappear
     */
    private int turn_left = 0;

    /**
     * The food level increase when a dinosaur eat this corpse
     */
    protected int food_level = 50;

    /**
     * The food level left in this corpse
     */
    protected int food_left = food_level;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param remove_turn the turn which corpse should be removed from the map
     */
    public Corpse(String name, char displayChar, int remove_turn) {
        super(name, displayChar);
        this.turn_left = remove_turn;
    }

    /**
     * Gets the price of this Item
     * @return -1
     */
    @Override
    public int getPrice(){
        return -1; // = This item has no price set
    }

    /**
     * Checks whether Corpse is Herbivore Food or not
     * @return false
     */
    @Override
    public Boolean isHerbivoreFood() {
        return false;
    }

    /**
     * Checks whether Corpse is Carnivore Food or not
     * @return true
     */
    @Override
    public Boolean isCarnivoreFood() {
        return true;
    }

    /**
     * Gets the Food level increase amount of this Corpse
     * @return food_level
     */
    @Override
    public int getFoodIncrease(){
        return food_level;
    }

    /**
     * Checks whether Corpse is Meal kit or not
     * @return false
     */
    @Override
    public Boolean isMealKit(){
        return false;
    }

    /**
     * A method to deal with Corpse when a turn passes.
     * @param currentLocation The location of this Corpse
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        // if turn_left <= 0, remove the Corpse
        if (turn_left <= 0 || food_left <= 0) {
            currentLocation.removeItem(this);
        }
        // else, decrement turn_left
        turn_left--;
    }

    /**
     * Getter for food_left
     * @return food_left
     */
    public int getFood_Left() {
        return food_left;
    }

    /**
     * Decrease food_left when a dinosaur eat a corpse slowly.
     * @param amount the partial amount eating
     * @return the eaten amount
     */
    public int eatPartial(int amount){
        if (this.food_left - amount >= 0){
            this.food_left -= amount;
            return amount;
        }
        else{
            int max_eat_amount = this.food_left;
            this.food_left = 0;
            return max_eat_amount;
        }
    }
}
