package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Item.DinoParkItem;
import game.action.AttackAction;
import game.action.FeedAction;

/**
 * class that represents a Herbivore dinosaur
 * @see Dinosaur
 * @see DinoParkItem
 * @see FeedAction
 */
public abstract class Herbivore extends Dinosaur{

    /**
     * The HP increase amount when the dinosaur eats a fruit
     */
    protected final int FRUIT_HP_INCREASE = 10;

    /**
     * Constructor
     * @param name Name(Type) of the Dinosaur
     * @param displayChar The Character that is displayed in the game
     * @param hitPoints The Maximum HP (Food level)
     * @param start_hp Starting HP (Food level)
     * @param hungry_level The Food level which the Dinosaur becomes hungry
     * @param unconscious_turn Turn to die when Dinosaur becomes unconscious
     * @param adult_age The Adult age of the Dinosaur
     * @param gender Gender of the Dinosaur
     */
    public Herbivore(String name, char displayChar, int hitPoints, int start_hp, int hungry_level, int unconscious_turn, int adult_age, Gender gender) {
        super(name, displayChar, hitPoints, start_hp, hungry_level, unconscious_turn, adult_age, gender);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Dinosaur.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the Actions object which is the collection of actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        actions.clear();
        actions.add(new AttackAction(this, otherActor.getWeapon().damage()));

        // If the other actor has herbivore foods, adds feed action of them.
        for (Item item : otherActor.getInventory()){
            if (((DinoParkItem)item).isHerbivoreFood()){
                actions.add(new FeedAction(this, (DinoParkItem)item));
            }
        }
        return actions;
    }

    /**
     * Getter for FRUIT_HP_INCREASE
     * @return The HP increase amount when the dinosaur eats a fruit
     */
    public int getFruit_Hp_Increase(){
        return FRUIT_HP_INCREASE;
    }

    /**
     * Checks whether the Dinosaur is a Carnivore or not
     * @return false
     */
    @Override
    public Boolean isCarnivore() {
        return false;
    }
}
