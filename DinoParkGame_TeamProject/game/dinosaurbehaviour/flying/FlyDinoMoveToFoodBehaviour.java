package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Corpse;
import game.dinosaurbehaviour.LocationMoveCompute;
import game.dinosaurs.FlyingDinosaur;

/**
 * Behaviour that represents flying dinosaur moving to food source
 * @see LocationMoveCompute
 * @see FlyingDinosaur
 * @see Actor
 * @see GameMap
 */
public class FlyDinoMoveToFoodBehaviour extends LocationMoveCompute{

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

        return (new MoveActorAction(onesteplocation, "to corpse"));
    }

    private Location getLocationNearbyFood(Actor actor, GameMap map) {
        Location actorlocation = map.locationOf(actor);

        int food_detectsquares = 7;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), food_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), food_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs) {
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    for (Item item : map.at(x,y).getItems()){
                        if (item instanceof Corpse){
                            return map.at(x,y);
                        }
                    }
                }
            }
        }
        return null;
    }
}
