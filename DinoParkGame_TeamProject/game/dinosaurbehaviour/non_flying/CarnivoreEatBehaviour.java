package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Item.CarnivoreFood;
import game.Item.DinoParkItem;
import game.action.StayEatAction;
import game.dinosaurbehaviour.Behaviour;

/**
 * Behaviour that represents Carnivore eating nearby food.
 * @see game.dinosaurbehaviour.Behaviour
 * @see DinoParkItem
 * @see StayEatAction
 */
public class CarnivoreEatBehaviour implements Behaviour {

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return StayEatAction(). return null if there is no nearby food.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            for (Item item : destination.getItems()){
                if (((DinoParkItem)item).isCarnivoreFood()){
                    destination.removeItem(item); // Remove the Item to represent eating
                    return new StayEatAction(((CarnivoreFood)item).getFoodIncrease());
                }
            }
        }
        return null;
    }
}
