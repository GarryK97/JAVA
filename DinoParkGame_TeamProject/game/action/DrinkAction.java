package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Dinosaur;

/**
 * Action class to represent a dinosaur drinks water
 * @see Action
 * @see Dinosaur
 */
public class DrinkAction extends Action {

    /**
     * The increase of water level when drink
     */
    private int water_amount;

    /**
     * Constructor
     * @param water_amount The increase of water level
     */
    public DrinkAction(int water_amount) {
        this.water_amount = water_amount;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ((Dinosaur)actor).drink(water_amount);
        return actor + " drank " + water_amount + "L of water.";
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks water.";
    }
}
