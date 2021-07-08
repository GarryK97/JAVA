package game.dinosaurbehaviour.non_flying;

import edu.monash.fit2099.engine.*;
import game.dinoparkground.DinoParkGround;
import game.dinosaurbehaviour.LocationMoveCompute;

/**
 * Behaviour that represent a dinosaur moving to the water
 * @see LocationMoveCompute
 * @see Actor
 * @see GameMap
 * @see DinoParkGround
 */
public class MoveToWaterBehaviour extends LocationMoveCompute{
    
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location waterlocation = getLocationNearbyWater(actor, map);
        Location actorlocation = map.locationOf(actor);

        if (waterlocation != null){
            Location destination = getLocationOneStep(actor, actorlocation, waterlocation);
            if (destination != null)
                return (new MoveActorAction(destination, "to Water"));
        }
        return null;
    }

    private Location getLocationNearbyWater(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);

        int water_detectsquares = 8;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), water_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), water_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs){
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    DinoParkGround ground = (DinoParkGround) map.at(x, y).getGround();
                    if (ground.isWater()) {
                        return map.at(x, y);
                    }
                }
            }
        }
        return null;
    }
}
