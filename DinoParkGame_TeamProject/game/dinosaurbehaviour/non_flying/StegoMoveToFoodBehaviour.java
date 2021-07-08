package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.Item.DinoParkItem;
import game.dinoparkground.DinoParkGround;
import game.dinoparkground.Plant;
import game.dinosaurbehaviour.LocationMoveCompute;

/**
 * Behaviour for a Stegosaur to move to nearby food source
 * @see LocationMoveCompute
 * @see DinoParkGround
 * @see Plant
 */
public class StegoMoveToFoodBehaviour extends LocationMoveCompute{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(). return null if there is no nearby food or cannot go close to food
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location foodlocation = getLocationNearbyFood(actor, map);
        Location actorlocation = map.locationOf(actor);

        if (foodlocation != null){
            Location destination = getLocationOneStep(actor, actorlocation, foodlocation);
            if (destination != null)
                return (new MoveActorAction(destination, "to Food"));
        }
        return null;
    }

    private Location getLocationNearbyFood(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);
        
        int food_detectsquares = 4;
        
        NumberRange xs = getNearbyXSquares(actorlocation.x(), food_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), food_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs){
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    DinoParkGround ground = (DinoParkGround) map.at(x, y).getGround();
                    if (ground.isPlant() && ((Plant)ground).canStegoEat()) {
                        return map.at(x, y);
                    }
                    for (Item item : map.at(x, y).getItems()) {
                        if (((DinoParkItem) item).isHerbivoreFood()) {
                            return map.at(x, y);
                        }
                    }
                }
            }
        }
        return null;
    }
}
