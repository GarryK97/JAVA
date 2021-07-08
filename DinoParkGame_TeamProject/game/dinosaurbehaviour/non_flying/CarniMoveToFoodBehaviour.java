package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Item.DinoParkItem;
import game.dinosaurbehaviour.LocationMoveCompute;

/**
 * Behaviour that represents Carnivore moves to its food source
 * @see game.dinosaurbehaviour.LocationMoveCompute
 * @see MoveActorAction
 * @see DinoParkItem
 */
public class CarniMoveToFoodBehaviour extends LocationMoveCompute {

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(). return null if there is no nearby food or cannot go close to food
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location foodlocation = getLocationNearbyFood(actor, map);
        // return null if there is no nearby food
        if (foodlocation == null){
            return null;
        }

        // return null if actor cannot go close
        Location onesteplocation = getLocationOneStep(actor, map.locationOf(actor), foodlocation);
        if (onesteplocation == null) {
            return null;
        }

        return (new MoveActorAction(onesteplocation, "to food"));
    }

    /**
     * Gets the location of nearby carnivore food source.
     * @param actor the actor
     * @param map the GameMap of current game
     * @return nearby food location, return null if there is no nearby food
     */
    private Location getLocationNearbyFood(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        int food_detectsquares = 5;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), food_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), food_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs) {
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    for (Item item : map.at(x,y).getItems()){
                        if (((DinoParkItem)item).isCarnivoreFood()){
                            return map.at(x,y);
                        }
                    }
                }
            }
        }
        return null;
    }
}
