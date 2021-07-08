package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Item.portableitem.Fruit;
import game.Player;
import game.dinoparkground.Plant;

import java.util.Random;

/**
 * The action for picking fruits from plants
 * @see game.dinoparkground.Plant
 */
public class PickFruitAction extends Action {

    /**
     * The direction of the plant
     */
    private String direction;

    /**
     * The location of the plant
     */
    private Location groundLocation;

    /**
     * Random variable Generator
     */
    private Random rand = new Random();

    /**
     * Constructor
     * @param direction The direction of the plant
     * @param groundLocation The location of the plant
     */
    public PickFruitAction(String direction, Location groundLocation) {
        this.direction = direction;
        this.groundLocation = groundLocation;
    }

    /**
     * Performs this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the descriptive String of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Plant plant = (Plant) groundLocation.getGround();
        if (plant.getFruit() > 0) {
            if (rand.nextInt(100) < plant.getPickProb()) {
                actor.addItemToInventory(new Fruit());
                plant.takeFruit(1);
                Player.add_points(10);
                return actor + " Found a fruit!";
            }
        }
        return "Sadly, there was no fruit.";
    }

    /**
     * The menu for Performing this action
     * @param actor The actor performing the action.
     * @return a descriptive String of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " searchs for fruit from " + direction;
    }
}
