package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Item.DinoParkItem;
import game.Player;
import game.dinosaurs.Dinosaur;
import game.dinosaurs.Herbivore;

/**
 * The action for feeding dinosaurs
 * @see game.Item.DinoParkItem
 * @see game.Item.portableitem
 * @see game.dinosaurs.Herbivore
 */
public class FeedAction extends Action {

    /**
     * The dinosaur to feed
     */
    private Actor target;

    /**
     * The food to give
     */
    private DinoParkItem food;

    /**
     * Constructor
     * @param target The dinosaur to feed
     * @param food The food to give
     */
    public FeedAction(Dinosaur target, DinoParkItem food) {
        this.target = target;
        this.food = food;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (food.isMealKit()){
            target.heal(999);
            actor.removeItemFromInventory((Item) food);
            return target + " ate meal kit.";
        }
        else if (food.isHerbivoreFood()){
            target.heal(((Herbivore)target).getFruit_Hp_Increase());
            actor.removeItemFromInventory((Item) food);
            Player.add_points(10);
            return target + " ate fruit.";
        }
        return null;
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + food + " to " + target;
    }
}
