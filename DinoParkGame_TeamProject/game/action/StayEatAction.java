package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The action for eating food
 */
public class StayEatAction extends Action {

    /**
     * The amount of foods
     */
    private int num_food = 1;

    /**
     * HP increase amount per food
     */
    private int hp_per_food = 0;

    /**
     * Constructor with number of foods
     * @param num_food The amount of foods
     * @param hp_per_food HP increase amount per food
     */
    public StayEatAction(int num_food, int hp_per_food) {
        this.num_food = num_food;
        this.hp_per_food = hp_per_food;
    }

    /**
     * Constructor for eating only one food
     * @param hp_per_food HP increase amount per food
     */
    public StayEatAction(int hp_per_food){
        this.hp_per_food = hp_per_food;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(num_food * hp_per_food);
        return (menuDescription(actor));
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats food from nearby food source.";
    }
}
