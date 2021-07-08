package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinoparkground.Lake;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.FlyingDinosaur;

/**
 * Action to represent Flying Dinosaur catching fish from lake
 * @see Action
 * @see FlyingDinosaur
 * @see Dinosaur
 * @see Actor
 * @see GameMap
 */
public class CatchFishAction extends Action {

    /**
     * Stores how many fish is taken from the catch
     */
    private int catch_num;

    /**
     * Stores how much water level should be increased
     */
    private int water_increase;

    /**
     * Constructor
     * @param catch_num how many fish is taken from the catch
     * @param water_increase how much water level should be increased
     */
    public CatchFishAction(int catch_num, int water_increase) {
        this.catch_num = catch_num;
        this.water_increase = water_increase;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Lake lake = (Lake) map.locationOf(actor).getGround();

        int catched_num = lake.removeFish(catch_num);
        ((Dinosaur)actor).drink(water_increase);
        actor.heal(10 * catched_num);

        return actor + " caught " + catched_num + " Fish from Lake";
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " Catches fish from Lake";
    }
}
