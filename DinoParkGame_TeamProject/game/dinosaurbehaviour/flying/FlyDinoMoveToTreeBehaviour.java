package game.dinosaurbehaviour.flying;

import edu.monash.fit2099.engine.*;
import game.dinoparkground.Tree;
import game.dinosaurbehaviour.LocationMoveCompute;

/**
 * Behaviour that represents flying dinosaur moving to tree to fly again
 * @see LocationMoveCompute
 * @see Actor
 * @see GameMap
 * @see Ground
 */
public class FlyDinoMoveToTreeBehaviour extends LocationMoveCompute{

    /**
     * Returns appropriate action after checking the conditions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return MoveActorAction(), null if there is no route to reach trees.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location treelocation = getLocationNearbyTree(actor, map);
        Location actorlocation = map.locationOf(actor);

        if (treelocation != null){
            Location destination = getLocationOneStep(actor, actorlocation, treelocation);
            if (destination != null)
                return (new MoveActorAction(destination, "to Tree"));
            else{
                Location next_tree = getNextTreeLocation(actor, map);
                if (next_tree != null)
                    return (new MoveActorAction(next_tree, "to Tree"));
            }

        }
        return null;
    }

    /**
     * Gets the location of nearby tree.
     * @param actor the Actor acting
     * @param map the GameMap containing the actor
     * @return location of nearby tree
     */
    private Location getLocationNearbyTree(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);

        int tree_detectsquares = 8;

        NumberRange xs = getNearbyXSquares(actorlocation.x(), tree_detectsquares);
        NumberRange ys = getNearbyYSquares(actorlocation.y(), tree_detectsquares);

        NumberRange max_xs = map.getXRange();
        NumberRange max_ys = map.getYRange();

        for (int x : xs){
            for (int y : ys) {
                if (max_xs.contains(x) && max_ys.contains(y)) {
                    if (map.at(x,y).getGround() instanceof Tree && map.at(x,y).getActor() == null) {
                        return map.at(x, y);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Gets the location of a tree that is next to the actor
     * @param actor the Actor acting
     * @param map the GameMap containing the actor
     * @return next tree location
     */
    private Location getNextTreeLocation(Actor actor, GameMap map){
        Location actorlocation = map.locationOf(actor);

        for (Exit exit : actorlocation.getExits()){
            Ground ground = exit.getDestination().getGround();
            if (ground instanceof Tree && ground.canActorEnter(actor)){
                return exit.getDestination();
            }
        }
        return null;
    }
}
